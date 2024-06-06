/**
 * Licensee: 
 * License Type: Evaluation
 */
package ormsamples;

import org.orm.*;
public class ListAASICData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing UserTransacao...");
		wb.walletbud.UserTransacao[] wBWalletBudUserTransacaos = wb.walletbud.UserTransacaoDAO.listUserTransacaoByQuery(null, null);
		int length = Math.min(wBWalletBudUserTransacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudUserTransacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		wb.walletbud.User[] wBWalletBudUsers = wb.walletbud.UserDAO.listUserByQuery(null, null);
		length = Math.min(wBWalletBudUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Transacao...");
		wb.walletbud.Transacao[] wBWalletBudTransacaos = wb.walletbud.TransacaoDAO.listTransacaoByQuery(null, null);
		length = Math.min(wBWalletBudTransacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudTransacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Notificacao...");
		wb.walletbud.Notificacao[] wBWalletBudNotificacaos = wb.walletbud.NotificacaoDAO.listNotificacaoByQuery(null, null);
		length = Math.min(wBWalletBudNotificacaos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudNotificacaos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Objetivo...");
		wb.walletbud.Objetivo[] wBWalletBudObjetivos = wb.walletbud.ObjetivoDAO.listObjetivoByQuery(null, null);
		length = Math.min(wBWalletBudObjetivos.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudObjetivos[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Comentario...");
		wb.walletbud.Comentario[] wBWalletBudComentarios = wb.walletbud.ComentarioDAO.listComentarioByQuery(null, null);
		length = Math.min(wBWalletBudComentarios.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudComentarios[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Categoria...");
		wb.walletbud.Categoria[] wBWalletBudCategorias = wb.walletbud.CategoriaDAO.listCategoriaByQuery(null, null);
		length = Math.min(wBWalletBudCategorias.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudCategorias[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing TransacaoFixa...");
		wb.walletbud.TransacaoFixa[] wBWalletBudTransacaoFixas = wb.walletbud.TransacaoFixaDAO.listTransacaoFixaByQuery(null, null);
		length = Math.min(wBWalletBudTransacaoFixas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudTransacaoFixas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Fixa...");
		wb.walletbud.Fixa[] wBWalletBudFixas = wb.walletbud.FixaDAO.listFixaByQuery(null, null);
		length = Math.min(wBWalletBudFixas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudFixas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Unica...");
		wb.walletbud.Unica[] wBWalletBudUnicas = wb.walletbud.UnicaDAO.listUnicaByQuery(null, null);
		length = Math.min(wBWalletBudUnicas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(wBWalletBudUnicas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing UserTransacao by Criteria...");
		wb.walletbud.UserTransacaoCriteria wBWalletBudUserTransacaoCriteria = new wb.walletbud.UserTransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudUserTransacaoCriteria.ID.eq();
		wBWalletBudUserTransacaoCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.UserTransacao[] wBWalletBudUserTransacaos = wBWalletBudUserTransacaoCriteria.listUserTransacao();
		int length =wBWalletBudUserTransacaos== null ? 0 : Math.min(wBWalletBudUserTransacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudUserTransacaos[i]);
		}
		System.out.println(length + " UserTransacao record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		wb.walletbud.UserCriteria wBWalletBudUserCriteria = new wb.walletbud.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudUserCriteria.id_user.eq();
		wBWalletBudUserCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.User[] wBWalletBudUsers = wBWalletBudUserCriteria.listUser();
		length =wBWalletBudUsers== null ? 0 : Math.min(wBWalletBudUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudUsers[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
		System.out.println("Listing Transacao by Criteria...");
		wb.walletbud.TransacaoCriteria wBWalletBudTransacaoCriteria = new wb.walletbud.TransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudTransacaoCriteria.id_transacao.eq();
		wBWalletBudTransacaoCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Transacao[] wBWalletBudTransacaos = wBWalletBudTransacaoCriteria.listTransacao();
		length =wBWalletBudTransacaos== null ? 0 : Math.min(wBWalletBudTransacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudTransacaos[i]);
		}
		System.out.println(length + " Transacao record(s) retrieved."); 
		
		System.out.println("Listing Notificacao by Criteria...");
		wb.walletbud.NotificacaoCriteria wBWalletBudNotificacaoCriteria = new wb.walletbud.NotificacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudNotificacaoCriteria.id_notificacao.eq();
		wBWalletBudNotificacaoCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Notificacao[] wBWalletBudNotificacaos = wBWalletBudNotificacaoCriteria.listNotificacao();
		length =wBWalletBudNotificacaos== null ? 0 : Math.min(wBWalletBudNotificacaos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudNotificacaos[i]);
		}
		System.out.println(length + " Notificacao record(s) retrieved."); 
		
		System.out.println("Listing Objetivo by Criteria...");
		wb.walletbud.ObjetivoCriteria wBWalletBudObjetivoCriteria = new wb.walletbud.ObjetivoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudObjetivoCriteria.id_objetivo.eq();
		wBWalletBudObjetivoCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Objetivo[] wBWalletBudObjetivos = wBWalletBudObjetivoCriteria.listObjetivo();
		length =wBWalletBudObjetivos== null ? 0 : Math.min(wBWalletBudObjetivos.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudObjetivos[i]);
		}
		System.out.println(length + " Objetivo record(s) retrieved."); 
		
		System.out.println("Listing Comentario by Criteria...");
		wb.walletbud.ComentarioCriteria wBWalletBudComentarioCriteria = new wb.walletbud.ComentarioCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudComentarioCriteria.id_comentario.eq();
		wBWalletBudComentarioCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Comentario[] wBWalletBudComentarios = wBWalletBudComentarioCriteria.listComentario();
		length =wBWalletBudComentarios== null ? 0 : Math.min(wBWalletBudComentarios.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudComentarios[i]);
		}
		System.out.println(length + " Comentario record(s) retrieved."); 
		
		System.out.println("Listing Categoria by Criteria...");
		wb.walletbud.CategoriaCriteria wBWalletBudCategoriaCriteria = new wb.walletbud.CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudCategoriaCriteria.id_categoria.eq();
		wBWalletBudCategoriaCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Categoria[] wBWalletBudCategorias = wBWalletBudCategoriaCriteria.listCategoria();
		length =wBWalletBudCategorias== null ? 0 : Math.min(wBWalletBudCategorias.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudCategorias[i]);
		}
		System.out.println(length + " Categoria record(s) retrieved."); 
		
		System.out.println("Listing TransacaoFixa by Criteria...");
		wb.walletbud.TransacaoFixaCriteria wBWalletBudTransacaoFixaCriteria = new wb.walletbud.TransacaoFixaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudTransacaoFixaCriteria.ID.eq();
		wBWalletBudTransacaoFixaCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.TransacaoFixa[] wBWalletBudTransacaoFixas = wBWalletBudTransacaoFixaCriteria.listTransacaoFixa();
		length =wBWalletBudTransacaoFixas== null ? 0 : Math.min(wBWalletBudTransacaoFixas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudTransacaoFixas[i]);
		}
		System.out.println(length + " TransacaoFixa record(s) retrieved."); 
		
		System.out.println("Listing Fixa by Criteria...");
		wb.walletbud.FixaCriteria wBWalletBudFixaCriteria = new wb.walletbud.FixaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudFixaCriteria.id_transacao.eq();
		wBWalletBudFixaCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Fixa[] wBWalletBudFixas = wBWalletBudFixaCriteria.listFixa();
		length =wBWalletBudFixas== null ? 0 : Math.min(wBWalletBudFixas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudFixas[i]);
		}
		System.out.println(length + " Fixa record(s) retrieved."); 
		
		System.out.println("Listing Unica by Criteria...");
		wb.walletbud.UnicaCriteria wBWalletBudUnicaCriteria = new wb.walletbud.UnicaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//wBWalletBudUnicaCriteria.id_transacao.eq();
		wBWalletBudUnicaCriteria.setMaxResults(ROW_COUNT);
		wb.walletbud.Unica[] wBWalletBudUnicas = wBWalletBudUnicaCriteria.listUnica();
		length =wBWalletBudUnicas== null ? 0 : Math.min(wBWalletBudUnicas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(wBWalletBudUnicas[i]);
		}
		System.out.println(length + " Unica record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListAASICData listAASICData = new ListAASICData();
			try {
				listAASICData.listTestData();
				//listAASICData.listByCriteria();
			}
			finally {
				wb.walletbud.AASICPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
