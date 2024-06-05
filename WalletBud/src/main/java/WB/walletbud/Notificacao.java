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

public class Notificacao {
	public Notificacao() {
	}
	
	private int id_notificacao;
	
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
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_notificacao());
	}
	
}
