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

public class CategoriaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_categoria;
	public final IntegerExpression userId_userId;
	public final AssociationExpression userId_user;
	public final StringExpression name;
	public final StringExpression tipo;
	public final CollectionExpression transacao;
	
	public CategoriaDetachedCriteria() {
		super(wb.walletbud.Categoria.class, wb.walletbud.CategoriaCriteria.class);
		id_categoria = new IntegerExpression("id_categoria", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
		transacao = new CollectionExpression("ORM_Transacao", this.getDetachedCriteria());
	}
	
	public CategoriaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.CategoriaCriteria.class);
		id_categoria = new IntegerExpression("id_categoria", this.getDetachedCriteria());
		userId_userId = new IntegerExpression("userId_user.id_user", this.getDetachedCriteria());
		userId_user = new AssociationExpression("userId_user", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		tipo = new StringExpression("tipo", this.getDetachedCriteria());
		transacao = new CollectionExpression("ORM_Transacao", this.getDetachedCriteria());
	}
	
	public wb.walletbud.UserDetachedCriteria createUserId_userCriteria() {
		return new wb.walletbud.UserDetachedCriteria(createCriteria("userId_user"));
	}
	
	public wb.walletbud.TransacaoDetachedCriteria createTransacaoCriteria() {
		return new wb.walletbud.TransacaoDetachedCriteria(createCriteria("ORM_Transacao"));
	}
	
	public Categoria uniqueCategoria(PersistentSession session) {
		return (Categoria) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Categoria[] listCategoria(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Categoria[]) list.toArray(new Categoria[list.size()]);
	}
}

