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
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_NOTIFICACAO_USERID_USER) {
			this.userId_user = (wb.walletbud.User) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id_notificacao;
	
	private wb.walletbud.User userId_user;
	
	private java.util.Date date;
	
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
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
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
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_notificacao());
	}
	
}
