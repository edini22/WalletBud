/**
 * Licensee: 
 * License Type: Evaluation
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateAASICData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = wb.walletbud.AASICPersistentManager.instance().getSession().beginTransaction();
		try {
			wb.walletbud.UserTransacao wBWalletBudUserTransacao = wb.walletbud.UserTransacaoDAO.loadUserTransacaoByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.UserTransacaoDAO.save(wBWalletBudUserTransacao);
			wb.walletbud.User wBWalletBudUser = wb.walletbud.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.UserDAO.save(wBWalletBudUser);
			wb.walletbud.Transacao wBWalletBudTransacao = wb.walletbud.TransacaoDAO.loadTransacaoByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.TransacaoDAO.save(wBWalletBudTransacao);
			wb.walletbud.Notificacao wBWalletBudNotificacao = wb.walletbud.NotificacaoDAO.loadNotificacaoByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.NotificacaoDAO.save(wBWalletBudNotificacao);
			wb.walletbud.Objetivo wBWalletBudObjetivo = wb.walletbud.ObjetivoDAO.loadObjetivoByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.ObjetivoDAO.save(wBWalletBudObjetivo);
			wb.walletbud.Comentario wBWalletBudComentario = wb.walletbud.ComentarioDAO.loadComentarioByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.ComentarioDAO.save(wBWalletBudComentario);
			wb.walletbud.Categoria wBWalletBudCategoria = wb.walletbud.CategoriaDAO.loadCategoriaByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.CategoriaDAO.save(wBWalletBudCategoria);
			wb.walletbud.TransacaoFixa wBWalletBudTransacaoFixa = wb.walletbud.TransacaoFixaDAO.loadTransacaoFixaByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.TransacaoFixaDAO.save(wBWalletBudTransacaoFixa);
			wb.walletbud.Fixa wBWalletBudFixa = wb.walletbud.FixaDAO.loadFixaByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.FixaDAO.save(wBWalletBudFixa);
			wb.walletbud.Unica wBWalletBudUnica = wb.walletbud.UnicaDAO.loadUnicaByQuery(null, null);
			// Update the properties of the persistent object
			wb.walletbud.UnicaDAO.save(wBWalletBudUnica);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving UserTransacao by UserTransacaoCriteria");
		wb.walletbud.UserTransacaoCriteria wBWalletBudUserTransacaoCriteria = new wb.walletbud.UserTransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudUserTransacaoCriteria.ID.eq();
		System.out.println(wBWalletBudUserTransacaoCriteria.uniqueUserTransacao());
		
		System.out.println("Retrieving User by UserCriteria");
		wb.walletbud.UserCriteria wBWalletBudUserCriteria = new wb.walletbud.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudUserCriteria.id_user.eq();
		System.out.println(wBWalletBudUserCriteria.uniqueUser());
		
		System.out.println("Retrieving Transacao by TransacaoCriteria");
		wb.walletbud.TransacaoCriteria wBWalletBudTransacaoCriteria = new wb.walletbud.TransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudTransacaoCriteria.id_transacao.eq();
		System.out.println(wBWalletBudTransacaoCriteria.uniqueTransacao());
		
		System.out.println("Retrieving Notificacao by NotificacaoCriteria");
		wb.walletbud.NotificacaoCriteria wBWalletBudNotificacaoCriteria = new wb.walletbud.NotificacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudNotificacaoCriteria.id_notificacao.eq();
		System.out.println(wBWalletBudNotificacaoCriteria.uniqueNotificacao());
		
		System.out.println("Retrieving Objetivo by ObjetivoCriteria");
		wb.walletbud.ObjetivoCriteria wBWalletBudObjetivoCriteria = new wb.walletbud.ObjetivoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudObjetivoCriteria.id_objetivo.eq();
		System.out.println(wBWalletBudObjetivoCriteria.uniqueObjetivo());
		
		System.out.println("Retrieving Comentario by ComentarioCriteria");
		wb.walletbud.ComentarioCriteria wBWalletBudComentarioCriteria = new wb.walletbud.ComentarioCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudComentarioCriteria.id_comentario.eq();
		System.out.println(wBWalletBudComentarioCriteria.uniqueComentario());
		
		System.out.println("Retrieving Categoria by CategoriaCriteria");
		wb.walletbud.CategoriaCriteria wBWalletBudCategoriaCriteria = new wb.walletbud.CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudCategoriaCriteria.id_categoria.eq();
		System.out.println(wBWalletBudCategoriaCriteria.uniqueCategoria());
		
		System.out.println("Retrieving TransacaoFixa by TransacaoFixaCriteria");
		wb.walletbud.TransacaoFixaCriteria wBWalletBudTransacaoFixaCriteria = new wb.walletbud.TransacaoFixaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudTransacaoFixaCriteria.ID.eq();
		System.out.println(wBWalletBudTransacaoFixaCriteria.uniqueTransacaoFixa());
		
		System.out.println("Retrieving Fixa by FixaCriteria");
		wb.walletbud.FixaCriteria wBWalletBudFixaCriteria = new wb.walletbud.FixaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudFixaCriteria.id_transacao.eq();
		System.out.println(wBWalletBudFixaCriteria.uniqueFixa());
		
		System.out.println("Retrieving Unica by UnicaCriteria");
		wb.walletbud.UnicaCriteria wBWalletBudUnicaCriteria = new wb.walletbud.UnicaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//wBWalletBudUnicaCriteria.id_transacao.eq();
		System.out.println(wBWalletBudUnicaCriteria.uniqueUnica());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateAASICData retrieveAndUpdateAASICData = new RetrieveAndUpdateAASICData();
			try {
				retrieveAndUpdateAASICData.retrieveAndUpdateTestData();
				//retrieveAndUpdateAASICData.retrieveByCriteria();
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
