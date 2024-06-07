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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class TransacaoPartilhadaDAO {
	public static TransacaoPartilhada loadTransacaoPartilhadaByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoPartilhadaByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada getTransacaoPartilhadaByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getTransacaoPartilhadaByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoPartilhadaByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada getTransacaoPartilhadaByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getTransacaoPartilhadaByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (TransacaoPartilhada) session.load(wb.walletbud.TransacaoPartilhada.class, Integer.valueOf(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada getTransacaoPartilhadaByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (TransacaoPartilhada) session.get(wb.walletbud.TransacaoPartilhada.class, Integer.valueOf(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (TransacaoPartilhada) session.load(wb.walletbud.TransacaoPartilhada.class, Integer.valueOf(ID), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada getTransacaoPartilhadaByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (TransacaoPartilhada) session.get(wb.walletbud.TransacaoPartilhada.class, Integer.valueOf(ID), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacaoPartilhada(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryTransacaoPartilhada(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacaoPartilhada(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryTransacaoPartilhada(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada[] listTransacaoPartilhadaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listTransacaoPartilhadaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada[] listTransacaoPartilhadaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listTransacaoPartilhadaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacaoPartilhada(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoPartilhada as TransacaoPartilhada");
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
	
	public static List queryTransacaoPartilhada(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoPartilhada as TransacaoPartilhada");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("TransacaoPartilhada", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada[] listTransacaoPartilhadaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTransacaoPartilhada(session, condition, orderBy);
			return (TransacaoPartilhada[]) list.toArray(new TransacaoPartilhada[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada[] listTransacaoPartilhadaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTransacaoPartilhada(session, condition, orderBy, lockMode);
			return (TransacaoPartilhada[]) list.toArray(new TransacaoPartilhada[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoPartilhadaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoPartilhadaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		TransacaoPartilhada[] transacaoPartilhadas = listTransacaoPartilhadaByQuery(session, condition, orderBy);
		if (transacaoPartilhadas != null && transacaoPartilhadas.length > 0)
			return transacaoPartilhadas[0];
		else
			return null;
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		TransacaoPartilhada[] transacaoPartilhadas = listTransacaoPartilhadaByQuery(session, condition, orderBy, lockMode);
		if (transacaoPartilhadas != null && transacaoPartilhadas.length > 0)
			return transacaoPartilhadas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateTransacaoPartilhadaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateTransacaoPartilhadaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoPartilhadaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateTransacaoPartilhadaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoPartilhadaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoPartilhada as TransacaoPartilhada");
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
	
	public static java.util.Iterator iterateTransacaoPartilhadaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoPartilhada as TransacaoPartilhada");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("TransacaoPartilhada", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada createTransacaoPartilhada() {
		return new wb.walletbud.TransacaoPartilhada();
	}
	
	public static boolean save(wb.walletbud.TransacaoPartilhada transacaoPartilhada) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(transacaoPartilhada);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.TransacaoPartilhada transacaoPartilhada) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(transacaoPartilhada);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.TransacaoPartilhada transacaoPartilhada)throws PersistentException {
		try {
			if (transacaoPartilhada.getUsertransacaoId() != null) {
				transacaoPartilhada.getUsertransacaoId().transacao.remove(transacaoPartilhada);
			}
			
			if (transacaoPartilhada.getUserId_user() != null) {
				transacaoPartilhada.getUserId_user().user_categoria.remove(transacaoPartilhada);
			}
			
			return delete(transacaoPartilhada);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.TransacaoPartilhada transacaoPartilhada, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (transacaoPartilhada.getUsertransacaoId() != null) {
				transacaoPartilhada.getUsertransacaoId().transacao.remove(transacaoPartilhada);
			}
			
			if (transacaoPartilhada.getUserId_user() != null) {
				transacaoPartilhada.getUserId_user().user_categoria.remove(transacaoPartilhada);
			}
			
			try {
				session.delete(transacaoPartilhada);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.TransacaoPartilhada transacaoPartilhada) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(transacaoPartilhada);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.TransacaoPartilhada transacaoPartilhada) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(transacaoPartilhada);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoPartilhada loadTransacaoPartilhadaByCriteria(TransacaoPartilhadaCriteria transacaoPartilhadaCriteria) {
		TransacaoPartilhada[] transacaoPartilhadas = listTransacaoPartilhadaByCriteria(transacaoPartilhadaCriteria);
		if(transacaoPartilhadas == null || transacaoPartilhadas.length == 0) {
			return null;
		}
		return transacaoPartilhadas[0];
	}
	
	public static TransacaoPartilhada[] listTransacaoPartilhadaByCriteria(TransacaoPartilhadaCriteria transacaoPartilhadaCriteria) {
		return transacaoPartilhadaCriteria.listTransacaoPartilhada();
	}
}
