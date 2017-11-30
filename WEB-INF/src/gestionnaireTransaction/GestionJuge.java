package gestionnaireTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

import gestionnaireTable.Juges;
import model.Juge;
import model.Jury;
import general.Connexion;
import general.IFT287Exception;

public class GestionJuge {
	
	private Juges juges;
	private Connexion cx;
	
	GestionJuge(Juges juges, Connexion cx){
		this.juges = juges;
		this.cx =cx;
	}
	
	//	— ajouterJuge <idJuge> <prenom> <nom> <age>
	/**
	 *  Permet d’ajouter un juge au système.
	 *  
	 * @param idJuge		l'id souhaité du juge
	 * @param prenom		le prenom du juge 
	 * @param nom			le nom du juge
	 * @param age			l'age du juge
	 * @param cx 
	 * @throws SQLException
	 * @throws IFT287Exception
	 */
	public void ajouterJuge(int idJuge, String prenom, String nom, int age)
			throws SQLException, IFT287Exception
	{
		try {
			Juge juge = new Juge(idJuge, prenom, nom, age, true);
			
			if(juges.exist(juge)) throw new IFT287Exception("Le juge est d�j� existant");			
			
			juges.ajouterJuge(juge);
			cx.getConnection().commit();
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
			throw e;
		}
		
	}

	//	— retirerJuge <idJuge>
	/**
	 * Permet de retirer un juge de la liste des juges disponibles.
	 * 
	 * @param idJuge	
	 * @param cx 
	 * @throws SQLException
	 * @throws IFT287Exception
	 */
	public void retirerJuge(int idJuge)
			throws SQLException, IFT287Exception
	{
		try {
			Juge j = juges.selectOne(idJuge);
			
			if(j == null) throw new IFT287Exception("Le juge n'existe pas");
			if(!j.isActif()) throw new IFT287Exception("Le juge n'est deja pas disponible");
			
			juges.retirerJuge(j);
			cx.getConnection().commit();  
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
			throw e;
		}
	}
	

	//	— afficherJuges 
	/**
	 * Aﬃche la liste de tous les juges actifs.
	 * @param cx 
	 *    
	 * @throws SQLException
	 * @throws IFT287Exception
	 */
	public void afficherJuges(Connexion cx)
			throws SQLException, IFT287Exception
	{
		try {
			juges.afficherJuges();		
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
			throw e;
		}
		
	}

	public ArrayList<Juge> getJuges() throws SQLException {		
		return juges.getJuges();
	}
}
