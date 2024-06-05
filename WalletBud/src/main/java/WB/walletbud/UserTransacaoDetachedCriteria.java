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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class UserTransacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final IntegerExpression transacaoId_transacaoId;
	public final AssociationExpression transacaoId_transacao;
	
	public UserTransacaoDetachedCriteria() {
		super(UserTransacao.class, UserTransacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this.getDetachedCriteria());
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this.getDetachedCriteria());
	}
	
	public UserTransacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, UserTransacaoCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this.getDetachedCriteria());
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this.getDetachedCriteria());
	}
	
	public UserDetachedCriteria createUserId_userCriteria() {
		return new UserDetachedCriteria(createCriteria("userId_user"));
	}
	
	public TransacaoDetachedCriteria createTransacaoId_transacaoCriteria() {
		return new TransacaoDetachedCriteria(createCriteria("transacaoId_transacao"));
	}
	
	public UserTransacao uniqueUserTransacao(PersistentSession session) {
		return (UserTransacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public UserTransacao[] listUserTransacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
	}
}

