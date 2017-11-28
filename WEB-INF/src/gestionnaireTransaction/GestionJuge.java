package gestionnaireTransaction;

import java.sql.SQLException;
import java.util.ArrayList;

import gestionnaireTable.Juges;
import model.Juge;
import general.Connexion;
import general.IFT287Exception;

public class GestionJuge {
	
	private Juges juges;	
	
	GestionJuge(Juges juges){
		this.juges = juges;
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
	public void ajouterJuge(int idJuge, String prenom, String nom, int age, Connexion cx)
			throws SQLException, IFT287Exception
	{
		try {
			Juge juge = new Juge(idJuge, prenom, nom, age, true);
			
			if(Juges.exist(juge, cx)) throw new IFT287Exception("Le juge est déjà existant");			
			
			Juges.ajouterJuge(juge, cx);
			cx.getConnection().commit();
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
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
	public void retirerJuge(int idJuge, Connexion cx)
			throws SQLException, IFT287Exception
	{
		try {
			Juge j = Juges.selectOne(idJuge, cx);
			
			if(j == null) throw new IFT287Exception("Le juge n'existe pas");
			if(!j.isActif()) throw new IFT287Exception("Le juge n'est deja pas disponible");
			
			Juges.retirerJuge(j, cx);
			cx.getConnection().commit();  
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
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
			Juges.afficherJuges(cx);		
		}catch(Exception e ) {
			System.out.println(e);
			cx.rollback();
		}
		
	}
}
