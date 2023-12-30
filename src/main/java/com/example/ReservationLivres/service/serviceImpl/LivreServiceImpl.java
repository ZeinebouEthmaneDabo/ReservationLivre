package com.example.ReservationLivres.service.serviceImpl;

import com.example.ReservationLivres.Repository.LivreRepository;
import com.example.ReservationLivres.entity.Livre;
import com.example.ReservationLivres.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @Override
    public Livre getLivreById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre not found"));
    }

    @Override
    public Livre createLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public Livre updateLivre(Long id, Livre updatedLivre) {
        Optional<Livre> optionalLivre = livreRepository.findById(id);
        if (optionalLivre.isPresent()) {
            Livre livre = optionalLivre.get();
            // Update relevant fields of the livre entity
            livre.setTitre(updatedLivre.getTitre());
            livre.setAuteur(updatedLivre.getAuteur());
            livre.setIsbn(updatedLivre.getIsbn());
            livre.setDisponibilite(updatedLivre.getDisponibilite());
            return livreRepository.save(livre);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre not found");
        }
    }

    @Override
    public boolean deleteLivre(Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre not found");
        }
    }
}
