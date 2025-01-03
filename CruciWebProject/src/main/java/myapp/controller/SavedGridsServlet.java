package myapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import myapp.dao.SavedGridDao;
import myapp.model.SavedGrid;

@WebServlet("/savedGrids")
public class SavedGridsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SavedGridDao savedGridDao = new SavedGridDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");
        
        if (idUser == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        try {
            List<SavedGrid> savedGrids = savedGridDao.getSavedGridsByUser(idUser);

            request.setAttribute("savedGrids", savedGrids);

            request.getRequestDispatcher("/views/savedGrids.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue.");
        }
        
    }
}
