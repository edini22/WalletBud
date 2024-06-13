package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.User;
import wb.walletbud.UserDAO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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

}
