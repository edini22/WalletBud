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

public class User {
	public User() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == wb.walletbud.ORMConstants.KEY_USER_NOTIFICACAO) {
			return ORM_notificacao;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_USER_OBJETIVO) {
			return ORM_objetivo;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_USER_COMENTARIO) {
			return ORM_comentario;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_USER_CATEGORIA) {
			return ORM_categoria;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_USER_USER_CATEGORIA) {
			return ORM_user_categoria;
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
	
	private java.util.Set ORM_notificacao = new java.util.HashSet();
	
	private java.util.Set ORM_objetivo = new java.util.HashSet();
	
	private java.util.Set ORM_comentario = new java.util.HashSet();
	
	private java.util.Set ORM_categoria = new java.util.HashSet();
	
	private java.util.Set ORM_user_categoria = new java.util.HashSet();
	
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
	
	private void setORM_Notificacao(java.util.Set value) {
		this.ORM_notificacao = value;
	}
	
	private java.util.Set getORM_Notificacao() {
		return ORM_notificacao;
	}
	
	public final wb.walletbud.NotificacaoSetCollection notificacao = new wb.walletbud.NotificacaoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_NOTIFICACAO, wb.walletbud.ORMConstants.KEY_NOTIFICACAO_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Objetivo(java.util.Set value) {
		this.ORM_objetivo = value;
	}
	
	private java.util.Set getORM_Objetivo() {
		return ORM_objetivo;
	}
	
	public final wb.walletbud.ObjetivoSetCollection objetivo = new wb.walletbud.ObjetivoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_OBJETIVO, wb.walletbud.ORMConstants.KEY_OBJETIVO_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Comentario(java.util.Set value) {
		this.ORM_comentario = value;
	}
	
	private java.util.Set getORM_Comentario() {
		return ORM_comentario;
	}
	
	public final wb.walletbud.ComentarioSetCollection comentario = new wb.walletbud.ComentarioSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_COMENTARIO, wb.walletbud.ORMConstants.KEY_COMENTARIO_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Categoria(java.util.Set value) {
		this.ORM_categoria = value;
	}
	
	private java.util.Set getORM_Categoria() {
		return ORM_categoria;
	}
	
	public final wb.walletbud.CategoriaSetCollection categoria = new wb.walletbud.CategoriaSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_CATEGORIA, wb.walletbud.ORMConstants.KEY_CATEGORIA_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_User_categoria(java.util.Set value) {
		this.ORM_user_categoria = value;
	}
	
	private java.util.Set getORM_User_categoria() {
		return ORM_user_categoria;
	}
	
	public final wb.walletbud.UserTransacaoSetCollection user_categoria = new wb.walletbud.UserTransacaoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_USER_CATEGORIA, wb.walletbud.ORMConstants.KEY_USERTRANSACAO_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_user());
	}
	
}
