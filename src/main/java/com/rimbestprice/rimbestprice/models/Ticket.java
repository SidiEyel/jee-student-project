package com.rimbestprice.rimbestprice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    @Id
    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_class", nullable = false)
    private TicketClass ticketClass;

    @Column(name = "number_of_tickets_available", nullable = false)
    private Integer numberOfTicketsAvailable;
    @ManyToOne
    @JoinColumn(name = "flight_number", nullable = false)
    private Flight flight;

    @Column(name = "creation_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    // Other attributes, getters, setters, and methods
    public boolean canModifyTicket() {
        long timeDifference = new Date().getTime() - this.getCreationTime().getTime();

        return timeDifference <= 48 * 60 * 60 * 1000;
    }
    public Ticket() {
        this.creationTime = new Date();
    }

}
