<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="myapp.dao.GridDao" %>
<%@ page import="myapp.model.Grid" %>
<%@ page import="java.util.List" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    Integer idUser = (Integer) session.getAttribute("idUser");
%>

<script>
    // Récupération des données transmises depuis le serveur
    const idFGrid = <%= request.getAttribute("idFGrid") %>;
    const dimX = <%= request.getAttribute("dimX") %>;
    const dimY = <%= request.getAttribute("dimY") %>;
    const gridName = "<%= request.getAttribute("name") %>";
    const difficulty = "<%= request.getAttribute("difficulty") %>";
    const createdAt = "<%= request.getAttribute("createdAt") %>";
    const solutionsRows = JSON.parse('<%= request.getAttribute("solutionsRows") %>');
    const solutionsColumns = JSON.parse('<%= request.getAttribute("solutionsColumns") %>');
    const hintsRows = JSON.parse('<%= request.getAttribute("hintsRows") %>');
    const hintsColumns = JSON.parse('<%= request.getAttribute("hintsColumns") %>');
    const idUser = <%= request.getAttribute("idUser") %>;

    // Affichage dans la console pour vérification
    /*console.log("Grille ID:", idFGrid);
    console.log("Dimensions:", dimX, "x", dimY);
    console.log("Nom de la grille:", gridName);
    console.log("Difficulté:", difficulty);
    console.log("Date de création:", createdAt);
    console.log("Solutions (Lignes):", solutionsRows);
    console.log("Solutions (Colonnes):", solutionsColumns);
    console.log("Indices (Lignes):", hintsRows);
    console.log("Indices (Colonnes):", hintsColumns);
    console.log("ID Utilisateur:", idUser);*/
</script>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Résolution d'une grille</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/solveGrid.css">
	<script src="<%=request.getContextPath()%>/js/solveGrid.js" defer></script>
</head>
<body>
 <header>
        <div class="logo">Mots Croisés</div>
        <div class="user-info">
            <% if (username != null && role != null) { %>
                <p>Bienvenue, <%= username %> (<%= role %>)</p>
                <a href="<%=request.getContextPath()%>/logout" class="btn">Déconnexion</a>
            <% } else { %>
                <a href="<%=request.getContextPath()%>/views/login.jsp" class="btn">Se connecter</a>
                <a href="<%=request.getContextPath()%>/views/register.jsp" class="btn">S'inscrire</a>
            <% } %>
            <a href="<%= request.getContextPath() %>/grids" class="btn">Retour à l'accueil</a>
            
        </div>
    </header>
 <script>

 function validateGrid() {
	    let error = false;

	    // Récupération des données de la grille utilisateur
	    let rows = grid.readRows();
	    let columns = grid.readColumns();

	    // Récupération des solutions depuis les variables globales chargées
	    const expectedRows = solutionsRows; // Ces variables doivent être chargées depuis le backend
	    const expectedColumns = solutionsColumns;

	    // Comparaison des solutions
	    rows.forEach((row, index) => {
	        if (row !== expectedRows[index]) {
	            error = true;
	        }
	    });

	    columns.forEach((column, index) => {
	        if (column !== expectedColumns[index]) {
	            error = true;
	        }
	    });

	    if (error) {
	        alert("La grille contient des erreurs. Veuillez réessayer.");
	    } else {
	        alert("Félicitations, la grille est correcte !");
	    }
	}
</script>
 

     <% 
            String responseMessage = (String) request.getAttribute("responseMessage");
            if (responseMessage != null) { 
        %>
            <div class="response"><%= responseMessage %></div>
        <% } %>

<div class="grid-hints">
	<section class="grid-container">
		<div class="grid">
			<table id="table">
				<tbody id="tbody">

				</tbody>
			</table>
		</div>
	</section>
	<section class="hints-container">
		<div class="hints">
			<div id="horizontals-hints">
				<h2>Horizontalement</h2>
				<div id="rows-hints">

				</div>
			</div>
			<div id="verticals-hints">
				<h2>Verticalement</h2>
				<div id="columns-hints">

				</div>
			</div>
			
		</div>
	</section>
</div>

<div class="validation">
	<input type="button" value="Vérifier le résultat" class="validateGrid" onclick="validateGrid()">
</div>

</body>
</html>
