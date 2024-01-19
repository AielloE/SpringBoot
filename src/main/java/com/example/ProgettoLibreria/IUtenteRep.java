package com.example.ProgettoLibreria;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUtenteRep extends CrudRepository<Utente, Integer> {
    @Query("select s from Utente s where username= :username and password = :password")
    public Utente login(String username, String password);
}
