/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package ormsamples;

import WB.walletbud.*;
import org.orm.*;
public class DeleteAASICData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
		try {
			User diagrama_de_classesWalletBudUser = UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			UserDAO.delete(diagrama_de_classesWalletBudUser);
			Categoria diagrama_de_classesWalletBudCategoria = CategoriaDAO.loadCategoriaByQuery(null, null);
			// Delete the persistent object
			CategoriaDAO.delete(diagrama_de_classesWalletBudCategoria);
			Comentario diagrama_de_classesWalletBudComentario = ComentarioDAO.loadComentarioByQuery(null, null);
			// Delete the persistent object
			ComentarioDAO.delete(diagrama_de_classesWalletBudComentario);
			Transacao diagrama_de_classesWalletBudTransacao = TransacaoDAO.loadTransacaoByQuery(null, null);
			// Delete the persistent object
			TransacaoDAO.delete(diagrama_de_classesWalletBudTransacao);
			Objetivo diagrama_de_classesWalletBudObjetivo = ObjetivoDAO.loadObjetivoByQuery(null, null);
			// Delete the persistent object
			ObjetivoDAO.delete(diagrama_de_classesWalletBudObjetivo);
			Notificacao diagrama_de_classesWalletBudNotificacao = NotificacaoDAO.loadNotificacaoByQuery(null, null);
			// Delete the persistent object
			NotificacaoDAO.delete(diagrama_de_classesWalletBudNotificacao);
			Fixa diagrama_de_classesWalletBudFixa = FixaDAO.loadFixaByQuery(null, null);
			// Delete the persistent object
			FixaDAO.delete(diagrama_de_classesWalletBudFixa);
			Unica diagrama_de_classesWalletBudUnica = UnicaDAO.loadUnicaByQuery(null, null);
			// Delete the persistent object
			UnicaDAO.delete(diagrama_de_classesWalletBudUnica);
			UserTransacao diagrama_de_classesWalletBudUserTransacao = UserTransacaoDAO.loadUserTransacaoByQuery(null, null);
			// Delete the persistent object
			UserTransacaoDAO.delete(diagrama_de_classesWalletBudUserTransacao);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteAASICData deleteAASICData = new DeleteAASICData();
			try {
				deleteAASICData.deleteTestData();
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
