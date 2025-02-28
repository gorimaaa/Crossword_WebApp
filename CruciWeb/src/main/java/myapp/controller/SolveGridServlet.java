package myapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import myapp.dao.GridDao;
import myapp.dao.SavedGridDao;
import myapp.model.Grid;
import myapp.model.SavedGrid;

@WebServlet("/solveGrid")
public class SolveGridServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GridDao gridobj = new GridDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");
        String gridIdParam = request.getParameter("Gridid");
        int gridId = Integer.parseInt(gridIdParam);
        if (idUser == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }else {
        	Grid grid = gridobj.getGridInformationForSolving(gridId);
        	 if (grid != null) {
                 // Transmission des données de l'objet Grid à la JSP
                 request.setAttribute("idFGrid", grid.getId());
                 request.setAttribute("dimX", grid.getDimX());
                 request.setAttribute("dimY", grid.getDimY());
                 request.setAttribute("name", grid.getName());
                 request.setAttribute("difficulty", grid.getDifficulty());
                 request.setAttribute("createdAt", grid.getCreatedAt());
                 request.setAttribute("solutionsRows", grid.getSolutionsRows());
                 request.setAttribute("solutionsColumns", grid.getSolutionsColumns());
                 request.setAttribute("hintsRows", grid.getHintsRows());
                 request.setAttribute("hintsColumns", grid.getHintsColumns());
                 request.setAttribute("idUser", grid.getIdUser());
             } else {
                 // Si la grille n'existe pas
                 request.setAttribute("errorMessage", "La grille demandée n'existe pas.");
             }
            request.getRequestDispatcher("/views/solveGrid.jsp").forward(request, response);

        }
       
        
    }
}
