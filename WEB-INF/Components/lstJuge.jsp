
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
			<th>age</th>
			<th>actif</th>
			<th>retirer</th>
		</tr>		
		 <c:forEach items="${lstJuge}" var="juge">
			<tr>
				<td>${juge.id}</td>		
				<td>${juge.prenom}</td>
				<td>${juge.nom}</td>				
				<td>${juge.age}</td>
				<td>${juge.actif}</td>
				<td>
					<form action="Juges" method="get">
						<input class="btn_black" type="submit" value="Retirer" />
						<input type="hidden" name="removeJuge" value="${juge.id}">
					</form>
				</td>
			</tr>
	    </c:forEach>
	</table>
</div>