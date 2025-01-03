<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="myapp.dao.GridDao" %>
<%@ page import="myapp.model.Grid" %>
<%
    String gridIdParam = request.getParameter("Gridid");
    Grid grid = null;

    if (gridIdParam != null) {
        try {
            int gridId = Integer.parseInt(gridIdParam);

            GridDao gridDao = new GridDao();
            grid = gridDao.getGridById(gridId);

            if (grid == null) {
                out.println("<p>La grille sélectionnée n'existe pas.</p>");
            }
        } catch (NumberFormatException e) {
            out.println("<p>ID de grille invalide.</p>");
        }
    } else {
        out.println("<p>Aucune grille sélectionnée.</p>");
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Résoudre la grille</title>
</head>
<body>
    <% if (grid != null) { %>
        <h1>Résolution de la grille : <%= grid.getName() %></h1>
        <p>Difficulté : <%= grid.getDifficulty() %></p>
        <p>Date de création : <%= grid.getCreatedAt() %></p>
    <% } %>
</body>
</html>
