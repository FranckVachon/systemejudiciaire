<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Jurys</title>
<style>
 	<%@ include file="/WEB-INF/css/style.css" %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />
	<div id="main-panel">
		<h1>Jurys</h1>
		<p>Inputs a impl�menter inscrireJury 123456789 Joe Blau M 28
			afficherJurys (sous forme de table? ouais je crois bien, une
			component de pr�f�rence, on va utiliser �a pour le dashboard)</p>

		<div>
			<jsp:include page="/WEB-INF/Components/lstJury.jsp" />
		</div>
		<div>
		<jsp:include page="/WEB-INF/Components/frmJury.jsp" />
		</div>
		<h2>Maquette</h2>
		<img src="res/maquetteJury.png" />
		<jsp:include page="/WEB-INF/footer.jsp" />
	</div>

</body>

</html>