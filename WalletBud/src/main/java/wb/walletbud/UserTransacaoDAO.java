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

public class UserTransacaoDAO {
	public static UserTransacao loadUserTransacaoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserTransacaoByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao getUserTransacaoByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getUserTransacaoByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserTransacaoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao getUserTransacaoByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getUserTransacaoByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (UserTransacao) session.load(wb.walletbud.UserTransacao.class, Integer.valueOf(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao getUserTransacaoByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (UserTransacao) session.get(wb.walletbud.UserTransacao.class, Integer.valueOf(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (UserTransacao) session.load(wb.walletbud.UserTransacao.class, Integer.valueOf(ID), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao getUserTransacaoByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (UserTransacao) session.get(wb.walletbud.UserTransacao.class, Integer.valueOf(ID), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserTransacao(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryUserTransacao(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserTransacao(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryUserTransacao(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao[] listUserTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listUserTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao[] listUserTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listUserTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUserTransacao(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.UserTransacao as UserTransacao");
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
	
	public static List queryUserTransacao(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.UserTransacao as UserTransacao");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("UserTransacao", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao[] listUserTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUserTransacao(session, condition, orderBy);
			return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao[] listUserTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUserTransacao(session, condition, orderBy, lockMode);
			return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		UserTransacao[] userTransacaos = listUserTransacaoByQuery(session, condition, orderBy);
		if (userTransacaos != null && userTransacaos.length > 0)
			return userTransacaos[0];
		else
			return null;
	}
	
	public static UserTransacao loadUserTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		UserTransacao[] userTransacaos = listUserTransacaoByQuery(session, condition, orderBy, lockMode);
		if (userTransacaos != null && userTransacaos.length > 0)
			return userTransacaos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUserTransacaoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateUserTransacaoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserTransacaoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateUserTransacaoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserTransacaoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.UserTransacao as UserTransacao");
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
	
	public static java.util.Iterator iterateUserTransacaoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.UserTransacao as UserTransacao");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("UserTransacao", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao createUserTransacao() {
		return new wb.walletbud.UserTransacao();
	}
	
	public static boolean save(wb.walletbud.UserTransacao userTransacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(userTransacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.UserTransacao userTransacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(userTransacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.UserTransacao userTransacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(userTransacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.UserTransacao userTransacao) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(userTransacao);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static UserTransacao loadUserTransacaoByCriteria(UserTransacaoCriteria userTransacaoCriteria) {
		UserTransacao[] userTransacaos = listUserTransacaoByCriteria(userTransacaoCriteria);
		if(userTransacaos == null || userTransacaos.length == 0) {
			return null;
		}
		return userTransacaos[0];
	}
	
	public static UserTransacao[] listUserTransacaoByCriteria(UserTransacaoCriteria userTransacaoCriteria) {
		return userTransacaoCriteria.listUserTransacao();
	}
}
