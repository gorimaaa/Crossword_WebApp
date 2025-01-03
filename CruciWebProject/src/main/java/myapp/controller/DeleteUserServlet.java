package myapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import myapp.dao.UserDao;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idUserStr = request.getParameter("idUser");
        
        if (idUserStr == null || idUserStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID utilisateur manquant.");
            return;
        }
        
        try {
            int idUser = Integer.parseInt(idUserStr);
            boolean success = userDao.deleteUserById(idUser);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/manageUsers");
            } else {
                throw new Exception("Échec de la suppression de l'utilisateur avec ID : " + idUser);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID utilisateur invalide : " + idUserStr);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur SQL : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue lors de la suppression : " + e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
