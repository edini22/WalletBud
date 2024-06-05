/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package ormsamples;

import WB.walletbud.*;
import org.orm.*;
public class RetrieveAndUpdateAASICData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
		try {
			User diagrama_de_classesWalletBudUser = UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			UserDAO.save(diagrama_de_classesWalletBudUser);
			Categoria diagrama_de_classesWalletBudCategoria = CategoriaDAO.loadCategoriaByQuery(null, null);
			// Update the properties of the persistent object
			CategoriaDAO.save(diagrama_de_classesWalletBudCategoria);
			Comentario diagrama_de_classesWalletBudComentario = ComentarioDAO.loadComentarioByQuery(null, null);
			// Update the properties of the persistent object
			ComentarioDAO.save(diagrama_de_classesWalletBudComentario);
			Transacao diagrama_de_classesWalletBudTransacao = TransacaoDAO.loadTransacaoByQuery(null, null);
			// Update the properties of the persistent object
			TransacaoDAO.save(diagrama_de_classesWalletBudTransacao);
			Objetivo diagrama_de_classesWalletBudObjetivo = ObjetivoDAO.loadObjetivoByQuery(null, null);
			// Update the properties of the persistent object
			ObjetivoDAO.save(diagrama_de_classesWalletBudObjetivo);
			Notificacao diagrama_de_classesWalletBudNotificacao = NotificacaoDAO.loadNotificacaoByQuery(null, null);
			// Update the properties of the persistent object
			NotificacaoDAO.save(diagrama_de_classesWalletBudNotificacao);
			Fixa diagrama_de_classesWalletBudFixa = FixaDAO.loadFixaByQuery(null, null);
			// Update the properties of the persistent object
			FixaDAO.save(diagrama_de_classesWalletBudFixa);
			Unica diagrama_de_classesWalletBudUnica = UnicaDAO.loadUnicaByQuery(null, null);
			// Update the properties of the persistent object
			UnicaDAO.save(diagrama_de_classesWalletBudUnica);
			UserTransacao diagrama_de_classesWalletBudUserTransacao = UserTransacaoDAO.loadUserTransacaoByQuery(null, null);
			// Update the properties of the persistent object
			UserTransacaoDAO.save(diagrama_de_classesWalletBudUserTransacao);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving User by UserCriteria");
		UserCriteria diagrama_de_classesWalletBudUserCriteria = new UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudUserCriteria.id_user.eq();
		System.out.println(diagrama_de_classesWalletBudUserCriteria.uniqueUser());
		
		System.out.println("Retrieving Categoria by CategoriaCriteria");
		CategoriaCriteria diagrama_de_classesWalletBudCategoriaCriteria = new CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudCategoriaCriteria.id_categoria.eq();
		System.out.println(diagrama_de_classesWalletBudCategoriaCriteria.uniqueCategoria());
		
		System.out.println("Retrieving Comentario by ComentarioCriteria");
		ComentarioCriteria diagrama_de_classesWalletBudComentarioCriteria = new ComentarioCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudComentarioCriteria.id_comentario.eq();
		System.out.println(diagrama_de_classesWalletBudComentarioCriteria.uniqueComentario());
		
		System.out.println("Retrieving Transacao by TransacaoCriteria");
		TransacaoCriteria diagrama_de_classesWalletBudTransacaoCriteria = new TransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudTransacaoCriteria.id_transacao.eq();
		System.out.println(diagrama_de_classesWalletBudTransacaoCriteria.uniqueTransacao());
		
		System.out.println("Retrieving Objetivo by ObjetivoCriteria");
		ObjetivoCriteria diagrama_de_classesWalletBudObjetivoCriteria = new ObjetivoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudObjetivoCriteria.id_objetivo.eq();
		System.out.println(diagrama_de_classesWalletBudObjetivoCriteria.uniqueObjetivo());
		
		System.out.println("Retrieving Notificacao by NotificacaoCriteria");
		NotificacaoCriteria diagrama_de_classesWalletBudNotificacaoCriteria = new NotificacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudNotificacaoCriteria.id_notificacao.eq();
		System.out.println(diagrama_de_classesWalletBudNotificacaoCriteria.uniqueNotificacao());
		
		System.out.println("Retrieving Fixa by FixaCriteria");
		FixaCriteria diagrama_de_classesWalletBudFixaCriteria = new FixaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudFixaCriteria.id_transacao.eq();
		System.out.println(diagrama_de_classesWalletBudFixaCriteria.uniqueFixa());
		
		System.out.println("Retrieving Unica by UnicaCriteria");
		UnicaCriteria diagrama_de_classesWalletBudUnicaCriteria = new UnicaCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudUnicaCriteria.id_transacao.eq();
		System.out.println(diagrama_de_classesWalletBudUnicaCriteria.uniqueUnica());
		
		System.out.println("Retrieving UserTransacao by UserTransacaoCriteria");
		UserTransacaoCriteria diagrama_de_classesWalletBudUserTransacaoCriteria = new UserTransacaoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//diagrama_de_classesWalletBudUserTransacaoCriteria.ID.eq();
		System.out.println(diagrama_de_classesWalletBudUserTransacaoCriteria.uniqueUserTransacao());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateAASICData retrieveAndUpdateAASICData = new RetrieveAndUpdateAASICData();
			try {
				retrieveAndUpdateAASICData.retrieveAndUpdateTestData();
				//retrieveAndUpdateAASICData.retrieveByCriteria();
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
