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

public class TransacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_transacao;
	public final IntegerExpression owner_idId;
	public final AssociationExpression owner_id;
	public final IntegerExpression categoriaId_categoriaId;
	public final AssociationExpression categoriaId_categoria;
	public final StringExpression name;
	public final FloatExpression value;
	public final FloatExpression shareValue;
	public final TimestampExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	public final BooleanExpression status;
	public final CollectionExpression comentario;
	public final CollectionExpression transacao;
	public final CollectionExpression notificacao;
	
	public TransacaoCriteria(Criteria criteria) {
		super(criteria);
		id_transacao = new IntegerExpression("id_transacao", this);
		owner_idId = new IntegerExpression("owner_id.id_user", this);
		owner_id = new AssociationExpression("owner_id", this);
		categoriaId_categoriaId = new IntegerExpression("categoriaId_categoria.id_categoria", this);
		categoriaId_categoria = new AssociationExpression("categoriaId_categoria", this);
		name = new StringExpression("name", this);
		value = new FloatExpression("value", this);
		shareValue = new FloatExpression("shareValue", this);
		date = new TimestampExpression("date", this);
		descrição = new StringExpression("descrição", this);
		local = new StringExpression("local", this);
		tipo = new StringExpression("tipo", this);
		status = new BooleanExpression("status", this);
		comentario = new CollectionExpression("ORM_Comentario", this);
		transacao = new CollectionExpression("ORM_Transacao", this);
		notificacao = new CollectionExpression("ORM_Notificacao", this);
	}
	
	public TransacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Transacao.class));
	}
	
	public TransacaoCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public UserCriteria createOwner_idCriteria() {
		return new UserCriteria(createCriteria("owner_id"));
	}
	
	public CategoriaCriteria createCategoriaId_categoriaCriteria() {
		return new CategoriaCriteria(createCriteria("categoriaId_categoria"));
	}
	
	public wb.walletbud.ComentarioCriteria createComentarioCriteria() {
		return new wb.walletbud.ComentarioCriteria(createCriteria("ORM_Comentario"));
	}
	
	public wb.walletbud.TransacaoPartilhadaCriteria createTransacaoCriteria() {
		return new wb.walletbud.TransacaoPartilhadaCriteria(createCriteria("ORM_Transacao"));
	}
	
	public wb.walletbud.NotificacaoCriteria createNotificacaoCriteria() {
		return new wb.walletbud.NotificacaoCriteria(createCriteria("ORM_Notificacao"));
	}
	
	public Transacao uniqueTransacao() {
		return (Transacao) super.uniqueResult();
	}
	
	public Transacao[] listTransacao() {
		java.util.List list = super.list();
		return (Transacao[]) list.toArray(new Transacao[list.size()]);
	}
}

