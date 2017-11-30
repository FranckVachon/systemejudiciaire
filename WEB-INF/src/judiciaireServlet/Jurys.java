package judiciaireServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import general.IFT287Exception;
import gestionnaireTransaction.GestionJudiciaire;

public class Jurys extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// verification de l'état de la session
			HttpSession session = request.getSession();
			Integer etat = (Integer) session.getAttribute("etat");
			if (etat == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			} else {
				session.setAttribute("etat", new Integer(JudiciaireConstantes.CONNECTE));
				GestionJudiciaire gJudiciaire = (GestionJudiciaire) session.getAttribute("gJudiciaire");
				request.setAttribute("lstJurys", gJudiciaire.getGestionJury().getJurys());
				
				if (request.getParameter("id") != null) {
					int id;
					String prenom;
					String nom;
					int age;
					char sexe;

					try {
						// Récuppère mes valeurs
						id = Integer.parseInt(request.getParameter("id"));
					} catch (NumberFormatException e) {
						throw new IFT287Exception("Format de id incorrect");
					}
					prenom = request.getParameter("prenom");
					nom = request.getParameter("nom");

					sexe = request.getParameter("sexe").charAt(0);
					try {
						age = Integer.parseInt(request.getParameter("age"));
					} catch (NumberFormatException e) {
						throw new IFT287Exception("Format de age incorrect");
					}
					
					gJudiciaire.getGestionJury().inscrireJury(id, prenom, nom, sexe, age);
					request.setAttribute("lstJurys", gJudiciaire.getGestionJury().getJurys());
				}
				
				
				// transfert de la requête à la page JSP pour affichage
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jurys.jsp");
				dispatcher.forward(request, response);
				session.setAttribute("etat", new Integer(JudiciaireConstantes.MEMBRE_SELECTIONNE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		} catch (IFT287Exception e) {
			List<String> listeMessageErreur = new LinkedList<String>();
			listeMessageErreur.add(e.toString());
			request.setAttribute("listeMessageErreur", listeMessageErreur);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jurys.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}