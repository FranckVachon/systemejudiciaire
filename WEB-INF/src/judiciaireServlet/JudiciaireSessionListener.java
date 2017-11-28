package judiciaireServlet;
import javax.servlet.http.*;
import gestionnaireTransaction.GestionJudiciaire;
import java.sql.*;

/**
 * Classe pour gestion des sessions
 * <P>
 * Système de gestion judiciaire 
 * Marc Dupuis, Francis Vachon 
 * Remerciements &copy; 2004 Marc Frappier, 
 * Université de Sherbrooke
 */

public class JudiciaireSessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("BiblioSessionListener " + se.getSession().getId());
        
        GestionJudiciaire gJudiciaire = (GestionJudiciaire)se.getSession().getAttribute("gJudiciaire");
        if (gJudiciaire != null)
        {
            System.out.println("connexion =" + gJudiciaire.getConnexion());
            try
            {
            	gJudiciaire.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Judiciaire inaccessible.");
        }
        // TODO Update
//        GestionJudiciaire biblioUpdate = (GestionJudiciaire)se.getSession().getAttribute("biblioUpdate");
//        if (biblioUpdate != null)
//        {
//            System.out.println("connexion = " + biblioUpdate.getConnexion());
//            try
//            {
//                biblioUpdate.fermer();
//            }
//            catch (SQLException e)
//            {
//                System.out.println("Impossible de fermer la connexion");
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            System.out.println("Judiciaire inaccessible.");
//        }
    }
}
