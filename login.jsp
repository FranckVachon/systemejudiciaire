<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>IFT287 - Système de gestion de bibliothèque</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="author" CONTENT="Marc Dupuis">
<META NAME="description"
CONTENT="Page d'accueil système de gestion juridique.">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion juridique</H1>
<FORM ACTION="Login" METHOD="POST">
    <BR>
    <BR>
    User Id : <INPUT TYPE="TEXT" NAME="userIdBD" VALUE="ift287_17">
    <BR>
    <BR>
    Mot de passe : <INPUT TYPE="PASSWORD" NAME="motDePasseBD" VALUE="reimah">
    <BR>
    <BR>
    Serveur : <SELECT NAME="serveur">
    			<OPTION VALUE="dinf">dinf
                <OPTION VALUE="local">local                
              </SELECT>
    <BR>
    <BR>
    <BR>
    BD : <INPUT TYPE="TEXT" NAME="bd"  VALUE="ift287_17db">
<BR>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Soumettre">
</FORM>
</CENTER>
<BR>
<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<%-- affichage de la date et heure; --%>
<%-- utile pour débogger et verifier si la page a été --%>
<%-- par le fureteur --%>
Date et heure normale de l'est: <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
</BODY>
</HTML>
