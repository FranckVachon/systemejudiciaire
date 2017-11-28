package model;

public class Avocat {

	private int id;
	private String prenom;
	private String nom;
	private int idType;
	private String type;
	
	public Avocat(int id, String prenom, String nom, String type, int idType) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.type = type;
		this.idType = idType;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void print() {
		System.out.println("L'honorable : Prenom: " + this.prenom + " Nom: " + this.nom +" Type: " + this.type);
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}
	
}
