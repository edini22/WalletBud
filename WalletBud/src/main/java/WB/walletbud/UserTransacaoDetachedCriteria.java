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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class UserTransacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	
	public UserTransacaoDetachedCriteria() {
		super(wb.walletbud.UserTransacao.class, wb.walletbud.UserTransacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
	}
	
	public UserTransacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.UserTransacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
	}
	
	public UserTransacao uniqueUserTransacao(PersistentSession session) {
		return (UserTransacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public UserTransacao[] listUserTransacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
	}
}

