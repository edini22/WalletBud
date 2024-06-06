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

public class Fixa extends wb.walletbud.Transacao {
	public Fixa() {
	}
	
	private int repetição;
	
	private boolean pago = false;
	
	private java.util.Date data_pagamento;
	
	private int id_fixa;
	
	public void setId_fixa(int value) {
		this.id_fixa = value;
	}
	
	public int getId_fixa() {
		return id_fixa;
	}
	
	public void setRepetição(int value) {
		this.repetição = value;
	}
	
	public int getRepetição() {
		return repetição;
	}
	
	public void setPago(boolean value) {
		this.pago = value;
	}
	
	public boolean getPago() {
		return pago;
	}
	
	public void setData_pagamento(java.util.Date value) {
		this.data_pagamento = value;
	}
	
	public java.util.Date getData_pagamento() {
		return data_pagamento;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
