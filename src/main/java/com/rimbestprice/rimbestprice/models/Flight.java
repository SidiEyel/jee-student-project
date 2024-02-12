package com.rimbestprice.rimbestprice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "flights")
@Data
public class Flight {

    @Id
    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "departure_city", nullable = false)
    private String departureCity;

    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;

    @Column(name = "departure_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Column(name = "arrival_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyAerienne companyAerienne;

    // Getters and Setters

    // Constructors if needed

    // Other methods if needed
}
