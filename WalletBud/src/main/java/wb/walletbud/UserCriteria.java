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

public class UserCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_user;
	public final StringExpression name;
	public final StringExpression password;
	public final StringExpression email;
	public final FloatExpression saldo;
	public final StringExpression idioma;
	public final FloatExpression objetivo;
	public final StringExpression token;
	public final TimestampExpression expToken;
	public final CollectionExpression transacaos;
	public final CollectionExpression notificacao;
	public final CollectionExpression comentario;
	public final CollectionExpression categoria;
	public final CollectionExpression user_categoria;
	public final CollectionExpression transacaoFixa;
	
	public UserCriteria(Criteria criteria) {
		super(criteria);
		id_user = new IntegerExpression("id_user", this);
		name = new StringExpression("name", this);
		password = new StringExpression("password", this);
		email = new StringExpression("email", this);
		saldo = new FloatExpression("saldo", this);
		idioma = new StringExpression("idioma", this);
		objetivo = new FloatExpression("objetivo", this);
		token = new StringExpression("token", this);
		expToken = new TimestampExpression("expToken", this);
		transacaos = new CollectionExpression("ORM_Transacaos", this);
		notificacao = new CollectionExpression("ORM_Notificacao", this);
		comentario = new CollectionExpression("ORM_Comentario", this);
		categoria = new CollectionExpression("ORM_Categoria", this);
		user_categoria = new CollectionExpression("ORM_User_categoria", this);
		transacaoFixa = new CollectionExpression("ORM_TransacaoFixa", this);
	}
	
	public UserCriteria(PersistentSession session) {
		this(session.createCriteria(User.class));
	}
	
	public UserCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public wb.walletbud.TransacaoCriteria createTransacaosCriteria() {
		return new wb.walletbud.TransacaoCriteria(createCriteria("ORM_Transacaos"));
	}
	
	public wb.walletbud.NotificacaoCriteria createNotificacaoCriteria() {
		return new wb.walletbud.NotificacaoCriteria(createCriteria("ORM_Notificacao"));
	}
	
	public wb.walletbud.ComentarioCriteria createComentarioCriteria() {
		return new wb.walletbud.ComentarioCriteria(createCriteria("ORM_Comentario"));
	}
	
	public wb.walletbud.CategoriaCriteria createCategoriaCriteria() {
		return new wb.walletbud.CategoriaCriteria(createCriteria("ORM_Categoria"));
	}
	
	public wb.walletbud.TransacaoPartilhadaCriteria createUser_categoriaCriteria() {
		return new wb.walletbud.TransacaoPartilhadaCriteria(createCriteria("ORM_User_categoria"));
	}
	
	public wb.walletbud.TransacaoFixaCriteria createTransacaoFixaCriteria() {
		return new wb.walletbud.TransacaoFixaCriteria(createCriteria("ORM_TransacaoFixa"));
	}
	
	public User uniqueUser() {
		return (User) super.uniqueResult();
	}
	
	public User[] listUser() {
		java.util.List list = super.list();
		return (User[]) list.toArray(new User[list.size()]);
	}
}

