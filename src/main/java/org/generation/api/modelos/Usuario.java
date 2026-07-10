package org.generation.api.modelos;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usuarioId", unique = true, nullable = false)
    private Long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="pass", nullable = false)
    private String pass;

    public Usuario(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }// constructor Usuario

    public Usuario() {}// constructor vacío JPA

    public Long getId() {
        return id;
    }// getId

    public String getEmail() {
        return email;
    }// getEmail

    public void setEmail(String email) {
        this.email = email;
    }// setEmail

    public String getPass() {
        return pass;
    }// getPass

    public void setPass(String pass) {
        this.pass = pass;
    }// setPass

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }// toString
}// class Usuario
