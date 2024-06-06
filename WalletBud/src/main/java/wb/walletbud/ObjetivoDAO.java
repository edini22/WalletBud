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

public class ObjetivoDAO {
	public static Objetivo loadObjetivoByORMID(int id_objetivo) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadObjetivoByORMID(session, id_objetivo);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo getObjetivoByORMID(int id_objetivo) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getObjetivoByORMID(session, id_objetivo);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByORMID(int id_objetivo, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadObjetivoByORMID(session, id_objetivo, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo getObjetivoByORMID(int id_objetivo, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getObjetivoByORMID(session, id_objetivo, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByORMID(PersistentSession session, int id_objetivo) throws PersistentException {
		try {
			return (Objetivo) session.load(wb.walletbud.Objetivo.class, Integer.valueOf(id_objetivo));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo getObjetivoByORMID(PersistentSession session, int id_objetivo) throws PersistentException {
		try {
			return (Objetivo) session.get(wb.walletbud.Objetivo.class, Integer.valueOf(id_objetivo));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByORMID(PersistentSession session, int id_objetivo, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Objetivo) session.load(wb.walletbud.Objetivo.class, Integer.valueOf(id_objetivo), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo getObjetivoByORMID(PersistentSession session, int id_objetivo, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Objetivo) session.get(wb.walletbud.Objetivo.class, Integer.valueOf(id_objetivo), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryObjetivo(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryObjetivo(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryObjetivo(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryObjetivo(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo[] listObjetivoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listObjetivoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo[] listObjetivoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listObjetivoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryObjetivo(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Objetivo as Objetivo");
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
	
	public static List queryObjetivo(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Objetivo as Objetivo");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Objetivo", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo[] listObjetivoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryObjetivo(session, condition, orderBy);
			return (Objetivo[]) list.toArray(new Objetivo[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo[] listObjetivoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryObjetivo(session, condition, orderBy, lockMode);
			return (Objetivo[]) list.toArray(new Objetivo[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadObjetivoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadObjetivoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Objetivo[] objetivos = listObjetivoByQuery(session, condition, orderBy);
		if (objetivos != null && objetivos.length > 0)
			return objetivos[0];
		else
			return null;
	}
	
	public static Objetivo loadObjetivoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Objetivo[] objetivos = listObjetivoByQuery(session, condition, orderBy, lockMode);
		if (objetivos != null && objetivos.length > 0)
			return objetivos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateObjetivoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateObjetivoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateObjetivoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateObjetivoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateObjetivoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Objetivo as Objetivo");
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
	
	public static java.util.Iterator iterateObjetivoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Objetivo as Objetivo");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Objetivo", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo createObjetivo() {
		return new wb.walletbud.Objetivo();
	}
	
	public static boolean save(wb.walletbud.Objetivo objetivo) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(objetivo);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.Objetivo objetivo) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(objetivo);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Objetivo objetivo)throws PersistentException {
		try {
			if (objetivo.getUserId_user() != null) {
				objetivo.getUserId_user().objetivo.remove(objetivo);
			}
			
			return delete(objetivo);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Objetivo objetivo, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (objetivo.getUserId_user() != null) {
				objetivo.getUserId_user().objetivo.remove(objetivo);
			}
			
			try {
				session.delete(objetivo);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.Objetivo objetivo) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(objetivo);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.Objetivo objetivo) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(objetivo);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Objetivo loadObjetivoByCriteria(ObjetivoCriteria objetivoCriteria) {
		Objetivo[] objetivos = listObjetivoByCriteria(objetivoCriteria);
		if(objetivos == null || objetivos.length == 0) {
			return null;
		}
		return objetivos[0];
	}
	
	public static Objetivo[] listObjetivoByCriteria(ObjetivoCriteria objetivoCriteria) {
		return objetivoCriteria.listObjetivo();
	}
}
