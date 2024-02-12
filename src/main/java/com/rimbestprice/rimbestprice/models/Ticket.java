package com.rimbestprice.rimbestprice.models;

import jakarta.persistence.*;
import lombok.*;

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
}
