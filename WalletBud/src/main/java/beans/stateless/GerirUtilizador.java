package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.time.LocalDate;

import jakarta.json.JsonObjectBuilder;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;

import wb.walletbud.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class GerirUtilizador {

    @EJB
    private GerirCategoria gerirCategoria;

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean createUser(String name, String password, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {

            User u = getUserByEmail(email);

            if (u != null) {
                t.rollback();
                return false;
            }

            User user = UserDAO.createUser();
            user.setName(name);
            user.setEmail(email);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = bytesToHex(encodedhash);

            user.setPassword(hashedPassword);

            UserDAO.save(user);

            if(gerirCategoria.createDefaultCategorias(user) != 0){
                t.rollback();
                return false;
            }

            t.commit();
            System.out.println("User inserido!");
            return true;
        } catch (Exception e) {
            t.rollback();
            System.out.println("User não inserido!");
            return false;
        }
    }

    public boolean editUser(String name, String password, String email, String idioma,String new_email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {

            User u = getUserByEmail(email);

            if (u == null) {
                System.out.println("email do token errado!");
                t.rollback();
                return false;
            }
            if (name != null) u.setName(name);
            if (password != null) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest(
                        password.getBytes(StandardCharsets.UTF_8));
                String hashedPassword = bytesToHex(encodedhash);

                u.setPassword(hashedPassword);
            }
            if (idioma != null) u.setIdioma(idioma);
            if (new_email != null) {
                //verifica se existe algum ‘user’ com o endereço eletrónico
                User us = getUserByEmail(new_email);
                if (us != null) {
                    return false;
                }
                u.setEmail(new_email);
            }


            UserDAO.save(u);

            t.commit();
            System.out.println("User editado!");
            return true;
        } catch (Exception e) {
            t.rollback();
            System.out.println("User não editado!");
            return false;
        }
    }

    public int verifyUser(String email, String password) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return -3;
            }

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = bytesToHex(encodedhash);

            String condition = "email = '" + email + "' AND password = '" + hashedPassword + "'";

            User[] users = UserDAO.listUserByQuery(condition, null);

            if (users.length == 0) {
                t.rollback();
                return -1;
            } else {
                t.commit();
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }

    }

    public User getUserByEmail(String email) throws PersistentException {
        try {
            String condition = "email = '" + email + "'";
            User[] users = UserDAO.listUserByQuery(condition, null);

            if (users.length == 0) {
                return null;
            }
            return users[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject getJsonUserInfo(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            JsonObject userJson = Json.createObjectBuilder()
                    .add("id", user.getId_user())
                    .add("name", user.getName())
                    .add("email", user.getEmail())
                    .add("balanco", user.getSaldo())
                    .add("idioma", user.getIdioma())
                    .build();
            t.commit();
            return userJson;
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }
    }


    public static Map<User, List<Transacao>> getAllTransacoesFromUsers(){
        Map<User, List<Transacao>> transacoes = new HashMap<>();

        try {
            User[] users = UserDAO.listUserByQuery(null, null);
            LocalDate now = LocalDate.now();

            for (User u : users) {
                List<Transacao> userTransacoes = new ArrayList<>();

                for (Transacao t : u.transacaos.toArray()) {
                    Timestamp transactionTimestamp = t.getDate();

                    // Convert Timestamp to LocalDate
                    LocalDate transactionDate = transactionTimestamp.toLocalDateTime().toLocalDate();

                    // Check if now is exactly three days before transaction date
                    if (now.plusDays(3).equals(transactionDate)) {
                        userTransacoes.add(t);
                    }
                }

                transacoes.put(u, userTransacoes);
            }

            return transacoes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject getJsonUserNotifs(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            JsonObjectBuilder notifJsonObjectBuilder = Json.createObjectBuilder();

            List<Notificacao> notifs = user.getNotifications();

            for (Notificacao n : notifs) {
                notifJsonObjectBuilder.add("notificacoes", Json.createObjectBuilder()
                        .add("id", n.getId_notificacao())
                        .add("date", n.getDate().toString())
                        .add("descricao", n.getDescrição())
                        .build());
            }
            t.commit();
            return notifJsonObjectBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();

            return Json.createObjectBuilder()
                    .build();
        }
    }


}
