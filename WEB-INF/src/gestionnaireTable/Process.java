package gestionnaireTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Avocat;
import model.Juge;
import model.Jury;
import model.Partie;
import model.Proces;
import model.Seance;
import general.Connexion;

public class Process {
	
	public static Proces selectOne(int idProces, Connexion cx) throws Exception {
		PreparedStatement s =  cx.getConnection().prepareStatement(""
				+ "SELECT p.id, p.date_debut, p.devant_jury, p.complet, p.id_poursuite, p.id_defense, p.id_decision, p.id_juge, d.nom "
				+ "FROM proces AS p "		
				+ "LEFT JOIN decision AS d ON p.id_decision = d.id "						
				+ "WHERE p.id = ?");
		
		s.setInt(1, idProces);		
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			Juge juge = Juges.selectOne(r.getInt(8), cx);
			Partie poursuite = Parties.selectOne(r.getInt(5), cx);
			Partie defense = Parties.selectOne(r.getInt(6), cx);
			ArrayList<Seance> seances = Seances.selectAll(idProces, cx);
			ArrayList<Jury> jurys = new ArrayList<Jury>(); 
			if( r.getBoolean(3))
				jurys = Jurys.selectAllByProces(idProces, cx);
			return new Proces(r.getInt(1),juge,poursuite, defense, r.getDate(2), seances, r.getBoolean(4), r.getBoolean(3),jurys,  r.getString(9));
			
		}
		else 
			return null;		
	}
	
	public static void creeProces(Proces proces, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("INSERT INTO proces(id, id_juge, date_debut, devant_jury, id_poursuite, id_defense) VALUES(?,?,?,?,?,?)");
		s.setInt(1, proces.getId());
		s.setInt(2, proces.getJuge().getId());
		s.setDate(3, proces.getDebut());
		if(!proces.getJurys().isEmpty())
			s.setBoolean(4, true);
		else
			s.setBoolean(4, false);
		s.setInt(5, proces.getPoursuite().getId());
		s.setInt(6, proces.getDefense().getId());

		s.executeUpdate();
		
	}

	public static void terminerProces(Proces proces, Connexion cx) throws SQLException {
		PreparedStatement s =  cx.getConnection().prepareStatement("UPDATE proces SET complet = ?, id_decision = ? WHERE id = ?");
		s.setBoolean(1, true);
		if(proces.getDecision() == "innocent")
			s.setInt(2, 0);
		else
			s.setInt(2, 1);
		s.setInt(3, proces.getId());

		s.executeUpdate();
		
	}

	public static void afficherProces(Proces proces, Connexion cx) throws SQLException {
		// JUGE QUERY AND DATE
		PreparedStatement s = cx.getConnection().prepareStatement(
				"SELECT juge.id AS jid, juge.prenom AS jprenom, juge.nom AS jnom, juge.age AS jage, juge.actif AS jactif, "
				+ "proces.devant_jury AS pjury, proces.complet AS pcomplet, proces.date_debut AS pdate,"
				+ " seance.id as sid, seance.date AS sdate, seance.id_proces AS sidproces, decision.nom AS ddecision "
				+ "FROM proces "
				+ "INNER JOIN juge ON (juge.id = proces.id_juge) "
				+ "LEFT JOIN seance ON (proces.id = seance.id_proces) "
				+ "LEFT JOIN decision ON (proces.id_decision = decision.id) "
				+ "WHERE proces.id = ?");
		s.setInt(1, proces.getId());			
		ResultSet rs = s.executeQuery();
		
		Juge juge = null; 
		ArrayList<Seance> seances = new ArrayList<Seance>();
		boolean devantJury = false;
		Date dateDebut = null;
		boolean complet = false;
		String decision = "";
		
		while (rs.next()) { 
			juge = new Juge(rs.getInt("jid"), rs.getString("jprenom"), rs.getString("jnom"), rs.getInt("jage"), rs.getBoolean("jactif"));
			
			if(rs.getDate("sdate") != null) {
				seances.add(new Seance(rs.getInt("sid"), rs.getDate("sdate"), rs.getInt("sidproces")));
			}
			
			devantJury = rs.getBoolean("pjury");	
			dateDebut = rs.getDate("pdate");
			complet = rs.getBoolean("pcomplet");
			decision = rs.getString("ddecision");
		}
		
		proces.setJuge(juge);
		proces.setDebut(dateDebut);;
		proces.setComplet(complet);
		proces.setDevantJury(devantJury);
		proces.setseances(seances);
		proces.setDecision(decision);
		
		
		//Partie Defense
		PreparedStatement s2 = cx.getConnection().prepareStatement(
				"SELECT partie.id as pid, partie.prenom as pprenom, partie.nom as pnom, "
						+ "		     avocat.id as aid, avocat.prenom as aprenom, avocat.nom as anom, "
						+ "			 type_avocat.nom as tatype, avocat.id_type AS atype "
						+ "			 FROM proces 						"
						+ "			 INNER JOIN partie ON (partie.id = proces.id_defense)"
						+ "			 INNER JOIN avocat ON (avocat.id = partie.id_avocat) "
						+ "			 INNER JOIN type_avocat ON (avocat.id_type = type_avocat.id) "
						+ "			 WHERE proces.id = ?");
		
		s2.setInt(1, proces.getId());
		ResultSet rs2 = s2.executeQuery();
		
		while (rs2.next()) {
			Avocat avocat = new Avocat(rs2.getInt("aid"), rs2.getString("aprenom"), rs2.getString("anom"), rs2.getString("tatype"), rs2.getInt("atype"));
			Partie partie = new Partie(rs2.getInt("pid"), avocat, rs2.getString("pprenom"), rs2.getString("pnom"));
			partie.setAvocat(avocat);
			proces.setDefense(partie);
		}
		
		//Partie offense
		PreparedStatement s3 = cx.getConnection().prepareStatement(
		"SELECT partie.id as pid, partie.prenom as pprenom, partie.nom as pnom, " 
		 + "				     avocat.id as aid, avocat.prenom as aprenom, avocat.nom as anom, " 
		 + "					 type_avocat.nom as atype, avocat.id_type AS aidtype " 
		 + "					 FROM proces 						" 
		 + "					 INNER JOIN partie ON (partie.id = proces.id_poursuite) " 
		 + "					 INNER JOIN avocat ON (avocat.id = partie.id_avocat) " 
		 + "					 INNER JOIN type_avocat ON (avocat.id_type = type_avocat.id) " 
		 + "					 WHERE proces.id = ? ");
				 
		s3.setInt(1, proces.getId());
		ResultSet rs3 = s3.executeQuery();
		
		while (rs3.next()) {
			Avocat avocat = new Avocat(rs3.getInt("aid"), rs3.getString("aprenom"), rs3.getString("anom"),rs3.getString("atype"),rs3.getInt("aidtype"));
			Partie partie = new Partie(rs3.getInt("pid"), avocat, rs3.getString("pprenom"), rs3.getString("pnom"));
			partie.setAvocat(avocat);
			proces.setPoursuite(partie);				
		}
		
	    // JURY QUERY
		PreparedStatement s4 = cx.getConnection().prepareStatement(
				"SELECT jury.nas AS jnas, jury.prenom AS jprenom, jury.nom AS jnom, "
				+ "jury.age AS jage, jury.actif AS jactif, sexe.nom AS ssexe  "
				+ "FROM proces "
				+ "LEFT JOIN proces_jury ON (proces.id = proces_jury.id_proces) "
				+ "LEFT JOIN jury ON (proces_jury.id_jury = jury.nas) "
				+ "LEFT JOIN sexe ON (sexe.id =jury.id_sexe) "
				+ "WHERE proces.id = ?"
				);
		
		s4.setInt(1, proces.getId());			
		ResultSet rs4 = s4.executeQuery();
		
		while (rs3.next()) {
			proces.getJurys().add(new Jury(rs4.getInt("jnas"), rs4.getString("jprenom"),rs4.getString("jnom"),rs4.getInt("jage"), rs4.getBoolean("jactif"), rs4.getString("ssexe").charAt(0)));
		}
		
		cx.getConnection().commit();  
	
		proces.print();;

	}

	public static boolean exist(Proces proces, Connexion cx) throws SQLException {
		
		PreparedStatement s =  cx.getConnection().prepareStatement("SELECT * FROM Proces WHERE id = ?");
		s.setInt(1, proces.getId());
		
		ResultSet r = s.executeQuery();
		
		return r.next();
			
	}



}
