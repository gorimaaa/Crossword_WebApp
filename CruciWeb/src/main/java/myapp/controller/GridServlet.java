package myapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import myapp.dao.GridDao;
import myapp.model.Grid;

@WebServlet("/grids")
public class GridServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GridDao gridDao = new GridDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sortOption = request.getParameter("sortOption");
        if (sortOption == null) {
            sortOption = "date"; 
        }

        List<Grid> availableGrids = gridDao.getGridsSortedBy(sortOption);

        request.setAttribute("grids", availableGrids);
        request.setAttribute("sortOption", sortOption);

        request.getRequestDispatcher("/views/accueil.jsp").forward(request, response);
    }
}
