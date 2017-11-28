<%@ page import="java.util.*,java.text.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<style>
div#nav-container {
	width: 100%;
	display: inline-block;
	background-color: #000;
	border-radius: 5px;
}

ul {
	padding: 0;
	margin: 0;
	list-style-type: none;
}

li {
	margin-left: 2px;
	float: left; /*pour IE*/
}

ul li a {
	display: block;
	float: left;
	width: 115px;
	margin: 5px 0;
	background-color: #111111;
	color: white;
	text-decoration: none;
	text-align: center;
	padding: 5px;
	border: 2px solid;
	border-radius: 5px;
}

ul li a:hover {
	background-color: #D3D3D3;
	border-color: #696969 #DCDCDC #DCDCDC #696969;
	color: black;
}
</style>

<div id="nav-container">
	<ul class="list-inline">
		<li><a href="/tp6/Acceuil">Acceuil</a></li>
		<li><a href="/tp6/TableauDeBord">Tableau de Bord</a></li>
		<li><a href="/tp6/Proces">Proces</a></li>
		<li><a href="/tp6/Juges">Juges</a></li>
		<li><a href="/tp6/Avocats">Avocats</a></li>
		<li><a href="/tp6/Jurys">Jurys</a></li>
		<li><a href="/tp6/Parties">Parties</a></li>
		<li><a href="/tp6/Logout">Deconnection</a></li>
	</ul>
</div>