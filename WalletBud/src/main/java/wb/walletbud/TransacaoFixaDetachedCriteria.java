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

public class TransacaoFixaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression transacaofixa_IDId;
	public final AssociationExpression transacaofixa_ID;
	public final DateExpression dataPagamento;
	public final DateExpression dataAtual;
	
	public TransacaoFixaDetachedCriteria() {
		super(wb.walletbud.TransacaoFixa.class, wb.walletbud.TransacaoFixaCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		transacaofixa_IDId = new IntegerExpression("transacaofixa_ID.id_transacao", this.getDetachedCriteria());
		transacaofixa_ID = new AssociationExpression("transacaofixa_ID", this.getDetachedCriteria());
		dataPagamento = new DateExpression("dataPagamento", this.getDetachedCriteria());
		dataAtual = new DateExpression("dataAtual", this.getDetachedCriteria());
	}
	
	public TransacaoFixaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.TransacaoFixaCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		transacaofixa_IDId = new IntegerExpression("transacaofixa_ID.id_transacao", this.getDetachedCriteria());
		transacaofixa_ID = new AssociationExpression("transacaofixa_ID", this.getDetachedCriteria());
		dataPagamento = new DateExpression("dataPagamento", this.getDetachedCriteria());
		dataAtual = new DateExpression("dataAtual", this.getDetachedCriteria());
	}
	
	public wb.walletbud.FixaDetachedCriteria createTransacaofixa_IDCriteria() {
		return new wb.walletbud.FixaDetachedCriteria(createCriteria("transacaofixa_ID"));
	}
	
	public TransacaoFixa uniqueTransacaoFixa(PersistentSession session) {
		return (TransacaoFixa) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public TransacaoFixa[] listTransacaoFixa(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (TransacaoFixa[]) list.toArray(new TransacaoFixa[list.size()]);
	}
}

