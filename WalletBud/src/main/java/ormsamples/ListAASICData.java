/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package ormsamples;

import WB.walletbud.*;
import org.orm.*;
public class ListAASICData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing User...");
		User[] diagrama_de_classesWalletBudUsers = UserDAO.listUserByQuery(null, null);
		int length = Math.min(diagrama_de_classesWalletBudUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Categoria...");
		Categoria[] diagrama_de_classesWalletBudCategorias = CategoriaDAO.listCategoriaByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudCategorias.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudCategorias[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Comentario...");
		Comentario[] diagrama_de_classesWalletBudComentarios = ComentarioDAO.listComentarioByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudComentarios.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudComentarios[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Transacao...");
		Transacao[] diagrama_de_classesWalletBudTransacaos = TransacaoDAO.listTransacaoByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudTransacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudTransacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Objetivo...");
		Objetivo[] diagrama_de_classesWalletBudObjetivos = ObjetivoDAO.listObjetivoByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudObjetivos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudObjetivos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Notificacao...");
		Notificacao[] diagrama_de_classesWalletBudNotificacaos = NotificacaoDAO.listNotificacaoByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudNotificacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudNotificacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Fixa...");
		Fixa[] diagrama_de_classesWalletBudFixas = FixaDAO.listFixaByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudFixas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudFixas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Unica...");
		Unica[] diagrama_de_classesWalletBudUnicas = UnicaDAO.listUnicaByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudUnicas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudUnicas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing UserTransacao...");
		UserTransacao[] diagrama_de_classesWalletBudUserTransacaos = UserTransacaoDAO.listUserTransacaoByQuery(null, null);
		length = Math.min(diagrama_de_classesWalletBudUserTransacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(diagrama_de_classesWalletBudUserTransacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing User by Criteria...");
		UserCriteria diagrama_de_classesWalletBudUserCriteria = new UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudUserCriteria.id_user.eq();
		diagrama_de_classesWalletBudUserCriteria.setMaxResults(ROW_COUNT);
		User[] diagrama_de_classesWalletBudUsers = diagrama_de_classesWalletBudUserCriteria.listUser();
		int length =diagrama_de_classesWalletBudUsers== null ? 0 : Math.min(diagrama_de_classesWalletBudUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudUsers[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
		System.out.println("Listing Categoria by Criteria...");
		CategoriaCriteria diagrama_de_classesWalletBudCategoriaCriteria = new CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudCategoriaCriteria.id_categoria.eq();
		diagrama_de_classesWalletBudCategoriaCriteria.setMaxResults(ROW_COUNT);
		Categoria[] diagrama_de_classesWalletBudCategorias = diagrama_de_classesWalletBudCategoriaCriteria.listCategoria();
		length =diagrama_de_classesWalletBudCategorias== null ? 0 : Math.min(diagrama_de_classesWalletBudCategorias.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudCategorias[i]);
		}
		System.out.println(length + " Categoria record(s) retrieved."); 
		
		System.out.println("Listing Comentario by Criteria...");
		ComentarioCriteria diagrama_de_classesWalletBudComentarioCriteria = new ComentarioCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudComentarioCriteria.id_comentario.eq();
		diagrama_de_classesWalletBudComentarioCriteria.setMaxResults(ROW_COUNT);
		Comentario[] diagrama_de_classesWalletBudComentarios = diagrama_de_classesWalletBudComentarioCriteria.listComentario();
		length =diagrama_de_classesWalletBudComentarios== null ? 0 : Math.min(diagrama_de_classesWalletBudComentarios.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudComentarios[i]);
		}
		System.out.println(length + " Comentario record(s) retrieved."); 
		
		System.out.println("Listing Transacao by Criteria...");
		TransacaoCriteria diagrama_de_classesWalletBudTransacaoCriteria = new TransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudTransacaoCriteria.id_transacao.eq();
		diagrama_de_classesWalletBudTransacaoCriteria.setMaxResults(ROW_COUNT);
		Transacao[] diagrama_de_classesWalletBudTransacaos = diagrama_de_classesWalletBudTransacaoCriteria.listTransacao();
		length =diagrama_de_classesWalletBudTransacaos== null ? 0 : Math.min(diagrama_de_classesWalletBudTransacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudTransacaos[i]);
		}
		System.out.println(length + " Transacao record(s) retrieved."); 
		
		System.out.println("Listing Objetivo by Criteria...");
		ObjetivoCriteria diagrama_de_classesWalletBudObjetivoCriteria = new ObjetivoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudObjetivoCriteria.id_objetivo.eq();
		diagrama_de_classesWalletBudObjetivoCriteria.setMaxResults(ROW_COUNT);
		Objetivo[] diagrama_de_classesWalletBudObjetivos = diagrama_de_classesWalletBudObjetivoCriteria.listObjetivo();
		length =diagrama_de_classesWalletBudObjetivos== null ? 0 : Math.min(diagrama_de_classesWalletBudObjetivos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudObjetivos[i]);
		}
		System.out.println(length + " Objetivo record(s) retrieved."); 
		
		System.out.println("Listing Notificacao by Criteria...");
		NotificacaoCriteria diagrama_de_classesWalletBudNotificacaoCriteria = new NotificacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudNotificacaoCriteria.id_notificacao.eq();
		diagrama_de_classesWalletBudNotificacaoCriteria.setMaxResults(ROW_COUNT);
		Notificacao[] diagrama_de_classesWalletBudNotificacaos = diagrama_de_classesWalletBudNotificacaoCriteria.listNotificacao();
		length =diagrama_de_classesWalletBudNotificacaos== null ? 0 : Math.min(diagrama_de_classesWalletBudNotificacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudNotificacaos[i]);
		}
		System.out.println(length + " Notificacao record(s) retrieved."); 
		
		System.out.println("Listing Fixa by Criteria...");
		FixaCriteria diagrama_de_classesWalletBudFixaCriteria = new FixaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudFixaCriteria.id_transacao.eq();
		diagrama_de_classesWalletBudFixaCriteria.setMaxResults(ROW_COUNT);
		Fixa[] diagrama_de_classesWalletBudFixas = diagrama_de_classesWalletBudFixaCriteria.listFixa();
		length =diagrama_de_classesWalletBudFixas== null ? 0 : Math.min(diagrama_de_classesWalletBudFixas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudFixas[i]);
		}
		System.out.println(length + " Fixa record(s) retrieved."); 
		
		System.out.println("Listing Unica by Criteria...");
		UnicaCriteria diagrama_de_classesWalletBudUnicaCriteria = new UnicaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudUnicaCriteria.id_transacao.eq();
		diagrama_de_classesWalletBudUnicaCriteria.setMaxResults(ROW_COUNT);
		Unica[] diagrama_de_classesWalletBudUnicas = diagrama_de_classesWalletBudUnicaCriteria.listUnica();
		length =diagrama_de_classesWalletBudUnicas== null ? 0 : Math.min(diagrama_de_classesWalletBudUnicas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudUnicas[i]);
		}
		System.out.println(length + " Unica record(s) retrieved."); 
		
		System.out.println("Listing UserTransacao by Criteria...");
		UserTransacaoCriteria diagrama_de_classesWalletBudUserTransacaoCriteria = new UserTransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//diagrama_de_classesWalletBudUserTransacaoCriteria.ID.eq();
		diagrama_de_classesWalletBudUserTransacaoCriteria.setMaxResults(ROW_COUNT);
		UserTransacao[] diagrama_de_classesWalletBudUserTransacaos = diagrama_de_classesWalletBudUserTransacaoCriteria.listUserTransacao();
		length =diagrama_de_classesWalletBudUserTransacaos== null ? 0 : Math.min(diagrama_de_classesWalletBudUserTransacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(diagrama_de_classesWalletBudUserTransacaos[i]);
		}
		System.out.println(length + " UserTransacao record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListAASICData listAASICData = new ListAASICData();
			try {
				listAASICData.listTestData();
				//listAASICData.listByCriteria();
			}
			finally {
				AASICPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
