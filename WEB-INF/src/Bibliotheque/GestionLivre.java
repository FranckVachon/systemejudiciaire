package Bibliotheque;

import java.sql.*;

/**
 * Gestion des transactions de reliées à la création et suppresion de livres
 * dans une bibliothèque.
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
 * Ce programme permet de gérer les transaction reliées à la 
 * création et suppresion de livres.
 *
 * Pré-condition
 *   la base de données de la bibliothèque doit exister
 *
 * Post-condition
 *   le programme effectue les maj associées à chaque
 *   transaction
 * </pre>
 */
public class GestionLivre
{
    private TableLivres livre;
    private TableReservations reservation;
    private Connexion cx;

    /**
     * Creation d'une instance
     */
    public GestionLivre(TableLivres livre, TableReservations reservation) throws BiblioException
    {
        this.cx = livre.getConnexion();
        if (livre.getConnexion() != reservation.getConnexion())
            throw new BiblioException("Les instances de livre et de reservation n'utilisent pas la même connexion au serveur");
        this.livre = livre;
        this.reservation = reservation;
    }

    /**
     * Ajout d'un nouveau livre dans la base de données. S'il existe deja, une
     * exception est levée.
     */
    public void acquerir(int idLivre, String titre, String auteur, String dateAcquisition)
            throws SQLException, BiblioException, Exception
    {
        try
        {
            // Vérifie si le livre existe déja
            if (livre.existe(idLivre))
                throw new BiblioException("Livre existe deja: " + idLivre);

            // Ajout du livre dans la table des livres
            livre.acquerir(idLivre, titre, auteur, dateAcquisition);
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            // System.out.println(e);
            cx.rollback();
            throw e;
        }
    }

    /**
     * Vente d'un livre.
     */
    public void vendre(int idLivre) throws SQLException, BiblioException, Exception
    {
        try
        {
            // Validation
            TupleLivre tupleLivre = livre.getLivre(idLivre);
            if (tupleLivre == null)
                throw new BiblioException("Livre inexistant: " + idLivre);
            if (!tupleLivre.isIdMembreNull())
                throw new BiblioException("Livre " + idLivre + " prete a " + tupleLivre.getIdMembre());
            if (reservation.getReservationLivre(idLivre) != null)
                throw new BiblioException("Livre " + idLivre + " réservé ");

            // Suppression du livre.
            int nb = livre.vendre(idLivre);
            if (nb == 0)
                throw new BiblioException("Livre " + idLivre + " inexistant");
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
