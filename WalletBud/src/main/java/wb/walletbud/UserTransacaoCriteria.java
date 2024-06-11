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

public class UserTransacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	
	public UserTransacaoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
	}
	
	public UserTransacaoCriteria(PersistentSession session) {
		this(session.createCriteria(UserTransacao.class));
	}
	
	public UserTransacaoCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public UserTransacao uniqueUserTransacao() {
		return (UserTransacao) super.uniqueResult();
	}
	
	public UserTransacao[] listUserTransacao() {
		java.util.List list = super.list();
		return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
	}
}

