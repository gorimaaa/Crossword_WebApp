package myapp.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException; 
import myapp.dao.UserDao;

@WebServlet("/login") 
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao(); 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = userDao.getUsernameByEmail(email);
        int userId = userDao.getUserIdByEmail(email);
        String role = userDao.getUserRole(email, password);

        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("role", role);
            session.setAttribute("username", username);
            session.setAttribute("idUser", userId);

            response.sendRedirect(request.getContextPath() + "/grids");
        } else {
            // Ajoute un message d'erreur à la requête
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
