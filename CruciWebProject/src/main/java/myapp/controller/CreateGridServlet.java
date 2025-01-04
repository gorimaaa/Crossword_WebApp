package myapp.controller;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException; 

@WebServlet("/CreateGridServlet")
public class CreateGridServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la vue JSP
        request.getRequestDispatcher("/views/creategrid.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Lire le corps de la requête
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        // Convertir en chaîne JSON
        String requestBody = jsonBuilder.toString();
        System.out.println("Body reçu : " + requestBody);

        /*// Optionnel : si vous voulez manipuler les données JSON
        // Vous pouvez utiliser une bibliothèque comme org.json, Gson ou Jackson
        JSONObject jsonObject = new JSONObject(requestBody); // Si vous utilisez org.json
        String gridName = jsonObject.getString("gridName");
        System.out.println("Nom de la grille : " + gridName);

        // Répondre au client
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"Requête traitée avec succès !\"}");
        // Rediriger vers la vue JSP*/
        request.getRequestDispatcher("/views/creategrid.jsp").forward(request, response);
    }
}

