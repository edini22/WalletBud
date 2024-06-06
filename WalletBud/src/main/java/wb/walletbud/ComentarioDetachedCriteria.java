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
	public final StringExpression descrição;
	
	public ComentarioDetachedCriteria() {
		super(wb.walletbud.Comentario.class, wb.walletbud.ComentarioCriteria.class);
		id_comentario = new IntegerExpression("id_comentario", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public ComentarioDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.ComentarioCriteria.class);
		id_comentario = new IntegerExpression("id_comentario", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
	}
	
	public Comentario uniqueComentario(PersistentSession session) {
		return (Comentario) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Comentario[] listComentario(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Comentario[]) list.toArray(new Comentario[list.size()]);
	}
}

