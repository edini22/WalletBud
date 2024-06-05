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

public class Categoria {
	public Categoria() {
	}
	
	private int id_categoria;
	
	private String name;
	
	private String tipo;
	
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
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_categoria());
	}
	
}
