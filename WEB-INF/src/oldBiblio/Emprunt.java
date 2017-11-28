package oldBiblio;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Bibliotheque.BiblioException;
import Bibliotheque.GestionBibliotheque;
import judiciaireServlet.JudiciaireConstantes;

/**
 * Classe traitant la requête provenant de la page emprunt.jsp
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */

public class Emprunt extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else if (etat.intValue() != JudiciaireConstantes.MEMBRE_SELECTIONNE
                || request.getParameter("selectionMembre") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
            dispatcher.forward(request, response);
        }
        else
            try
            {
                String idLivreParam = request.getParameter("idLivre");
                request.setAttribute("idLivre", idLivreParam);

                // conversion du parametre idLivre en entier
                int idLivre = -1; // inialisation requise par compilateur Java
                try
                {
                    idLivre = Integer.parseInt(idLivreParam);
                }
                catch (NumberFormatException e)
                {
                    throw new BiblioException("Format de no Livre " + idLivreParam + " incorrect.");
                }

                // exécuter la transaction
                String datePret = (new Date(System.currentTimeMillis())).toString();
                int idMembre = Integer.parseInt((String) request.getSession().getAttribute("idMembre"));
                GestionBibliotheque biblioUpdate = (GestionBibliotheque) request.getSession()
                        .getAttribute("biblioUpdate");
                synchronized (biblioUpdate)
                {
                    biblioUpdate.getGestionPret().preter(idLivre, idMembre, datePret);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
                dispatcher.forward(request, response);
            }
            catch (BiblioException e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/emprunt.jsp");
                dispatcher.forward(request, response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
            }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
