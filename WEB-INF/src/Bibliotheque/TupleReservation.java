package Bibliotheque;

import java.sql.*;

/**
 * Permet de représenter un tuple de la table membre.
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
 * </pre>
 */

public class TupleReservation
{
    private int idReservation;
    private int idLivre;
    private int idMembre;
    private Date dateReservation;
    
    public TupleReservation()
    {
    }
    
    public TupleReservation(int idReservation, int idLivre, int idMembre, Date dateReservation)
    {
        this.setIdReservation(idReservation);
        this.setIdLivre(idLivre);
        this.setIdMembre(idMembre);
        this.setDateReservation(dateReservation);
    }
    
    /**
     * @return the idReservation
     */
    public int getIdReservation()
    {
        return idReservation;
    }
    /**
     * @param idReservation the idReservation to set
     */
    public void setIdReservation(int idReservation)
    {
        this.idReservation = idReservation;
    }
    /**
     * @return the idLivre
     */
    public int getIdLivre()
    {
        return idLivre;
    }
    /**
     * @param idLivre the idLivre to set
     */
    public void setIdLivre(int idLivre)
    {
        this.idLivre = idLivre;
    }
    /**
     * @return the idMembre
     */
    public int getIdMembre()
    {
        return idMembre;
    }
    /**
     * @param idMembre the idMembre to set
     */
    public void setIdMembre(int idMembre)
    {
        this.idMembre = idMembre;
    }
    /**
     * @return the dateReservation
     */
    public Date getDateReservation()
    {
        return dateReservation;
    }
    /**
     * @param dateReservation the dateReservation to set
     */
    public void setDateReservation(Date dateReservation)
    {
        this.dateReservation = dateReservation;
    }
}
