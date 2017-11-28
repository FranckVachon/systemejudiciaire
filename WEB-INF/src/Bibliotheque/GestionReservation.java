package Bibliotheque;

import java.sql.*;

/**
 * Gestion des transactions de reliées aux réservations de livres par les
 * membres dans une bibliothèque.
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
 * Ce programme permet de gérer les transactions réserver,
 * prendre et annuler.
 *
 * Pré-condition
 *   la base de données de la bibliothèque doit exister
 *
 * Post-condition
 *   le programme effectue les maj associées à chaque
 *   transaction
 * </pre>
 */

public class GestionReservation
{
    private TableLivres livre;
    private TableMembres membre;
    private TableReservations reservation;
    private Connexion cx;

    /**
     * Creation d'une instance. La connection de l'instance de livre et de
     * membre doit être la même que cx, afin d'assurer l'intégrité des
     * transactions.
     */
    public GestionReservation(TableLivres livre, TableMembres membre, TableReservations reservation) throws BiblioException
    {
        if (livre.getConnexion() != membre.getConnexion() || reservation.getConnexion() != membre.getConnexion())
            throw new BiblioException(
                    "Les instances de livre, de membre et de reservation n'utilisent pas la même connexion au serveur");
        this.cx = livre.getConnexion();
        this.livre = livre;
        this.membre = membre;
        this.reservation = reservation;
    }

    /**
     * Réservation d'un livre par un membre. Le livre doit être prêté.
     */
    public void reserver(int idReservation, int idLivre, int idMembre, String dateReservation)
            throws SQLException, BiblioException, Exception
    {
        try
        {
            // Verifier que le livre est preté
            TupleLivre tupleLivre = livre.getLivre(idLivre);
            if (tupleLivre == null)
                throw new BiblioException("Livre inexistant: " + idLivre);
            if (tupleLivre.isIdMembreNull())
                throw new BiblioException("Livre " + idLivre + " n'est pas prete");
            if (tupleLivre.getIdMembre() == idMembre)
                throw new BiblioException("Livre " + idLivre + " deja prete a ce membre");

            // Vérifier que le membre existe
            TupleMembre tupleMembre = membre.getMembre(idMembre);
            if (tupleMembre == null)
                throw new BiblioException("Membre inexistant: " + idMembre);

            // Verifier si date reservation >= datePret
            if (Date.valueOf(dateReservation).before(tupleLivre.getDatePret()))
                throw new BiblioException("Date de reservation inferieure à la date de pret");

            // Vérifier que la réservation n'existe pas
            if (reservation.existe(idReservation))
                throw new BiblioException("Réservation " + idReservation + " existe deja");

            // Creation de la reservation
            reservation.reserver(idReservation, idLivre, idMembre, dateReservation);
            
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
     * Prise d'une réservation. Le livre ne doit pas être prêté. Le membre ne
     * doit pas avoir dépassé sa limite de pret. La réservation doit la être la
     * première en liste.
     */
    public void prendreRes(int idReservation, String datePret) throws SQLException, BiblioException, Exception
    {
        try
        {
            // Vérifie s'il existe une réservation pour le livre
            TupleReservation tupleReservation = reservation.getReservation(idReservation);
            if (tupleReservation == null)
                throw new BiblioException("Réservation inexistante : " + idReservation);

            // Vérifie que c'est la première réservation pour le livre
            TupleReservation tupleReservationPremiere = reservation.getReservationLivre(tupleReservation.getIdLivre());
            if (tupleReservation.getIdReservation() != tupleReservationPremiere.getIdReservation())
                throw new BiblioException("La réservation n'est pas la première de la liste "
                        + "pour ce livre; la premiere est " + tupleReservationPremiere.getIdReservation());

            // Verifier si le livre est disponible
            TupleLivre tupleLivre = livre.getLivre(tupleReservation.getIdLivre());
            if (tupleLivre == null)
                throw new BiblioException("Livre inexistant: " + tupleReservation.getIdLivre());
            if (!tupleLivre.isIdMembreNull())
                throw new BiblioException("Livre " + tupleLivre.getIdLivre() + " deja prêté à " + tupleLivre.getIdMembre());

            // Vérifie si le membre existe et sa limite de pret
            TupleMembre tupleMembre = membre.getMembre(tupleReservation.getIdMembre());
            if (tupleMembre == null)
                throw new BiblioException("Membre inexistant: " + tupleReservation.getIdMembre());
            if (tupleMembre.getNbPret() >= tupleMembre.getLimitePret())
                throw new BiblioException("Limite de prêt du membre " + tupleReservation.getIdMembre() + " atteinte");

            // Verifier si datePret >= tupleReservation.dateReservation
            if (Date.valueOf(datePret).before(tupleReservation.getDateReservation()))
                throw new BiblioException("Date de prêt inférieure à la date de réservation");

            // Enregistrement du pret.
            if (livre.preter(tupleReservation.getIdLivre(), tupleReservation.getIdMembre(), datePret) == 0)
                throw new BiblioException("Livre supprimé par une autre transaction");
            if (membre.preter(tupleReservation.getIdMembre()) == 0)
                throw new BiblioException("Membre supprimé par une autre transaction");
            // Eliminer la réservation
            reservation.annulerRes(idReservation);
            
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
     * Annulation d'une réservation. La réservation doit exister.
     */
    public void annulerRes(int idReservation) throws SQLException, BiblioException, Exception
    {
        try
        {
            // Vérifier que la réservation existe
            if (reservation.annulerRes(idReservation) == 0)
                throw new BiblioException("Réservation " + idReservation + " n'existe pas");

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
