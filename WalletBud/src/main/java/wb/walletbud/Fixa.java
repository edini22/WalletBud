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
	
	private java.util.Set this_getSet (int key) {
		if (key == wb.walletbud.ORMConstants.KEY_FIXA_FIXA) {
			return ORM_fixa;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private java.sql.Timestamp dataTransacao;
	
	private int repeticao;
	
	private java.util.Set ORM_fixa = new java.util.HashSet();
	
	public void setDataTransacao(java.sql.Timestamp value) {
		this.dataTransacao = value;
	}
	
	public java.sql.Timestamp getDataTransacao() {
		return dataTransacao;
	}
	
	public void setRepeticao(int value) {
		this.repeticao = value;
	}
	
	public int getRepeticao() {
		return repeticao;
	}
	
	private void setORM_Fixa(java.util.Set value) {
		this.ORM_fixa = value;
	}
	
	private java.util.Set getORM_Fixa() {
		return ORM_fixa;
	}
	
	public final wb.walletbud.TransacaoFixaSetCollection fixa = new wb.walletbud.TransacaoFixaSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_FIXA_FIXA, wb.walletbud.ORMConstants.KEY_TRANSACAOFIXA_TRANSACAOFIXA_ID, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return super.toString();
	}
	
}
