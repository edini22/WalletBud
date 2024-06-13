/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package wb.walletbud;

import org.hibernate.transform.Transformers;
import org.orm.*;
import org.hibernate.Query;

import java.util.List;
import java.util.Map;

public class TransacaoDAO {
	public static Transacao loadTransacaoByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoByORMID(session, id_transacao);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getTransacaoByORMID(session, id_transacao);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getTransacaoByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Transacao) session.load(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Transacao) session.get(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Transacao) session.load(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Transacao) session.get(wb.walletbud.Transacao.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static List<Map<String, Object>> queryTransacoesByUserId(int userId) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
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
	
	public static List queryTransacao(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryTransacao(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static List<Map<String, Object>> queryTransacoesByUserIdandDays(int userId, int days) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
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
	
	public static List queryTransacao(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryTransacao(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTransacao(session, condition, orderBy);
			return (Transacao[]) list.toArray(new Transacao[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTransacao(session, condition, orderBy, lockMode);
			return (Transacao[]) list.toArray(new Transacao[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.Transacao transacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(transacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Transacao transacao)throws PersistentException {
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
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setTransacaoId_transacao(null);
			}
			wb.walletbud.TransacaoPartilhada[] lTransacaos = transacao.transacao.toArray();
			for(int i = 0; i < lTransacaos.length; i++) {
				lTransacaos[i].setUsertransacaoId(null);
			}
			return delete(transacao);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Transacao transacao, org.orm.PersistentSession session)throws PersistentException {
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
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setTransacaoId_transacao(null);
			}
			wb.walletbud.TransacaoPartilhada[] lTransacaos = transacao.transacao.toArray();
			for(int i = 0; i < lTransacaos.length; i++) {
				lTransacaos[i].setUsertransacaoId(null);
			}
			try {
				session.delete(transacao);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.Transacao transacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(transacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.Transacao transacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(transacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByCriteria(TransacaoCriteria transacaoCriteria) {
		Transacao[] transacaos = listTransacaoByCriteria(transacaoCriteria);
		if(transacaos == null || transacaos.length == 0) {
			return null;
		}
		return transacaos[0];
	}
	
	public static Transacao[] listTransacaoByCriteria(TransacaoCriteria transacaoCriteria) {
		return transacaoCriteria.listTransacao();
	}
}
