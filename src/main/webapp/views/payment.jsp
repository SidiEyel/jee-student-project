<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rimbestprice.rimbestprice.models.User"%>
<%@ page import="java.util.Objects"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
</head>
<body>

<%
    // Check if the user is logged in
    User user = (User) request.getSession().getAttribute("user");
    boolean isLoggedIn = Objects.nonNull(user);
    String ticket = (String) request.getAttribute("ticket");
   
%>

<%
    if (isLoggedIn) {
%>
    <h2>Payment Form</h2>
    <p>Welcome, <%= user.getUsername()%> !</p>
    <form action="make_payment" method="post">
        Card Number: <input type="text" name="cardNumber" required><br>
        Owner: <input type="text" name="owner" required><br>
        CVV: <input type="text" name="cvv" required><br>
        Expiration Date: <input type="text" name="expirationDate" required><br>
        Account Number: <input type="text" name="accountNumber" required><br>
        Amount: <input type="text" name="amount" required><br>
        <input type="hidden" value="<%= ticket %>" name="ticket" >
        <input type="submit" value="Make Payment">
  
    </form>
<%
    } else {
%>
    <p>You need to be logged in to make a payment. Please <a href="login">login</a>.</p>
<%
    }
%>

</body>
</html>
