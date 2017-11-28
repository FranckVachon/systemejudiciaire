package model;

import java.sql.Date;
import java.util.ArrayList;

public class Proces {
	
	private int id;
	private Juge juge;
	private Partie poursuite;
	private Partie defense;
	private Date debut;
	private ArrayList<Seance> seances; 
	private boolean complet;
	private boolean devantJury;
	private ArrayList<Jury> jurys;
	private String decision;
	
	public Proces(int id, Juge juge, Partie poursuite, Partie defense, Date debut, ArrayList<Seance> seances, boolean complet,
			boolean devantJury, ArrayList<Jury> jurys, String decision) throws Exception {
		super();
		this.id = id;
		this.juge = juge;
		this.poursuite = poursuite;
		this.defense = defense;
		this.debut = debut;
		this.seances = seances;
		this.complet = complet;
		this.devantJury = devantJury;		
		this.jurys = jurys;
	}

	public Proces() {
		seances = new ArrayList<Seance>();
		jurys = new ArrayList<Jury>();
	}

	public Proces(int idProces, Juge juge, Date dateInitiale, boolean devantJury, Partie defense,
			Partie poursuite) {
		this.id = idProces;
		this.juge = juge;
		this.poursuite = poursuite;
		this.defense = defense;
		this.debut = dateInitiale;
		this.seances = new ArrayList<Seance>();
		this.complet = false;
		this.devantJury = devantJury;		
		this.jurys = new ArrayList<Jury>();
	}

	public Juge getJuge() {
		return juge;
	}

	public void setJuge(Juge juge) {
		this.juge = juge;
	}

	public Partie getPoursuite() {
		return poursuite;
	}

	public void setPoursuite(Partie poursuite) {
		this.poursuite = poursuite;
	}

	public Partie getDefense() {
		return defense;
	}

	public void setDefense(Partie defense) {
		this.defense = defense;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public ArrayList<Seance> getseances() {
		return seances;
	}

	public void setseances(ArrayList<Seance> seances) {
		this.seances = seances;
	}

	public boolean isComplet() {
		return complet;
	}

	public void setComplet(boolean complet) {
		this.complet = complet;
	}

	public boolean isDevantJury() {
		return devantJury;
	}

	public void setDevantJury(boolean devantJury) {
		this.devantJury = devantJury;
	}

	public ArrayList<Jury> getJurys() {
		return jurys;
	}

	public void setJurys(ArrayList<Jury> jurys) {
		this.jurys = jurys;
	} 
		
	public ArrayList<Seance> getSeances() {
		return seances;
	}

	public void setSeances(ArrayList<Seance> seances) {
		this.seances = seances;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public void print() {
		System.out.println("Le procès:");
		System.out.print("Le juge: ");
		juge.print();
		System.out.print("La poursuite: ");
		poursuite.print();
		System.out.print("La defense: ");
		defense.print();
		if(this.devantJury) {
			System.out.println("Nos Jurys: ");
			for(Jury j : jurys) {
				j.print();
			}
		}
		System.out.println("================================");
		System.out.println("Début du procès: " + debut);
		if(seances.size() > 0 ) {
			System.out.println("Séance prévues : ");
			for(Seance seance : seances) {			
				seance.print();
			}
		}
		else {
			System.out.println("Aucune séeances prévues");
		}
		if(complet) {
			System.out.println("La décision: " + decision);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
