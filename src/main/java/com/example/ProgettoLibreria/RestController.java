package com.example.ProgettoLibreria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/libri")
public class RestController {
    @Autowired
    private ILibroRep iLibroRep;

    @GetMapping
    public Iterable<Libro> getAllLibri() {
        return iLibroRep.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Integer id) {
        return iLibroRep.findById(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/titolo/{titolo}")
    public ResponseEntity<Libro> getLibroByTitolo(@PathVariable String titolo) {
        return iLibroRep.findByTitolo(titolo)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
