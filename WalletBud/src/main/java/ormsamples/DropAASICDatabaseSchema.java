/**
 * Licensee: GSenra(University of Minho)
 * License Type: Academic
 */
package ormsamples;

import WB.walletbud.AASICPersistentManager;
import org.orm.*;
public class DropAASICDatabaseSchema {
	public static void main(String[] args) {
		try {
			System.out.println("Are you sure to drop table(s)? (Y/N)");
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			if (reader.readLine().trim().toUpperCase().equals("Y")) {
				ORMDatabaseInitiator.dropSchema(AASICPersistentManager.instance());
				AASICPersistentManager.instance().disposePersistentManager();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
