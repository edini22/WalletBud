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

public class TransacaoFixa {
	public TransacaoFixa() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == wb.walletbud.ORMConstants.KEY_TRANSACAOFIXA_USERID_USER) {
			return ORM_userId_user;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_TRANSACAOFIXA_TRANSACAOFIXA_ID) {
			this.transacaofixa_ID = (wb.walletbud.Fixa) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private wb.walletbud.Fixa transacaofixa_ID;
	
	private java.sql.Timestamp dataPagamento;
	
	private java.sql.Timestamp dataAtual;
	
	private java.util.Set ORM_userId_user = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setDataPagamento(java.sql.Timestamp value) {
		this.dataPagamento = value;
	}
	
	public java.sql.Timestamp getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataAtual(java.sql.Timestamp value) {
		this.dataAtual = value;
	}
	
	public java.sql.Timestamp getDataAtual() {
		return dataAtual;
	}
	
	private void setORM_UserId_user(java.util.Set value) {
		this.ORM_userId_user = value;
	}
	
	private java.util.Set getORM_UserId_user() {
		return ORM_userId_user;
	}
	
	public final wb.walletbud.UserSetCollection userId_user = new wb.walletbud.UserSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_TRANSACAOFIXA_USERID_USER, wb.walletbud.ORMConstants.KEY_USER_TRANSACAOFIXA, wb.walletbud.ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public void setTransacaofixa_ID(wb.walletbud.Fixa value) {
		if (transacaofixa_ID != null) {
			transacaofixa_ID.fixa.remove(this);
		}
		if (value != null) {
			value.fixa.add(this);
		}
	}
	
	public wb.walletbud.Fixa getTransacaofixa_ID() {
		return transacaofixa_ID;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Transacaofixa_ID(wb.walletbud.Fixa value) {
		this.transacaofixa_ID = value;
	}
	
	private wb.walletbud.Fixa getORM_Transacaofixa_ID() {
		return transacaofixa_ID;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
