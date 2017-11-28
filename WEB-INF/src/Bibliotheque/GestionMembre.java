package Bibliotheque;

import java.sql.*;

/**
 * Gestion des transactions de reliées à la création et suppresion de membres
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
 * création et suppresion de membres.
 *
 * Pré-condition
 *   la base de données de la bibliothèque doit exister
 *
 * Post-condition
 *   le programme effectue les maj associées à chaque
 *   transaction
 * </pre>
 */

public class GestionMembre
{
    private Connexion cx;
    private TableMembres membre;
    private TableReservations reservation;

    /**
     * Creation d'une instance
     */
    public GestionMembre(TableMembres membre, TableReservations reservation) throws BiblioException
    {
        this.cx = membre.getConnexion();
        if (membre.getConnexion() != reservation.getConnexion())
            throw new BiblioException("Les instances de TableMembres et de TableReservations n'utilisent pas la même connexion au serveur");
        this.membre = membre;
        this.reservation = reservation;
    }
    
    public boolean existe(int idMembre)
            throws SQLException
    {
        return this.membre.existe(idMembre);
    }

    /**
     * Ajout d'un nouveau membre dans la base de donnees. S'il existe deja, une
     * exception est levee.
     */
    public void inscrire(int idMembre, String nom, long telephone, int limitePret)
            throws SQLException, BiblioException, Exception
    {
        try
        {
            // Vérifie si le membre existe déja
            if (membre.existe(idMembre))
                throw new BiblioException("Membre existe deja: " + idMembre);

            // Ajout du membre.
            membre.inscrire(idMembre, nom, telephone, limitePret);
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Suppression d'un membre de la base de donnees.
     */
    public void desinscrire(int idMembre) throws SQLException, BiblioException, Exception
    {
        try
        {
            // Vérifie si le membre existe et son nombre de pret en cours
            TupleMembre tupleMembre = membre.getMembre(idMembre);
            if (tupleMembre == null)
                throw new BiblioException("Membre inexistant: " + idMembre);
            if (tupleMembre.getNbPret() > 0)
                throw new BiblioException("Le membre " + idMembre + " a encore des prets.");
            if (reservation.getReservationMembre(idMembre) != null)
                throw new BiblioException("Membre " + idMembre + " a des réservations");

            // Suppression du membre
            int nb = membre.desinscrire(idMembre);
            if (nb == 0)
                throw new BiblioException("Membre " + idMembre + " inexistant");
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}// class
