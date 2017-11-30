package gestionnaireTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

import gestionnaireTable.Avocats;
import gestionnaireTable.Parties;
import model.Avocat;
import model.Partie;
import general.Connexion;
import general.IFT287Exception;

public class GestionPartie {

	private Parties parties;
	private Avocats avocats;
	private Connexion cx; 

	GestionPartie(Parties parties, Avocats avocats, Connexion cx) throws IFT287Exception {
		this.cx = cx;
		this.parties = parties;
		this.avocats = avocats;
	}

	// — ajouterPartie <idPartie> <prenom> <nom> <idAvocat>
	/**
	 * Permet d’ajouter une personne participant à un procès comme défendeur ou
	 * poursuivant.
	 * 
	 * @param idParti
	 *            l'id du parti que l'on souhaite ajouté au proces
	 * @param prenom
	 *            le prenom du partie que l'on souhait rajouter
	 * @param nom
	 *            le nom du partie que
	 * @param idAvocat
	 *            l'id de l'avocat créée
	 * @param cx
	 * @throws SQLException
	 * @throws IFT287Exception
	 */
	public void ajouterPartie(int idParti, String prenom, String nom, int idAvocat)
			throws SQLException, IFT287Exception {
		try {

			Avocat a = avocats.selectOne(idAvocat);
			if (a == null)
				throw new IFT287Exception("L'avocat en question n'existe pas");

			Partie partie = new Partie(idParti, a, prenom, nom);
			if (parties.exist(partie))
				throw new IFT287Exception("Le parti en question existe d�j�");

			parties.ajouterPartie(partie);

			cx.getConnection().commit();
		} catch (Exception e) {
			System.out.println(e);
			cx.rollback();
			throw e;
		}
	}

	public ArrayList<Partie> getParties() throws SQLException {
		return parties.getParties(); 
	}
}
