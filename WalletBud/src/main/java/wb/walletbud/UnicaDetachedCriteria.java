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

public class UnicaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_transacao;
	public final IntegerExpression owner_idId;
	public final AssociationExpression owner_id;
	public final IntegerExpression categoriaId_categoriaId;
	public final AssociationExpression categoriaId_categoria;
	public final StringExpression name;
	public final FloatExpression value;
	public final TimestampExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	public final FloatExpression shareValue;
	public final CollectionExpression comentario;
	public final CollectionExpression transacao;
	
	public UnicaDetachedCriteria() {
		super(wb.walletbud.Unica.class, wb.walletbud.UnicaCriteria.class);
		id_transacao = new IntegerExpression("id_transacao", this.getDetachedCriteria());
		owner_idId = new IntegerExpression("owner_id.id_user", this.getDetachedCriteria());
		owner_id = new AssociationExpression("owner_id", this.getDetachedCriteria());
		categoriaId_categoriaId = new IntegerExpression("categoriaId_categoria.id_categoria", this.getDetachedCriteria());
		categoriaId_categoria = new AssociationExpression("categoriaId_categoria", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		date = new TimestampExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
		local = new StringExpression("local", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
		shareValue = new FloatExpression("shareValue", this.getDetachedCriteria());
		comentario = new CollectionExpression("ORM_Comentario", this.getDetachedCriteria());
		transacao = new CollectionExpression("ORM_Transacao", this.getDetachedCriteria());
	}
	
	public UnicaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.UnicaCriteria.class);
		id_transacao = new IntegerExpression("id_transacao", this.getDetachedCriteria());
		owner_idId = new IntegerExpression("owner_id.id_user", this.getDetachedCriteria());
		owner_id = new AssociationExpression("owner_id", this.getDetachedCriteria());
		categoriaId_categoriaId = new IntegerExpression("categoriaId_categoria.id_categoria", this.getDetachedCriteria());
		categoriaId_categoria = new AssociationExpression("categoriaId_categoria", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		date = new TimestampExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
		local = new StringExpression("local", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
		shareValue = new FloatExpression("shareValue", this.getDetachedCriteria());
		comentario = new CollectionExpression("ORM_Comentario", this.getDetachedCriteria());
		transacao = new CollectionExpression("ORM_Transacao", this.getDetachedCriteria());
	}
	
	public wb.walletbud.UserDetachedCriteria createOwner_idCriteria() {
		return new wb.walletbud.UserDetachedCriteria(createCriteria("owner_id"));
	}
	
	public wb.walletbud.CategoriaDetachedCriteria createCategoriaId_categoriaCriteria() {
		return new wb.walletbud.CategoriaDetachedCriteria(createCriteria("categoriaId_categoria"));
	}
	
	public wb.walletbud.ComentarioDetachedCriteria createComentarioCriteria() {
		return new wb.walletbud.ComentarioDetachedCriteria(createCriteria("ORM_Comentario"));
	}
	
	public wb.walletbud.TransacaoPartilhadaDetachedCriteria createTransacaoCriteria() {
		return new wb.walletbud.TransacaoPartilhadaDetachedCriteria(createCriteria("ORM_Transacao"));
	}
	
	public Unica uniqueUnica(PersistentSession session) {
		return (Unica) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Unica[] listUnica(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Unica[]) list.toArray(new Unica[list.size()]);
	}
}

