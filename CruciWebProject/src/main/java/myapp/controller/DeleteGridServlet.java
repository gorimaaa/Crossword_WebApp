package myapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import myapp.dao.GridDao;

import java.io.IOException;

@WebServlet("/deleteGrid")
public class DeleteGridServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GridDao gridDao = new GridDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                boolean isDeleted = gridDao.deleteGridById(id);

                if (isDeleted) {
                    response.sendRedirect(request.getContextPath() + "/grids");
                } else {
                    response.getWriter().println("<h1>Échec de la suppression.</h1>");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("<h1>ID invalide.</h1>");
            }
        } else {
            response.getWriter().println("<h1>Aucune grille sélectionnée.</h1>");
        }
    }
}
