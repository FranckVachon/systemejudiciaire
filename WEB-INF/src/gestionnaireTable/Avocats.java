package gestionnaireTable;

import java.sql.PreparedStatement;
import general.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Avocat;


public class Avocats {

	public static boolean exist(Avocat avocat, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM avocat WHERE id = ?");
		s.setInt(1, avocat.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}
	

	public static Avocat selectOne(int idAvocat, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT a.id, a.prenom, a.nom, t.nom,a.id_type "
				+ "FROM avocat AS a "				
				+ "INNER JOIN type_avocat AS t ON a.id_type = t.id "
				+ "WHERE a.id = ?");
		
		s.setInt(1, idAvocat);		
		ResultSet r = s.executeQuery();
		
		if(r.next())
			return new Avocat(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5));			
		else 
			return null;
	}
	
	public static int ajouterAvocat(Avocat avocat, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO avocat(id, prenom, nom, id_type) VALUES(?,?,?,?)");
		s.setInt(1, avocat.getId());
		s.setString(2, avocat.getPrenom());
		s.setString(3, avocat.getNom());
		s.setInt(4, avocat.getIdType());    	 

		return s.executeUpdate();
		
	}

}
