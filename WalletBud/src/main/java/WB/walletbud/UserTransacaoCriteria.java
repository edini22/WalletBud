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

public class UserTransacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final IntegerExpression transacaoId_transacaoId;
	public final AssociationExpression transacaoId_transacao;
	
	public UserTransacaoCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		userId_userId = new IntegerExpression("userId_user.id_user", this);
		userId_user = new AssociationExpression("userId_user", this);
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this);
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this);
	}
	
	public UserTransacaoCriteria(PersistentSession session) {
		this(session.createCriteria(UserTransacao.class));
	}
	
	public UserTransacaoCriteria() throws PersistentException {
		this(AASICPersistentManager.instance().getSession());
	}
	
	public UserCriteria createUserId_userCriteria() {
		return new UserCriteria(createCriteria("userId_user"));
	}
	
	public TransacaoCriteria createTransacaoId_transacaoCriteria() {
		return new TransacaoCriteria(createCriteria("transacaoId_transacao"));
	}
	
	public UserTransacao uniqueUserTransacao() {
		return (UserTransacao) super.uniqueResult();
	}
	
	public UserTransacao[] listUserTransacao() {
		java.util.List list = super.list();
		return (UserTransacao[]) list.toArray(new UserTransacao[list.size()]);
	}
}

