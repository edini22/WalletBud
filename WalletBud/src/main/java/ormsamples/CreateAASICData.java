/**
 * Licensee: 
 * License Type: Evaluation
 */
package ormsamples;

import org.orm.*;
public class CreateAASICData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = wb.walletbud.AASICPersistentManager.instance().getSession().beginTransaction();
		try {
			wb.walletbud.UserTransacao wBWalletBudUserTransacao = wb.walletbud.UserTransacaoDAO.createUserTransacao();
			// Initialize the properties of the persistent object here
			wb.walletbud.UserTransacaoDAO.save(wBWalletBudUserTransacao);
			wb.walletbud.User wBWalletBudUser = wb.walletbud.UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : saldo
			wb.walletbud.UserDAO.save(wBWalletBudUser);
			wb.walletbud.Transacao wBWalletBudTransacao = wb.walletbud.TransacaoDAO.createTransacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : value
			wb.walletbud.TransacaoDAO.save(wBWalletBudTransacao);
			wb.walletbud.Fixa wBWalletBudFixa = wb.walletbud.FixaDAO.createFixa();
			// Initialize the properties of the persistent object here
			wb.walletbud.FixaDAO.save(wBWalletBudFixa);
			wb.walletbud.Unica wBWalletBudUnica = wb.walletbud.UnicaDAO.createUnica();
			// Initialize the properties of the persistent object here
			wb.walletbud.UnicaDAO.save(wBWalletBudUnica);
			wb.walletbud.Notificacao wBWalletBudNotificacao = wb.walletbud.NotificacaoDAO.createNotificacao();
			// Initialize the properties of the persistent object here
			wb.walletbud.NotificacaoDAO.save(wBWalletBudNotificacao);
			wb.walletbud.Objetivo wBWalletBudObjetivo = wb.walletbud.ObjetivoDAO.createObjetivo();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : value
			wb.walletbud.ObjetivoDAO.save(wBWalletBudObjetivo);
			wb.walletbud.Comentario wBWalletBudComentario = wb.walletbud.ComentarioDAO.createComentario();
			// Initialize the properties of the persistent object here
			wb.walletbud.ComentarioDAO.save(wBWalletBudComentario);
			wb.walletbud.Categoria wBWalletBudCategoria = wb.walletbud.CategoriaDAO.createCategoria();
			// Initialize the properties of the persistent object here
			wb.walletbud.CategoriaDAO.save(wBWalletBudCategoria);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateAASICData createAASICData = new CreateAASICData();
			try {
				createAASICData.createTestData();
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
