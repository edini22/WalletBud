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
			wb.walletbud.TransacaoPartilhada wBWalletBudTransacaoPartilhada = wb.walletbud.TransacaoPartilhadaDAO.createTransacaoPartilhada();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : confirma, userId_user, usertransacaoId
			wb.walletbud.TransacaoPartilhadaDAO.save(wBWalletBudTransacaoPartilhada);
			wb.walletbud.User wBWalletBudUser = wb.walletbud.UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : transacaoFixa, user_categoria, categoria, comentario, notificacao, objetivo, idioma, saldo, email, password, name
			wb.walletbud.UserDAO.save(wBWalletBudUser);
			wb.walletbud.Transacao wBWalletBudTransacao = wb.walletbud.TransacaoDAO.createTransacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : transacao, comentario, status, tipo, date, shareValue, value, name, categoriaId_categoria
			wb.walletbud.TransacaoDAO.save(wBWalletBudTransacao);
			wb.walletbud.Notificacao wBWalletBudNotificacao = wb.walletbud.NotificacaoDAO.createNotificacao();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : userId_user
			wb.walletbud.NotificacaoDAO.save(wBWalletBudNotificacao);
			wb.walletbud.Comentario wBWalletBudComentario = wb.walletbud.ComentarioDAO.createComentario();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : data, descrição, transacaoId_transacao, userId_user
			wb.walletbud.ComentarioDAO.save(wBWalletBudComentario);
			wb.walletbud.Categoria wBWalletBudCategoria = wb.walletbud.CategoriaDAO.createCategoria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : transacao, tipo, name, userId_user
			wb.walletbud.CategoriaDAO.save(wBWalletBudCategoria);
			wb.walletbud.TransacaoFixa wBWalletBudTransacaoFixa = wb.walletbud.TransacaoFixaDAO.createTransacaoFixa();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : userId_user, payvalue, dataAtual, dataPagamento, transacaofixa_ID
			wb.walletbud.TransacaoFixaDAO.save(wBWalletBudTransacaoFixa);
			wb.walletbud.Fixa wBWalletBudFixa = wb.walletbud.FixaDAO.createFixa();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : fixa
			wb.walletbud.FixaDAO.save(wBWalletBudFixa);
			wb.walletbud.Unica wBWalletBudUnica = wb.walletbud.UnicaDAO.createUnica();
			// Initialize the properties of the persistent object here
			wb.walletbud.UnicaDAO.save(wBWalletBudUnica);
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
