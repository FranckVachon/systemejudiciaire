package model;

public class Juge {

	private int id;
	private String prenom;
	private String nom;
	private int age;
	private boolean actif;

	public Juge(int id, String prenom, String nom, int age, boolean actif) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.actif = actif;
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
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isActif() {
		return actif;
	}
	
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	public void print() {
		System.out.println("Prenom: " + this.prenom + " Nom: " + this.nom + " Age: " + this.age + " Actif: " + this.actif);		
	}

}
