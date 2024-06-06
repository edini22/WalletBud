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

public class Transacao {
	public Transacao() {
	}
	
	private int id_transacao;
	
	private String name;
	
	private float value;
	
	private java.util.Date date;
	
	private String descrição;
	
	private String local;
	
	private String tipo;
	
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
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_transacao());
	}
	
}
