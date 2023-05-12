/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author amine
 */
public class Typeappoinment {
    private String nomtype , description ; 
    private int id;

    public Typeappoinment( int id,String nomtype, String description) {
        this.nomtype = nomtype;
        this.description = description;
        this.id = id;
    }

    public Typeappoinment(int id) {
        this.id = id;
    }

    public Typeappoinment(String nomtype, String description) {
        this.nomtype = nomtype;
        this.description = description;
    }

    public Typeappoinment() {
    }

    public String getNomtype() {
        return nomtype;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setNomtype(String nomtype) {
        this.nomtype = nomtype;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
     @Override
    public String toString() {
        return  "id=" + id + ", nom=" + nomtype + ", description =" + description +'}';
    }
}
