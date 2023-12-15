package com.example.reservationLivre.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("client")
public class Client extends User {
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    // Méthodes spécifiques au Client

    // Ajoutez d'autres attributs et méthodes si nécessaire
}
