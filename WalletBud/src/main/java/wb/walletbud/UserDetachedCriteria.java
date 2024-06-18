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

public class UserDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id_user;
	public final StringExpression name;
	public final StringExpression password;
	public final StringExpression email;
	public final FloatExpression saldo;
	public final StringExpression idioma;
	public final FloatExpression objetivo;
	public final StringExpression token;
	public final TimestampExpression expToken;
	public final StringExpression moeda;
	public final CollectionExpression transacaos;
	public final CollectionExpression notificacao;
	public final CollectionExpression comentario;
	public final CollectionExpression categoria;
	public final CollectionExpression user_categoria;
	public final CollectionExpression transacaoFixa;
	
	public UserDetachedCriteria() {
		super(wb.walletbud.User.class, wb.walletbud.UserCriteria.class);
		id_user = new IntegerExpression("id_user", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		saldo = new FloatExpression("saldo", this.getDetachedCriteria());
		idioma = new StringExpression("idioma", this.getDetachedCriteria());
		objetivo = new FloatExpression("objetivo", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
		expToken = new TimestampExpression("expToken", this.getDetachedCriteria());
		moeda = new StringExpression("moeda", this.getDetachedCriteria());
		transacaos = new CollectionExpression("ORM_Transacaos", this.getDetachedCriteria());
		notificacao = new CollectionExpression("ORM_Notificacao", this.getDetachedCriteria());
		comentario = new CollectionExpression("ORM_Comentario", this.getDetachedCriteria());
		categoria = new CollectionExpression("ORM_Categoria", this.getDetachedCriteria());
		user_categoria = new CollectionExpression("ORM_User_categoria", this.getDetachedCriteria());
		transacaoFixa = new CollectionExpression("ORM_TransacaoFixa", this.getDetachedCriteria());
	}
	
	public UserDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, wb.walletbud.UserCriteria.class);
		id_user = new IntegerExpression("id_user", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		saldo = new FloatExpression("saldo", this.getDetachedCriteria());
		idioma = new StringExpression("idioma", this.getDetachedCriteria());
		objetivo = new FloatExpression("objetivo", this.getDetachedCriteria());
		token = new StringExpression("token", this.getDetachedCriteria());
		expToken = new TimestampExpression("expToken", this.getDetachedCriteria());
		moeda = new StringExpression("moeda", this.getDetachedCriteria());
		transacaos = new CollectionExpression("ORM_Transacaos", this.getDetachedCriteria());
		notificacao = new CollectionExpression("ORM_Notificacao", this.getDetachedCriteria());
		comentario = new CollectionExpression("ORM_Comentario", this.getDetachedCriteria());
		categoria = new CollectionExpression("ORM_Categoria", this.getDetachedCriteria());
		user_categoria = new CollectionExpression("ORM_User_categoria", this.getDetachedCriteria());
		transacaoFixa = new CollectionExpression("ORM_TransacaoFixa", this.getDetachedCriteria());
	}
	
	public wb.walletbud.TransacaoDetachedCriteria createTransacaosCriteria() {
		return new wb.walletbud.TransacaoDetachedCriteria(createCriteria("ORM_Transacaos"));
	}
	
	public wb.walletbud.NotificacaoDetachedCriteria createNotificacaoCriteria() {
		return new wb.walletbud.NotificacaoDetachedCriteria(createCriteria("ORM_Notificacao"));
	}
	
	public wb.walletbud.ComentarioDetachedCriteria createComentarioCriteria() {
		return new wb.walletbud.ComentarioDetachedCriteria(createCriteria("ORM_Comentario"));
	}
	
	public wb.walletbud.CategoriaDetachedCriteria createCategoriaCriteria() {
		return new wb.walletbud.CategoriaDetachedCriteria(createCriteria("ORM_Categoria"));
	}
	
	public wb.walletbud.TransacaoPartilhadaDetachedCriteria createUser_categoriaCriteria() {
		return new wb.walletbud.TransacaoPartilhadaDetachedCriteria(createCriteria("ORM_User_categoria"));
	}
	
	public wb.walletbud.TransacaoFixaDetachedCriteria createTransacaoFixaCriteria() {
		return new wb.walletbud.TransacaoFixaDetachedCriteria(createCriteria("ORM_TransacaoFixa"));
	}
	
	public User uniqueUser(PersistentSession session) {
		return (User) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public User[] listUser(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (User[]) list.toArray(new User[list.size()]);
	}
}

