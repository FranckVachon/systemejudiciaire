package Bibliotheque;

import java.sql.*;

/**
 * Système de gestion d'une bibliothèque
 *
 * <pre>
 * Marc Frappier - 83 427 378
 * Université de Sherbrooke
 * version 2.0 - 13 novembre 2004
 * ift287 - exploitation de bases de données
 *
 * Vincent Ducharme
 * Université de Sherbrooke
 * version 3.0 - 17 juin 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de gérer les transaction de base d'une
 * bibliothèque.  Il gère des livres, des membres et des
 * réservations. Les données sont conservées dans une base de
 * données relationnelles accédée avec JDBC.
 *
 * Pré-condition
 *   la base de données de la bibliothèque doit exister
 *
 * Post-condition
 *   le programme effectue les maj associées à chaque
 *   transaction
 * </pre>
 */
public class GestionBibliotheque
{
    private Connexion cx;
    private TableLivres livre;
    private TableMembres membre;
    private TableReservations reservation;
    private GestionLivre gestionLivre;
    private GestionMembre gestionMembre;
    private GestionPret gestionPret;
    private GestionReservation gestionReservation;
    private GestionInterrogation gestionInterrogation;

    /**
     * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
     * de transactions et de tables.
     * 
     * <pre>
     * 
     * @param serveur SQL
     * @param bd nom de la bade de données
     * @param user user id pour établir une connexion avec le serveur SQL
     * @param password mot de passe pour le user id
     * </pre>
     */
    public GestionBibliotheque(String serveur, String bd, String user, String password)
            throws BiblioException, SQLException
    {
        // allocation des objets pour le traitement des transactions
        cx = new Connexion(serveur, bd, user, password);
        livre = new TableLivres(getConnexion());
        membre = new TableMembres(getConnexion());
        reservation = new TableReservations(getConnexion());
        setGestionLivre(new GestionLivre(livre, reservation));
        setGestionMembre(new GestionMembre(membre, reservation));
        setGestionPret(new GestionPret(livre, membre, reservation));
        setGestionReservation(new GestionReservation(livre, membre, reservation));
        setGestionInterrogation(new GestionInterrogation(getConnexion()));
    }

    public void fermer() throws SQLException
    {
        // fermeture de la connexion
        getConnexion().fermer();
    }

    /**
	 * @return the Connexion
	 */
	public Connexion getConnexion()
	{
		return cx;
	}

	/**
     * @return the gestionLivre
     */
    public GestionLivre getGestionLivre()
    {
        return gestionLivre;
    }

    /**
     * @param gestionLivre the gestionLivre to set
     */
    private void setGestionLivre(GestionLivre gestionLivre)
    {
        this.gestionLivre = gestionLivre;
    }

    /**
     * @return the gestionMembre
     */
    public GestionMembre getGestionMembre()
    {
        return gestionMembre;
    }

    /**
     * @param gestionMembre the gestionMembre to set
     */
    private void setGestionMembre(GestionMembre gestionMembre)
    {
        this.gestionMembre = gestionMembre;
    }

    /**
     * @return the gestionPret
     */
    public GestionPret getGestionPret()
    {
        return gestionPret;
    }

    /**
     * @param gestionPret the gestionPret to set
     */
    private void setGestionPret(GestionPret gestionPret)
    {
        this.gestionPret = gestionPret;
    }

    /**
     * @return the gestionReservation
     */
    public GestionReservation getGestionReservation()
    {
        return gestionReservation;
    }

    /**
     * @param gestionReservation the gestionReservation to set
     */
    private void setGestionReservation(GestionReservation gestionReservation)
    {
        this.gestionReservation = gestionReservation;
    }

    /**
     * @return the gestionInterrogation
     */
    public GestionInterrogation getGestionInterrogation()
    {
        return gestionInterrogation;
    }

    /**
     * @param gestionInterrogation the gestionInterrogation to set
     */
    private void setGestionInterrogation(GestionInterrogation gestionInterrogation)
    {
        this.gestionInterrogation = gestionInterrogation;
    }
}
