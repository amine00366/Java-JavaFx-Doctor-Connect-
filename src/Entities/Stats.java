/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MSI
 */
public class Stats {
    String nom;
    int nbr;
    float prc;

    public Stats(String nom, int nbr, float prc) {
        this.nom = nom;
        this.nbr = nbr;
        this.prc = prc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public float getPrc() {
        return prc;
    }

    public void setPrc(float prc) {
        this.prc = prc;
        
    }

    @Override
    public String toString() {
        return "Categorie :" + nom + ", nombre=" + nbr + ", prc=" + prc + "%";
    }
    
}
