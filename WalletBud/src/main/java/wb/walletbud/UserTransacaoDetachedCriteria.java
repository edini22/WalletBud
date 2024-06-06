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
	public final IntegerExpression usertransacaoIdId;
	public final AssociationExpression usertransacaoId;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	
	public UserTransacaoDetachedCriteria() {
		super(wb.walletbud.UserTransacao.class, wb.walletbud.UserTransacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		usertransacaoIdId = new IntegerExpression("usertransacaoId.id_transacao", this.getDetachedCriteria());
		usertransacaoId = new AssociationExpression("usertransacaoId", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
	}
	
	public UserTransacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.UserTransacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		usertransacaoIdId = new IntegerExpression("usertransacaoId.id_transacao", this.getDetachedCriteria());
		usertransacaoId = new AssociationExpression("usertransacaoId", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
	}
	
	public wb.walletbud.TransacaoDetachedCriteria createUsertransacaoIdCriteria() {
		return new wb.walletbud.TransacaoDetachedCriteria(createCriteria("usertransacaoId"));
	}
	
	public wb.walletbud.UserDetachedCriteria createUserId_userCriteria() {
		return new wb.walletbud.UserDetachedCriteria(createCriteria("userId_user"));
	}
	
	public UserTransacao uniqueUserTransacao(PersistentSession session) {
		return (UserTransacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public UserTransacao[] listUserTransacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
	}
}

