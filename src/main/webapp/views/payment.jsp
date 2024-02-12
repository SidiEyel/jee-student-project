<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
</head>
<body>
    <h2>Payment Form</h2>
    <form action="make_payment" method="post">
        Card Number: <input type="text" name="cardNumber" required><br>
        Owner: <input type="text" name="owner" required><br>
        CVV: <input type="text" name="cvv" required><br>
        Expiration Date: <input type="text" name="expirationDate" required><br>
        Account Number: <input type="text" name="accountNumber" required><br>
        Amount: <input type="text" name="amount" required><br>
        <input type="submit" value="Make Payment">
    </form>
</body>
</html>
