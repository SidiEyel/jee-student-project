<%@ page import="com.rimbestprice.rimbestprice.models.CompanyAerienne" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

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
<h1><%= "Hello World!" %>
</h1>
<br/>

<a href="register"> register </a>
<a href="login"> login </a>
<a href="make_payment"> payment </a>



</body>
</html>