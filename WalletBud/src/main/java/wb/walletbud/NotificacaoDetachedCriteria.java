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

public class NotificacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_notificacao;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final TimestampExpression date;
	public final StringExpression descrição;
	
	public NotificacaoDetachedCriteria() {
		super(wb.walletbud.Notificacao.class, wb.walletbud.NotificacaoCriteria.class);
		id_notificacao = new IntegerExpression("id_notificacao", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		date = new TimestampExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public NotificacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.NotificacaoCriteria.class);
		id_notificacao = new IntegerExpression("id_notificacao", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		date = new TimestampExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public wb.walletbud.UserDetachedCriteria createUserId_userCriteria() {
		return new wb.walletbud.UserDetachedCriteria(createCriteria("userId_user"));
	}
	
	public Notificacao uniqueNotificacao(PersistentSession session) {
		return (Notificacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Notificacao[] listNotificacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Notificacao[]) list.toArray(new Notificacao[list.size()]);
	}
}

