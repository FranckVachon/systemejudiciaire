
<%@ page import="java.util.*,java.text.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 

<div class="panel">
	<table id="lstPartie">
		<tr>
			<th>id</th>
			<th>prenom</th>
			<th>nom</th>
			<th>idAvocat</th>
		</tr>		
		 <c:forEach items="${lstPartie}" var="partie">
			<tr>
				<td>${partie.id}</td>		
				<td>${partie.prenom}</td>
				<td>${partie.nom}</td>
				<td>${partie.avocat.id}</td>				
			</tr>
	    </c:forEach>
	</table>
</div>