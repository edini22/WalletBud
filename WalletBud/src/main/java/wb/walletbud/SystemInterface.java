package wb.walletbud;


import jakarta.json.*;

import org.orm.PersistentException;
import org.orm.PersistentTransaction;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SystemInterface {

    public SystemInterface(){

    }

    public static void main(String[] args) {
        //SystemInterface si = new SystemInterface();

        try {
//            createUser("Rois", "1234", "r@gmail.com");
            verifyUser("r@gmail.com", "1234");
        } catch (PersistentException e) {
            System.out.println("ERROOOOOOOOO");
        }
        System.exit(0);
    }

    private static String bytesToHex(byte[] hash) {
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

    public static boolean createUser(String name, String password, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {

            User u = getUserByEmail(email);

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

            if(createDefaultCategorias(user) != 0){
                t.rollback();
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

    public static int createDefaultCategorias(User user) throws PersistentException {
        try {
            HashMap<String, ArrayList<String>> categorias = new HashMap<>();
            ArrayList<String> nomes = new ArrayList<>();
            nomes.add("Pessoal");
            nomes.add("Familiar");
            nomes.add("Alimentação");
            nomes.add("Renda");
            nomes.add("Entretenimento");
            categorias.put("despesa", nomes);
            ArrayList<String> nomes2 = new ArrayList<>();
            nomes2.add("Renda");
            nomes2.add("Salário");
            categorias.put("receita", nomes2);

            for (Map.Entry<String, ArrayList<String>> entry : categorias.entrySet()) {
                String tipo = entry.getKey();
                ArrayList<String> names = entry.getValue();
                for(String nome : names){
                    Categoria categoria = CategoriaDAO.createCategoria();
                    categoria.setName(nome);
                    categoria.setTipo(tipo);
                    categoria.setUserId_user(user);

                    CategoriaDAO.save(categoria);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
        return 0;
    }

    public static int verifyUser(String email, String password) throws PersistentException {

        try {
            User user = getUserByEmail(email);

            if (user == null) {
                return -3;
            }

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = bytesToHex(encodedhash);

            String condition = "email = '" + email + "' AND password = '" + hashedPassword + "'";

            User[] users = UserDAO.listUserByQuery(condition, null);

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

    public static int createCategoria(String nome, String tipo, String email) throws PersistentException {

        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);

            if(user == null) return -3;

            String condition = "tipo = '" + tipo + "' AND name = '" + nome + "' AND Userid_user = '" + user.getId_user() + "'";
            Categoria[] categorias = CategoriaDAO.listCategoriaByQuery(condition, null);

            if (categorias.length != 0) {
                t.rollback();
                return -1;
            }

            Categoria categoria = CategoriaDAO.createCategoria();
            categoria.setName(nome);
            categoria.setTipo(tipo);
            categoria.setUserId_user(user);

            CategoriaDAO.save(categoria);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }

        return 0;
    }

    public static User getUserByEmail(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
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

    public static JsonObject getJsonCategoriaById(int id, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "id_categoria = " + id + " AND Userid_user = " + user.getId_user();
            Categoria[] categorias = CategoriaDAO.listCategoriaByQuery(condition, null);

            if (categorias.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Categoria categoria = categorias[0];
            JsonObject categoriaJson = Json.createObjectBuilder()
                    .add("id", categoria.getId_categoria())
                    .add("name", categoria.getName())
                    .add("tipo", categoria.getTipo())
                    .build();


            return categoriaJson;

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public static Categoria getCategoriaById(int id, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);
            if (user == null) {
                return null;
            }

            String condition = "id_categoria = " + id + " AND Userid_user = " + user.getId_user();
            Categoria[] categorias = CategoriaDAO.listCategoriaByQuery(condition, null);

            if (categorias.length == 0) {
                return null;
            }

            return categorias[0];

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static JsonObject getCategorias(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();

        try{
            User user = getUserByEmail(email);
            if(user == null){
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "'";

            Categoria[] categorias = CategoriaDAO.listCategoriaByQuery(condition,null);

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Categoria categoria : categorias) {
                JsonObject categoriaJson = Json.createObjectBuilder()
                        .add("id", categoria.getId_categoria())
                        .add("name", categoria.getName())
                        .add("tipo", categoria.getTipo())
                        .build();
                arrayBuilder.add(categoriaJson);
            }

            return Json.createObjectBuilder()
                    .add("categorias", arrayBuilder.build())
                    .build();

        } catch (Exception e) {
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public static int editCategoria(int id, String nome, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();

        try {
            User user = getUserByEmail(email);

            if(user == null) return -3;

            String condition = "id_categoria = '" + id + "' AND Userid_user = '" + user.getId_user() + "'";
            Categoria[] categorias = CategoriaDAO.listCategoriaByQuery(condition, null);

            if (categorias.length == 0) {
                t.rollback();
                return -3;
            }

            Categoria categoria = categorias[0];

            condition = "tipo = '" + categoria.getTipo() + "' AND name = '" + nome + "' AND Userid_user = '" + user.getId_user() + "'";
            Categoria[] cat = CategoriaDAO.listCategoriaByQuery(condition, null);

            if (cat.length != 0) {
                t.rollback();
                return -1;
            }

            categoria.setName(nome);
            CategoriaDAO.save(categoria);

            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }

        return 0;
    }

    public static int createUnica(String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time,String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);
            if(user == null) return -3;

            Categoria cat = getCategoriaById(categoria, email);
            if(cat == null) return -4;

            Unica unica = UnicaDAO.createUnica();
            unica.setName(name);
            unica.setValue(value);
            unica.setShareValue(value);
            unica.setDescrição(descricao);
            unica.setLocal(local);
            unica.setTipo(tipo);
            unica.setCategoriaId_categoria(cat);
            unica.setDate(time);
            unica.setOwner_id(user);
            UnicaDAO.save(unica);

            float saldo = user.getSaldo();
            if(tipo.equals("receita")){
                saldo += value;
            }else {
                saldo -= value;
            }
            user.setSaldo(saldo);
            UserDAO.save(user);


            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }

        return 0;
    }

    public static int editUnica(int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time,String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);
            if(user == null) return -3;
            Categoria cat = null;
            if(categoria != -1) {
                cat = getCategoriaById(categoria, email);
                if(cat == null) return -4;
            }

            String condition = "Id_transacao = '" + id + "' AND UserId_user = '" + user.getId_user() + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null);

            if (unicas.length == 0) {
                t.rollback();
                return -1;
            }

            Unica unica = unicas[0];

            if(!unica.getTipo().equals(tipo)){
                t.rollback();
                return -1;
            }

            if (name != null) unica.setName(name);
            if (value != -1){
                float aSvalue = unica.getShareValue();
                float nSvalue = (value * aSvalue) / unica.getValue();
                float diff = nSvalue - aSvalue ;

                if(unica.getTipo().equals("receita")){
                    user.setSaldo(user.getSaldo() + diff);
                } else {
                    user.setSaldo(user.getSaldo() - diff);
                }
                UserDAO.save(user);

                //iterar pelos user todos partilhados a alterar
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                for(TransacaoPartilhada transacao : tp){
                    User u = transacao.getUserId_user();
                    if(unica.getTipo().equals("receita")){
                        u.setSaldo(u.getSaldo() + diff);
                    } else {
                        u.setSaldo(u.getSaldo() - diff);
                    }
                    UserDAO.save(u);
                }
//                if(tp.length != 0) {
//                    float newSvalue = nSvalue / (tp.length + 1);
//                    unica.setShareValue(newSvalue);
//                } else {
                unica.setShareValue(nSvalue);
//                }

                unica.setValue(value);
            }
            if (descricao != null) unica.setDescrição(descricao);
            if (local != null) unica.setLocal(local);
            if (categoria != -1) unica.setCategoriaId_categoria(cat);
            if (time != null) unica.setDate(time);

            UnicaDAO.save(unica);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }
        return 0;
    }

    public static JsonObject getUnicas(String email, String tipo) throws PersistentException {
        try {
            User user = getUserByEmail(email);
            if(user == null){
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition,null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);
            ArrayList<Unica> transacoes_id = new ArrayList<>(Arrays.asList(unicas));

            for(TransacaoPartilhada transacaoPartilhada : transacoes_partilhada){
                try{
                    Unica u = UnicaDAO.getUnicaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if(u != null && u.getTipo().equals(tipo)){
                        transacoes_id.add(u);
                    }
                } catch (Exception e){
                    //nao era unica
                }
            }


            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Unica unica : transacoes_id) {
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                User Owner = unica.getOwner_id();
                JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
                JsonObject userJs = Json.createObjectBuilder()
                        .add("id", Owner.getId_user())
                        .add("name", Owner.getName())
                        .add("email", Owner.getEmail())
                        .build();
                userArrayBuilder.add(userJs);
                for (TransacaoPartilhada tpPartilhada : tp) {
                    User u = tpPartilhada.getUserId_user();
                    JsonObject userJson = Json.createObjectBuilder()
                            .add("id", u.getId_user())
                            .add("name", u.getName())
                            .add("email", u.getEmail())
                            .build();
                    userArrayBuilder.add(userJson);
                }
                JsonArray userArray = userArrayBuilder.build();

                JsonObject unicaJson = Json.createObjectBuilder()
                        .add("id", unica.getId_transacao())
                        .add("name", unica.getName())
                        .add("value", unica.getValue())
                        .add("shareValue", unica.getShareValue())
                        .add("date", unica.getDate().toString())
                        .add("descricao", unica.getDescrição())
                        .add("tipo", unica.getTipo())
                        .add("local", unica.getLocal())
                        .add("users", userArray)
                        .build();
                arrayBuilder.add(unicaJson);
            }

            return Json.createObjectBuilder()
                    .add(tipo + "s", arrayBuilder.build())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public static JsonObject getJsonUnicaById(int id, String email, String tipo) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "Id_transacao = " + id + " AND Tipo = '" + tipo + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null); // ate aqui qualuer um pode requisitar esta transacao


            if (unicas.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Unica unica = unicas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);
            //verificar se o owner o se com quem esta transacao esta partilhada corresponde ao user que esta a pedir, senao se verificar return vazio
            if(!(unica.getOwner_id() == user || checkTransationAcess(user, tp)) ){
                return Json.createObjectBuilder()
                        .build();
            }
            User Owner = unica.getOwner_id();
            //ver com quem esta partilhada a transacao
            // Construir o JsonArray para a lista de usuários
            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
            JsonObject userJs = Json.createObjectBuilder()
                    .add("id", Owner.getId_user())
                    .add("name", Owner.getName())
                    .add("email", Owner.getEmail())
                    .build();
            userArrayBuilder.add(userJs);
            for (TransacaoPartilhada tpPartilhada : tp) {
                User u = tpPartilhada.getUserId_user();
                JsonObject userJson = Json.createObjectBuilder()
                        .add("id", u.getId_user())
                        .add("name", u.getName())
                        .add("email", u.getEmail())
                        .build();
                userArrayBuilder.add(userJson);
            }
            JsonArray userArray = userArrayBuilder.build();


            JsonObject unicaJson = Json.createObjectBuilder()
                    .add("id", unica.getId_transacao())
                    .add("name", unica.getName())
                    .add("value", unica.getValue())
                    .add("shareValue", unica.getShareValue())
                    .add("date", unica.getDate().toString())
                    .add("descricao", unica.getDescrição())
                    .add("tipo", unica.getTipo())
                    .add("local", unica.getLocal())
                    .add("users", userArray)
                    .build();

            return unicaJson;

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    private static boolean checkTransationAcess( User user , TransacaoPartilhada[] tp ){
        //return se corresponder a algum deles
        boolean check = false;
        for(TransacaoPartilhada tpPartilhada : tp){
            if( tpPartilhada.getUserId_user() == user){
                check = true;
                break;
            }
        }
        return check;

    }

    public static int shareUnica(int idUnica, JsonArray usersArray, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try{

            User user = getUserByEmail(email);
            if (user == null) {
                return -3;
            }
            Unica unica = UnicaDAO.getUnicaByORMID(idUnica);

            if(unica == null || unica.getOwner_id() != user){
                return -1;
            }

            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                User us = getUserByEmail(userEmail);
                if (us == null) {
                    continue;
                }

                //verificar se este user ja esta com acesso a transacao ou seja
                String condition = "TransacaoId_transacao = " + idUnica + " AND UserId_user = " + us.getId_user();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                if(tps.length == 0 && unica.getOwner_id() != us){
                    TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                    tp.setUserId_user(us);
                    tp.setUsertransacaoId(unica);
                    TransacaoPartilhadaDAO.save(tp);
                }
                else{
                    t.rollback();
                    return -2;
                }
            }

            //atualizar saldos
            float aSvalue = unica.getShareValue();
            int anUsers = (int) (unica.getValue() / unica.getShareValue());

            float nSvalue = unica.getValue() / (anUsers + usersArray.size());

            float diff = nSvalue - aSvalue;

            if(unica.getTipo().equals("receita")){
                user.setSaldo(user.getSaldo() + diff);
            } else {
                user.setSaldo(user.getSaldo() - diff);
            }
            UserDAO.save(user);

            String condition = "TransacaoId_transacao = " + unica.getId_transacao();
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

            for(TransacaoPartilhada transacao : tp){
                User u = transacao.getUserId_user();
                boolean exists = false;
                for (JsonValue userValue : usersArray) {
                    JsonObject userObject = userValue.asJsonObject();
                    String userEmail = userObject.getString("email");
                    if(u.getEmail().equals(userEmail)){
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    if(unica.getTipo().equals("receita")){
                        u.setSaldo(u.getSaldo() + diff);
                    } else {
                        u.setSaldo(u.getSaldo() - diff);
                    }
                } else {
                    if(unica.getTipo().equals("receita")){
                        u.setSaldo(u.getSaldo() + nSvalue);
                    } else {
                        u.setSaldo(u.getSaldo() - nSvalue);
                    }
                }

                UserDAO.save(u);
            }

            unica.setShareValue(nSvalue);
            UnicaDAO.save(unica);

            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -1;
        }
        return 0;
    }

    public static JsonObject getJsonUserInfo(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = getUserByEmail(email);

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

            return userJson;
        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }
    }

}
