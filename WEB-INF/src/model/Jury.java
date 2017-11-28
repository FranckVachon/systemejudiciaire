package model;

public class Jury {
	
	private int nas;
	private String prenom;
	private String nom;
	private int age;
	private boolean actif;
	private char sexe;
	
		
	public Jury(int nas, String prenom, String nom, int age, boolean actif, char sexe) {
		super();
		this.nas = nas;
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.actif = actif;		
		this.sexe = sexe;
		
	}
	
	public int getNas() {
		return nas;
	}
	
	public void setNas(int nas) {
		this.nas = nas;
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
	
	public char getSexe() {
		return sexe;
	}
	
	public void setSexe(char sexe) {
		this.sexe = sexe;
	} 
	
	
	public void print() {
		System.out.println("Prenom: " + this.prenom + " Nom: " + this.nom + " Age: " + this.age + " Actif: " + this.actif +  " Sexe: " + this.sexe);		
	}
	
}
