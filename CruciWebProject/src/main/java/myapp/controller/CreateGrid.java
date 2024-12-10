package myapp.controller;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException; 

@WebServlet("/CreateGrid")
public class CreateGrid extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Rediriger vers la vue JSP
        request.getRequestDispatcher("/views/creategrid.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        // Rediriger vers la vue JSP
        request.getRequestDispatcher("/views/creategrid.jsp").forward(request, response);
    }
}

