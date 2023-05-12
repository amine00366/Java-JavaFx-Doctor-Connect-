/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author SAHLI
 */
public class Consultation {
      private Integer id,id_medcin_id,id_user_id;
   // public static Doctor id_medcin;
    //private User id_User;
    private String etat_Consultation;
     private Timestamp date_debut,date_fin;
   // private Fiche fiche;
public Consultation() {
    }

    public Consultation(Integer id, Integer id_medcin_id, Integer id_user_id, String etat_Consultation, Timestamp date_debut, Timestamp date_fin) {
        this.id = id;
        this.id_medcin_id = id_medcin_id;
        this.id_user_id = id_user_id;
        this.etat_Consultation = etat_Consultation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", id_medcin_id=" + id_medcin_id + ", id_user_id=" + id_user_id + ", etat_Consultation=" + etat_Consultation + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

    public void setId_medcin_id(Integer id_medcin_id) {
        this.id_medcin_id = id_medcin_id;
    }

    public void setId_user_id(Integer id_user_id) {
        this.id_user_id = id_user_id;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public Integer getId_medcin_id() {
        return id_medcin_id;
    }

    public Integer getId_user_id() {
        return id_user_id;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }
 
     

    public Integer getId() {
        return id;
    }

    public String getEtat_Consultation() {
        return etat_Consultation;
    }
    public void setId(Integer id) {
        this.id = id;
    }

     public void setEtat_Consultation(String etat_Consultation) {
        this.etat_Consultation = etat_Consultation;
    }
    
   
}
