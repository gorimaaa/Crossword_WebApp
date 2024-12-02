package myapp.controller;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException; 

@WebServlet("/Home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ajouter un attribut pour le transmettre à la JSP
        request.setAttribute("message", "Bienvenue sur la page d'accueil!");

        // Rediriger vers la vue JSP
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
}

