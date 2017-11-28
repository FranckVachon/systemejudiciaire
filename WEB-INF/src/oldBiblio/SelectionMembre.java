package oldBiblio;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Bibliotheque.BiblioException;
import Bibliotheque.GestionBibliotheque;
import judiciaireServlet.JudiciaireConstantes;

/**
 * Classe traitant la requête provenant de la page selectionMembre.jsp
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */

public class SelectionMembre extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            // verification de l'état de la session
            HttpSession session = request.getSession();
            Integer etat = (Integer) session.getAttribute("etat");
            if (etat == null)
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                session.setAttribute("idMembre", null);
                session.setAttribute("etat", new Integer(JudiciaireConstantes.CONNECTE));
                // lecture des paramètres du formulaire selectionMembre.jsp
                String idMembreParam = request.getParameter("idMembre");
                request.setAttribute("idMembre", idMembreParam);
                // conversion du parametre idMembre en entier
                int idMembre = -1; // inialisation requise par compilateur Java
                try
                {
                    idMembre = Integer.parseInt(idMembreParam);
                    // enregistrer dans la session le paramètre idMembre
                    // cette valeur sera utilisée dans listePretMembre.jsp
                    session.setAttribute("idMembre", idMembreParam);
                }
                catch (NumberFormatException e)
                {
                    throw new BiblioException("Format de no Membre " + idMembreParam + " incorrect.");
                }

                // vérifier existence du membre
                GestionBibliotheque biblioInterrogation = (GestionBibliotheque)session.getAttribute("biblioInterrogation");
                if (!biblioInterrogation.getGestionMembre().existe(idMembre))
                    throw new BiblioException("Membre " + idMembre + " inexistant.");

                // transfert de la requête à la page JSP pour affichage
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
                dispatcher.forward(request, response);
                session.setAttribute("etat", new Integer(JudiciaireConstantes.MEMBRE_SELECTIONNE));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
        catch (BiblioException e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
