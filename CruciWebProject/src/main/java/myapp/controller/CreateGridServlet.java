package myapp.controller;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import myapp.dao.GridDao;

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
        try {
            // Récupération des paramètres envoyés depuis le front-end
            int dimX = Integer.parseInt(request.getParameter("dim_x"));
            int dimY = Integer.parseInt(request.getParameter("dim_y"));
            String name = request.getParameter("gridName");
            String difficulty = request.getParameter("difficulty");
            String solutionsRows = request.getParameter("rows");
            String solutionsColumns = request.getParameter("columns");
            String hintsRows = request.getParameter("rows_hints");
            String hintsColumns = request.getParameter("columns_hints");
            int userId = Integer.parseInt(request.getParameter("userId"));
            GridDao gridDao = new GridDao();
            System.out.println(userId);

            // Insertion dans la base de données
            boolean success = gridDao.insertFinishedGrid(dimX, dimY, name, difficulty, solutionsRows, solutionsColumns, hintsRows, hintsColumns, userId);

            // Retourner une réponse HTTP appropriée
            if (success) {
                request.setAttribute("responseMessage", "La sauvegarde de la grille s'est bien passé.");
            } else {
                request.setAttribute("responseMessage", "Une erreur s'est passé lors de la création.");
            }
        } catch (Exception e) {
            // Gestion des erreurs
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("responseMessage", "Une erreur s'est passé lors de la création");
        }
    
              // Rediriger vers la vue JSP*/
        request.getRequestDispatcher("/views/creategrid.jsp").forward(request, response);
    }
}

