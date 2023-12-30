package com.example.ReservationLivres;
import com.example.ReservationLivres.entity.Livre;
import com.example.ReservationLivres.service.LivreService;
import com.example.ReservationLivres.service.serviceImpl.LivreServiceImpl;
import com.example.ReservationLivres.Repository.LivreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivreServiceTest {

    @Mock
    private LivreRepository livreRepository;

    @InjectMocks
    private LivreService livreService = new LivreServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLivres() {
        // Arrange
        List<Livre> livres = Arrays.asList(new Livre(), new Livre());
        when(livreRepository.findAll()).thenReturn(livres);

        // Act
        List<Livre> result = livreService.getAllLivres();

        // Assert
        assertEquals(livres, result);
    }

    @Test
    void testGetLivreById_ExistingId() {
        // Arrange
        Long id = 1L;
        Livre livre = new Livre();
        when(livreRepository.findById(id)).thenReturn(Optional.of(livre));

        // Act
        Livre result = livreService.getLivreById(id);

        // Assert
        assertEquals(livre, result);
    }

    @Test
    void testGetLivreById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(livreRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> livreService.getLivreById(id));
    }

    @Test
    void testCreateLivre() {
        // Arrange
        Livre livre = new Livre();
        when(livreRepository.save(livre)).thenReturn(livre);

        // Act
        Livre result = livreService.createLivre(livre);

        // Assert
        assertEquals(livre, result);
    }

    @Test
    void testUpdateLivre_ExistingId() {
        // Arrange
        Long id = 1L;
        Livre existingLivre = new Livre();
        Livre updatedLivre = new Livre();
        when(livreRepository.findById(id)).thenReturn(Optional.of(existingLivre));
        when(livreRepository.save(existingLivre)).thenReturn(existingLivre);

        // Act
        Livre result = livreService.updateLivre(id, updatedLivre);

        // Assert
        assertEquals(updatedLivre, result);
    }

    @Test
    void testUpdateLivre_NonExistingId() {
        // Arrange
        Long id = 1L;
        Livre updatedLivre = new Livre();
        when(livreRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> livreService.updateLivre(id, updatedLivre));
    }

    @Test
    void testDeleteLivre_ExistingId() {
        // Arrange
        Long id = 1L;
        when(livreRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = livreService.deleteLivre(id);

        // Assert
        assertTrue(result);
        verify(livreRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteLivre_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(livreRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> livreService.deleteLivre(id));
    }
}
