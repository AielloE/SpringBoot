package com.example.ProgettoLibreria;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    String titolo;

    @NotNull
    @Size(min=2, max=50)
    String autore;

    @Size(min=0, max=90)
    String descrizione;



    @OneToMany(mappedBy = "libro")
    Set<UtenteLibro> utente_libro;

    public Libro (){}


    public Libro(String titolo, String autore, String descrizione) {
        this.titolo = titolo;
        this.autore = autore;
        this.descrizione = descrizione;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Book{" +
                "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

}
