/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package WB.walletbud;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class TransacaoCriteria extends AbstractORMCriteria {
	public final IntegerExpression id_transacao;
	public final StringExpression name;
	public final FloatExpression value;
	public final DateExpression date;
	public final StringExpression descrição;
	public final StringExpression local;
	public final StringExpression tipo;
	public final CollectionExpression userTransacao;
	
	public TransacaoCriteria(Criteria criteria) {
		super(criteria);
		id_transacao = new IntegerExpression("id_transacao", this);
		name = new StringExpression("name", this);
		value = new FloatExpression("value", this);
		date = new DateExpression("date", this);
		descrição = new StringExpression("descrição", this);
		local = new StringExpression("local", this);
		tipo = new StringExpression("tipo", this);
		userTransacao = new CollectionExpression("ORM_UserTransacao", this);
	}
	
	public TransacaoCriteria(PersistentSession session) {
		this(session.createCriteria(Transacao.class));
	}
	
	public TransacaoCriteria() throws PersistentException {
		this(AASICPersistentManager.instance().getSession());
	}
	
	public UserTransacaoCriteria createUserTransacaoCriteria() {
		return new UserTransacaoCriteria(createCriteria("ORM_UserTransacao"));
	}
	
	public Transacao uniqueTransacao() {
		return (Transacao) super.uniqueResult();
	}
	
	public Transacao[] listTransacao() {
		java.util.List list = super.list();
		return (Transacao[]) list.toArray(new Transacao[list.size()]);
	}
}

