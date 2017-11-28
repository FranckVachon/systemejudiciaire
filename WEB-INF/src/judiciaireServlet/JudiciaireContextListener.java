package judiciaireServlet;

import javax.servlet.*;
import java.util.*;

/**
 * Classe pour gestion des sessions
 * <P>
 * Système de gestion judiciaire &copy; 2017 Marc Dupuis, Francis Vachon,  Université de
 * Remerciement Marc Frappier pour l'exemple de Bibliothèque &copy; 2004
 * Sherbrooke
 */

public class JudiciaireContextListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("Contexte du système judiciaire WEB démarré : " + sce.getServletContext().getServletContextName());
        System.out.println("Voici les paramètres du contexte tels que définis dans web.xml");
        Enumeration<String> initParams = sce.getServletContext().getInitParameterNames();
        while (initParams.hasMoreElements())
        {
            String name = (String) initParams.nextElement();
            System.out.println(name + ":" + sce.getServletContext().getInitParameter(name));
        }
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("Le contexte de l'application GestionBibliotheque vient d'être détruit.");
    }
}
