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

public class TransacaoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_transacao;
	public final StringExpression name;
	public final FloatExpression value;
	public final DateExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	
	public TransacaoDetachedCriteria() {
		super(wb.walletbud.Transacao.class, wb.walletbud.TransacaoCriteria.class);
		id_transacao = new IntegerExpression("id_transacao", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
		local = new StringExpression("local", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
	}
	
	public TransacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.TransacaoCriteria.class);
		id_transacao = new IntegerExpression("id_transacao", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
		local = new StringExpression("local", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
	}
	
	public Transacao uniqueTransacao(PersistentSession session) {
		return (Transacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Transacao[] listTransacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Transacao[]) list.toArray(new Transacao[list.size()]);
	}
}

