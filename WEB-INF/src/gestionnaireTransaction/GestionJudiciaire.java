package gestionnaireTransaction;

import java.sql.SQLException;

import general.Connexion;
import general.IFT287Exception;
import gestionnaireTable.Avocats;
import gestionnaireTable.Juges;
import gestionnaireTable.Jurys;
import gestionnaireTable.Parties;
import gestionnaireTable.Process;
import gestionnaireTable.Seances;

public class GestionJudiciaire {
	private Connexion cx;
	private GestionAvocat gestionAvocat;
	private GestionJuge gestionJuge;
	private GestionJury gestionJury;
	private GestionPartie gestionPartie;
	private GestionProces gestionProces;
	private Avocats avocats;
	private Juges juges;
	private Jurys jurys;
	private Parties parties;
	private Seances seances;
	private Process process;
	
	/**
	 * Ouvre une connexion avec la BD NoSQL et alloue les gestionnaires de
	 * transactions et de tables.
	 * 
	 * <pre>
	 * 
	 * @param serveur NoSQL (local ou dinf)
	 * @param bd nom de la base de données
	 * @param user user id pour établir une connexion avec le serveur NoSQL
	 * @param password mot de passe pour le user id
	 * </pre>
	 * @throws SQLException 
	 */
	public GestionJudiciaire(String serveur, String bd, String user, String password) throws IFT287Exception, SQLException {
		// allocation des objets pour le traitement des transactions
		cx = new Connexion(serveur, bd, user, password);
		avocats = new Avocats(cx);
		juges = new Juges(cx);
		jurys = new Jurys(cx);
		parties = new Parties(cx);
		seances = new Seances(cx);
		process = new Process(cx);
		gestionAvocat = new GestionAvocat(avocats, cx);	
		gestionJuge = new GestionJuge(juges,cx);
		gestionJury = new GestionJury(jurys, cx);
		gestionPartie = new GestionPartie(parties, avocats, cx);
		gestionProces = new GestionProces(process, seances, parties, juges, jurys, cx);
	}

	public void fermer() throws SQLException {
		// fermeture de la connexion
		cx.fermer();
	}

	public Connexion getCx() {
		return cx;
	}

	public void setCx(Connexion cx) {
		this.cx = cx;
	}

	public GestionAvocat getGestionAvocat() {
		return gestionAvocat;
	}

	public void setGestionAvocat(GestionAvocat gestionAvocat) {
		this.gestionAvocat = gestionAvocat;
	}

	public GestionJuge getGestionJuge() {
		return gestionJuge;
	}

	public void setGestionJuge(GestionJuge gestionJuge) {
		this.gestionJuge = gestionJuge;
	}

	public GestionJury getGestionJury() {
		return gestionJury;
	}

	public void setGestionJury(GestionJury gestionJury) {
		this.gestionJury = gestionJury;
	}

	public GestionPartie getGestionPartie() {
		return gestionPartie;
	}

	public void setGestionPartie(GestionPartie gestionPartie) {
		this.gestionPartie = gestionPartie;
	}

	public GestionProces getGestionProces() {
		return gestionProces;
	}

	public void setGestionProces(GestionProces gestionProces) {
		this.gestionProces = gestionProces;
	}

	public Connexion getConnexion() {
		return cx;
	}

}
