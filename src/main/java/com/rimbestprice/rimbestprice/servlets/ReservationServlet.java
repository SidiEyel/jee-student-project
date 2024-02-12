package com.rimbestprice.rimbestprice.servlets;

import com.rimbestprice.rimbestprice.dao.CompanyAerienneDao;
import com.rimbestprice.rimbestprice.dao.FlightDao;
import com.rimbestprice.rimbestprice.models.CompanyAerienne;
import com.rimbestprice.rimbestprice.models.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

// @WebServlet("/flight")

public class ReservationServlet extends HttpServlet {
    private FlightDao flightDao;


    public void init() {
        flightDao = new FlightDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("pirnt action " + action);
        switch (action) {
            case "add":
                addFlight(request, response);
                break;


            default:
                loadReservationPage(request, response);
        }
    }

    private void loadReservationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Flight> flights = flightDao.getAllFlights();


        flights.sort(Comparator.comparing(Flight::getPrice));
        request.setAttribute("flights", flights);

        request.getRequestDispatcher("/views/reservation.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Flight> flights = flightDao.getAllFlights();


        flights.sort(Comparator.comparing(Flight::getPrice));
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("/views/reservation.jsp").forward(request, response);
    }

    private void addFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {

            String departureCity = request.getParameter("departureCity");
            String arrivalCity = request.getParameter("arrivalCity");
            Date departureDate = dateFormat.parse(request.getParameter("departureDate"));
            Date arrivalDate = dateFormat.parse(request.getParameter("arrivalDate"));


            Flight flight = new Flight();

            flight.setDepartureCity(departureCity);
            flight.setArrivalCity(arrivalCity);
            flight.setDepartureDate(departureDate);
            flight.setArrivalDate(arrivalDate);


            // Fetch CompanyAerienne by companyAerienneId and set it to flight


            flightDao.addFlight(flight);

            response.sendRedirect("flightList"); // Redirect to flightList.jsp
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
        }
    }

}




