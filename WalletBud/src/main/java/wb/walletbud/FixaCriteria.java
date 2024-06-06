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

public class FixaCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_transacao;
	public final StringExpression name;
	public final FloatExpression value;
	public final DateExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	public final IntegerExpression repetição;
	public final BooleanExpression pago;
	public final DateExpression data_pagamento;
	public final IntegerExpression id_fixa;
	
	public FixaCriteria(Criteria criteria) {
		super(criteria);
		id_transacao = new IntegerExpression("id_transacao", this);
		name = new StringExpression("name", this);
		value = new FloatExpression("value", this);
		date = new DateExpression("date", this);
		descrição = new StringExpression("descrição", this);
		local = new StringExpression("local", this);
		tipo = new StringExpression("tipo", this);
		repetição = new IntegerExpression("repetição", this);
		pago = new BooleanExpression("pago", this);
		data_pagamento = new DateExpression("data_pagamento", this);
		id_fixa = new IntegerExpression("id_fixa", this);
	}
	
	public FixaCriteria(PersistentSession session) {
		this(session.createCriteria(Fixa.class));
	}
	
	public FixaCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public Fixa uniqueFixa() {
		return (Fixa) super.uniqueResult();
	}
	
	public Fixa[] listFixa() {
		java.util.List list = super.list();
		return (Fixa[]) list.toArray(new Fixa[list.size()]);
	}
}

