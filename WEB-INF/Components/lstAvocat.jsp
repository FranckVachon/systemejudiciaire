
<%@ page import="java.util.*,java.text.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 

<div class="panel">
	<table id="lstJuge">
		<tr>
			<th>id</th>
			<th>prenom</th>
			<th>nom</th>
			<th>type</th>
		</tr>		
		 <c:forEach items="${lstAvocat}" var="avocat">
			<tr>
				<td>${avocat.id}</td>		
				<td>${avocat.prenom}</td>
				<td>${avocat.nom}</td>
				<td>${avocat.idType}</td>				
			</tr>
	    </c:forEach>
	</table>
</div>