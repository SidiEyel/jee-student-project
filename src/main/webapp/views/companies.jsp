<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.rimbestprice.rimbestprice.models.CompanyAerienne"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Companies</title>
</head>
<body>
    <h1>Companies</h1>
    <form action="company" method="post">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="nom">
        <input type="submit" value="Add Company">
    </form>
    <h2>List of Companies</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <%
            List<CompanyAerienne> companies = (List<CompanyAerienne>) request.getAttribute("companies");
            for (CompanyAerienne company : companies) {
        %>
        <tr>
            <td><%= company.getId() %></td>
            <td><%= company.getNom() %></td>
            <td>
                <form action="company" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= company.getId() %>">
                    Name: <input type="text" name="nom" value="<%= company.getNom() %>">
                    <input type="submit" value="Update">
                </form>
                <form action="company" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= company.getId() %>">
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
