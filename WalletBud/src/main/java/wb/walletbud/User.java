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

import java.util.ArrayList;
import java.util.List;

public class User implements Observer{
	
	private List<Notificacao> notifications = new ArrayList<>();

    @Override
    public void notify(Notificacao notification) {
        notification.setUserId_user(this);
        notifications.add(notification);
        System.out.println("Received notification: " + notification.getDescrição());
    }


    public List<Notificacao> getNotifications() {
        return notifications;
    }
	
	public User() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == wb.walletbud.ORMConstants.KEY_USER_TRANSACAOS) {
			return ORM_transacaos;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_USER_NOTIFICACAO) {
			return ORM_notificacao;
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
		else if (key == wb.walletbud.ORMConstants.KEY_USER_TRANSACAOFIXA) {
			return ORM_transacaoFixa;
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
	
	private String idioma = "pt";
	
	private float objetivo;
	
	private String token;
	
	private java.sql.Timestamp expToken;

	private String moeda = "EUR";
	
	private java.util.Set ORM_transacaos = new java.util.HashSet();
	
	private java.util.Set ORM_notificacao = new java.util.HashSet();
	
	private java.util.Set ORM_comentario = new java.util.HashSet();
	
	private java.util.Set ORM_categoria = new java.util.HashSet();
	
	private java.util.Set ORM_user_categoria = new java.util.HashSet();
	
	private java.util.Set ORM_transacaoFixa = new java.util.HashSet();
	
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
	
	public void setObjetivo(float value) {
		this.objetivo = value;
	}
	
	public float getObjetivo() {
		return objetivo;
	}
	
	public void setToken(String value) {
		this.token = value;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setExpToken(java.sql.Timestamp value) {
		this.expToken = value;
	}
	
	public java.sql.Timestamp getExpToken() {
		return expToken;
	}

	public void setMoeda(String value) {
		this.moeda = value;
	}

	public String getMoeda() {
		return moeda;
	}

	private void setORM_Transacaos(java.util.Set value) {
		this.ORM_transacaos = value;
	}
	
	private java.util.Set getORM_Transacaos() {
		return ORM_transacaos;
	}
	
	public final wb.walletbud.TransacaoSetCollection transacaos = new wb.walletbud.TransacaoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_TRANSACAOS, wb.walletbud.ORMConstants.KEY_TRANSACAO_OWNER_ID, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Notificacao(java.util.Set value) {
		this.ORM_notificacao = value;
	}
	
	private java.util.Set getORM_Notificacao() {
		return ORM_notificacao;
	}
	
	public final wb.walletbud.NotificacaoSetCollection notificacao = new wb.walletbud.NotificacaoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_NOTIFICACAO, wb.walletbud.ORMConstants.KEY_NOTIFICACAO_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
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
	
	public final wb.walletbud.TransacaoPartilhadaSetCollection user_categoria = new wb.walletbud.TransacaoPartilhadaSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_USER_CATEGORIA, wb.walletbud.ORMConstants.KEY_TRANSACAOPARTILHADA_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_TransacaoFixa(java.util.Set value) {
		this.ORM_transacaoFixa = value;
	}
	
	private java.util.Set getORM_TransacaoFixa() {
		return ORM_transacaoFixa;
	}
	
	public final wb.walletbud.TransacaoFixaSetCollection transacaoFixa = new wb.walletbud.TransacaoFixaSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_USER_TRANSACAOFIXA, wb.walletbud.ORMConstants.KEY_TRANSACAOFIXA_USERID_USER, wb.walletbud.ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_user());
	}
	
}
