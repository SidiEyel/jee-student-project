<%@ page import="com.rimbestprice.rimbestprice.models.User" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reservation</title>
    <script>
        function populateForm(flightNumber, departureCity, arrivalCity, departureDate, arrivalDate, price, companyId) {
            document.getElementById('action').value = 'update';
            document.getElementById('departureCity').value = departureCity;
            document.getElementById('arrivalCity').value = arrivalCity;
            document.getElementById('departureDate').value = departureDate.substring(0, 16); 
            document.getElementById('arrivalDate').value = arrivalDate.substring(0, 16);
        }
    </script>
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("user");
    boolean isLoggedIn = Objects.nonNull(user);
%>

<h1>Recherche </h1>

<form action="flightList" method="post">
    <input type="hidden" name="action" id="action" value="<%= request.getAttribute("action") != null ? request.getAttribute("action") : "add" %>">
    Departure City: <input type="text" name="departureCity" id="departureCity" required><br>
    Arrival City: <input type="text" name="arrivalCity" id="arrivalCity" required><br>
    Departure Date: <input type="datetime-local" name="departureDate" id="departureDate" required><br>
    Arrival Date: <input type="datetime-local" name="arrivalDate" id="arrivalDate" required><br>
    <input type="submit" value="submit">
</form>

<h1><%= "Hello World!" %></h1>
<br/>

<%-- Check if the user is not logged in before showing the register link --%>
<% if (!isLoggedIn) { %>
    <a href="register"> Register </a>
    <a href="login"> Login </a>
<% } else { %>
    <a href="logout"> Logout </a>
<% } %>

<a href="make_payment"> Payment </a>
<a href="flightList">List</a>

</body>
</html>
