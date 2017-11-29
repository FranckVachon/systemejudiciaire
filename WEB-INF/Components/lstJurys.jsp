
<%@ page import="java.util.*,java.text.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<style>
table#lstJury {
	border-radius: 5px;
	border-collapse: collapse;
	border: solid 2px black;
}

table#lstJury th {
	background-color: #111111;
	color: white;
}

table#lstJury td, table#lstJury th {
	border: solid 1px black;
	border-collapse: collapse;
	padding: 10px;
	width: 75px;
	font-size: 18px;
}
</style>
<div>
	<table id="lstJury">
		<tr>
			<th>id</th>
			<th>prenom</th>
			<th>nom</th>
			<th>sexe</th>
			<th>age</th>
		</tr>
		<tr>
			<td>-1</td>
			<td>place</td>
			<td>holder</td>
			<td>M</td>
			<td>21</td>
		</tr>
	</table>
</div>