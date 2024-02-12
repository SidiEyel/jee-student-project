<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rimbestprice.rimbestprice.models.Flight"%>
<%@ page import="com.rimbestprice.rimbestprice.models.CompanyAerienne"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Flights</title>
    <script>
        function populateForm(flightNumber, departureCity, arrivalCity, departureDate, arrivalDate, price, companyId) {
            document.getElementById('action').value = 'update';
            document.getElementById('flightNumber').value = flightNumber;
            document.getElementById('departureCity').value = departureCity;
            document.getElementById('arrivalCity').value = arrivalCity;
            document.getElementById('departureDate').value = departureDate.substring(0, 16); // Adjust if date format from server is different
            document.getElementById('arrivalDate').value = arrivalDate.substring(0, 16); // Adjust if date format from server is different
            document.getElementById('price').value = price;
            document.getElementById('companyAerienneId').value = companyId;
        }
    </script>
</head>
<body>
    <h1>Flights</h1>
    <form action="flight" method="post">
        <input type="hidden" name="action" id="action" value="<%= request.getAttribute("action") != null ? request.getAttribute("action") : "add" %>">
        Flight Number: <input type="text" name="flightNumber" id="flightNumber"><br>
        Departure City: <input type="text" name="departureCity" id="departureCity"><br>
        Arrival City: <input type="text" name="arrivalCity" id="arrivalCity"><br>
        Departure Date: <input type="datetime-local" name="departureDate" id="departureDate"><br>
        Arrival Date: <input type="datetime-local" name="arrivalDate" id="arrivalDate"><br>
        Price: <input type="text" name="price" id="price"><br>
        Company Aerienne:
        <select name="companyAerienneId" id="companyAerienneId">
            <% List<CompanyAerienne> companies = (List<CompanyAerienne>) request.getAttribute("companies");
               for (CompanyAerienne company : companies) { %>
               <option value="<%= company.getId() %>"><%= company.getNom() %></option>
            <% } %>
        </select><br>
        <input type="submit" value="Submit">
    </form>

    <h2>List of Flights</h2>
    <table border="1">
        <tr>
            <th>Flight Number</th>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Departure Date</th>
            <th>Arrival Date</th>
            <th>Price</th>
            <th>Company Aerienne</th>
            <th>Actions</th>
        </tr>
        <%
            List<Flight> flights = (List<Flight>) request.getAttribute("flights");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            for (Flight flight : flights) {
        %>
        <tr>
            <td><%= flight.getFlightNumber() %></td>
            <td><%= flight.getDepartureCity() %></td>
            <td><%= flight.getArrivalCity() %></td>
            <td><%= sdf.format(flight.getDepartureDate()) %></td>
            <td><%= sdf.format(flight.getArrivalDate()) %></td>
            <td><%= flight.getPrice() %></td>
            <td><%= flight.getCompanyAerienne().getNom() %></td>
            <td>
                <button type="button" onclick="populateForm('<%= flight.getFlightNumber() %>', '<%= flight.getDepartureCity() %>', '<%= flight.getArrivalCity() %>', '<%= sdf.format(flight.getDepartureDate()) %>', '<%= sdf.format(flight.getArrivalDate()) %>', '<%= flight.getPrice() %>', '<%= flight.getCompanyAerienne().getId() %>')">Edit</button>
                <form action="flight" method="post" style="display: inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="flightNumber" value="<%= flight.getFlightNumber() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
