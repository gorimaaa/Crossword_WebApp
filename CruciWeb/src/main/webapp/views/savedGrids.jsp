<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="myapp.model.SavedGrid" %>
<%
    Integer idUser = (Integer) session.getAttribute("idUser");
    List<SavedGrid> savedGrids = (List<SavedGrid>) request.getAttribute("savedGrids");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Grilles sauvegardées</title>
</head>
<body>
    <h1>Vos grilles sauvegardées</h1>
    <% if (idUser == null) { %>
        <p>Vous devez être connecté pour voir vos grilles sauvegardées.</p>
        <a href="views/login.jsp">Se connecter</a>
    <% } else { %>
        <% if (savedGrids == null || savedGrids.isEmpty()) { %>
            <p>Aucune grille sauvegardée trouvée.</p>
        <% } else { %>
            <ul>
                <% for (SavedGrid grid : savedGrids) { %>
                    <li>
                        <strong>Grille ID :</strong> <%= grid.getIdFGrid() %> <br>
                        <strong>Réponses lignes :</strong> <%= grid.getAnswersRows() %> <br>
                        <strong>Réponses colonnes :</strong> <%= grid.getAnswersColumns() %> <br>
                        <a href="views/solveGrid.jsp?Gridid=<%= grid.getIdFGrid() %>&idUser=<%= idUser %>">Continuer cette grille</a>
                    </li>
                    <br>
                <% } %>
            </ul>
        <% } %>
    <% } %>
</body>
</html>
