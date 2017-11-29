package gestionnaireTable;

import java.sql.PreparedStatement;
import general.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Avocat;
import model.Juge;


public class Avocats {

	private Connexion cx;

	public Avocats(Connexion cx) {
		this.cx = cx;
	}


	public boolean exist(Avocat avocat) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM avocat WHERE id = ?");
		s.setInt(1, avocat.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}
	

	public Avocat selectOne(int idAvocat) throws SQLException {
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
	
	public int ajouterAvocat(Avocat avocat) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO avocat(id, prenom, nom, id_type) VALUES(?,?,?,?)");
		s.setInt(1, avocat.getId());
		s.setString(2, avocat.getPrenom());
		s.setString(3, avocat.getNom());
		s.setInt(4, avocat.getIdType());    	 

		return s.executeUpdate();
		
	}

	
	public ArrayList<Avocat> getAvocats() throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM avocat");
		ResultSet r = s.executeQuery();	
		
		ArrayList<Avocat> lstAvocat = new ArrayList<Avocat>();
		while (r.next()) {
			lstAvocat.add(new Avocat(r.getInt(1), r.getString(2), r.getString(3), "", r.getInt(4)));
		}
		
		return lstAvocat;
	}


}
