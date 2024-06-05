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

public class NotificacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_notificacao;
	public final DateExpression date;
	public final StringExpression descrição;
	
	public NotificacaoDetachedCriteria() {
		super(Notificacao.class, NotificacaoCriteria.class);
		id_notificacao = new IntegerExpression("id_notificacao", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public NotificacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, NotificacaoCriteria.class);
		id_notificacao = new IntegerExpression("id_notificacao", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public Notificacao uniqueNotificacao(PersistentSession session) {
		return (Notificacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Notificacao[] listNotificacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Notificacao[]) list.toArray(new Notificacao[list.size()]);
	}
}

