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

public class Comentario {
	public Comentario() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_COMENTARIO_TRANSACAOID_TRANSACAO) {
			this.transacaoId_transacao = (wb.walletbud.Transacao) owner;
		}
		
		else if (key == wb.walletbud.ORMConstants.KEY_COMENTARIO_USERID_USER) {
			this.userId_user = (wb.walletbud.User) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id_comentario;
	
	private wb.walletbud.User userId_user;
	
	private wb.walletbud.Transacao transacaoId_transacao;
	
	private String descrição;
	
	private java.sql.Timestamp data;
	
	private void setId_comentario(int value) {
		this.id_comentario = value;
	}
	
	public int getId_comentario() {
		return id_comentario;
	}
	
	public int getORMID() {
		return getId_comentario();
	}
	
	public void setDescrição(String value) {
		this.descrição = value;
	}
	
	public String getDescrição() {
		return descrição;
	}
	
	public void setData(java.sql.Timestamp value) {
		this.data = value;
	}
	
	public java.sql.Timestamp getData() {
		return data;
	}
	
	public void setTransacaoId_transacao(wb.walletbud.Transacao value) {
		if (transacaoId_transacao != null) {
			transacaoId_transacao.comentario.remove(this);
		}
		if (value != null) {
			value.comentario.add(this);
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
	
	public void setUserId_user(wb.walletbud.User value) {
		if (userId_user != null) {
			userId_user.comentario.remove(this);
		}
		if (value != null) {
			value.comentario.add(this);
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
		return String.valueOf(getId_comentario());
	}
	
}
