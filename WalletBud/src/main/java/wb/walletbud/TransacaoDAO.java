/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * <p>
 * This is an automatic generated file. It will be regenerated every time
 * you generate persistence class.
 * <p>
 * Modifying its content may cause the program not work, or your work may lost.
 * <p>
 * Licensee:
 * License Type: Evaluation
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package wb.walletbud;

import org.hibernate.transform.Transformers;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;

import java.util.List;
import java.util.Map;

public class TransacaoDAO {
    public static Transacao loadTransacaoByORMID(int id_transacao) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return loadTransacaoByORMID(session, id_transacao);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao getTransacaoByORMID(int id_transacao) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return getTransacaoByORMID(session, id_transacao);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return loadTransacaoByORMID(session, id_transacao, lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao getTransacaoByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return getTransacaoByORMID(session, id_transacao, lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByORMID(PersistentSession session, int id_transacao) throws PersistentException {
        try {
            return (Transacao) session.load(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao));
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao getTransacaoByORMID(PersistentSession session, int id_transacao) throws PersistentException {
        try {
            return (Transacao) session.get(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao));
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            return (Transacao) session.load(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao), lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao getTransacaoByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            return (Transacao) session.get(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao), lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List queryTransacao(String condition, String orderBy) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return queryTransacao(session, condition, orderBy);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List queryTransacao(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return queryTransacao(session, condition, orderBy, lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao[] listTransacaoByQuery(String condition, String orderBy) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return listTransacaoByQuery(session, condition, orderBy);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao[] listTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return listTransacaoByQuery(session, condition, orderBy, lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List queryTransacao(PersistentSession session, String condition, String orderBy) throws PersistentException {
        StringBuffer sb = new StringBuffer("From wb.walletbud.Transacao as Transacao");
        if (condition != null)
            sb.append(" Where ").append(condition);
        if (orderBy != null)
            sb.append(" Order By ").append(orderBy);
        try {
            Query query = session.createQuery(sb.toString());
            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List queryTransacao(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        StringBuffer sb = new StringBuffer("From wb.walletbud.Transacao as Transacao");
        if (condition != null)
            sb.append(" Where ").append(condition);
        if (orderBy != null)
            sb.append(" Order By ").append(orderBy);
        try {
            Query query = session.createQuery(sb.toString());
            query.setLockMode("Transacao", lockMode);
            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao[] listTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
        try {
            List list = queryTransacao(session, condition, orderBy);
            return (Transacao[]) list.toArray(new Transacao[list.size()]);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao[] listTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            List list = queryTransacao(session, condition, orderBy, lockMode);
            return (Transacao[]) list.toArray(new Transacao[list.size()]);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryTransacoesByUserId(PersistentSession session, int userId) throws PersistentException {
        try {
            String sqlQuery =
                    "(SELECT t.Id_transacao AS Id, t.Date AS Date, 'Unica' AS Discriminator " +
                            "FROM Transacao t " +
                            "LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao " +
                            "WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId) " +
                            "AND t.Status = 1 " +
                            "AND t.Discriminator = 'Unica') " +
                            "UNION ALL " +
                            "(SELECT tf.ID AS Id, tf.DataAtual AS Date, 'Fixa' AS Discriminator " +
                            "FROM User_TransacaoFixa utf " +
                            "JOIN TransacaoFixa tf ON utf.TransacaoFixaID = tf.ID " +
                            "WHERE utf.UserId_user = :userId) " +
                            "ORDER BY Date DESC";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryTransacoesByUserIdandDays(PersistentSession session, int userId, int days) throws PersistentException {
        try {
            String sqlQuery =
                    "(SELECT t.Id_transacao AS Id, t.Date AS date, 'Unica' AS Discriminator " +
                            "FROM Transacao t " +
                            "LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao " +
                            "WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId) " +
                            "AND t.Status = 1 " +
                            "AND t.Discriminator = 'Unica' " +
                            "AND t.Date >= DATE_SUB(CURDATE(), INTERVAL :days DAY)) " +
                            "UNION ALL " +
                            "(SELECT tf.ID AS Id, tf.DataAtual AS date, 'Fixa' AS Discriminator " +
                            "FROM User_TransacaoFixa utf " +
                            "JOIN TransacaoFixa tf ON utf.TransacaoFixaID = tf.ID " +
                            "WHERE utf.UserId_user = :userId " +
                            "AND tf.DataAtual >= DATE_SUB(CURDATE(), INTERVAL :days DAY)) " +
                            "ORDER BY date DESC";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("days", days)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryBalanco(PersistentSession session, int userId) throws PersistentException {
        try {
            String sqlQuery = "(SELECT t.Id_transacao AS Id, t.Date AS date, 'Unica' AS Discriminator " +
                    "FROM Transacao t " +
                    "LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao " +
                    "WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId) " +
                    "AND t.Status = 1 " +
                    "AND t.Discriminator = 'Unica' " +
                    "AND MONTH(t.Date) = MONTH(CURDATE()) " +  // Filtra pelo mês atual
                    "AND YEAR(t.Date) = YEAR(CURDATE())) " +    // Filtra pelo ano atual
                    "UNION ALL " +
                    "(SELECT tf.ID AS Id, tf.DataAtual AS date, 'Fixa' AS Discriminator " +
                    "FROM User_TransacaoFixa utf " +
                    "JOIN TransacaoFixa tf ON utf.TransacaoFixaID = tf.ID " +
                    "WHERE utf.UserId_user = :userId " +
                    "AND MONTH(tf.DataAtual) = MONTH(CURDATE()) " +  // Filtra pelo mês atual
                    "AND YEAR(tf.DataAtual) = YEAR(CURDATE())) " +  // Filtra pelo ano atual
                    "ORDER BY date DESC";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            System.out.println(query.toString());  // Verifique a string SQL gerada

            return query.list();
        } catch (Exception e) {
            throw new PersistentException("Erro ao executar a consulta SQL", e);
        }
    }


    public static List<Map<String, Object>> queryTransacoesByUserIdAndTime(PersistentSession session, int userId, int year, int month) throws PersistentException {
        try {

            String sqlQuery =
                    "(SELECT t.Id_transacao AS Id, t.Date AS date, 'Unica' AS Discriminator\n" +
                            " FROM Transacao t\n" +
                            "          LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                            " WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId)\n" +
                            "   AND t.Status = 1\n" +
                            "   AND t.Discriminator = 'Unica'\n" +
                            "   AND YEAR(t.Date) = :year\n" +
                            "   AND MONTH(t.Date) = :month)\n" +
                            "UNION ALL\n" +
                            "(SELECT tf.ID AS Id, tf.DataPagamento AS date, 'Fixa' AS Discriminator\n" +
                            " FROM User_TransacaoFixa utf\n" +
                            "          JOIN TransacaoFixa tf ON utf.TransacaoFixaID = tf.ID\n" +
                            " WHERE utf.UserId_user = :userId\n" +
                            "   AND YEAR(tf.DataPagamento) = :year\n" +
                            "   AND MONTH(tf.DataPagamento) = :month)\n" +
                            "ORDER BY date;";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryPendentesByUserId(PersistentSession session, int userId) throws PersistentException {
        try {
            String sqlQuery =
                    "SELECT DISTINCT t.Id_transacao AS Id, t.Date AS date, t.Discriminator AS Discriminator\n" +
                            "FROM Transacao t\n" +
                            "         LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                            "WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId)\n" +
                            "  AND t.Status = 0\n" +
                            "ORDER BY date;";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryGastosByAnoById(PersistentSession session, int userId, int year) throws PersistentException {
        try {
            String sqlQuery = "SELECT MONTH(date) AS Month, SUM(Cost) AS TotalCost\n" +
                    "FROM (\n" +
                    "    SELECT t.Id_transacao AS Id, t.Date AS date, 'Unica' AS Discriminator, t.ShareValue AS Cost, t.Tipo as Tipo\n" +
                    "    FROM Transacao t\n" +
                    "          LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                    "    WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId OR tp.UserId_user IS NULL)\n" +
                    "        AND t.Status = 1\n" +
                    "        AND t.Discriminator = 'Unica'\n" +
                    "        AND t.Tipo = 'despesa'\n" +
                    "        AND YEAR(t.Date) = :year\n" +
                    "\n" +
                    "    UNION ALL\n" +
                    "\n" +
                    "    SELECT tf.ID AS Id, tf.DataAtual AS date, 'Fixa' AS Discriminator, tf.Payvalue AS Cost, t.Tipo AS Tipo\n" +
                    "    FROM TransacaoFixa tf\n" +
                    "        JOIN User_TransacaoFixa utf ON tf.ID = utf.TransacaoFixaID\n" +
                    "        JOIN Transacao t ON tf.TransacaoId_transacao = t.Id_transacao\n" +
                    "    WHERE utf.UserId_user = :userId\n" +
                    "        AND YEAR(DataAtual) = :year\n" +
                    "        AND t.Tipo = 'despesa'\n" +
                    ") AS combined\n" +
                    "GROUP BY Month\n" +
                    "ORDER BY Month;";


            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("year", year)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryGastosTotalAnoById(PersistentSession session, int userId, int year) throws PersistentException {
        try {
            String sqlQuery = "SELECT SUM(Cost) AS TotalCost\n" +
                    "FROM (\n" +
                    "         SELECT t.Id_transacao AS Id, t.Date AS date, 'Unica' AS Discriminator, t.ShareValue AS Cost, t.Tipo as Tipo\n" +
                    "         FROM Transacao t\n" +
                    "                  LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                    "         WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId OR tp.UserId_user IS NULL)\n" +
                    "           AND t.Status = 1\n" +
                    "           AND t.Discriminator = 'Unica'\n" +
                    "           AND t.Tipo = 'despesa'\n" +
                    "           AND YEAR(t.Date) = :year\n" +
                    "\n" +
                    "         UNION ALL\n" +
                    "\n" +
                    "         SELECT tf.ID AS Id, tf.DataAtual AS date, 'Fixa' AS Discriminator, tf.Payvalue AS Cost, t.Tipo AS Tipo\n" +
                    "         FROM TransacaoFixa tf\n" +
                    "                  JOIN User_TransacaoFixa utf ON tf.ID = utf.TransacaoFixaID\n" +
                    "                  JOIN Transacao t ON tf.TransacaoId_transacao = t.Id_transacao\n" +
                    "         WHERE utf.UserId_user = :userId\n" +
                    "           AND YEAR(DataAtual) = :year\n" +
                    "           AND t.Tipo = 'despesa'\n" +
                    "     ) AS combined;";


            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("year", year)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryGastosTotalByCategoriaById(PersistentSession session, int userId, int year, int month) throws PersistentException {
        try {
            String sqlQuery = "SELECT combined.Categoria AS Categoria, SUM(Cost) AS TotalCost\n" +
                    "FROM (\n" +
                    "    SELECT t.Id_transacao AS Id, t.Date AS date, 'Unica' AS Discriminator, t.ShareValue AS Cost, t.Tipo as Tipo, c.Name AS Categoria\n" +
                    "    FROM Transacao t\n" +
                    "        LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                    "        JOIN Categoria c ON t.CategoriaId_categoria = c.Id_categoria\n" +
                    "    WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId OR tp.UserId_user IS NULL)\n" +
                    "        AND t.Status = 1\n" +
                    "        AND t.Discriminator = 'Unica'\n" +
                    "        AND t.Tipo = 'despesa'\n" +
                    "        AND YEAR(t.Date) = :year\n" +
                    "        AND MONTH(t.Date) = :month\n" +
                    "\n" +
                    "    UNION ALL\n" +
                    "\n" +
                    "    SELECT tf.ID AS Id, tf.DataAtual AS date, 'Fixa' AS Discriminator, tf.Payvalue AS Cost, t.Tipo AS Tipo, c.Name AS Categoria\n" +
                    "    FROM TransacaoFixa tf\n" +
                    "        JOIN User_TransacaoFixa utf ON tf.ID = utf.TransacaoFixaID\n" +
                    "        JOIN Transacao t ON tf.TransacaoId_transacao = t.Id_transacao\n" +
                    "        JOIN Categoria c ON t.CategoriaId_categoria = c.Id_categoria\n" +
                    "    WHERE utf.UserId_user = :userId\n" +
                    "    AND YEAR(DataAtual) = :year\n" +
                    "    AND MONTH(DataAtual) = :month\n" +
                    "    AND t.Tipo = 'despesa'\n" +
                    ") AS combined\n" +
                    "GROUP BY combined.Categoria;";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static List<Map<String, Object>> queryGastosSemanaPassada(PersistentSession session, int userId, String endDate) throws PersistentException {
        try {
            String sqlQuery = "WITH RECURSIVE dates AS (\n" +
                    "    SELECT DATE_SUB(DATE(:endDate), INTERVAL (WEEKDAY(:endDate) + 7) DAY) AS date\n" +
                    "    UNION ALL\n" +
                    "    SELECT date + INTERVAL 1 DAY\n" +
                    "    FROM dates\n" +
                    "    WHERE date < DATE_SUB(DATE(:endDate), INTERVAL (WEEKDAY(:endDate) + 1) DAY)\n" +
                    ")\n" +
                    "SELECT\n" +
                    "    dates.date AS Date,\n" +
                    "    DATE_FORMAT(dates.date, '%W') AS DayOfWeek,\n" +
                    "    IFNULL(SUM(combined.Cost), 0) AS TotalCost\n" +
                    "FROM dates\n" +
                    "         LEFT JOIN (\n" +
                    "    SELECT\n" +
                    "        t.Id_transacao AS Id,\n" +
                    "        t.Date AS date,\n" +
                    "        'Unica' AS Discriminator,\n" +
                    "        t.ShareValue AS Cost,\n" +
                    "        t.Tipo AS Tipo,\n" +
                    "        c.Name AS Categoria\n" +
                    "    FROM Transacao t\n" +
                    "             LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                    "             JOIN Categoria c ON t.CategoriaId_categoria = c.Id_categoria\n" +
                    "    WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId OR tp.UserId_user IS NULL)\n" +
                    "      AND t.Status = 1\n" +
                    "      AND t.Discriminator = 'Unica'\n" +
                    "      AND t.Tipo = 'despesa'\n" +
                    "      AND t.Date BETWEEN DATE_SUB(DATE(:endDate), INTERVAL (WEEKDAY(:endDate) + 7) DAY) AND DATE_SUB(DATE(:endDate), INTERVAL (WEEKDAY(:endDate) + 1) DAY)\n" +
                    "    UNION ALL\n" +
                    "    SELECT\n" +
                    "        tf.ID AS Id,\n" +
                    "        tf.DataAtual AS date,\n" +
                    "        'Fixa' AS Discriminator,\n" +
                    "        tf.Payvalue AS Cost,\n" +
                    "        t.Tipo AS Tipo,\n" +
                    "        c.Name AS Categoria\n" +
                    "    FROM TransacaoFixa tf\n" +
                    "             JOIN User_TransacaoFixa utf ON tf.ID = utf.TransacaoFixaID\n" +
                    "             JOIN Transacao t ON tf.TransacaoId_transacao = t.Id_transacao\n" +
                    "             JOIN Categoria c ON t.CategoriaId_categoria = c.Id_categoria\n" +
                    "    WHERE utf.UserId_user = :userId\n" +
                    "      AND tf.DataAtual BETWEEN DATE_SUB(DATE(:endDate), INTERVAL (WEEKDAY(:endDate) + 7) DAY) AND DATE_SUB(DATE(:endDate), INTERVAL (WEEKDAY(:endDate) + 1) DAY)\n" +
                    "      AND t.Tipo = 'despesa'\n" +
                    ") AS combined ON dates.date = DATE(combined.date)\n" +
                    "\n" +
                    "GROUP BY dates.date\n" +
                    "ORDER BY dates.date;";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("endDate", endDate)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }


    public static List<Map<String, Object>> queryGastosTotalByDiaSemana(PersistentSession session, int userId, String startDate) throws PersistentException {
        try {
            String sqlQuery = "WITH RECURSIVE dates AS (\n" +
                    "    SELECT DATE(:startDate) AS date\n" +
                    "    UNION ALL\n" +
                    "    SELECT date + INTERVAL 1 DAY\n" +
                    "    FROM dates\n" +
                    "    WHERE date < :startDate + INTERVAL 34 DAY\n" +
                    ")\n" +
                    "SELECT\n" +
                    "    dates.date AS Date,\n" +
                    "    DATE_FORMAT(dates.date, '%W') AS DayOfWeek,\n" +
                    "    IFNULL(SUM(combined.Cost), 0) AS TotalCost\n" +
                    "FROM dates\n" +
                    "    LEFT JOIN (\n" +
                    "    SELECT\n" +
                    "        t.Id_transacao AS Id,\n" +
                    "        t.Date AS date,\n" +
                    "        'Unica' AS Discriminator,\n" +
                    "        t.ShareValue AS Cost,\n" +
                    "        t.Tipo AS Tipo,\n" +
                    "        c.Name AS Categoria\n" +
                    "    FROM Transacao t\n" +
                    "             LEFT JOIN TransacaoPartilhada tp ON t.Id_transacao = tp.TransacaoId_transacao\n" +
                    "             JOIN Categoria c ON t.CategoriaId_categoria = c.Id_categoria\n" +
                    "    WHERE (t.UserId_user = :userId OR tp.UserId_user = :userId OR tp.UserId_user IS NULL)\n" +
                    "      AND t.Status = 1\n" +
                    "      AND t.Discriminator = 'Unica'\n" +
                    "      AND t.Tipo = 'despesa'\n" +
                    "      AND t.Date BETWEEN :startDate AND :startDate + INTERVAL 34 DAY\n" +
                    "    UNION ALL\n" +
                    "    SELECT\n" +
                    "        tf.ID AS Id,\n" +
                    "        tf.DataAtual AS date,\n" +
                    "        'Fixa' AS Discriminator,\n" +
                    "        tf.Payvalue AS Cost,\n" +
                    "        t.Tipo AS Tipo,\n" +
                    "        c.Name AS Categoria\n" +
                    "    FROM TransacaoFixa tf\n" +
                    "             JOIN User_TransacaoFixa utf ON tf.ID = utf.TransacaoFixaID\n" +
                    "             JOIN Transacao t ON tf.TransacaoId_transacao = t.Id_transacao\n" +
                    "             JOIN Categoria c ON t.CategoriaId_categoria = c.Id_categoria\n" +
                    "    WHERE utf.UserId_user = :userId\n" +
                    "      AND tf.DataAtual BETWEEN :startDate AND :startDate + INTERVAL 34 DAY\n" +
                    "      AND t.Tipo = 'despesa'\n" +
                    ") AS combined ON dates.date = DATE(combined.date)\n" +
                    "\n" +
                    "GROUP BY dates.date\n" +
                    "ORDER BY dates.date;";

            Query query = session.createSQLQuery(sqlQuery)
                    .setParameter("userId", userId)
                    .setParameter("startDate", startDate)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query.list();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByQuery(String condition, String orderBy) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return loadTransacaoByQuery(session, condition, orderBy);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return loadTransacaoByQuery(session, condition, orderBy, lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
        Transacao[] transacaos = listTransacaoByQuery(session, condition, orderBy);
        if (transacaos != null && transacaos.length > 0)
            return transacaos[0];
        else
            return null;
    }

    public static Transacao loadTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        Transacao[] transacaos = listTransacaoByQuery(session, condition, orderBy, lockMode);
        if (transacaos != null && transacaos.length > 0)
            return transacaos[0];
        else
            return null;
    }

    public static java.util.Iterator iterateTransacaoByQuery(String condition, String orderBy) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return iterateTransacaoByQuery(session, condition, orderBy);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static java.util.Iterator iterateTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        try {
            PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
            return iterateTransacaoByQuery(session, condition, orderBy, lockMode);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static java.util.Iterator iterateTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
        StringBuffer sb = new StringBuffer("From wb.walletbud.Transacao as Transacao");
        if (condition != null)
            sb.append(" Where ").append(condition);
        if (orderBy != null)
            sb.append(" Order By ").append(orderBy);
        try {
            Query query = session.createQuery(sb.toString());
            return query.iterate();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static java.util.Iterator iterateTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
        StringBuffer sb = new StringBuffer("From wb.walletbud.Transacao as Transacao");
        if (condition != null)
            sb.append(" Where ").append(condition);
        if (orderBy != null)
            sb.append(" Order By ").append(orderBy);
        try {
            Query query = session.createQuery(sb.toString());
            query.setLockMode("Transacao", lockMode);
            return query.iterate();
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao createTransacao() {
        return new wb.walletbud.Transacao();
    }

    public static boolean save(wb.walletbud.Transacao transacao) throws PersistentException {
        try {
            wb.walletbud.AASICPersistentManager.instance().saveObject(transacao);
            return true;
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static boolean delete(wb.walletbud.Transacao transacao) throws PersistentException {
        try {
            wb.walletbud.AASICPersistentManager.instance().deleteObject(transacao);
            return true;
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static boolean deleteAndDissociate(wb.walletbud.Transacao transacao) throws PersistentException {
        if (transacao instanceof wb.walletbud.Fixa) {
            return wb.walletbud.FixaDAO.deleteAndDissociate((wb.walletbud.Fixa) transacao);
        }

        if (transacao instanceof wb.walletbud.Unica) {
            return wb.walletbud.UnicaDAO.deleteAndDissociate((wb.walletbud.Unica) transacao);
        }

        try {
            if (transacao.getOwner_id() != null) {
                transacao.getOwner_id().transacaos.remove(transacao);
            }

            if (transacao.getCategoriaId_categoria() != null) {
                transacao.getCategoriaId_categoria().transacao.remove(transacao);
            }

            wb.walletbud.Comentario[] lComentarios = transacao.comentario.toArray();
            for (int i = 0; i < lComentarios.length; i++) {
                lComentarios[i].setTransacaoId_transacao(null);
            }
            wb.walletbud.TransacaoPartilhada[] lTransacaos = transacao.transacao.toArray();
            for (int i = 0; i < lTransacaos.length; i++) {
                lTransacaos[i].setUsertransacaoId(null);
            }
            wb.walletbud.Notificacao[] lNotificacaos = transacao.notificacao.toArray();
            for (int i = 0; i < lNotificacaos.length; i++) {
                lNotificacaos[i].setTransacaoId_transacao(null);
            }
            return delete(transacao);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static boolean deleteAndDissociate(wb.walletbud.Transacao transacao, org.orm.PersistentSession session) throws PersistentException {
        if (transacao instanceof wb.walletbud.Fixa) {
            return wb.walletbud.FixaDAO.deleteAndDissociate((wb.walletbud.Fixa) transacao, session);
        }

        if (transacao instanceof wb.walletbud.Unica) {
            return wb.walletbud.UnicaDAO.deleteAndDissociate((wb.walletbud.Unica) transacao, session);
        }

        try {
            if (transacao.getOwner_id() != null) {
                transacao.getOwner_id().transacaos.remove(transacao);
            }

            if (transacao.getCategoriaId_categoria() != null) {
                transacao.getCategoriaId_categoria().transacao.remove(transacao);
            }

            wb.walletbud.Comentario[] lComentarios = transacao.comentario.toArray();
            for (int i = 0; i < lComentarios.length; i++) {
                lComentarios[i].setTransacaoId_transacao(null);
            }
            wb.walletbud.TransacaoPartilhada[] lTransacaos = transacao.transacao.toArray();
            for (int i = 0; i < lTransacaos.length; i++) {
                lTransacaos[i].setUsertransacaoId(null);
            }
            wb.walletbud.Notificacao[] lNotificacaos = transacao.notificacao.toArray();
            for (int i = 0; i < lNotificacaos.length; i++) {
                lNotificacaos[i].setTransacaoId_transacao(null);
            }
            try {
                session.delete(transacao);
                return true;
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static boolean refresh(wb.walletbud.Transacao transacao) throws PersistentException {
        try {
            wb.walletbud.AASICPersistentManager.instance().getSession().refresh(transacao);
            return true;
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static boolean evict(wb.walletbud.Transacao transacao) throws PersistentException {
        try {
            wb.walletbud.AASICPersistentManager.instance().getSession().evict(transacao);
            return true;
        } catch (Exception e) {
            throw new PersistentException(e);
        }
    }

    public static Transacao loadTransacaoByCriteria(TransacaoCriteria transacaoCriteria) {
        Transacao[] transacaos = listTransacaoByCriteria(transacaoCriteria);
        if (transacaos == null || transacaos.length == 0) {
            return null;
        }
        return transacaos[0];
    }

    public static Transacao[] listTransacaoByCriteria(TransacaoCriteria transacaoCriteria) {
        return transacaoCriteria.listTransacao();
    }
}
