package com.rimbestprice.rimbestprice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@Entity
@Table(name = "user_reservations")

public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_number", nullable = false)
    private Ticket ticket;

    @Column(name = "reservation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationTime;

    // Constructors, getters, setters, and other methods...

    public boolean canModifyReservation() {
        long timeDifference = new Date().getTime() - this.getTicket().getFlight().getDepartureDate().getTime();
        return timeDifference <= 48 * 60 * 60 * 1000;
    }
    @Override
    public String toString() {
        return "UserReservation{id=" + id + ", user=" + user.getId() + ", ticket=" + ticket.getTicketNumber() + ", reservationTime=" + reservationTime + "}";
    }
    
}

