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

public class Notificacao {
	public Notificacao() {
	}

	public Notificacao(int id_notificacao, wb.walletbud.Transacao transacaoId_transacao, wb.walletbud.User userId_user, java.sql.Timestamp date, String descrição) {
		this.id_notificacao = id_notificacao;
		this.transacaoId_transacao = transacaoId_transacao;
		this.userId_user = userId_user;
		this.date = date;
		this.descrição = descrição;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_NOTIFICACAO_USERID_USER) {
			this.userId_user = (wb.walletbud.User) owner;
		}
		
		else if (key == wb.walletbud.ORMConstants.KEY_NOTIFICACAO_TRANSACAOID_TRANSACAO) {
			this.transacaoId_transacao = (wb.walletbud.Transacao) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id_notificacao;
	
	private wb.walletbud.Transacao transacaoId_transacao;
	
	private wb.walletbud.User userId_user;
	
	private java.sql.Timestamp date;
	
	private String descrição;
	
	private void setId_notificacao(int value) {
		this.id_notificacao = value;
	}
	
	public int getId_notificacao() {
		return id_notificacao;
	}
	
	public int getORMID() {
		return getId_notificacao();
	}
	
	public void setDate(java.sql.Timestamp value) {
		this.date = value;
	}
	
	public java.sql.Timestamp getDate() {
		return date;
	}
	
	public void setDescrição(String value) {
		this.descrição = value;
	}
	
	public String getDescrição() {
		return descrição;
	}
	
	public void setUserId_user(wb.walletbud.User value) {
		if (userId_user != null) {
			userId_user.notificacao.remove(this);
		}
		if (value != null) {
			value.notificacao.add(this);
		}
	}
	
	public wb.walletbud.User getUserId_user() {
		return userId_user;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_UserId_user(wb.walletbud.User value) {
		this.userId_user = value;
	}
	
	private wb.walletbud.User getORM_UserId_user() {
		return userId_user;
	}
	
	public void setTransacaoId_transacao(wb.walletbud.Transacao value) {
		if (transacaoId_transacao != null) {
			transacaoId_transacao.notificacao.remove(this);
		}
		if (value != null) {
			value.notificacao.add(this);
		}
	}
	
	public wb.walletbud.Transacao getTransacaoId_transacao() {
		return transacaoId_transacao;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_TransacaoId_transacao(wb.walletbud.Transacao value) {
		this.transacaoId_transacao = value;
	}
	
	private wb.walletbud.Transacao getORM_TransacaoId_transacao() {
		return transacaoId_transacao;
	}
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_notificacao());
	}
	
}
