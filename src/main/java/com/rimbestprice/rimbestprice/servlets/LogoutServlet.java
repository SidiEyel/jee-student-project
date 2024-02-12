package com.rimbestprice.rimbestprice.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Invalidate the session and remove any user data
        request.getSession().invalidate();

        // Redirect to the login page or your application's landing page
        response.sendRedirect("views/login.jsp"); // Replace "login.jsp" with the page you want to redirect to
    }
}

