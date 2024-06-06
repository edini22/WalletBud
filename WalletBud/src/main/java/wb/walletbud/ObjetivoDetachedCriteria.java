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

public class ObjetivoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_objetivo;
	public final FloatExpression value;
	public final StringExpression name;
	public final StringExpression descrição;
	
	public ObjetivoDetachedCriteria() {
		super(wb.walletbud.Objetivo.class, wb.walletbud.ObjetivoCriteria.class);
		id_objetivo = new IntegerExpression("id_objetivo", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public ObjetivoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.ObjetivoCriteria.class);
		id_objetivo = new IntegerExpression("id_objetivo", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public Objetivo uniqueObjetivo(PersistentSession session) {
		return (Objetivo) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Objetivo[] listObjetivo(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Objetivo[]) list.toArray(new Objetivo[list.size()]);
	}
}

