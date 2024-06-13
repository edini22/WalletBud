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

public class NotificacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_notificacao;
	public final IntegerExpression transacaoId_transacaoId;
	public final AssociationExpression transacaoId_transacao;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final TimestampExpression date;
	public final StringExpression descrição;
	
	public NotificacaoCriteria(Criteria criteria) {
		super(criteria);
		id_notificacao = new IntegerExpression("id_notificacao", this);
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this);
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this);
		userId_userId = new IntegerExpression("userId_user.id_user", this);
		userId_user = new AssociationExpression("userId_user", this);
		date = new TimestampExpression("date", this);
		descrição = new StringExpression("descrição", this);
	}
	
	public NotificacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Notificacao.class));
	}
	
	public NotificacaoCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public TransacaoCriteria createTransacaoId_transacaoCriteria() {
		return new TransacaoCriteria(createCriteria("transacaoId_transacao"));
	}
	
	public UserCriteria createUserId_userCriteria() {
		return new UserCriteria(createCriteria("userId_user"));
	}
	
	public Notificacao uniqueNotificacao() {
		return (Notificacao) super.uniqueResult();
	}
	
	public Notificacao[] listNotificacao() {
		java.util.List list = super.list();
		return (Notificacao[]) list.toArray(new Notificacao[list.size()]);
	}
}

