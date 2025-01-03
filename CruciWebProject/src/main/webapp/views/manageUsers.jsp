<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="myapp.model.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
String username = (String) session.getAttribute("username");
String role = (String) session.getAttribute("role");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des utilisateurs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <div class="logo">Mots Croisés</div>
        <div class="user-info">
            <p>Bienvenue, <%= username %> (<%= role %>)</p>
            <a href="${pageContext.request.contextPath}/logout" class="btn">Déconnexion</a>
            <a href="${pageContext.request.contextPath}/grids" class="btn">Accueil</a>
        </div>
    </header>
    <main>
        <h1>Gestion des utilisateurs</h1>
        <% if (users == null || users.isEmpty()) { %>
            <p>Aucun utilisateur trouvé.</p>
        <% } else { %>
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom d'utilisateur</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (User user : users) { %>
                        <tr>
                            <td><%= user.getId() %></td>
                            <td><%= user.getUsername() %></td>
                            <td><%= user.getEmail() %></td>
                            <td>
                                <form action="<%= request.getContextPath() %>/deleteUser" method="get">
                                    <input type="hidden" name="idUser" value="<%= user.getId() %>">
                                    <button type="submit" class="btn delete-btn">Supprimer</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    </main>
</body>
</html>
