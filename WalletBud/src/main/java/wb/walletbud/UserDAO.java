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

public class UserDAO {
	public static User loadUserByORMID(int id_user) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserByORMID(session, id_user);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User getUserByORMID(int id_user) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getUserByORMID(session, id_user);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByORMID(int id_user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserByORMID(session, id_user, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User getUserByORMID(int id_user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return getUserByORMID(session, id_user, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByORMID(PersistentSession session, int id_user) throws PersistentException {
		try {
			return (User) session.load(wb.walletbud.User.class, Integer.valueOf(id_user));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User getUserByORMID(PersistentSession session, int id_user) throws PersistentException {
		try {
			return (User) session.get(wb.walletbud.User.class, Integer.valueOf(id_user));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByORMID(PersistentSession session, int id_user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (User) session.load(wb.walletbud.User.class, Integer.valueOf(id_user), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User getUserByORMID(PersistentSession session, int id_user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (User) session.get(wb.walletbud.User.class, Integer.valueOf(id_user), lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryUser(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return queryUser(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User[] listUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User[] listUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return listUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.User as User");
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
	
	public static List queryUser(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.User as User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User", lockMode);
			return query.list();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User[] listUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUser(session, condition, orderBy);
			return (User[]) list.toArray(new User[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User[] listUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUser(session, condition, orderBy, lockMode);
			return (User[]) list.toArray(new User[list.size()]);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return loadUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		User[] users = listUserByQuery(session, condition, orderBy);
		if (users != null && users.length > 0)
			return users[0];
		else
			return null;
	}
	
	public static User loadUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		User[] users = listUserByQuery(session, condition, orderBy, lockMode);
		if (users != null && users.length > 0)
			return users[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = wb.walletbud.AASICPersistentManager.instance().getSession();
			return iterateUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.User as User");
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
	
	public static java.util.Iterator iterateUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From wb.walletbud.User as User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User createUser() {
		return new wb.walletbud.User();
	}
	
	public static boolean save(wb.walletbud.User user) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().saveObject(user);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(wb.walletbud.User user) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().deleteObject(user);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.User user)throws PersistentException {
		try {
			wb.walletbud.Transacao[] lTransacaoss = user.transacaos.toArray();
			for(int i = 0; i < lTransacaoss.length; i++) {
				lTransacaoss[i].setOwner_id(null);
			}
			wb.walletbud.Notificacao[] lNotificacaos = user.notificacao.toArray();
			for(int i = 0; i < lNotificacaos.length; i++) {
				lNotificacaos[i].setUserId_user(null);
			}
			wb.walletbud.Objetivo[] lObjetivos = user.objetivo.toArray();
			for(int i = 0; i < lObjetivos.length; i++) {
				lObjetivos[i].setUserId_user(null);
			}
			wb.walletbud.Comentario[] lComentarios = user.comentario.toArray();
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setUserId_user(null);
			}
			wb.walletbud.Categoria[] lCategorias = user.categoria.toArray();
			for(int i = 0; i < lCategorias.length; i++) {
				lCategorias[i].setUserId_user(null);
			}
			wb.walletbud.TransacaoPartilhada[] lUser_categorias = user.user_categoria.toArray();
			for(int i = 0; i < lUser_categorias.length; i++) {
				lUser_categorias[i].setUserId_user(null);
			}
			wb.walletbud.TransacaoFixa[] lTransacaoFixas = user.transacaoFixa.toArray();
			for(int i = 0; i < lTransacaoFixas.length; i++) {
				lTransacaoFixas[i].userId_user.remove(user);
			}
			return delete(user);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(wb.walletbud.User user, org.orm.PersistentSession session)throws PersistentException {
		try {
			wb.walletbud.Transacao[] lTransacaoss = user.transacaos.toArray();
			for(int i = 0; i < lTransacaoss.length; i++) {
				lTransacaoss[i].setOwner_id(null);
			}
			wb.walletbud.Notificacao[] lNotificacaos = user.notificacao.toArray();
			for(int i = 0; i < lNotificacaos.length; i++) {
				lNotificacaos[i].setUserId_user(null);
			}
			wb.walletbud.Objetivo[] lObjetivos = user.objetivo.toArray();
			for(int i = 0; i < lObjetivos.length; i++) {
				lObjetivos[i].setUserId_user(null);
			}
			wb.walletbud.Comentario[] lComentarios = user.comentario.toArray();
			for(int i = 0; i < lComentarios.length; i++) {
				lComentarios[i].setUserId_user(null);
			}
			wb.walletbud.Categoria[] lCategorias = user.categoria.toArray();
			for(int i = 0; i < lCategorias.length; i++) {
				lCategorias[i].setUserId_user(null);
			}
			wb.walletbud.TransacaoPartilhada[] lUser_categorias = user.user_categoria.toArray();
			for(int i = 0; i < lUser_categorias.length; i++) {
				lUser_categorias[i].setUserId_user(null);
			}
			wb.walletbud.TransacaoFixa[] lTransacaoFixas = user.transacaoFixa.toArray();
			for(int i = 0; i < lTransacaoFixas.length; i++) {
				lTransacaoFixas[i].userId_user.remove(user);
			}
			try {
				session.delete(user);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(wb.walletbud.User user) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().refresh(user);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(wb.walletbud.User user) throws PersistentException {
		try {
			wb.walletbud.AASICPersistentManager.instance().getSession().evict(user);
			return true;
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static User loadUserByCriteria(UserCriteria userCriteria) {
		User[] users = listUserByCriteria(userCriteria);
		if(users == null || users.length == 0) {
			return null;
		}
		return users[0];
	}
	
	public static User[] listUserByCriteria(UserCriteria userCriteria) {
		return userCriteria.listUser();
	}
}
