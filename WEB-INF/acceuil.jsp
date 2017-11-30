<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
<style>
html, body, div#main-panel {
	height: 100%;
}

img#background-splash {
	width: 90%;
	height: 90%;
	display: block;
	margin: auto;
	padding: 20px;
}
</style>
</head>

<body>
	<div id="main-panel">
		<jsp:include page="/WEB-INF/header.jsp" />
		<h1>Bienvenue dans le système judiciaire</h1>
		<img id="background-splash" src='res/courtroom.png' alt='[]' />
		<jsp:include page="/WEB-INF/messageErreur.jsp" />
		<jsp:include page="/WEB-INF/footer.jsp" />
	</div>
</body>
</html>