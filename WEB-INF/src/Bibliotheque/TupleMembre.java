package Bibliotheque;

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

public class TupleMembre
{
    private int idMembre;
    private String nom;
    private long telephone;
    private int limitePret;
    private int nbPret;
    
    public TupleMembre()
    {
    }
    
    public TupleMembre(int idMembre, String nom, long telephone, int limitePret, int nbPret)
    {
        this.setIdMembre(idMembre);
        this.setNom(nom);
        this.setTelephone(telephone);
        this.setLimitePret(limitePret);
        this.setNbPret(nbPret);
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
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }
    /**
     * @param nom the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    /**
     * @return the telephone
     */
    public long getTelephone()
    {
        return telephone;
    }
    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(long telephone)
    {
        this.telephone = telephone;
    }
    /**
     * @return the limitePret
     */
    public int getLimitePret()
    {
        return limitePret;
    }
    /**
     * @param limitePret the limitePret to set
     */
    public void setLimitePret(int limitePret)
    {
        this.limitePret = limitePret;
    }
    /**
     * @return the nbPret
     */
    public int getNbPret()
    {
        return nbPret;
    }
    /**
     * @param nbPret the nbPret to set
     */
    public void setNbPret(int nbPret)
    {
        this.nbPret = nbPret;
    }
}
