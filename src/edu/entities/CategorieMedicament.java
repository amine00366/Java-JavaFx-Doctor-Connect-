/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

/**
 *
 * @author SAHLI
 */
class CategorieMedicament {
    private int id;
    private String nom;

    @Override
    public String toString() {
        return "CategorieMedicament{" + "id=" + id + ", nom=" + nom + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public CategorieMedicament(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public CategorieMedicament() {
    }
}
