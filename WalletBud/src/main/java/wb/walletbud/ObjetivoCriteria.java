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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ObjetivoCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_objetivo;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final FloatExpression value;
	public final StringExpression name;
	public final StringExpression descrição;
	
	public ObjetivoCriteria(Criteria criteria) {
		super(criteria);
		id_objetivo = new IntegerExpression("id_objetivo", this);
		userId_userId = new IntegerExpression("userId_user.id_user", this);
		userId_user = new AssociationExpression("userId_user", this);
		value = new FloatExpression("value", this);
		name = new StringExpression("name", this);
		descrição = new StringExpression("descrição", this);
	}
	
	public ObjetivoCriteria(PersistentSession session) {
		this(session.createCriteria(Objetivo.class));
	}
	
	public ObjetivoCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public UserCriteria createUserId_userCriteria() {
		return new UserCriteria(createCriteria("userId_user"));
	}
	
	public Objetivo uniqueObjetivo() {
		return (Objetivo) super.uniqueResult();
	}
	
	public Objetivo[] listObjetivo() {
		java.util.List list = super.list();
		return (Objetivo[]) list.toArray(new Objetivo[list.size()]);
	}
}

