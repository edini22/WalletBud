/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package ormsamples;

import WB.walletbud.AASICPersistentManager;
import org.orm.*;
public class CreateAASICDatabaseSchema {
	public static void main(String[] args) {
		try {
			ORMDatabaseInitiator.createSchema(AASICPersistentManager.instance());
			AASICPersistentManager.instance().disposePersistentManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
