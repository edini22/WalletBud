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
	
	private java.util.Set this_getSet (int key) {
		if (key == wb.walletbud.ORMConstants.KEY_TRANSACAO_COMENTARIO) {
			return ORM_comentario;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_TRANSACAO_TRANSACAO) {
			return ORM_transacao;
		}
		else if (key == wb.walletbud.ORMConstants.KEY_TRANSACAO_NOTIFICACAO) {
			return ORM_notificacao;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == wb.walletbud.ORMConstants.KEY_TRANSACAO_CATEGORIAID_CATEGORIA) {
			this.categoriaId_categoria = (wb.walletbud.Categoria) owner;
		}
		
		else if (key == wb.walletbud.ORMConstants.KEY_TRANSACAO_OWNER_ID) {
			this.owner_id = (wb.walletbud.User) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id_transacao;
	
	private wb.walletbud.User owner_id;
	
	private wb.walletbud.Categoria categoriaId_categoria;
	
	private String name;
	
	private float value;
	
	private float shareValue;
	
	private java.sql.Timestamp date;
	
	private String descrição;
	
	private String local;
	
	private String tipo;
	
	private boolean status = false;
	
	private java.util.Set ORM_comentario = new java.util.HashSet();
	
	private java.util.Set ORM_transacao = new java.util.HashSet();
	
	private java.util.Set ORM_notificacao = new java.util.HashSet();
	
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
	
	public void setShareValue(float value) {
		this.shareValue = value;
	}
	
	public float getShareValue() {
		return shareValue;
	}
	
	public void setDate(java.sql.Timestamp value) {
		this.date = value;
	}
	
	public java.sql.Timestamp getDate() {
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
	
	public void setStatus(boolean value) {
		this.status = value;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setCategoriaId_categoria(wb.walletbud.Categoria value) {
		if (categoriaId_categoria != null) {
			categoriaId_categoria.transacao.remove(this);
		}
		if (value != null) {
			value.transacao.add(this);
		}
	}
	
	public wb.walletbud.Categoria getCategoriaId_categoria() {
		return categoriaId_categoria;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_CategoriaId_categoria(wb.walletbud.Categoria value) {
		this.categoriaId_categoria = value;
	}
	
	private wb.walletbud.Categoria getORM_CategoriaId_categoria() {
		return categoriaId_categoria;
	}
	
	private void setORM_Comentario(java.util.Set value) {
		this.ORM_comentario = value;
	}
	
	private java.util.Set getORM_Comentario() {
		return ORM_comentario;
	}
	
	public final wb.walletbud.ComentarioSetCollection comentario = new wb.walletbud.ComentarioSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_TRANSACAO_COMENTARIO, wb.walletbud.ORMConstants.KEY_COMENTARIO_TRANSACAOID_TRANSACAO, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Transacao(java.util.Set value) {
		this.ORM_transacao = value;
	}
	
	private java.util.Set getORM_Transacao() {
		return ORM_transacao;
	}
	
	public final wb.walletbud.TransacaoPartilhadaSetCollection transacao = new wb.walletbud.TransacaoPartilhadaSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_TRANSACAO_TRANSACAO, wb.walletbud.ORMConstants.KEY_TRANSACAOPARTILHADA_USERTRANSACAOID, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setOwner_id(wb.walletbud.User value) {
		if (owner_id != null) {
			owner_id.transacaos.remove(this);
		}
		if (value != null) {
			value.transacaos.add(this);
		}
	}
	
	public wb.walletbud.User getOwner_id() {
		return owner_id;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Owner_id(wb.walletbud.User value) {
		this.owner_id = value;
	}
	
	private wb.walletbud.User getORM_Owner_id() {
		return owner_id;
	}
	
	private void setORM_Notificacao(java.util.Set value) {
		this.ORM_notificacao = value;
	}
	
	private java.util.Set getORM_Notificacao() {
		return ORM_notificacao;
	}
	
	public final wb.walletbud.NotificacaoSetCollection notificacao = new wb.walletbud.NotificacaoSetCollection(this, _ormAdapter, wb.walletbud.ORMConstants.KEY_TRANSACAO_NOTIFICACAO, wb.walletbud.ORMConstants.KEY_NOTIFICACAO_TRANSACAOID_TRANSACAO, wb.walletbud.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public int getId() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getId_transacao());
	}
	
}
