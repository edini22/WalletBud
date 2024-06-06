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

public class UnicaCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_transacao;
	public final StringExpression name;
	public final FloatExpression value;
	public final DateExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	
	public UnicaCriteria(Criteria criteria) {
		super(criteria);
		id_transacao = new IntegerExpression("id_transacao", this);
		name = new StringExpression("name", this);
		value = new FloatExpression("value", this);
		date = new DateExpression("date", this);
		descrição = new StringExpression("descrição", this);
		local = new StringExpression("local", this);
		tipo = new StringExpression("tipo", this);
	}
	
	public UnicaCriteria(PersistentSession session) {
		this(session.createCriteria(Unica.class));
	}
	
	public UnicaCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public Unica uniqueUnica() {
		return (Unica) super.uniqueResult();
	}
	
	public Unica[] listUnica() {
		java.util.List list = super.list();
		return (Unica[]) list.toArray(new Unica[list.size()]);
	}
}

