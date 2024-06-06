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

public class UnicaDAO {
	public static Unica loadUnicaByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUnicaByORMID(session, id_transacao);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica getUnicaByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getUnicaByORMID(session, id_transacao);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUnicaByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica getUnicaByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getUnicaByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Unica) session.load(wb.walletbud.Unica.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica getUnicaByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Unica) session.get(wb.walletbud.Unica.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Unica) session.load(wb.walletbud.Unica.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica getUnicaByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Unica) session.get(wb.walletbud.Unica.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUnica(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryUnica(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUnica(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryUnica(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica[] listUnicaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listUnicaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica[] listUnicaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listUnicaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUnica(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Unica as Unica");
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
	
	public static List queryUnica(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Unica as Unica");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Unica", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica[] listUnicaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUnica(session, condition, orderBy);
			return (Unica[]) list.toArray(new Unica[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica[] listUnicaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUnica(session, condition, orderBy, lockMode);
			return (Unica[]) list.toArray(new Unica[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUnicaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUnicaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Unica[] unicas = listUnicaByQuery(session, condition, orderBy);
		if (unicas != null && unicas.length > 0)
			return unicas[0];
		else
			return null;
	}
	
	public static Unica loadUnicaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Unica[] unicas = listUnicaByQuery(session, condition, orderBy, lockMode);
		if (unicas != null && unicas.length > 0)
			return unicas[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUnicaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateUnicaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUnicaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateUnicaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUnicaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Unica as Unica");
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
	
	public static java.util.Iterator iterateUnicaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Unica as Unica");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Unica", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica createUnica() {
		return new wb.walletbud.Unica();
	}
	
	public static boolean save(wb.walletbud.Unica unica) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(unica);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.Unica unica) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(unica);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Unica unica)throws PersistentException {
		try {
			if (unica.getCategoriaId_categoria() != null) {
				unica.getCategoriaId_categoria().transacao.remove(unica);
			}
			
			wb.walletbud.Comentario[] lComentarios = unica.comentario.toArray();
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setTransacaoId_transacao(null);
			}
			wb.walletbud.UserTransacao[] lTransacaos = unica.transacao.toArray();
			for(int i = 0; i < lTransacaos.length; i++) {
				lTransacaos[i].setUsertransacaoId(null);
			}
			return delete(unica);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Unica unica, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (unica.getCategoriaId_categoria() != null) {
				unica.getCategoriaId_categoria().transacao.remove(unica);
			}
			
			wb.walletbud.Comentario[] lComentarios = unica.comentario.toArray();
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setTransacaoId_transacao(null);
			}
			wb.walletbud.UserTransacao[] lTransacaos = unica.transacao.toArray();
			for(int i = 0; i < lTransacaos.length; i++) {
				lTransacaos[i].setUsertransacaoId(null);
			}
			try {
				session.delete(unica);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.Unica unica) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(unica);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.Unica unica) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(unica);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Unica loadUnicaByCriteria(UnicaCriteria unicaCriteria) {
		Unica[] unicas = listUnicaByCriteria(unicaCriteria);
		if(unicas == null || unicas.length == 0) {
			return null;
		}
		return unicas[0];
	}
	
	public static Unica[] listUnicaByCriteria(UnicaCriteria unicaCriteria) {
		return unicaCriteria.listUnica();
	}
}
