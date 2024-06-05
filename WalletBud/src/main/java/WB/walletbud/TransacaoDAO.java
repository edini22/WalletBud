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

public class TransacaoDAO {
	public static Transacao loadTransacaoByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadTransacaoByORMID(session, id_transacao);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(int id_transacao) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return getTransacaoByORMID(session, id_transacao);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadTransacaoByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return getTransacaoByORMID(session, id_transacao, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Transacao) session.load(Transacao.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(PersistentSession session, int id_transacao) throws PersistentException {
		try {
			return (Transacao) session.get(Transacao.class, Integer.valueOf(id_transacao));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Transacao) session.load(Transacao.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao getTransacaoByORMID(PersistentSession session, int id_transacao, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Transacao) session.get(Transacao.class, Integer.valueOf(id_transacao), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacao(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return queryTransacao(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacao(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return queryTransacao(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return listTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return listTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryTransacao(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Transacao as Transacao");
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
	
	public static List queryTransacao(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Transacao as Transacao");
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
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTransacao(session, condition, orderBy);
			return (Transacao[]) list.toArray(new Transacao[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao[] listTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryTransacao(session, condition, orderBy, lockMode);
			return (Transacao[]) list.toArray(new Transacao[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao loadTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return loadTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
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
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return iterateTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = AASICPersistentManager.instance().getSession();
			return iterateTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Transacao as Transacao");
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
	
	public static java.util.Iterator iterateTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From diagrama_de_classes.walletbud.Transacao as Transacao");
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
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Transacao createTransacao() {
		return new Transacao();
	}
	
	public static boolean save(Transacao transacao) throws PersistentException {
		try {
			AASICPersistentManager.instance().saveObject(transacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Transacao transacao) throws PersistentException {
		try {
			AASICPersistentManager.instance().deleteObject(transacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(Transacao transacao)throws PersistentException {
		if (transacao instanceof Fixa) {
			return FixaDAO.deleteAndDissociate((Fixa) transacao);
		}
		
		if (transacao instanceof Unica) {
			return UnicaDAO.deleteAndDissociate((Unica) transacao);
		}
		
		try {
			UserTransacao[] lUserTransacaos = transacao.userTransacao.toArray();
			for(int i = 0; i < lUserTransacaos.length; i++) {
				lUserTransacaos[i].setTransacaoId_transacao(null);
			}
			return delete(transacao);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(Transacao transacao, org.orm.PersistentSession session)throws PersistentException {
		if (transacao instanceof Fixa) {
			return FixaDAO.deleteAndDissociate((Fixa) transacao, session);
		}
		
		if (transacao instanceof Unica) {
			return UnicaDAO.deleteAndDissociate((Unica) transacao, session);
		}
		
		try {
			UserTransacao[] lUserTransacaos = transacao.userTransacao.toArray();
			for(int i = 0; i < lUserTransacaos.length; i++) {
				lUserTransacaos[i].setTransacaoId_transacao(null);
			}
			try {
				session.delete(transacao);
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
	
	public static boolean refresh(Transacao transacao) throws PersistentException {
		try {
			AASICPersistentManager.instance().getSession().refresh(transacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Transacao transacao) throws PersistentException {
		try {
			AASICPersistentManager.instance().getSession().evict(transacao);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
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
