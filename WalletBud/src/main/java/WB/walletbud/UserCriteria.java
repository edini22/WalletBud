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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class UserCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_user;
	public final StringExpression name;
	public final StringExpression password;
	public final StringExpression email;
	public final FloatExpression saldo;
	public final StringExpression idioma;
	public final CollectionExpression userTransacao;
	
	public UserCriteria(Criteria criteria) {
		super(criteria);
		id_user = new IntegerExpression("id_user", this);
		name = new StringExpression("name", this);
		password = new StringExpression("password", this);
		email = new StringExpression("email", this);
		saldo = new FloatExpression("saldo", this);
		idioma = new StringExpression("idioma", this);
		userTransacao = new CollectionExpression("ORM_UserTransacao", this);
	}
	
	public UserCriteria(PersistentSession session) {
		this(session.createCriteria(User.class));
	}
	
	public UserCriteria() throws PersistentException {
		this(AASICPersistentManager.instance().getSession());
	}
	
	public UserTransacaoCriteria createUserTransacaoCriteria() {
		return new UserTransacaoCriteria(createCriteria("ORM_UserTransacao"));
	}
	
	public User uniqueUser() {
		return (User) super.uniqueResult();
	}
	
	public User[] listUser() {
		java.util.List list = super.list();
		return (User[]) list.toArray(new User[list.size()]);
	}
}

