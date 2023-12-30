package com.example.ReservationLivres;

import com.example.ReservationLivres.Repository.ReservationRepository;
import com.example.ReservationLivres.entity.Reservation;
import com.example.ReservationLivres.service.serviceImpl.ReservationServiceImpl;
import com.example.ReservationLivres.utils.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
        // Arrange
        List<Reservation> reservations = new ArrayList<>();
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.getAllReservations();

        // Assert
        assertEquals(reservations, result);
    }

    @Test
    void testGetReservationById_ExistingId() {
        // Arrange
        Long id = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.getReservationById(id);

        // Assert
        assertEquals(reservation, result);
    }

    @Test
    void testGetReservationById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> reservationService.getReservationById(id));
    }

    @Test
    void testCreateReservation() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation result = reservationService.createReservation(reservation);

        // Assert
        assertEquals(reservation, result);
    }

    @Test
    void testUpdateReservation_ExistingId() {
        // Arrange
        Long id = 1L;
        Reservation existingReservation = new Reservation();
        Reservation updatedReservation = new Reservation();

        when(reservationRepository.findById(id)).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(existingReservation)).thenReturn(existingReservation);

        // Act
        Reservation result = reservationService.updateReservation(id, updatedReservation);

        // Assert
        assertEquals(existingReservation, result);
    }

    @Test
    void testUpdateReservation_NonExistingId() {
        // Arrange
        Long id = 1L;
        Reservation updatedReservation = new Reservation();

        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> reservationService.updateReservation(id, updatedReservation));
    }

    @Test
    void testDeleteReservation_ExistingId() {
        // Arrange
        Long id = 1L;
        when(reservationRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = reservationService.deleteReservation(id);

        // Assert
        assertTrue(result);
        verify(reservationRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteReservation_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(reservationRepository.existsById(id)).thenReturn(false);

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> reservationService.deleteReservation(id));
        verify(reservationRepository, never()).deleteById(id);
    }
}

