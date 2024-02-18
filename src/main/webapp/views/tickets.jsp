<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rimbestprice.rimbestprice.models.Ticket"%>
<%@ page import="com.rimbestprice.rimbestprice.models.Flight"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tickets</title>
    <script>
        function populateForm(ticketNumber, price, ticketClass, numberOfTicketsAvailable, flightNumber) {
            document.getElementById('action').value = 'update';
            document.getElementById('ticketNumber').value = ticketNumber;
            document.getElementById('price').value = price;
            document.getElementById('ticketClass').value = ticketClass;
            document.getElementById('numberOfTicketsAvailable').value = numberOfTicketsAvailable;
            document.getElementById('flightNumber').value = flightNumber;
        }
    </script>
</head>
<body>
    <h1>Tickets</h1>
    <form action="ticket" method="post">
        <input type="hidden" name="action" id="action" value="<%= request.getAttribute("action") != null ? request.getAttribute("action") : "add" %>">
        Ticket Number: <input type="text" name="ticketNumber" id="ticketNumber"><br>
        Price: <input type="text" name="price" id="price"><br>
        Ticket Class:
        <select name="ticketClass" id="ticketClass">
            <option value="FIRST_CLASS">First Class</option>
            <option value="BUSINESS_CLASS">Business Class</option>
            <option value="ECONOMY_CLASS">Economy Class</option>
        </select><br>
        Number of Tickets Available: <input type="text" name="numberOfTicketsAvailable" id="numberOfTicketsAvailable"><br>
        Flight:
        <select name="flightNumber" id="flightNumber">
            <% List<Flight> flights = (List<Flight>) request.getAttribute("flights");
               for (Flight flight : flights) { %>
            <option value="<%= flight.getFlightNumber() %>"><%= flight.getFlightNumber() %></option>
            <% } %>
        </select><br>
        <input type="submit" value="Submit">
    </form>

    <h2>List of Tickets</h2>
    <table border="1">
        <tr>
            <th>Ticket Number</th>
            <th>Price</th>
            <th>Ticket Class</th>
            <th>Number of Tickets Available</th>
            <th>Date</th>
            <th>Flight</th>
            <th>Actions</th>
        </tr>
        <%
            List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
            for (Ticket ticket : tickets) {
        %>
        <tr>
            <td><%= ticket.getTicketNumber() %></td>
            <td><%= ticket.getPrice() %></td>
            <td><%= ticket.getTicketClass() %></td>
            <td><%= ticket.getNumberOfTicketsAvailable() %></td>
            <td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ticket.getCreationTime()) %></td>
            <td><%= ticket.getFlight().getFlightNumber() %></td>
            <td>
                <% if (ticket.canModifyTicket()) { %>
                    <button type="button" onclick="populateForm('<%= ticket.getTicketNumber() %>', '<%= ticket.getPrice() %>', '<%= ticket.getTicketClass() %>', '<%= ticket.getNumberOfTicketsAvailable() %>', '<%= ticket.getFlight().getFlightNumber() %>')">Edit</button>
                    <form action="ticket" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="ticketNumber" value="<%= ticket.getTicketNumber() %>">
                        <input type="submit" value="Delete">
                    </form>
                <% } else { %>
                    <span>Cannot modify this ticket.</span>
                <% } %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
