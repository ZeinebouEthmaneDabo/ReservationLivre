package com.example.reservationLivre.Entity;

import jakarta.persistence.*;

@Entity
public class Livre {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String ISBN;
    private boolean disponibilite;

    // Constructeurs, getters, setters

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public void mettreAJourDisponibilite(boolean nouvelleDisponibilite) {
        this.disponibilite = nouvelleDisponibilite;
    }

    // Ajoutez d'autres attributs et méthodes si nécessaire
}
