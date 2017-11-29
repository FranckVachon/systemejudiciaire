package model;

public class Partie {
	
	private int id;
	private Avocat avocat;
	private String prenom;
	private String nom;
	
	public Partie(int id, Avocat avocat, String prenom, String nom) {
		super();
		this.id = id;
		this.avocat = avocat;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Avocat getAvocat() {
		return avocat;
	}

	public void setAvocat(Avocat avocat) {
		this.avocat = avocat;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void print() {
		avocat.print();
		System.out.println("Le client: Prenom:"  + this.prenom + " Nom: " + this.nom);		
		
	}
	
	
}
