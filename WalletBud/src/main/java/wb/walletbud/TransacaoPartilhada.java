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

public class TransacaoPartilhada {
	public TransacaoPartilhada() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_TRANSACAOPARTILHADA_USERID_USER) {
			this.userId_user = (wb.walletbud.User) owner;
		}
		
		else if (key == wb.walletbud.ORMConstants.KEY_TRANSACAOPARTILHADA_USERTRANSACAOID) {
			this.usertransacaoId = (wb.walletbud.Transacao) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private wb.walletbud.Transacao usertransacaoId;
	
	private wb.walletbud.User userId_user;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setUserId_user(wb.walletbud.User value) {
		if (userId_user != null) {
			userId_user.user_categoria.remove(this);
		}
		if (value != null) {
			value.user_categoria.add(this);
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
	
	public void setUsertransacaoId(wb.walletbud.Transacao value) {
		if (usertransacaoId != null) {
			usertransacaoId.transacao.remove(this);
		}
		if (value != null) {
			value.transacao.add(this);
		}
	}
	
	public wb.walletbud.Transacao getUsertransacaoId() {
		return usertransacaoId;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_UsertransacaoId(wb.walletbud.Transacao value) {
		this.usertransacaoId = value;
	}
	
	private wb.walletbud.Transacao getORM_UsertransacaoId() {
		return usertransacaoId;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
