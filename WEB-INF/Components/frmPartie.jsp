<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 

<form action="Parties" method="get">
	<div class="panel">
		<div class="row">
			<div class="col-xs-6">
				<label for="id">ID</label> <input name="id" type="number" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<label for="prenom">Prenom</label> <input name="prenom" type="text" />
			</div>
			<div class="col-xs-6">
				<label for="nom">Nom</label> <input name="nom" type="text" />
			</div>
		</div>
		<div class="row">				
			<div class="col-xs-6">
				<label for="age">Age</label> <input name="age" type="number" />
			</div>
			<div class="col-xs-6">
				<label for="idAvocat">Avocat</label>
				<div class="select-wrap">
					<select name="idAvocat">
						 <c:forEach items="${lstAvocat}" var="avocat">							
							<option value="${avocat.id}" selected="selected">${avocat.prenom} ${avocat.nom}</option>								
					    </c:forEach>					
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<input class="btn_black" type="submit" value="Ajouter">
		</div>
	</div>
</form>
