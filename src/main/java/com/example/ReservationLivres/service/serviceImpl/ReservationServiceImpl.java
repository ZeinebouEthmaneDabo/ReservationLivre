package com.example.ReservationLivres.service.serviceImpl;



import com.example.ReservationLivres.Repository.ReservationRepository;
import com.example.ReservationLivres.entity.Reservation;
import com.example.ReservationLivres.service.ReservationService;
import com.example.ReservationLivres.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.RESERVATION_NOT_FOUND));
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        // You may perform additional validation or checks before creating the reservation
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            // Update relevant fields of the reservation entity
            reservation.setLivre(updatedReservation.getLivre());
            reservation.setUser(updatedReservation.getUser());
            return reservationRepository.save(reservation);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.RESERVATION_NOT_FOUND);
        }
    }

    @Override
    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.RESERVATION_NOT_FOUND);
        }
    }
}
