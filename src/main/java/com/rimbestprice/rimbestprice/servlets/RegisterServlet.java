package com.rimbestprice.rimbestprice.servlets;

// ... [import necessary classes]
import com.rimbestprice.rimbestprice.models.User;
import com.rimbestprice.rimbestprice.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        if (!userDao.userExists(username, email)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password); // This will hash the password

            userDao.addUser(newUser);

            // Redirect or show a success message
            response.sendRedirect("login"); // assuming you have a login page
        } else {
            // Handle the case where user already exists
            // For example, set an error message and forward back to the registration page
            request.setAttribute("errorMessage", "Username or Email already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}


