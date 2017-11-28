package Bibliotheque;

import java.sql.*;

/**
 * Permet de représenter un tuple de la table livre.
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
 * </pre>
 */

public class TupleLivre
{
    private int idLivre;
    private String titre;
    private String auteur;
    private Date dateAcquisition;
    private int idMembre;
    private boolean idMembreNull;
    private Date datePret;

    public TupleLivre()
    {
    }

    public TupleLivre(int idLivre, String titre, String auteur, Date dateAcquisition, int idMembre, Date datePret)
    {
        this.setIdLivre(idLivre);
        this.setTitre(titre);
        this.setAuteur(auteur);
        this.setDateAcquisition(dateAcquisition);
        this.setIdMembre(idMembre);
        this.setDatePret(datePret);
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
     * @return the titre
     */
    public String getTitre()
    {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    /**
     * @return the auteur
     */
    public String getAuteur()
    {
        return auteur;
    }

    /**
     * @param auteur the auteur to set
     */
    public void setAuteur(String auteur)
    {
        this.auteur = auteur;
    }

    /**
     * @return the dateAcquisition
     */
    public Date getDateAcquisition()
    {
        return dateAcquisition;
    }

    /**
     * @param dateAcquisition the dateAcquisition to set
     */
    public void setDateAcquisition(Date dateAcquisition)
    {
        this.dateAcquisition = dateAcquisition;
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
     * @return the idMembreNull
     */
    public boolean isIdMembreNull()
    {
        return idMembreNull;
    }

    /**
     * @param idMembreNull the idMembreNull to set
     */
    public void setIdMembreNull(boolean idMembreNull)
    {
        this.idMembreNull = idMembreNull;
    }

    /**
     * @return the datePret
     */
    public Date getDatePret()
    {
        return datePret;
    }

    /**
     * @param datePret the datePret to set
     */
    public void setDatePret(Date datePret)
    {
        this.datePret = datePret;
    }
}
