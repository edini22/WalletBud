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

public class ComentarioCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_comentario;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final IntegerExpression transacaoId_transacaoId;
	public final AssociationExpression transacaoId_transacao;
	public final StringExpression descrição;
	public final TimestampExpression data;
	
	public ComentarioCriteria(Criteria criteria) {
		super(criteria);
		id_comentario = new IntegerExpression("id_comentario", this);
		userId_userId = new IntegerExpression("userId_user.id_user", this);
		userId_user = new AssociationExpression("userId_user", this);
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this);
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this);
		descrição = new StringExpression("descrição", this);
		data = new TimestampExpression("data", this);
	}
	
	public ComentarioCriteria(PersistentSession session) {
		this(session.createCriteria(Comentario.class));
	}
	
	public ComentarioCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public UserCriteria createUserId_userCriteria() {
		return new UserCriteria(createCriteria("userId_user"));
	}
	
	public TransacaoCriteria createTransacaoId_transacaoCriteria() {
		return new TransacaoCriteria(createCriteria("transacaoId_transacao"));
	}
	
	public Comentario uniqueComentario() {
		return (Comentario) super.uniqueResult();
	}
	
	public Comentario[] listComentario() {
		java.util.List list = super.list();
		return (Comentario[]) list.toArray(new Comentario[list.size()]);
	}
}

