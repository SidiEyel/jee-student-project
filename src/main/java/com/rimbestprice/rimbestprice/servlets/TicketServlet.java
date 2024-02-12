package com.rimbestprice.rimbestprice.servlets;

import com.rimbestprice.rimbestprice.dao.FlightDao;
import com.rimbestprice.rimbestprice.dao.TicketDao;
import com.rimbestprice.rimbestprice.models.Flight;
import com.rimbestprice.rimbestprice.models.Ticket;
import com.rimbestprice.rimbestprice.models.TicketClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Uncomment the next line if you want to map this servlet to an URL via annotation
// @WebServlet("/ticket")
public class TicketServlet extends HttpServlet {

    private TicketDao ticketDao;
    private FlightDao flightDao;

    public void init() {
        ticketDao = new TicketDao();
        flightDao = new FlightDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addTicket(request, response);
                    break;
                case "update":
                    updateTicket(request, response);
                    break;
                case "delete":
                    deleteTicket(request, response);
                    break;
                case "edit":
                    editTicket(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> tickets = ticketDao.getAllTickets();
        List<Flight> flights = flightDao.getAllFlights();

        request.setAttribute("tickets", tickets);
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("/views/tickets.jsp").forward(request, response);
    }

    private void addTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String ticketNumber = request.getParameter("ticketNumber");
            Double price = Double.parseDouble(request.getParameter("price"));
            TicketClass ticketClass = TicketClass.valueOf(request.getParameter("ticketClass"));
            Integer numberOfTicketsAvailable = Integer.parseInt(request.getParameter("numberOfTicketsAvailable"));
            String flightNumber = request.getParameter("flightNumber");

            Flight flight = flightDao.getFlightByFlightNumber(flightNumber);
            if (flight != null) {
                Ticket ticket = new Ticket();
                ticket.setTicketNumber(ticketNumber);
                ticket.setPrice(price);
                ticket.setTicketClass(ticketClass);
                ticket.setNumberOfTicketsAvailable(numberOfTicketsAvailable);
                ticket.setFlight(flight);

                ticketDao.addTicket(ticket);
                response.sendRedirect("ticket");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Flight not found.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        }
    }

    private void updateTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String ticketNumber = request.getParameter("ticketNumber");
            Double price = Double.parseDouble(request.getParameter("price"));
            TicketClass ticketClass = TicketClass.valueOf(request.getParameter("ticketClass"));
            Integer numberOfTicketsAvailable = Integer.parseInt(request.getParameter("numberOfTicketsAvailable"));
            String flightNumber = request.getParameter("flightNumber");

            Ticket ticket = ticketDao.getTicketByTicketNumber(ticketNumber);
            Flight flight = flightDao.getFlightByFlightNumber(flightNumber);
            if (ticket != null && flight != null) {
                ticket.setPrice(price);
                ticket.setTicketClass(ticketClass);
                ticket.setNumberOfTicketsAvailable(numberOfTicketsAvailable);
                ticket.setFlight(flight);

                ticketDao.updateTicket(ticket);
                response.sendRedirect("ticket");
            } else {
                if (ticket == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket not found.");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Flight not found.");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        }
    }

    private void deleteTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ticketNumber = request.getParameter("ticketNumber");
        Ticket ticket = ticketDao.getTicketByTicketNumber(ticketNumber);
        if (ticket != null) {
            ticketDao.deleteTicket(ticketNumber);
            response.sendRedirect("ticket");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket not found.");
        }
    }

    private void editTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketNumber = request.getParameter("ticketNumber");
        Ticket ticket = ticketDao.getTicketByTicketNumber(ticketNumber);
        if (ticket != null) {
            List<Flight> flights = flightDao.getAllFlights();  // Fetch all flights for the dropdown
            request.setAttribute("ticketToEdit", ticket);
            request.setAttribute("flights", flights);
            request.setAttribute("action", "update");  // Set action as 'update' for the form in JSP
            request.getRequestDispatcher("/views/tickets.jsp").forward(request, response);  // Forward to tickets.jsp
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found.");
        }
    }

}
