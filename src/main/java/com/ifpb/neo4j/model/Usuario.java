package com.ifpb.neo4j.model;

import java.time.LocalDate;

public class Usuario {

    private String email;
    private String nome;
    private LocalDate nascimento;

    public Usuario(String email, String nome, LocalDate nascimento) {
        this.email = email;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                '}';
    }

}
