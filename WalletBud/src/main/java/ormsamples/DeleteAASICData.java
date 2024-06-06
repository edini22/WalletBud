/**
 * Licensee: 
 * License Type: Evaluation
 */
package ormsamples;

import org.orm.*;
public class DeleteAASICData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = wb.walletbud.AASICPersistentManager.instance().getSession().beginTransaction();
		try {
			wb.walletbud.UserTransacao wBWalletBudUserTransacao = wb.walletbud.UserTransacaoDAO.loadUserTransacaoByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.UserTransacaoDAO.delete(wBWalletBudUserTransacao);
			wb.walletbud.User wBWalletBudUser = wb.walletbud.UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.UserDAO.delete(wBWalletBudUser);
			wb.walletbud.Transacao wBWalletBudTransacao = wb.walletbud.TransacaoDAO.loadTransacaoByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.TransacaoDAO.delete(wBWalletBudTransacao);
			wb.walletbud.Notificacao wBWalletBudNotificacao = wb.walletbud.NotificacaoDAO.loadNotificacaoByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.NotificacaoDAO.delete(wBWalletBudNotificacao);
			wb.walletbud.Objetivo wBWalletBudObjetivo = wb.walletbud.ObjetivoDAO.loadObjetivoByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.ObjetivoDAO.delete(wBWalletBudObjetivo);
			wb.walletbud.Comentario wBWalletBudComentario = wb.walletbud.ComentarioDAO.loadComentarioByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.ComentarioDAO.delete(wBWalletBudComentario);
			wb.walletbud.Categoria wBWalletBudCategoria = wb.walletbud.CategoriaDAO.loadCategoriaByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.CategoriaDAO.delete(wBWalletBudCategoria);
			wb.walletbud.TransacaoFixa wBWalletBudTransacaoFixa = wb.walletbud.TransacaoFixaDAO.loadTransacaoFixaByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.TransacaoFixaDAO.delete(wBWalletBudTransacaoFixa);
			wb.walletbud.Fixa wBWalletBudFixa = wb.walletbud.FixaDAO.loadFixaByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.FixaDAO.delete(wBWalletBudFixa);
			wb.walletbud.Unica wBWalletBudUnica = wb.walletbud.UnicaDAO.loadUnicaByQuery(null, null);
			// Delete the persistent object
			wb.walletbud.UnicaDAO.delete(wBWalletBudUnica);
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
				wb.walletbud.AASICPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
