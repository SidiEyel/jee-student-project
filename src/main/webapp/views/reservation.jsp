<%@ page import="com.rimbestprice.rimbestprice.models.CompanyAerienne" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<%--    <title>JSP - Veuillez reservez svp !</title>--%>

    <title>reservation</title>
    <script>
        function populateForm(flightNumber, departureCity, arrivalCity, departureDate, arrivalDate, price, companyId) {
            document.getElementById('action').value = 'update';

            document.getElementById('departureCity').value = departureCity;
            document.getElementById('arrivalCity').value = arrivalCity;
            document.getElementById('departureDate').value = departureDate.substring(0, 16); // Adjust if date format from server is different
            document.getElementById('arrivalDate').value = arrivalDate.substring(0, 16); // Adjust if date format from server is different


        }
    </script>
</head>
<body>
<h1>recherche </h1>
<form action="flightList" method="post">
    <input type="hidden" name="action" id="action" value="<%= request.getAttribute("action") != null ? request.getAttribute("action") : "add" %>">


    Departure City: <input type="text" name="departureCity" id="departureCity" required><br>
    Arrival City: <input type="text" name="arrivalCity" id="arrivalCity" required><br>
    Departure Date: <input type="datetime-local" name="departureDate" id="departureDate" required><br>
    Arrival Date: <input type="datetime-local" name="arrivalDate" id="arrivalDate" required><br>


<%--    <select name="companyAerienneId" id="companyAerienneId">--%>
<%--        <% List<CompanyAerienne> companies = (List<CompanyAerienne>) request.getAttribute("companies");--%>
<%--            for (CompanyAerienne company : companies) { %>--%>
<%--        <option value="<%= company.getId() %>"><%= company.getNom() %></option>--%>
<%--        <% } %>--%>
<%--    </select><br>--%>
    <input type="submit" value="submit">
</form>
<h1><%= "Hello World!" %>
</h1>
<br/>

<a href="register"> register </a>
<a href="login"> login </a>
<a href="make_payment"> payment </a>


<a href="flightList">list</a>

</body>
</html>