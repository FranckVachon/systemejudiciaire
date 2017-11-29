package gestionnaireTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Jury;
import model.Proces;
import general.Connexion;

public class Jurys {

	private Connexion cx;

	public Jurys(Connexion cx) {
		this.cx = cx;
	}

	public boolean exist(Jury jury) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM avocat WHERE id = ?");
		s.setInt(1, jury.getNas());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
	}
	
	public void inscrireJury(Jury jury) throws SQLException {
		
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO jury(nas, prenom, nom, id_sexe, age) VALUES(?,?,?,?,?)");
		s.setInt(1, jury.getNas());
		s.setString(2, jury.getPrenom());
		s.setString(3, jury.getNom());

		if(Character.toUpperCase(jury.getSexe()) == 'M') {
			s.setInt(4, 1);	 
		}
		else if(Character.toUpperCase(jury.getSexe()) == 'F'){
			s.setInt(4, 0);
		}

		s.setInt(5, jury.getAge());

		s.executeUpdate();
		
	}

	public void afficherJurys() throws SQLException {

		PreparedStatement s =  cx.getConnection().prepareStatement(
				"SELECT jury.nas AS jnas, jury.prenom AS jprenom, jury.nom AS jnom, "
						+ "jury.age AS jage, jury.actif AS jactif, sexe.nom AS ssexe  "
						+ "FROM jury "
						+ "LEFT JOIN sexe ON (sexe.id =jury.id_sexe) "
						+ "WHERE jury.actif = ?");
		s.setBoolean(1, true);		

		ResultSet rs = s.executeQuery();

		cx.getConnection().commit();  
		while (rs.next()) { 
			Jury j = new Jury(rs.getInt("jnas"), rs.getString("jprenom"), rs.getString("jnom"), rs.getInt("jage"), rs.getBoolean("jactif"), rs.getString("ssexe").charAt(0));     
			j.print();  	       
		}
		 
		
	}

	public void assignerJury(Jury jury, Proces proces) throws SQLException {
		
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO proces_jury(id_proces, id_jury) VALUES(?,?)");
		s.setInt(1, proces.getId());
		s.setInt(2, jury.getNas());

		s.executeUpdate();
		
		PreparedStatement s2 =  cx.getConnection().prepareStatement("UPDATE jury SET actif = ? WHERE nas = ?");
		s2.setBoolean(1, false);
		s2.setInt(2, jury.getNas());
		
		s2.executeUpdate();
		
	}

	public void updateJury(Jury jury, Proces proces) throws SQLException {
		PreparedStatement s2 =  cx.getConnection().prepareStatement(
				"UPDATE jury AS j " +
				"SET actif = ? " +
				"FROM proces_jury pj " +
				"WHERE " + 
					"pj.id_jury = j.nas AND " +
					"pj.id_proces = ?");
		s2.setBoolean(1, jury.isActif());
		s2.setInt(2, jury.getAge());
		
		s2.executeUpdate();
				
	}

	public ArrayList<Jury> selectAllByProces(int idProces) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT j.nas, j.prenom, j.nom, j.age, j.actif, j.id_sexe, s.nom "
				+ "FROM jury AS j "			
				+ "INNER JOIN sexe AS s ON s.id = j.id_sexe"					
				+ "WHERE p.id = ?");
		
		s.setInt(1, idProces);		
		ResultSet r = s.executeQuery();
		
		ArrayList<Jury> jurys = new ArrayList<Jury>();
		
		while(r.next()) {
			jurys.add(new Jury(r.getInt("j.nas"), r.getString("j.prenom"), r.getString("j.nom"), r.getInt("j.age"), r.getBoolean("j.actif"), r.getString("s.nom").charAt(0) ));
		}
		
		return jurys;
	}

	public Jury selectOne(int nas) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT j.nas, j.prenom, j.nom, j.age, j.actif, s.nom "
				+ "FROM jury AS j "
				+ "INNER JOIN sexe AS s ON j.id_sexe = s.id "				
				+ "WHERE j.nas = ?");
		s.setInt(1, nas);
		
		ResultSet r = s.executeQuery();		
		if(r.next()) {
			return new Jury(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4), r.getBoolean(5), r.getString(6).charAt(0));
		}			
		else 
			return null;
	}

	public ArrayList<Jury> getJurys() throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM jury");
		ResultSet r = s.executeQuery();	
		
		ArrayList<Jury> lstJury = new ArrayList<Jury>();
		while (r.next()) {
			lstJury.add(new Jury(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4), r.getBoolean(5), r.getString(6).charAt(0)));
		}
		
		return lstJury;
	}


}
