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

public class ComentarioDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_comentario;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final IntegerExpression transacaoId_transacaoId;
	public final AssociationExpression transacaoId_transacao;
	public final StringExpression descrição;
	
	public ComentarioDetachedCriteria() {
		super(wb.walletbud.Comentario.class, wb.walletbud.ComentarioCriteria.class);
		id_comentario = new IntegerExpression("id_comentario", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this.getDetachedCriteria());
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public ComentarioDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.ComentarioCriteria.class);
		id_comentario = new IntegerExpression("id_comentario", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		transacaoId_transacaoId = new IntegerExpression("transacaoId_transacao.id_transacao", this.getDetachedCriteria());
		transacaoId_transacao = new AssociationExpression("transacaoId_transacao", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public wb.walletbud.UserDetachedCriteria createUserId_userCriteria() {
		return new wb.walletbud.UserDetachedCriteria(createCriteria("userId_user"));
	}
	
	public wb.walletbud.TransacaoDetachedCriteria createTransacaoId_transacaoCriteria() {
		return new wb.walletbud.TransacaoDetachedCriteria(createCriteria("transacaoId_transacao"));
	}
	
	public Comentario uniqueComentario(PersistentSession session) {
		return (Comentario) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Comentario[] listComentario(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Comentario[]) list.toArray(new Comentario[list.size()]);
	}
}

