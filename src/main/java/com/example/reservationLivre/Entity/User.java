package com.example.reservationLivre.Entity;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String motDePasse;

    // Constructeurs, getters, setters

    // Ajoutez d'autres méthodes si nécessaire
}

