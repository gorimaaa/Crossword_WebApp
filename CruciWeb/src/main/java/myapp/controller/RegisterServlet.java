package myapp.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import myapp.dao.UserDao;

@WebServlet("/register") 
public class RegisterServlet extends HttpServlet {
    private UserDao userDao = new UserDao(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try {
            boolean isRegistered = userDao.registerUser(username, password, email);

            if (isRegistered) {
                response.sendRedirect("views/login.jsp");
            } else {
                request.setAttribute("errorMessage", "Nom d'utilisateur ou email déjà pris. Essayez un autre.");
                request.setAttribute("username", username); 
                request.setAttribute("email", email);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/register.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur est survenue lors de l'inscription.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
