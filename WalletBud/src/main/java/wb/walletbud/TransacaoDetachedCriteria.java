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
	public final IntegerExpression categoriaId_categoriaId;
	public final AssociationExpression categoriaId_categoria;
	public final StringExpression name;
	public final FloatExpression value;
	public final DateExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	public final CollectionExpression comentario;
	public final CollectionExpression transacao;
	
	public TransacaoDetachedCriteria() {
		super(wb.walletbud.Transacao.class, wb.walletbud.TransacaoCriteria.class);
		id_transacao = new IntegerExpression("id_transacao", this.getDetachedCriteria());
		categoriaId_categoriaId = new IntegerExpression("categoriaId_categoria.id_categoria", this.getDetachedCriteria());
		categoriaId_categoria = new AssociationExpression("categoriaId_categoria", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
		local = new StringExpression("local", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
		comentario = new CollectionExpression("ORM_Comentario", this.getDetachedCriteria());
		transacao = new CollectionExpression("ORM_Transacao", this.getDetachedCriteria());
	}
	
	public TransacaoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.TransacaoCriteria.class);
		id_transacao = new IntegerExpression("id_transacao", this.getDetachedCriteria());
		categoriaId_categoriaId = new IntegerExpression("categoriaId_categoria.id_categoria", this.getDetachedCriteria());
		categoriaId_categoria = new AssociationExpression("categoriaId_categoria", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		value = new FloatExpression("value", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		descrição = new StringExpression("descrição", this.getDetachedCriteria());
		local = new StringExpression("local", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
		comentario = new CollectionExpression("ORM_Comentario", this.getDetachedCriteria());
		transacao = new CollectionExpression("ORM_Transacao", this.getDetachedCriteria());
	}
	
	public wb.walletbud.CategoriaDetachedCriteria createCategoriaId_categoriaCriteria() {
		return new wb.walletbud.CategoriaDetachedCriteria(createCriteria("categoriaId_categoria"));
	}
	
	public wb.walletbud.ComentarioDetachedCriteria createComentarioCriteria() {
		return new wb.walletbud.ComentarioDetachedCriteria(createCriteria("ORM_Comentario"));
	}
	
	public wb.walletbud.UserTransacaoDetachedCriteria createTransacaoCriteria() {
		return new wb.walletbud.UserTransacaoDetachedCriteria(createCriteria("ORM_Transacao"));
	}
	
	public Transacao uniqueTransacao(PersistentSession session) {
		return (Transacao) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Transacao[] listTransacao(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Transacao[]) list.toArray(new Transacao[list.size()]);
	}
}

