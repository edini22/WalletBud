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

public class Transacao {
	public Transacao() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_TRANSACAO_USERTRANSACAO) {
			return ORM_userTransacao;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id_transacao;
	
	private String name;
	
	private float value;
	
	private java.util.Date date;
	
	private String descrição;
	
	private String local;
	
	private String tipo;
	
	private java.util.Set ORM_userTransacao = new java.util.HashSet();
	
	private void setId_transacao(int value) {
		this.id_transacao = value;
	}
	
	public int getId_transacao() {
		return id_transacao;
	}
	
	public int getORMID() {
		return getId_transacao();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
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
	
	public void setLocal(String value) {
		this.local = value;
	}
	
	public String getLocal() {
		return local;
	}
	
	/**
	 * receita ou despesa
	 */
	public void setTipo(String value) {
		this.tipo = value;
	}
	
	/**
	 * receita ou despesa
	 */
	public String getTipo() {
		return tipo;
	}
	
	private void setORM_UserTransacao(java.util.Set value) {
		this.ORM_userTransacao = value;
	}
	
	private java.util.Set getORM_UserTransacao() {
		return ORM_userTransacao;
	}
	
	public final UserTransacaoSetCollection userTransacao = new UserTransacaoSetCollection(this, _ormAdapter, ORMConstants.KEY_TRANSACAO_USERTRANSACAO, ORMConstants.KEY_USERTRANSACAO_TRANSACAOID_TRANSACAO, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_transacao());
	}
	
}
