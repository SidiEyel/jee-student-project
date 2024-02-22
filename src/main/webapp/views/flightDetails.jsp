<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Flight Details</title>
    <style>
        table {
            width: 50%;
            margin: auto;
            border-collapse: collapse;
            text-align: left;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .make-payment-button {
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .ticket-container {
            margin-top: 20px;
            text-align: center;
        }

        .ticket-table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            text-align: left;
        }

        .ticket-table th, .ticket-table td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .ticket-table th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Flight Details</h2>
    <table border="1">
        <tr>
            <th>Flight Number</th>
            <td><%= request.getAttribute("flightNumber") %></td>
        </tr>
        <tr>
            <th>Departure City</th>
            <td><%= request.getAttribute("departureCity") %></td>
        </tr>
        <tr>
            <th>Arrival City</th>
            <td><%= request.getAttribute("arrivalCity") %></td>
        </tr>
        <tr>
            <th>Departure Date</th>
            <td><%= request.getAttribute("departureDate") %></td>
        </tr>
        <tr>
            <th>Arrival Date</th>
            <td><%= request.getAttribute("arrivalDate") %></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><%= request.getAttribute("price") %></td>
        </tr>
        <tr>
            <th>Company Aerienne</th>
            <td><%= request.getAttribute("companyAerienne") %></td>
        </tr>
    </table>

    <div class="ticket-container">
        <table class="ticket-table">
            <thead>
                <tr>
                    <th>Ticket Number</th>
                    <th>Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ticket" items="${tickets}">
                    <tr>
                        <td>${ticket.ticketNumber}</td>
                        <td>${ticket.price}</td>
                      
                        <td>
                            <form method="get" action="make_payment">
                                
                                <input type="hidden" name="ticket" value="${ticket.ticketNumber}">
                                <button class="make-payment-button" type="submit">Make Payment</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
