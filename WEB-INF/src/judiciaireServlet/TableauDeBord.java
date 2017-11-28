package judiciaireServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TableauDeBord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
			// verification de l'état de la session
			HttpSession session = request.getSession();
			Integer etat = (Integer) session.getAttribute("etat");
			if (etat == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			} else {
				//session.setAttribute("idMembre", null);
				session.setAttribute("etat", new Integer(JudiciaireConstantes.CONNECTE));
				// lecture des paramètres du formulaire selectionMembre.jsp
				//String idMembreParam = request.getParameter("idMembre");
				//request.setAttribute("idMembre", idMembreParam);
				// conversion du parametre idMembre en entier
				//int idMembre = -1; // inialisation requise par compilateur Java
//				try {
//					idMembre = Integer.parseInt(idMembreParam);
//					// enregistrer dans la session le paramètre idMembre
//					// cette valeur sera utilisée dans listePretMembre.jsp
//					session.setAttribute("idMembre", idMembreParam);
//				} catch (NumberFormatException e) {
//					throw new BiblioException("Format de no Membre " + idMembreParam + " incorrect.");
//				}

//				// vérifier existence du membre
//				GestionBibliotheque biblioInterrogation = (GestionBibliotheque) session
//						.getAttribute("biblioInterrogation");
//				if (!biblioInterrogation.getGestionMembre().existe(idMembre))
//					throw new BiblioException("Membre " + idMembre + " inexistant.");

				// transfert de la requête à la page JSP pour affichage
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/tableauDeBord.jsp");
				dispatcher.forward(request, response);
				session.setAttribute("etat", new Integer(JudiciaireConstantes.MEMBRE_SELECTIONNE));
			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
//		} catch (BiblioException e) {
//			List<String> listeMessageErreur = new LinkedList<String>();
//			listeMessageErreur.add(e.toString());
//			request.setAttribute("listeMessageErreur", listeMessageErreur);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
//			dispatcher.forward(request, response);
//		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}