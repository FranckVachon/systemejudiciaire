
<%@ page import="java.util.*,java.text.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 

<div class="panel">
	<table id="lstJury">
		<tr>
			<th>id</th>
			<th>prenom</th>
			<th>nom</th>
			<th>sexe</th>
			<th>age</th>
		</tr>
		 <c:forEach items="${lstJurys}" var="jury">
			<tr>
				<td>${jury.nas}</td>		
				<td>${jury.prenom}</td>
				<td>${jury.nom}</td>
				<td>${jury.sexe}</td>
				<td>${jury.age}</td>
			</tr>        
	    </c:forEach>
	</table>
</div>