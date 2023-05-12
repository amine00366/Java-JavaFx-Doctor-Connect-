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
public class Fiche {
    int id,id_consultation_id ;
    String note;

    public Fiche() {
    }

    public Fiche(int id, int id_consultation_id, String note) {
        this.id = id;
        this.id_consultation_id = id_consultation_id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getId_consultation_id() {
        return id_consultation_id;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_consultation_id(int id_consultation_id) {
        this.id_consultation_id = id_consultation_id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Fiche{" + "id=" + id + ", id_consultation_id=" + id_consultation_id + ", note=" + note + '}';
    }
    
}
