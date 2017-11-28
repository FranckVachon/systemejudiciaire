package gestionnaireTransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import gestionnaireTable.Avocats;
import model.Avocat;
import general.Connexion;
import general.IFT287Exception;

public  class GestionAvocat {
		
	private Avocats avocats;

	/**
	 * Creation d'une instance
	 */
	public GestionAvocat(Avocats avocats) throws IFT287Exception {
		this.avocats = avocats;
	}
	
	//	— 	ajouterAvocat <idAvocat> <prenom> <nom> <type> 
	/**
	 * Permet d’ajouter un avocat pour représenter un client. Le type peut ˆetre 0 pour un avocat privé, 1 pour un avocat du directeur des poursuites criminelles et pénales.
	 *     
	 * @param idAvocat			l'id de l'Avocat 
	 * @param prenom			le prenom de l'avocat 
	 * @param nom				le nom de l'avocat
	 * @param type				0 pour un avocat privé, 1 pour un avocat du directeur des poursuites criminelles/pénales
	 * @param cx 
	 * @throws SQLException
	 * @throws IFT287Exception
	 */
	public void ajouterAvocat(int idAvocat, String prenom, String nom, int type, Connexion cx)
			throws SQLException, IFT287Exception
	{
		try {
			if(type!= 0 && type!= 1) throw new IFT287Exception("Le type d'avocat ne peut qu'�tre 0 ou 1");
						
			Avocat avocat = new Avocat(idAvocat, prenom, nom, "", type);
			
			if(Avocats.exist(avocat, cx))  throw new IFT287Exception("L'avocat existe déjà");
			
			Avocats.ajouterAvocat(avocat, cx);
			cx.getConnection().commit();
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
		}
	}
}
