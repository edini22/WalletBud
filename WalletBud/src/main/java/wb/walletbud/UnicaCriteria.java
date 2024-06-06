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
	
	public UnicaCriteria(Criteria criteria) {
		super(criteria);
		id_transacao = new IntegerExpression("id_transacao", this);
		categoriaId_categoriaId = new IntegerExpression("categoriaId_categoria.id_categoria", this);
		categoriaId_categoria = new AssociationExpression("categoriaId_categoria", this);
		name = new StringExpression("name", this);
		value = new FloatExpression("value", this);
		date = new DateExpression("date", this);
		descrição = new StringExpression("descrição", this);
		local = new StringExpression("local", this);
		tipo = new StringExpression("tipo", this);
		comentario = new CollectionExpression("ORM_Comentario", this);
		transacao = new CollectionExpression("ORM_Transacao", this);
	}
	
	public UnicaCriteria(PersistentSession session) {
		this(session.createCriteria(Unica.class));
	}
	
	public UnicaCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public CategoriaCriteria createCategoriaId_categoriaCriteria() {
		return new CategoriaCriteria(createCriteria("categoriaId_categoria"));
	}
	
	public wb.walletbud.ComentarioCriteria createComentarioCriteria() {
		return new wb.walletbud.ComentarioCriteria(createCriteria("ORM_Comentario"));
	}
	
	public wb.walletbud.UserTransacaoCriteria createTransacaoCriteria() {
		return new wb.walletbud.UserTransacaoCriteria(createCriteria("ORM_Transacao"));
	}
	
	public Unica uniqueUnica() {
		return (Unica) super.uniqueResult();
	}
	
	public Unica[] listUnica() {
		java.util.List list = super.list();
		return (Unica[]) list.toArray(new Unica[list.size()]);
	}
}

