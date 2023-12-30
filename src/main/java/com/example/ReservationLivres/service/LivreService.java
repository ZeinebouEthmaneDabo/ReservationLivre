package com.example.ReservationLivres.service;

import com.example.ReservationLivres.entity.Livre;

import java.util.List;

public interface LivreService {
    List<Livre> getAllLivres();

    Livre getLivreById(Long id);

    Livre createLivre(Livre livre);

    Livre updateLivre(Long id, Livre updatedLivre);

    boolean deleteLivre(Long id);
}

