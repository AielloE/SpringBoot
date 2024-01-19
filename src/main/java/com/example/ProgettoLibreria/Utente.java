package com.example.ProgettoLibreria;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String cognome;
    private String username;
    private String password;

    @OneToMany(mappedBy = "utente")
    Set<UtenteLibro> utente_libro;

    public Utente(){}

    public Set<UtenteLibro> getUtente_libro() {
        return utente_libro;
    }

    public void setUtente_libro(Set<UtenteLibro> utente_libro) {
        this.utente_libro = utente_libro;
    }

    public Utente(String cognome, String nome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


