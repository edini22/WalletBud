/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package WB.walletbud;

import org.orm.*;
import org.hibernate.Query;

import java.util.List;

public class FixaDAO {
	public static Fixa loadFixaByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadFixaByORMID(session, id_transacao);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return getFixaByORMID(session, id_transacao);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadFixaByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return getFixaByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Fixa) session.load(Fixa.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Fixa) session.get(Fixa.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Fixa) session.load(Fixa.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Fixa) session.get(Fixa.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return queryFixa(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return queryFixa(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return listFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return listFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Fixa as Fixa");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Fixa as Fixa");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Fixa", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryFixa(session, condition, orderBy);
			return (Fixa[]) list.toArray(new Fixa[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryFixa(session, condition, orderBy, lockMode);
			return (Fixa[]) list.toArray(new Fixa[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Fixa[] fixas = listFixaByQuery(session, condition, orderBy);
		if (fixas != null && fixas.length > 0)
			return fixas[0];
		else
			return null;
	}
	
	public static Fixa loadFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Fixa[] fixas = listFixaByQuery(session, condition, orderBy, lockMode);
		if (fixas != null && fixas.length > 0)
			return fixas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return iterateFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return iterateFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Fixa as Fixa");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Fixa as Fixa");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Fixa", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa createFixa() {
		return new Fixa();
	}
	
	public static boolean save(Fixa fixa) throws PersistentException {
		try {
			AASICPersistentManager.instance().saveObject(fixa);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Fixa fixa) throws PersistentException {
		try {
			AASICPersistentManager.instance().deleteObject(fixa);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(Fixa fixa)throws PersistentException {
		try {
			UserTransacao[] lUserTransacaos = fixa.userTransacao.toArray();
			for(int i = 0; i < lUserTransacaos.length; i++) {
				lUserTransacaos[i].setTransacaoId_transacao(null);
			}
			return delete(fixa);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(Fixa fixa, org.orm.PersistentSession session)throws PersistentException {
		try {
			UserTransacao[] lUserTransacaos = fixa.userTransacao.toArray();
			for(int i = 0; i < lUserTransacaos.length; i++) {
				lUserTransacaos[i].setTransacaoId_transacao(null);
			}
			try {
				session.delete(fixa);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(Fixa fixa) throws PersistentException {
		try {
			AASICPersistentManager.instance().getSession().refresh(fixa);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Fixa fixa) throws PersistentException {
		try {
			AASICPersistentManager.instance().getSession().evict(fixa);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByCriteria(FixaCriteria fixaCriteria) {
		Fixa[] fixas = listFixaByCriteria(fixaCriteria);
		if(fixas == null || fixas.length == 0) {
			return null;
		}
		return fixas[0];
	}
	
	public static Fixa[] listFixaByCriteria(FixaCriteria fixaCriteria) {
		return fixaCriteria.listFixa();
	}
}
