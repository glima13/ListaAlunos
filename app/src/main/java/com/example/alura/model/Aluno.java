package com.example.alura.model;

import java.io.Serializable;

public class Aluno implements Serializable {

    private int id = 0;
    private String nomes;
    private String telefones;
    private String emails;

    public Aluno(String nomes, String telefones, String emails) {
        this.nomes = nomes;
        this.telefones = telefones;
        this.emails = emails;
    }

    public Aluno() {

    }

    public String getNomes() {
        return nomes;
    }

    public String getTelefones() {
        return telefones;
    }

    public String getEmails() {
        return emails;
    }

    public void setNomes(String nomes) {
        this.nomes = nomes;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nomes;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
