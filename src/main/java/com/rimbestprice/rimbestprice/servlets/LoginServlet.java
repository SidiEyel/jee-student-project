package com.rimbestprice.rimbestprice.servlets;

import com.rimbestprice.rimbestprice.models.User;
import com.rimbestprice.rimbestprice.dao.UserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername(username);

        if (user != null && new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            // User found with correct credentials
            request.getSession().setAttribute("user", user);

            // Adding user's admin status to session
            boolean isAdmin = user.isAdmin();
            request.getSession().setAttribute("is_admin", isAdmin);
            // User is authenticated, redirect to another page or dashboard
            //response.sendRedirect("views/home.jsp");
            //Replace "home.jsp" with the target page
            response.sendRedirect("home");
        } else {
            // Authentication failed, set error message and forward back to the login page
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
