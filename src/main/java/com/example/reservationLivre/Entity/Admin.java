package com.example.reservationLivre.Entity;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import org.hibernate.mapping.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@DiscriminatorValue("admin")

public class Admin extends User {
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Livre> livres = new ArrayList<>();;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

}
