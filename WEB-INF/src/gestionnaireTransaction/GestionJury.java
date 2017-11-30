package gestionnaireTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

import gestionnaireTable.Jurys;
import model.Jury;
import general.Connexion;
import general.IFT287Exception;

public class GestionJury {
	
	private Connexion cx;
	private Jurys jurys;
	
	GestionJury(Jurys jurys, Connexion cx){
		this.cx = cx;
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
	public void inscrireJury(int nas, String prenom, String nom, char sexe, int age)
			throws SQLException, IFT287Exception
	{
		try {
			
			if(sexe != 'M' && sexe != 'F') throw new IFT287Exception("Le sexe doit �tre m ou f");
			
			Jury jury = new Jury(nas, prenom, nom, age, true, sexe); 	
			
			if (jurys.exist(jury)) throw new IFT287Exception("Un jury avec ce NAS existe d�j�");
			
			jurys.inscrireJury(jury);
				
			cx.getConnection().commit();
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
			throw e;
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
	public void afficherJurys()
			throws SQLException, IFT287Exception
	{
		try {
			jurys.afficherJurys();			
			
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
			throw e;
		}
	}

	public ArrayList<Jury> getJurys() throws SQLException {		
		return jurys.getJurys();
	}
    

}
