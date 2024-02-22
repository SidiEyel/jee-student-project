package com.rimbestprice.rimbestprice.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.mapping.List;

import com.rimbestprice.rimbestprice.dao.TicketDao;
import com.rimbestprice.rimbestprice.dao.UserReservationDao;
import com.rimbestprice.rimbestprice.models.Ticket;
import com.rimbestprice.rimbestprice.models.User;
import com.rimbestprice.rimbestprice.models.UserReservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

//@WebServlet(name = "PaymentServlet", urlPatterns = {"/make_payment"})
public class PaymentServlet extends HttpServlet {
    protected   TicketDao ticketdao ;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setAttribute("ticket", request.getParameter("ticket"));
                System.out.println(request.getParameter("ticket"));
        request.getRequestDispatcher("/views/payment.jsp").forward(request, response);
    }
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        ticketdao = new TicketDao();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumber = request.getParameter("cardNumber");
        String owner = request.getParameter("owner");
        String cvv = request.getParameter("cvv");
        String expirationDate = request.getParameter("expirationDate");
        String accountNumber = request.getParameter("accountNumber");
        String amount = request.getParameter("amount");

        String apiEndpoint = "http://127.0.0.1:8000/make_payment"; // Replace with your FastAPI endpoint

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(apiEndpoint);

        // Construct a JSON String
        String json = String.format("{\"card_number\": \"%s\", \"owner\": \"%s\", \"cvv\": \"%s\", \"expiration_date\": \"%s\", \"account_number\": \"%s\", \"amount\": %s}",
                cardNumber, owner, cvv, expirationDate, accountNumber, amount);

        // Set the request content to be JSON
        httppost.setHeader("Content-type", "application/json");
        httppost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        // Execute and get the response.
        HttpResponse httpResponse = httpclient.execute(httppost);

        // Read and output the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        boolean succes=false;
        if (result.toString().equals("Payment successful")) {
        Ticket ticket = ticketdao.getTicketByTicketNumber(request.getParameter("ticket"));
        System.out.println(ticket.toString());
  
        // if (result.equals("Payment successful")) {
            createReservation(request,ticket);
            System.out.println("Payment successful");
            ticket.setNumberOfTicketsAvailable(ticket.getNumberOfTicketsAvailable()-1);
            ticketdao.updateTicket(ticket);
              succes =true;
        }

        // You can further process the response or forward it to a JSP page
        System.out.println("Response from Payment API: " + result.toString());
        User user = (User) request.getSession().getAttribute("user");
        UserReservationDao reservationdReservation = new UserReservationDao();
        java.util.List<UserReservation> reservqvtions= reservationdReservation.getReservationsByUserId(user.getId());
        request.setAttribute("reservations", reservqvtions);
        request.setAttribute("success", succes);
        request.getRequestDispatcher("/views/reservations.jsp").forward(request, response);
    }

    private void createReservation(HttpServletRequest request,Ticket ticket) {
     System.out.println("user");
    User user = (User) request.getSession().getAttribute("user");

    UserReservation reservation = new UserReservation();
    reservation.setUser(user);  
    reservation.setTicket(ticket);
    reservation.setReservationTime(new Date());  
    UserReservationDao userReservationDao = new UserReservationDao();
    userReservationDao.addUserReservation(reservation);
}
}
