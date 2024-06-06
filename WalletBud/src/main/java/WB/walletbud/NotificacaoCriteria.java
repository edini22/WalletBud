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
	public final DateExpression date;
	public final StringExpression descrição;
	
	public NotificacaoCriteria(Criteria criteria) {
		super(criteria);
		id_notificacao = new IntegerExpression("id_notificacao", this);
		date = new DateExpression("date", this);
		descrição = new StringExpression("descrição", this);
	}
	
	public NotificacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Notificacao.class));
	}
	
	public NotificacaoCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public Notificacao uniqueNotificacao() {
		return (Notificacao) super.uniqueResult();
	}
	
	public Notificacao[] listNotificacao() {
		java.util.List list = super.list();
		return (Notificacao[]) list.toArray(new Notificacao[list.size()]);
	}
}

