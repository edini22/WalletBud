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

public class TransacaoFixaDAO {
	public static TransacaoFixa loadTransacaoFixaByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoFixaByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa getTransacaoFixaByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getTransacaoFixaByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoFixaByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa getTransacaoFixaByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getTransacaoFixaByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (TransacaoFixa) session.load(wb.walletbud.TransacaoFixa.class, Integer.valueOf(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa getTransacaoFixaByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (TransacaoFixa) session.get(wb.walletbud.TransacaoFixa.class, Integer.valueOf(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (TransacaoFixa) session.load(wb.walletbud.TransacaoFixa.class, Integer.valueOf(ID), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa getTransacaoFixaByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (TransacaoFixa) session.get(wb.walletbud.TransacaoFixa.class, Integer.valueOf(ID), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacaoFixa(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryTransacaoFixa(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacaoFixa(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryTransacaoFixa(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa[] listTransacaoFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listTransacaoFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa[] listTransacaoFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listTransacaoFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacaoFixa(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoFixa as TransacaoFixa");
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
	
	public static List queryTransacaoFixa(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoFixa as TransacaoFixa");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("TransacaoFixa", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa[] listTransacaoFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTransacaoFixa(session, condition, orderBy);
			return (TransacaoFixa[]) list.toArray(new TransacaoFixa[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa[] listTransacaoFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTransacaoFixa(session, condition, orderBy, lockMode);
			return (TransacaoFixa[]) list.toArray(new TransacaoFixa[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadTransacaoFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		TransacaoFixa[] transacaoFixas = listTransacaoFixaByQuery(session, condition, orderBy);
		if (transacaoFixas != null && transacaoFixas.length > 0)
			return transacaoFixas[0];
		else
			return null;
	}
	
	public static TransacaoFixa loadTransacaoFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		TransacaoFixa[] transacaoFixas = listTransacaoFixaByQuery(session, condition, orderBy, lockMode);
		if (transacaoFixas != null && transacaoFixas.length > 0)
			return transacaoFixas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateTransacaoFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateTransacaoFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateTransacaoFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoFixa as TransacaoFixa");
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
	
	public static java.util.Iterator iterateTransacaoFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.TransacaoFixa as TransacaoFixa");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("TransacaoFixa", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa createTransacaoFixa() {
		return new wb.walletbud.TransacaoFixa();
	}
	
	public static boolean save(wb.walletbud.TransacaoFixa transacaoFixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(transacaoFixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.TransacaoFixa transacaoFixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(transacaoFixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.TransacaoFixa transacaoFixa)throws PersistentException {
		try {
			if (transacaoFixa.getTransacaofixa_ID() != null) {
				transacaoFixa.getTransacaofixa_ID().fixa.remove(transacaoFixa);
			}
			
			wb.walletbud.User[] lUserId_users = transacaoFixa.userId_user.toArray();
			for(int i = 0; i < lUserId_users.length; i++) {
				lUserId_users[i].transacaoFixa.remove(transacaoFixa);
			}
			return delete(transacaoFixa);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.TransacaoFixa transacaoFixa, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (transacaoFixa.getTransacaofixa_ID() != null) {
				transacaoFixa.getTransacaofixa_ID().fixa.remove(transacaoFixa);
			}
			
			wb.walletbud.User[] lUserId_users = transacaoFixa.userId_user.toArray();
			for(int i = 0; i < lUserId_users.length; i++) {
				lUserId_users[i].transacaoFixa.remove(transacaoFixa);
			}
			try {
				session.delete(transacaoFixa);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.TransacaoFixa transacaoFixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(transacaoFixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.TransacaoFixa transacaoFixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(transacaoFixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static TransacaoFixa loadTransacaoFixaByCriteria(TransacaoFixaCriteria transacaoFixaCriteria) {
		TransacaoFixa[] transacaoFixas = listTransacaoFixaByCriteria(transacaoFixaCriteria);
		if(transacaoFixas == null || transacaoFixas.length == 0) {
			return null;
		}
		return transacaoFixas[0];
	}
	
	public static TransacaoFixa[] listTransacaoFixaByCriteria(TransacaoFixaCriteria transacaoFixaCriteria) {
		return transacaoFixaCriteria.listTransacaoFixa();
	}
}
