package com.example.ReservationLivres.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    private String isbn;
    private String disponibilite;


    @OneToMany(mappedBy = "livre")
    private List<Reservation> reservations;



}
