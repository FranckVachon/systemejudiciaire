package gestionnaireTransaction;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestionnaireTable.Jurys;
import model.Avocat;
import model.Juge;
import model.Jury;
import model.Partie;
import model.Proces;
import model.Seance;
import general.Connexion;
import general.IFT287Exception;

public class GestionJury {
	
	private Jurys jurys;
	
	GestionJury(Jurys jurys){
		this.jurys = jurys;
	}

	//	— inscrireJury <nas> <prenom> <nom> <sexe> <age> 
	/**
	 * Permet d’inscrire une personne comme jury dans le système.
	 *     
	 * @param nas			numero d'assurance sociale
	 * @param prenom		prenom du jury
	 * @param nom			nom du jury
	 * @param sexe			sexe du jury
	 * @param age			age du jury
	 * @param cx 
	 * @throws SQLException
	 * @throws IFT287Exception
	 */
	public void inscrireJury(int nas, String prenom, String nom, char sexe, int age, Connexion cx)
			throws SQLException, IFT287Exception
	{
		try {
			
			if(sexe != 'M' && sexe != 'F') throw new IFT287Exception("Le sexe doit être m ou f");
			
			Jury jury = new Jury(nas, prenom, nom, age, true, sexe); 	
			
			if (Jurys.exist(jury,cx)) throw new IFT287Exception("Un jury avec ce NAS existe déjà");
			
			Jurys.inscrireJury(jury, cx);
				
			cx.getConnection().commit();
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
		}
	}



	//	— afficherJurys 
	/**
	 * Aﬃche la liste des jury disponibles pour participer à un procès.
	 * @param cx 
	 * 
	 * @throws SQLException
	 * @throws IFT287Exception
	 */    
	public void afficherJurys(Connexion cx)
			throws SQLException, IFT287Exception
	{
		try {
			Jurys.afficherJurys(cx);			
			
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();			
		}
	}
    

}
