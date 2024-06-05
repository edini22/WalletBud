package WB.walletbud;

import org.orm.PersistentException;
import org.orm.PersistentTransaction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SystemInterface {

    public SystemInterface(){

    }

    public static void main(String[] args) {
        SystemInterface si = new SystemInterface();

        try {
            si.createUser("Paco Nassa", "1234", "pn@gmail.com");
        } catch (PersistentException e) {
            System.out.println("ERROOOOOOOOO");
        }
        System.exit(0);
    }

    public void createUser(String name, String password, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {

            User user = UserDAO.createUser();
            user.setName(name);
            user.setEmail(email);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));

            user.setPassword(encodedhash.toString());

            UserDAO.save(user);

            t.commit();
            System.out.println("User inserido!");
        } catch (Exception e) {
            t.rollback();
            System.out.println("User não inserido!");
        }
    }

}
