<%@ page import="com.rimbestprice.rimbestprice.models.Flight" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: random
  Date: 2024-02-08
  Time: 12:42 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>flightList</title>
</head>
<body>
<h2>List of Flights</h2>
<table border="1">
    <tr>

        <th>Departure City</th>
        <th>Arrival City</th>
        <th>Departure Date</th>
        <th>Arrival Date</th>
        <th>Price</th>
        <th>Company Aerienne</th>
        <th>Details</th>
    </tr>
    <%
        List<Flight> flights = (List<Flight>) request.getAttribute("flights");
            Collections.sort(flights, Comparator.comparingDouble(Flight::getPrice));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        for (Flight flight : flights) {
    %>
    <tr>

        <td><%= flight.getDepartureCity() %></td>
        <td><%= flight.getArrivalCity() %></td>
        <td><%= sdf.format(flight.getDepartureDate()) %></td>
        <td><%= sdf.format(flight.getArrivalDate()) %></td>
        <td><%= flight.getPrice() %></td>
        <td><%= flight.getCompanyAerienne().getNom() %></td>
        <td>   <a href="<%= request.getContextPath() %>/flightDetails?flightNumber=<%= flight.getFlightNumber() %>">Details</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
