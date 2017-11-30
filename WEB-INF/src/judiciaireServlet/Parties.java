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

public class Parties extends HttpServlet {
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
				
				request.setAttribute("lstAvocat", gJudiciaire.getGestionAvocat().getAvocats());
				request.setAttribute("lstPartie", gJudiciaire.getGestionPartie().getParties());
								
				if (request.getParameter("id") != null) {
					int id;
					String prenom;
					String nom;
					int idAvocat;										

					try {
						// Récuppère mes valeurs
						id = Integer.parseInt(request.getParameter("id"));
					} catch (NumberFormatException e) {
						throw new IFT287Exception("Format de id incorrect");
					}
					prenom = request.getParameter("prenom");
					nom = request.getParameter("nom");

					try {
						idAvocat = Integer.parseInt(request.getParameter("idAvocat"));
					} catch (NumberFormatException e) {
						throw new IFT287Exception("Format de age incorrect");
					}

					gJudiciaire.getGestionPartie().ajouterPartie(id, prenom, nom, idAvocat);
					request.setAttribute("lstPartie", gJudiciaire.getGestionPartie().getParties());
				}

				
				
				// transfert de la requête à la page JSP pour affichage
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/parties.jsp");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/parties.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}