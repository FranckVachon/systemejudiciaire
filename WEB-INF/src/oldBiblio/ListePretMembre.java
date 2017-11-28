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
 * Classe traitant la requête provenant de la page listePretMembre.jsp
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */

public class ListePretMembre extends HttpServlet
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
        else if (etat.intValue() != JudiciaireConstantes.MEMBRE_SELECTIONNE)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("retourner") != null)
            traiterRetourner(request, response);
        else if (request.getParameter("renouveler") != null)
            traiterRenouveler(request, response);
        else if (request.getParameter("emprunter") != null)
            traiterEmprunter(request, response);
        else if (request.getParameter("selectionMembre") != null)
            traiterSelectionMembre(request, response);
        else
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add("Choix non reconnu");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void traiterRetourner(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if (request.getParameter("pretSelectionne") == null)
                throw new BiblioException("Aucun prêt sélectionné");
            int idLivre = Integer.parseInt(request.getParameter("pretSelectionne"));
            String dateRetour = (new Date(System.currentTimeMillis())).toString();
            GestionBibliotheque biblioUpdate = (GestionBibliotheque) request.getSession().getAttribute("biblioUpdate");
            // exécuter la maj en utilisant synchronized pour s'assurer
            // que le thread du servlet est le seul à exécuter une transaction
            // sur biblio
            synchronized (biblioUpdate)
            {
                biblioUpdate.getGestionPret().retourner(idLivre, dateRetour);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (BiblioException e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public void traiterRenouveler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if (request.getParameter("pretSelectionne") == null)
                throw new BiblioException("Aucun prêt sélectionné");
            int idLivre = Integer.parseInt(request.getParameter("pretSelectionne"));
            String datePret = (new Date(System.currentTimeMillis())).toString();
            GestionBibliotheque biblioUpdate = (GestionBibliotheque) request.getSession().getAttribute("biblioUpdate");
            synchronized (biblioUpdate)
            {
                biblioUpdate.getGestionPret().renouveler(idLivre, datePret);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (BiblioException e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/listePretMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    public void traiterEmprunter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/emprunt.jsp");
        dispatcher.forward(request, response);
    }

    public void traiterSelectionMembre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
