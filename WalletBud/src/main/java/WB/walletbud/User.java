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

public class User {
	public User() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_USER_USERTRANSACAO) {
			return ORM_userTransacao;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id_user;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private float saldo = 0.0f;
	
	private String idioma = "portugues";
	
	private java.util.Set ORM_userTransacao = new java.util.HashSet();
	
	private void setId_user(int value) {
		this.id_user = value;
	}
	
	public int getId_user() {
		return id_user;
	}
	
	public int getORMID() {
		return getId_user();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setSaldo(float value) {
		this.saldo = value;
	}
	
	public float getSaldo() {
		return saldo;
	}
	
	public void setIdioma(String value) {
		this.idioma = value;
	}
	
	public String getIdioma() {
		return idioma;
	}
	
	private void setORM_UserTransacao(java.util.Set value) {
		this.ORM_userTransacao = value;
	}
	
	private java.util.Set getORM_UserTransacao() {
		return ORM_userTransacao;
	}
	
	public final UserTransacaoSetCollection userTransacao = new UserTransacaoSetCollection(this, _ormAdapter, ORMConstants.KEY_USER_USERTRANSACAO, ORMConstants.KEY_USERTRANSACAO_USERID_USER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_user());
	}
	
}
