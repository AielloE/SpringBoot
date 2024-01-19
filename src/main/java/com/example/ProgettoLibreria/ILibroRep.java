package com.example.ProgettoLibreria;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ILibroRep extends CrudRepository<Libro, Integer> {
    @Query("select b from Libro b inner join UtenteLibro ub on b.id=ub.libro.id and ub.utente.id=:utenteId")
    List<Libro> findLibriByUtenteLibri(@Param("utenteId") Integer utenteId);

    Optional<Libro> findByTitolo(String titolo);

}
