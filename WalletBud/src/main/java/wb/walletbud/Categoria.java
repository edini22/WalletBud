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

public class Categoria {
	public Categoria() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == wb.walletbud.ORMConstants.KEY_CATEGORIA_TRANSACAO) {
			return ORM_transacao;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_CATEGORIA_USERID_USER) {
			this.userId_user = (wb.walletbud.User) owner;
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
	
	private int id_categoria;
	
	private wb.walletbud.User userId_user;
	
	private String name;
	
	private String tipo;
	
	private java.util.Set ORM_transacao = new java.util.HashSet();
	
	private void setId_categoria(int value) {
		this.id_categoria = value;
	}
	
	public int getId_categoria() {
		return id_categoria;
	}
	
	public int getORMID() {
		return getId_categoria();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTipo(String value) {
		this.tipo = value;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setUserId_user(wb.walletbud.User value) {
		if (userId_user != null) {
			userId_user.categoria.remove(this);
		}
		if (value != null) {
			value.categoria.add(this);
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
	
	private void setORM_Transacao(java.util.Set value) {
		this.ORM_transacao = value;
	}
	
	private java.util.Set getORM_Transacao() {
		return ORM_transacao;
	}
	
	public final wb.walletbud.TransacaoSetCollection transacao = new wb.walletbud.TransacaoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_CATEGORIA_TRANSACAO, wb.walletbud.ORMConstants.KEY_TRANSACAO_CATEGORIAID_CATEGORIA, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_categoria());
	}
	
}
