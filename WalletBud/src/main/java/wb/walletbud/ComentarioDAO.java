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

public class ComentarioDAO {
	public static Comentario loadComentarioByORMID(int id_comentario) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadComentarioByORMID(session, id_comentario);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario getComentarioByORMID(int id_comentario) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getComentarioByORMID(session, id_comentario);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByORMID(int id_comentario, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadComentarioByORMID(session, id_comentario, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario getComentarioByORMID(int id_comentario, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getComentarioByORMID(session, id_comentario, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByORMID(PersistentSession session, int id_comentario) throws PersistentException {
		try {
			return (Comentario) session.load(wb.walletbud.Comentario.class, Integer.valueOf(id_comentario));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario getComentarioByORMID(PersistentSession session, int id_comentario) throws PersistentException {
		try {
			return (Comentario) session.get(wb.walletbud.Comentario.class, Integer.valueOf(id_comentario));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByORMID(PersistentSession session, int id_comentario, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Comentario) session.load(wb.walletbud.Comentario.class, Integer.valueOf(id_comentario), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario getComentarioByORMID(PersistentSession session, int id_comentario, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Comentario) session.get(wb.walletbud.Comentario.class, Integer.valueOf(id_comentario), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryComentario(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryComentario(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryComentario(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryComentario(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario[] listComentarioByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listComentarioByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario[] listComentarioByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listComentarioByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryComentario(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Comentario as Comentario");
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
	
	public static List queryComentario(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Comentario as Comentario");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Comentario", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario[] listComentarioByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryComentario(session, condition, orderBy);
			return (Comentario[]) list.toArray(new Comentario[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario[] listComentarioByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryComentario(session, condition, orderBy, lockMode);
			return (Comentario[]) list.toArray(new Comentario[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadComentarioByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadComentarioByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Comentario[] comentarios = listComentarioByQuery(session, condition, orderBy);
		if (comentarios != null && comentarios.length > 0)
			return comentarios[0];
		else
			return null;
	}
	
	public static Comentario loadComentarioByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Comentario[] comentarios = listComentarioByQuery(session, condition, orderBy, lockMode);
		if (comentarios != null && comentarios.length > 0)
			return comentarios[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateComentarioByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateComentarioByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateComentarioByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateComentarioByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateComentarioByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Comentario as Comentario");
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
	
	public static java.util.Iterator iterateComentarioByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Comentario as Comentario");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Comentario", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario createComentario() {
		return new wb.walletbud.Comentario();
	}
	
	public static boolean save(wb.walletbud.Comentario comentario) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(comentario);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.Comentario comentario) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(comentario);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Comentario comentario)throws PersistentException {
		try {
			if (comentario.getUserId_user() != null) {
				comentario.getUserId_user().comentario.remove(comentario);
			}
			
			if (comentario.getTransacaoId_transacao() != null) {
				comentario.getTransacaoId_transacao().comentario.remove(comentario);
			}
			
			return delete(comentario);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Comentario comentario, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (comentario.getUserId_user() != null) {
				comentario.getUserId_user().comentario.remove(comentario);
			}
			
			if (comentario.getTransacaoId_transacao() != null) {
				comentario.getTransacaoId_transacao().comentario.remove(comentario);
			}
			
			try {
				session.delete(comentario);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.Comentario comentario) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(comentario);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.Comentario comentario) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(comentario);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Comentario loadComentarioByCriteria(ComentarioCriteria comentarioCriteria) {
		Comentario[] comentarios = listComentarioByCriteria(comentarioCriteria);
		if(comentarios == null || comentarios.length == 0) {
			return null;
		}
		return comentarios[0];
	}
	
	public static Comentario[] listComentarioByCriteria(ComentarioCriteria comentarioCriteria) {
		return comentarioCriteria.listComentario();
	}
}
