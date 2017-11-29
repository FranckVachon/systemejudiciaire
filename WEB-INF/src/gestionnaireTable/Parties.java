package gestionnaireTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Avocat;
import model.Partie;
import general.Connexion;

public class Parties {

	private Connexion cx;
	private Avocats avocats;
	
	public Parties(Connexion cx) {
		this.cx = cx;
		this.avocats = new Avocats(cx);
	}

	public Partie selectOne(int id) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT p.id, p.prenom, p.nom, a.id, a.prenom, a.nom, a.id_type, t.nom "
				+ "FROM partie AS p "
				+ "INNER JOIN avocat AS a ON p.id_avocat = a.id "
				+ "INNER JOIN type_avocat AS t ON t.id = a.id_type "
				+ "WHERE p.id = ?");
		s.setInt(1, id);
		
		ResultSet r = s.executeQuery();		
		if(r.next()) {
			Avocat a = new Avocat(r.getInt(4),r.getString(5), r.getString(6), r.getString(8), r.getInt(7) );
			
			return new Partie(r.getInt(1), a, r.getString(2), r.getString(3));
		}			
		else 
			return null;
	}


	
	public boolean exist(Partie parti) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM partie WHERE id = ?");
		s.setInt(1, parti.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}	
	
	
	public void ajouterPartie(Partie partie) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO partie(id, prenom, nom, id_avocat) VALUES(?,?,?,?)");
		s.setInt(1, partie.getId());
		s.setString(2, partie.getPrenom());
		s.setString(3, partie.getNom());
		s.setInt(4, partie.getAvocat().getId());    	 

		s.executeUpdate();
		
	}

	public ArrayList<Partie> getParties() throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM partie");
		ResultSet r = s.executeQuery();	
		
		ArrayList<Partie> lstPartie= new ArrayList<Partie>();
		while (r.next()) {
			Avocat avocat = avocats.selectOne(r.getInt(4));
			lstPartie.add(new Partie(r.getInt(1), avocat, r.getString(2), r.getString(3)));
		}
		
		return lstPartie;
	}
}
