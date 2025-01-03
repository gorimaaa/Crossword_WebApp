<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="myapp.model.Grid" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    List<Grid> grids = (List<Grid>) request.getAttribute("grids");
    Integer idUser = (Integer) session.getAttribute("idUser");
    String sortOption = request.getParameter("sortOption"); // Option de tri sélectionnée
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Mots croisés</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <header>
        <div class="logo">Mots Croisés</div>
        <div class="user-info">
            <% if (username != null && role != null) { %>
                <p>Bienvenue, <%= username %> (<%= role %>)</p>
                <a href="logout" class="btn">Déconnexion</a>
            <% } else { %>
                <a href="views/login.jsp" class="btn">Se connecter</a>
                <a href="views/register.jsp" class="btn">S'inscrire</a>
            <% } %>
        </div>
    </header>
    <main>
        <h1>Bienvenue sur notre site de mots croisés !</h1>
        <% if ("user".equals(role)) { %>
            <a href="views/creategrid.jsp?idUser=<%= idUser %>" class="btn" style="margin-bottom: 20px;">Créer une grille</a>
            <a href="${pageContext.request.contextPath}/savedGrids" class="btn">Voir mes grilles sauvegardées</a>
        <% } else if ("admin".equals(role)) { %>
		<a href="${pageContext.request.contextPath}/manageUsers" class="btn">Gérer les utilisateurs</a>
        <% } %>
        <form action="grids" method="get" class="sort-form">
            <label for="sortOption">Trier par :</label>
            <select name="sortOption" id="sortOption">
                <option value="date" <%= "date".equals(sortOption) ? "selected" : "" %>>Date d'ajout</option>
                <option value="difficulty" <%= "difficulty".equals(sortOption) ? "selected" : "" %>>Difficulté</option>
            </select>
            <button type="submit">Appliquer</button>
        </form>
        <h2>Grilles disponibles</h2>
        <% if (grids == null || grids.isEmpty()) { %>
            <p>Aucune grille disponible pour le moment. Revenez plus tard !</p>
        <% } else { %>
            <div class="grid-list">
                <% for (Grid grid : grids) { %>
                    <div class="grid-item">
                        <h3>Nom : <%= grid.getName() %></h3>
                        <p>Difficulté : <%= grid.getDifficulty() %></p>
                        <p>Date de création : <%= grid.getCreatedAt() %></p>
                        <% if ("admin".equals(role)) { %>
                            <a href="deleteGrid?id=<%= grid.getId() %>" class="btn delete-btn">Supprimer cette grille</a>
                        <% } else { %>
                            <a href="views/solveGrid.jsp?Gridid=<%= grid.getId() %>&idUser=<%= idUser %>" class="btn">Résoudre cette grille</a>
                        <% } %>
                    </div>
                <% } %>
            </div>
        <% } %>
    </main>
</body>
</html>
