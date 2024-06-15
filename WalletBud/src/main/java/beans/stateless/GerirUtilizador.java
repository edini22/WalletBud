package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.orm.PersistentException;
import org.orm.PersistentSession;
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

    public boolean createUser(PersistentSession session, String name, String password, String email) throws PersistentException {
        try {

            User u = getUserByEmail(session, email);

            if (u != null) {
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
                return false;
            }

            System.out.println("User inserido!");
            return true;
        } catch (Exception e) {
            System.out.println("User não inserido!");
            return false;
        }
    }

    public boolean editUser(PersistentSession session, String name, String password, String email, String idioma,String new_email) throws PersistentException {
        try {

            User u = getUserByEmail(session, email);

            if (u == null) {
                System.out.println("email do token errado!");
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
                User us = getUserByEmail(session, new_email);
                if (us != null) {
                    return false;
                }
                u.setEmail(new_email);
            }


            UserDAO.save(u);

            System.out.println("User editado!");
            return true;
        } catch (Exception e) {
            System.out.println("User não editado!");
            return false;
        }
    }

    public int verifyUser(PersistentSession session, String email, String password) throws PersistentException {
        try {
            User user = getUserByEmail(session, email);

            if (user == null) {
                return -3;
            }

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = bytesToHex(encodedhash);

            String condition = "email = '" + email + "' AND password = '" + hashedPassword + "'";

            User[] users = UserDAO.listUserByQuery(session, condition, null);

            if (users.length == 0) {
                return -1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }

    }

    public User getUserByEmail(PersistentSession session,String email) throws PersistentException {
        try {
            String condition = "email = '" + email + "'";
            User[] users = UserDAO.listUserByQuery(session, condition, null);

            if (users.length == 0) {
                return null;
            }
            return users[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject getJsonUserInfo(PersistentSession session, String email) throws PersistentException {
        try {
            User user = getUserByEmail(session, email);

            if (user == null) {
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
            System.out.println(userJson.toString());

            return userJson;
        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }
    }

}
