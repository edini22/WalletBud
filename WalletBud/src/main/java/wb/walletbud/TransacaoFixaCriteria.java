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

public class TransacaoFixaCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression transacaofixa_IDId;
	public final AssociationExpression transacaofixa_ID;
	public final TimestampExpression dataPagamento;
	public final TimestampExpression dataAtual;
	public final CollectionExpression userId_user;
	
	public TransacaoFixaCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		transacaofixa_IDId = new IntegerExpression("transacaofixa_ID.id_transacao", this);
		transacaofixa_ID = new AssociationExpression("transacaofixa_ID", this);
		dataPagamento = new TimestampExpression("dataPagamento", this);
		dataAtual = new TimestampExpression("dataAtual", this);
		userId_user = new CollectionExpression("ORM_UserId_user", this);
	}
	
	public TransacaoFixaCriteria(PersistentSession session) {
		this(session.createCriteria(TransacaoFixa.class));
	}
	
	public TransacaoFixaCriteria() throws PersistentException {
		this(wb.walletbud.AASICPersistentManager.instance().getSession());
	}
	
	public FixaCriteria createTransacaofixa_IDCriteria() {
		return new FixaCriteria(createCriteria("transacaofixa_ID"));
	}
	
	public wb.walletbud.UserCriteria createUserId_userCriteria() {
		return new wb.walletbud.UserCriteria(createCriteria("ORM_UserId_user"));
	}
	
	public TransacaoFixa uniqueTransacaoFixa() {
		return (TransacaoFixa) super.uniqueResult();
	}
	
	public TransacaoFixa[] listTransacaoFixa() {
		java.util.List list = super.list();
		return (TransacaoFixa[]) list.toArray(new TransacaoFixa[list.size()]);
	}
}

