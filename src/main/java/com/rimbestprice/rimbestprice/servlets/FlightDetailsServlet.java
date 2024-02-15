package com.rimbestprice.rimbestprice.servlets;

  
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

import com.rimbestprice.rimbestprice.dao.FlightDao;
import com.rimbestprice.rimbestprice.models.Flight;


public class FlightDetailsServlet extends HttpServlet {
    private FlightDao flightDao;

    public void init() {
        flightDao = new FlightDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightNumber = request.getParameter("flightNumber");
        Flight flight = flightDao.getFlightByFlightNumber(flightNumber);

        if (flight != null) {
            request.setAttribute("flightNumber", flight.getFlightNumber());
            request.setAttribute("departureCity", flight.getDepartureCity());
            request.setAttribute("arrivalCity", flight.getArrivalCity());
            request.setAttribute("departureDate", flight.getDepartureDate());
            request.setAttribute("arrivalDate", flight.getArrivalDate());
            request.setAttribute("price", flight.getPrice());
            request.setAttribute("companyAerienne", flight.getCompanyAerienne().getNom());

            request.getRequestDispatcher("/views/flightDetails.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Flight not found.");
        }
    }
}
