package gestionnaireTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Juge;
import general.Connexion;

public class Juges {
	
	public static Juge selectOne(int id, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT * FROM juge "								
				+ "WHERE id = ?");
		s.setInt(1, id);
		
		ResultSet r = s.executeQuery();		
		if(r.next())
			return new Juge(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4), r.getBoolean(5));
		else 
			return null;
	}
	
	public static boolean exist(Juge juge, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM juge WHERE id = ?");
		s.setInt(1, juge.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}

	public static int ajouterJuge(Juge juge, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO juge(id, prenom, nom, age) VALUES(?,?,?,?)");
		
		s.setInt(1, juge.getId());
		s.setString(2, juge.getPrenom());
		s.setString(3, juge.getNom());
		s.setInt(4, juge.getAge());
	
		return s.executeUpdate();
	}
	
	public static int retirerJuge(Juge juge, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("UPDATE juge SET actif = ? WHERE id = ?");
		s.setBoolean(1, false);
		s.setInt(2, juge.getId());
		
		return s.executeUpdate();
	}
	
	public static void afficherJuges(Connexion cx) throws SQLException {
		
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM juge WHERE actif = ?");
		s.setBoolean(1, true);		
	
		ResultSet rs = s.executeQuery();
		  	
		while (rs.next()) { 
			Juge j = new Juge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5));       
			j.print();  	       
		} 
		cx.getConnection().commit();	
	}

}
