<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Juges</title>
<style>
	<%@ include file="/WEB-INF/css/style.css" %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/header.jsp" />	
	<div id="main-panel">
		<h1>Juges</h1>
		<div>
			<jsp:include page="/WEB-INF/Components/lstJuge.jsp" />
		</div>
		<div>
			<jsp:include page="/WEB-INF/Components/frmJuge.jsp" />
		</div>		
		<jsp:include page="/WEB-INF/messageErreur.jsp" />
		<jsp:include page="/WEB-INF/footer.jsp" />
	</div>
</body>
</html>