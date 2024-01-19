package com.example.ProgettoLibreria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Persona {
    @NotNull
    @Size(min=2, max=30)
    String nome;

    @NotNull
    @Size(min=2, max=30)
    String cognome;

    @NotNull
    @Size(min=2, max=30)
    String username;
    @NotNull
    @Size(min=6, max=20)
    String password;
    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return this.cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public Persona(String nome, String cognome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonaForm{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

