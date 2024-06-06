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

public class CategoriaCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_categoria;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final StringExpression name;
	public final StringExpression tipo;
	public final CollectionExpression transacao;
	
	public CategoriaCriteria(Criteria criteria) {
		super(criteria);
		id_categoria = new IntegerExpression("id_categoria", this);
		userId_userId = new IntegerExpression("userId_user.id_user", this);
		userId_user = new AssociationExpression("userId_user", this);
		name = new StringExpression("name", this);
		tipo = new StringExpression("tipo", this);
		transacao = new CollectionExpression("ORM_Transacao", this);
	}
	
	public CategoriaCriteria(PersistentSession session) {
		this(session.createCriteria(Categoria.class));
	}
	
	public CategoriaCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public UserCriteria createUserId_userCriteria() {
		return new UserCriteria(createCriteria("userId_user"));
	}
	
	public wb.walletbud.TransacaoCriteria createTransacaoCriteria() {
		return new wb.walletbud.TransacaoCriteria(createCriteria("ORM_Transacao"));
	}
	
	public Categoria uniqueCategoria() {
		return (Categoria) super.uniqueResult();
	}
	
	public Categoria[] listCategoria() {
		java.util.List list = super.list();
		return (Categoria[]) list.toArray(new Categoria[list.size()]);
	}
}

