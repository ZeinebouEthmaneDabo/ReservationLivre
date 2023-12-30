package com.example.ReservationLivres.service;


import com.example.ReservationLivres.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);

    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation updatedReservation);

    boolean deleteReservation(Long id);
}
