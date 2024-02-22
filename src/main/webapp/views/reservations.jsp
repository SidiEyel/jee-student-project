<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reservations</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <h2>User Reservations</h2>

    <c:if test="${not empty reservations}">
   
        <table border="1">
            <thead>
                <tr>
                    <th>Reservation ID</th>
                    <th>Ticket Number</th>
                    <th>Reservation Time</th>
                    <th>Imprimer</th> <!-- New column for printing -->
                    <th>Modifier</th> <!-- New column for modifying -->
                    <!-- Add more columns if needed -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.id}</td>
                        <td>${reservation.ticket.ticketNumber}</td>
                        <td>${reservation.reservationTime}</td>
                        <td>
                            <a href="printReservation?reservationId=${reservation.id}" target="_blank">
                               Imprimer
                            </a>
                        </td>
                        <td>
                            <c:choose>
        <c:when test="${reservation.canModifyReservation}">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modifierModal${reservation.id}">
                Modifier
            </button>
        </c:when>
        <c:otherwise>
            <p>Cannot modify</p>
        </c:otherwise>
    </c:choose>
                           
                        </td>
                
                    </tr>

                    <div class="modal" id="modifierModal${reservation.id}" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Modifier Reservation</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Reservation ID: ${reservation.id}</p>
                                    <p>Ticket Number: ${reservation.ticket.ticketNumber}</p>
                                </div>
                                <div class="modal-footer">
                                
                                    <button type="button" class="btn btn-primary">Save Changes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <%-- Display success message if payment was successful --%>
    <c:if test="${success}">
        <p style="color: green;">Reservation created successfully!</p>
    </c:if>

    <%-- Display error message if payment failed --%>
    <c:if test="${not success}">
        <p style="color: red;">Reservation creation failed. Please try again.</p>
    </c:if>

    <!-- Bootstrap JS for modal functionality -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
