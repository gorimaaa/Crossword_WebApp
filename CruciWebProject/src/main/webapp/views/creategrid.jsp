<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="myapp.model.Grid" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    Integer idUser = (Integer) session.getAttribute("idUser");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Création de la grille</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/creategrid.css">
	<script src="<%=request.getContextPath()%>/js/creategrid.js" defer></script>
</head>
<body>
 <header>
        <div class="logo">Mots Croisés</div>
        <div class="user-info">
            <% if (username != null && role != null) { %>
                <p>Bienvenue, <%= username %> (<%= role %>)</p>
                <a href="logout" class="btn">Déconnexion</a>
            <% } else { %>
                <a href="<%=request.getContextPath()%>/views/login.jsp" class="btn">Se connecter</a>
                <a href="<%=request.getContextPath()%>/views/register.jsp" class="btn">S'inscrire</a>
            <% } %>
        </div>
    </header>
 <script>

    function validateGrid() {
        let error = false;
        // Récupération du nom de la grille et de la difficulté
        const gridName = document.getElementById("gridName").value.trim();
        const difficulty = document.getElementById("difficulty").value;

        if (!gridName) {
            alert("Veuillez entrer un nom pour la grille.");
            return;
        }

        let rows = grid.readRows();
        let columns = grid.readColumns();
        let rows_hints = grid.readRowsHints();
        let columns_hints = grid.readColumnsHints();
        let dim_x = rows.length;
        let dim_y = columns.length;

        // Vérification des `rows` et `columns` pour détecter des espaces
        rows.forEach((row, index) => {
            if (row.includes(" ")) {
                error = true;
            }
        });

        columns.forEach((column, index) => {
            if (column.includes(" ")) {
                error = true;
            }
        });

        if (error) {
            alert("Merci de remplir toutes la grille");
            return;
        }
	let vara =15;
        // Envoi des données au backend via une requête POST
        fetch("<%= request.getContextPath() %>/CreateGridServlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: new URLSearchParams({
                rows: JSON.stringify(rows),
                columns: JSON.stringify(columns),
                rows_hints: JSON.stringify(rows_hints),
                columns_hints: JSON.stringify(columns_hints),
                gridName: gridName,
                difficulty: difficulty,
                dim_x: dim_x,
                dim_y: dim_y,
                userId: <%= idUser %>,
            }).toString(),
        })
            .then((response) => {
                if (response.ok) {
                    alert("Grille sauvegardée avec succès !");
                } else {
                    alert("Erreur lors de la sauvegarde de la grille.");
                }
            })
            .catch((error) => {
                console.error("Erreur :", error);
                alert("Erreur lors de la communication avec le serveur.");
            });
    }
</script>
 

     <% 
            String responseMessage = (String) request.getAttribute("responseMessage");
            if (responseMessage != null) { 
        %>
            <div class="response"><%= responseMessage %></div>
        <% } %>
<section class="container-dimensions">
	<div class="dimensions">
		<h3>Sélectionnez une dimension pour votre grille</h3>
		<label for="horizontal-dim">Cases horizontales:</label>
		<select name="h-d" id="dim-horizontal">
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
		</select>

		<label for="vertical-dim">Cases verticales:</label>
		<select name="v-d" id="dim-vertical">
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
		</select>
		<input type="button" value="Valider" onclick="gridDimensions()"/>
	</div>
</section>
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
<section class="grid-setup">
    <form id="grid-form">
        <div class="grid-name">
            <label for="gridName">Nom de la grille :</label>
            <input type="text" id="gridName" name="gridName" required>
        </div>
        <div class="grid-difficulty">
            <label for="difficulty">Difficulté :</label>
            <select id="difficulty" name="difficulty" required>
                <option value="easy">Facile</option>
                <option value="medium">Moyenne</option>
                <option value="hard">Difficile</option>
            </select>
        </div>
    </form>
</section>
<div class="validation">
	<input type="button" value="Valider la grille et les indices" class="validateGrid" onclick="validateGrid()">
</div>

</body>
</html>