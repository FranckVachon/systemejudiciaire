package gestionnaireTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Jury;
import model.Partie;
import model.Proces;
import model.Seance;
import general.Connexion;

public class Seances {
	
	public static ArrayList<Seance> selectAll(int idProces, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT s.id, s.date, s.id_proces "
				+ "FROM seance AS s "			
				+ "INNER JOIN proces AS p ON p.id = s.id_proces "					
				+ "WHERE p.id = ?");
		
		s.setInt(1, idProces);		
		ResultSet r = s.executeQuery();
		
		ArrayList<Seance> seances = new ArrayList<Seance>();
		
		while(r.next()) {
			seances.add(new Seance(r.getInt(1), r.getDate(2), r.getInt(3)));
		}
		
		return seances;
	}

	public static void ajouterSeance(Seance seance, Proces proces, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO seance(id, id_proces, date) VALUES(?,?,?)");
		s.setInt(1, seance.getId());
		s.setInt(2, proces.getId());
		s.setDate(3, seance.getDate());

		s.executeUpdate();
		
	}

	public static void supprimmerSeance(Seance seance, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("DELETE FROM seance WHERE id = ?");
		s.setInt(1, seance.getId());

		s.executeUpdate();
		
	}

	public static boolean exist(Seance seance, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM seance WHERE id = ?");
		s.setInt(1, seance.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}

	public static Seance selectOne(int idSeance, Connexion cx) throws SQLException {		
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT s.id, s.date, s.id_proces "
				+ "FROM seance AS s "				
				+ "WHERE id = ?");
		s.setInt(1, idSeance);
		
		ResultSet r = s.executeQuery();		
		if(r.next()) {
			return new Seance(r.getInt(1), r.getDate(2), r.getInt(3));
		}			
		else 
			return null;
		
	}	

}
