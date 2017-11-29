package gestionnaireTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Proces;
import model.Seance;
import general.Connexion;

public class Seances {
	
	private Connexion cx;
	
	public Seances(Connexion cx) {
		this.cx = cx;
	}

	public ArrayList<Seance> selectAll(int idProces) throws SQLException {
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

	public void ajouterSeance(Seance seance, Proces proces) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO seance(id, id_proces, date) VALUES(?,?,?)");
		s.setInt(1, seance.getId());
		s.setInt(2, proces.getId());
		s.setDate(3, seance.getDate());

		s.executeUpdate();
		
	}

	public void supprimmerSeance(Seance seance) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("DELETE FROM seance WHERE id = ?");
		s.setInt(1, seance.getId());

		s.executeUpdate();
		
	}

	public boolean exist(Seance seance) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM seance WHERE id = ?");
		s.setInt(1, seance.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}

	public Seance selectOne(int idSeance) throws SQLException {		
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
