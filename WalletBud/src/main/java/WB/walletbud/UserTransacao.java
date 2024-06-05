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

public class UserTransacao {
	public UserTransacao() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_USERTRANSACAO_USERID_USER) {
			this.userId_user = (User) owner;
		}
		
		else if (key == ORMConstants.KEY_USERTRANSACAO_TRANSACAOID_TRANSACAO) {
			this.transacaoId_transacao = (Transacao) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private User userId_user;
	
	private Transacao transacaoId_transacao;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setUserId_user(User value) {
		if (userId_user != null) {
			userId_user.userTransacao.remove(this);
		}
		if (value != null) {
			value.userTransacao.add(this);
		}
	}
	
	public User getUserId_user() {
		return userId_user;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_UserId_user(User value) {
		this.userId_user = value;
	}
	
	private User getORM_UserId_user() {
		return userId_user;
	}
	
	public void setTransacaoId_transacao(Transacao value) {
		if (transacaoId_transacao != null) {
			transacaoId_transacao.userTransacao.remove(this);
		}
		if (value != null) {
			value.userTransacao.add(this);
		}
	}
	
	public Transacao getTransacaoId_transacao() {
		return transacaoId_transacao;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_TransacaoId_transacao(Transacao value) {
		this.transacaoId_transacao = value;
	}
	
	private Transacao getORM_TransacaoId_transacao() {
		return transacaoId_transacao;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
