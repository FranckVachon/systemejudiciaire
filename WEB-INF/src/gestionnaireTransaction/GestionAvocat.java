package gestionnaireTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

import gestionnaireTable.Avocats;
import model.Avocat;
import general.Connexion;
import general.IFT287Exception;

public  class GestionAvocat {
		
	private Avocats avocats;
	private Connexion cx;
	/**
	 * Creation d'une instance
	 */
	public GestionAvocat(Avocats avocats, Connexion cx) throws IFT287Exception {
		this.avocats = new Avocats(cx);
		this.cx = cx;
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
	public void ajouterAvocat(int idAvocat, String prenom, String nom, int type)
			throws SQLException, IFT287Exception
	{
		try {
			if(type!= 0 && type!= 1) throw new IFT287Exception("Le type d'avocat ne peut qu'�tre 0 ou 1");
						
			Avocat avocat = new Avocat(idAvocat, prenom, nom, "", type);
			
			if(avocats.exist(avocat)) throw new IFT287Exception("L'avocat existe déjà");
			
			avocats.ajouterAvocat(avocat);
			cx.getConnection().commit();
		}catch(Exception e ) {			
			System.out.println(e);
			cx.rollback();
			throw new IFT287Exception("L'avocat existe d�j�");
		}
	}

	public ArrayList<Avocat> getAvocats() throws SQLException {
		return avocats.getAvocats();
	}
}
