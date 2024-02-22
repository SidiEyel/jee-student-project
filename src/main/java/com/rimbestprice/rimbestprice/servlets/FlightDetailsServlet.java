package com.rimbestprice.rimbestprice.servlets;

import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;

import com.rimbestprice.rimbestprice.dao.FlightDao;
import com.rimbestprice.rimbestprice.dao.TicketDao;
import com.rimbestprice.rimbestprice.models.Flight;
import com.rimbestprice.rimbestprice.models.Ticket;


public class FlightDetailsServlet extends HttpServlet {
    private FlightDao flightDao;
    private TicketDao  ticketDao;
    public void init() {
        flightDao = new FlightDao();
        ticketDao = new TicketDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightNumber = request.getParameter("flightNumber");
        Flight flight = flightDao.getFlightByFlightNumber(flightNumber);
        List<Ticket> tickets = ticketDao.getTicketsByFlightNumber(flightNumber);
        List<Ticket> tickets2 = new ArrayList<>(); // Use ArrayList instead of List

        for (Ticket ticket : tickets) {
            if (ticket.getNumberOfTicketsAvailable() > 0) {
                tickets2.add(ticket);
            }
        }
        
        if (flight != null) {
            request.setAttribute("flightNumber", flight.getFlightNumber());
            request.setAttribute("departureCity", flight.getDepartureCity());
            request.setAttribute("arrivalCity", flight.getArrivalCity());
            request.setAttribute("departureDate", flight.getDepartureDate());
            request.setAttribute("arrivalDate", flight.getArrivalDate());
            request.setAttribute("price", flight.getPrice());
            request.setAttribute("companyAerienne", flight.getCompanyAerienne().getNom());
            request.setAttribute("tickets", tickets2);
            request.getRequestDispatcher("/views/flightDetails.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Flight not found.");
        }
    }
}
