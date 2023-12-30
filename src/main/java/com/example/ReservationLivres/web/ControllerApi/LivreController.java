package com.example.ReservationLivres.web.ControllerApi;

import com.example.ReservationLivres.entity.Livre;
import com.example.ReservationLivres.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ReservationLivres.utils.Constants;
import java.util.List;

@RestController
@RequestMapping(Constants.APP_ROOT+Constants.LIVRE)
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        Livre livre = livreService.getLivreById(id);
        return new ResponseEntity<>(livre, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        Livre createdLivre = livreService.createLivre(livre);
        return new ResponseEntity<>(createdLivre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre updatedLivre) {
        Livre updated = livreService.updateLivre(id, updatedLivre);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        boolean deleted = livreService.deleteLivre(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
