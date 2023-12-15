package com.example.reservationLivre.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private LocalDateTime dateReservation;
    private LocalDateTime dateRetourPrevue;
    private String status;

    // Constructeurs, getters, setters

    public void confirmerReservation() {
        this.status = "CONFIRMEE";
    }

    public void annulerReservation() {
        this.status = "ANNULEE";
    }

    // Ajoutez d'autres attributs et méthodes si nécessaire
}

