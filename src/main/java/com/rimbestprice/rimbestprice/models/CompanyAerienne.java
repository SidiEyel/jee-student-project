package com.rimbestprice.rimbestprice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_aeriennes")
@Data
public class CompanyAerienne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;
}
