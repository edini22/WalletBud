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

public class Objetivo {
	public Objetivo() {
	}
	
	private int id_objetivo;
	
	private float value;
	
	private String name;
	
	private String descrição;
	
	private void setId_objetivo(int value) {
		this.id_objetivo = value;
	}
	
	public int getId_objetivo() {
		return id_objetivo;
	}
	
	public int getORMID() {
		return getId_objetivo();
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
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
		return String.valueOf(getId_objetivo());
	}
	
}
