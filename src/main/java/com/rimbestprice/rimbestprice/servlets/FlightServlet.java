package com.rimbestprice.rimbestprice.servlets;

import com.rimbestprice.rimbestprice.dao.CompanyAerienneDao;
import com.rimbestprice.rimbestprice.dao.FlightDao;
import com.rimbestprice.rimbestprice.models.CompanyAerienne;
import com.rimbestprice.rimbestprice.models.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

// @WebServlet("/flight")
public class FlightServlet extends HttpServlet {
    private FlightDao flightDao;
    private CompanyAerienneDao companyAerienneDao;

    public void init() {
        flightDao = new FlightDao();
        companyAerienneDao = new CompanyAerienneDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("pirnt action "+ action);
        switch (action) {
            case "add":
                addFlight(request, response);
                break;
            case "update":
                updateFlight(request, response);
                break;
            case "delete":
                deleteFlight(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Flight> flights = flightDao.getAllFlights();
        List<CompanyAerienne> companies = companyAerienneDao.getAllCompanyAeriennes(); // Fetch the companies

        flights.sort(Comparator.comparing(Flight::getPrice));
        request.setAttribute("flights", flights);
        request.setAttribute("companies", companies); // Set companies as a request attribute
        request.getRequestDispatcher("/views/flights.jsp").forward(request, response);
    }

    private void addFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            String flightNumber = request.getParameter("flightNumber");
            String departureCity = request.getParameter("departureCity");
            String arrivalCity = request.getParameter("arrivalCity");
            Date departureDate = dateFormat.parse(request.getParameter("departureDate"));
            Date arrivalDate = dateFormat.parse(request.getParameter("arrivalDate"));
            double price = Double.parseDouble(request.getParameter("price"));
            Long companyAerienneId = Long.parseLong(request.getParameter("companyAerienneId"));

            Flight flight = new Flight();
            flight.setFlightNumber(flightNumber);
            flight.setDepartureCity(departureCity);
            flight.setArrivalCity(arrivalCity);
            flight.setDepartureDate(departureDate);
            flight.setArrivalDate(arrivalDate);
            flight.setPrice(price);

            // Fetch CompanyAerienne by companyAerienneId and set it to flight
            CompanyAerienne companyAerienne = companyAerienneDao.getCompanyAerienneById(companyAerienneId);
            flight.setCompanyAerienne(companyAerienne);

            flightDao.addFlight(flight);

            response.sendRedirect("flight"); // Redirect to avoid double submission
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
        }
    }

    private void updateFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            String flightNumber = request.getParameter("flightNumber");
            String departureCity = request.getParameter("departureCity");
            String arrivalCity = request.getParameter("arrivalCity");
            String departureDateString = request.getParameter("departureDate");
            String arrivalDateString = request.getParameter("arrivalDate");
            Date departureDate = (departureDateString != null && !departureDateString.isEmpty()) ? dateFormat.parse(departureDateString) : null;
            Date arrivalDate = (arrivalDateString != null && !arrivalDateString.isEmpty()) ? dateFormat.parse(arrivalDateString) : null;
            double price = Double.parseDouble(request.getParameter("price"));
            Long companyAerienneId = Long.parseLong(request.getParameter("companyAerienneId"));

            Flight flight = flightDao.getFlightByFlightNumber(flightNumber);
            if (flight != null) {
                flight.setDepartureCity(departureCity);
                flight.setArrivalCity(arrivalCity);
                flight.setDepartureDate(departureDate);
                flight.setArrivalDate(arrivalDate);
                flight.setPrice(price);

                // Fetch CompanyAerienne by companyAerienneId and set it to flight
                CompanyAerienne companyAerienne = companyAerienneDao.getCompanyAerienneById(companyAerienneId);
                flight.setCompanyAerienne(companyAerienne);

                flightDao.updateFlight(flight); // Save the updated flight
            }

            response.sendRedirect("flight"); // Redirect to avoid double submission
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
        }
    }

    private void deleteFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flightNumber = request.getParameter("flightNumber");
        flightDao.deleteFlight(flightNumber);
        response.sendRedirect("flight");
    }

    private void editFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightNumber = request.getParameter("flightNumber");
        Flight flight = flightDao.getFlightByFlightNumber(flightNumber);
        if (flight != null) {
            request.setAttribute("flightToEdit", flight); // Set the flight as a request attribute
            request.setAttribute("action", "update"); // Set action as 'update'
            request.getRequestDispatcher("/views/flights.jsp").forward(request, response); // Forward to the JSP page for editing
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Flight not found.");
        }
    }

}
