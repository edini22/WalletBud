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

public class FixaDAO {
	public static Fixa loadFixaByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadFixaByORMID(session, id_transacao);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getFixaByORMID(session, id_transacao);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadFixaByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getFixaByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Fixa) session.load(wb.walletbud.Fixa.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Fixa) session.get(wb.walletbud.Fixa.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Fixa) session.load(wb.walletbud.Fixa.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa getFixaByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Fixa) session.get(wb.walletbud.Fixa.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryFixa(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryFixa(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryFixa(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Fixa as Fixa");
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
	
	public static List queryFixa(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Fixa as Fixa");
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
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryFixa(session, condition, orderBy);
			return (Fixa[]) list.toArray(new Fixa[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa[] listFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryFixa(session, condition, orderBy, lockMode);
			return (Fixa[]) list.toArray(new Fixa[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Fixa loadFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
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
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateFixaByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateFixaByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateFixaByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateFixaByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Fixa as Fixa");
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
	
	public static java.util.Iterator iterateFixaByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.Fixa as Fixa");
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
			throw new PersistentException(e);
		}
	}
	
	public static Fixa createFixa() {
		return new wb.walletbud.Fixa();
	}
	
	public static boolean save(wb.walletbud.Fixa fixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(fixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.Fixa fixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(fixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Fixa fixa)throws PersistentException {
		try {
			wb.walletbud.TransacaoFixa[] lFixas = fixa.fixa.toArray();
			for(int i = 0; i < lFixas.length; i++) {
				lFixas[i].setTransacaofixa_ID(null);
			}
			if (fixa.getCategoriaId_categoria() != null) {
				fixa.getCategoriaId_categoria().transacao.remove(fixa);
			}
			
			wb.walletbud.Comentario[] lComentarios = fixa.comentario.toArray();
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setTransacaoId_transacao(null);
			}
			wb.walletbud.UserTransacao[] lTransacaos = fixa.transacao.toArray();
			for(int i = 0; i < lTransacaos.length; i++) {
				lTransacaos[i].setUsertransacaoId(null);
			}
			return delete(fixa);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.Fixa fixa, org.orm.PersistentSession session)throws PersistentException {
		try {
			wb.walletbud.TransacaoFixa[] lFixas = fixa.fixa.toArray();
			for(int i = 0; i < lFixas.length; i++) {
				lFixas[i].setTransacaofixa_ID(null);
			}
			if (fixa.getCategoriaId_categoria() != null) {
				fixa.getCategoriaId_categoria().transacao.remove(fixa);
			}
			
			wb.walletbud.Comentario[] lComentarios = fixa.comentario.toArray();
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setTransacaoId_transacao(null);
			}
			wb.walletbud.UserTransacao[] lTransacaos = fixa.transacao.toArray();
			for(int i = 0; i < lTransacaos.length; i++) {
				lTransacaos[i].setUsertransacaoId(null);
			}
			try {
				session.delete(fixa);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.Fixa fixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(fixa);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.Fixa fixa) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(fixa);
			return true;
		}
		catch (Exception e) {
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
