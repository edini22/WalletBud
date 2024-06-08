package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.Categoria;
import wb.walletbud.CategoriaDAO;
import wb.walletbud.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class GerirCategoria {

    @EJB
    private GerirUtilizador gerirUtilizador;

    public int createDefaultCategorias(User user) throws PersistentException {
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

    public int createCategoria(String nome, String tipo, String email) throws PersistentException {

        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

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

    public JsonObject getJsonCategoriaById(int id, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
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

    public Categoria getCategoriaById(int id, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
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

    public JsonObject getCategorias(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();

        try{
            User user = gerirUtilizador.getUserByEmail(email);
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

    public int editCategoria(int id, String nome, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();

        try {
            User user = gerirUtilizador.getUserByEmail(email);

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
}
