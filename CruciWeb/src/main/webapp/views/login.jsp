<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - Mots Croisés</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #333;
            color: white;
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
        }

        .btn {
            padding: 8px 16px;
            color: white;
            background-color: #007BFF;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        /* Section principale */
        main {
            padding: 20px;
            max-width: 500px;
            margin: 50px auto;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }

        input[type="email"],
        input[type="password"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }

        button {
            padding: 10px 15px;
            font-size: 16px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218838;
        }

        .back-link {
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            color: #007BFF;
            text-decoration: none;
        }

        .back-link a:hover {
            text-decoration: underline;
        }
        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <header>
        <div class="logo">Mots Croisés</div>
        <a href="<%= request.getContextPath() %>/grids" class="btn">Retour à l'accueil</a>
    </header>
    <main>
        <h2>Connexion</h2>
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
            <div class="error"><%= errorMessage %></div>
        <% } %>
        <form action="<%= request.getContextPath() %>/login" method="post" accept-charset="UTF-8">
            <label for="email">Adresse email :</label>
			<input type="email" id="email" name="email" placeholder="Entrez votre adresse email" required>


            <label for="password">Mot de passe :</label>
            <input type="password" id="password" name="password" placeholder="Entrez votre mot de passe" required>

            <button type="submit">Se connecter</button>
        </form>
        <div class="back-link">
            <p>Pas encore inscrit ? <a href="<%= request.getContextPath() %>/views/register.jsp">Créer un compte</a></p>
        </div>
    </main>
</body>
</html>
