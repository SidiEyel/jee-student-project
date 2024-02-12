package com.rimbestprice.rimbestprice.servlets;

import com.rimbestprice.rimbestprice.dao.FlightDao;
import com.rimbestprice.rimbestprice.models.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class FlightListServlet extends HttpServlet {

    private FlightDao flightDao;

    public void init() {
        flightDao = new FlightDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String departureCity = request.getParameter("departureCity");


        List<Flight> flights = flightDao.getByArgs(departureCity);
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("/views/flightList.jsp").forward(request, response);
    }
}
