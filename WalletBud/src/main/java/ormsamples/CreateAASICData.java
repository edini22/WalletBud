/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package ormsamples;

import WB.walletbud.*;
import org.orm.*;
public class CreateAASICData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
		try {
			User diagrama_de_classesWalletBudUser = UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : userTransacao, saldo
			UserDAO.save(diagrama_de_classesWalletBudUser);
			Categoria diagrama_de_classesWalletBudCategoria = CategoriaDAO.createCategoria();
			// Initialize the properties of the persistent object here
			CategoriaDAO.save(diagrama_de_classesWalletBudCategoria);
			Comentario diagrama_de_classesWalletBudComentario = ComentarioDAO.createComentario();
			// Initialize the properties of the persistent object here
			ComentarioDAO.save(diagrama_de_classesWalletBudComentario);
			Transacao diagrama_de_classesWalletBudTransacao = TransacaoDAO.createTransacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : userTransacao, value
			TransacaoDAO.save(diagrama_de_classesWalletBudTransacao);
			Objetivo diagrama_de_classesWalletBudObjetivo = ObjetivoDAO.createObjetivo();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : value
			ObjetivoDAO.save(diagrama_de_classesWalletBudObjetivo);
			Notificacao diagrama_de_classesWalletBudNotificacao = NotificacaoDAO.createNotificacao();
			// Initialize the properties of the persistent object here
			NotificacaoDAO.save(diagrama_de_classesWalletBudNotificacao);
			Fixa diagrama_de_classesWalletBudFixa = FixaDAO.createFixa();
			// Initialize the properties of the persistent object here
			FixaDAO.save(diagrama_de_classesWalletBudFixa);
			Unica diagrama_de_classesWalletBudUnica = UnicaDAO.createUnica();
			// Initialize the properties of the persistent object here
			UnicaDAO.save(diagrama_de_classesWalletBudUnica);
			UserTransacao diagrama_de_classesWalletBudUserTransacao = UserTransacaoDAO.createUserTransacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : transacaoId_transacao, userId_user
			UserTransacaoDAO.save(diagrama_de_classesWalletBudUserTransacao);
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
				AASICPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
