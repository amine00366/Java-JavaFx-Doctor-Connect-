/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAHLI
 */
public class Ordonnance {
 // private List<Medicament> medicaments;
    private int id,id_consultation_id,medicament_id;
    
//private List<Consultation> id_consultation_id;
 private String frequence,dose,Nom_Medicament;
  //  private Consultation consultation;
    private Date date_creation;
//    private Collection<Medicament> medicaments;
      //  private Doctor nom ;

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", id_consultation_id=" + id_consultation_id + ", frequence=" + frequence + ", dose=" + dose + ", Nom_Medicament=" + Nom_Medicament + ", date_creation=" + date_creation + '}';
    }

    public void setMedicament_id(int medicament_id) {
        this.medicament_id = medicament_id;
    }

    public int getMedicament_id() {
        return medicament_id;
    }


    public String getFrequence() {
        return frequence;
    }

   

   

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public int getId_consultation_id() {
        return id_consultation_id;
    }

    public void setId_consultation_id(int id_consultation_id) {
        this.id_consultation_id = id_consultation_id;
    }
    
     


        public Ordonnance() {
    }

    public void setNom_Medicament(String Nom_Medicament) {
        this.Nom_Medicament = Nom_Medicament;
    }

    public String getNom_Medicament() {
        return Nom_Medicament;
    }

    
   

     

     
     

    

    

   
 

    public Ordonnance( String frequence, String dose) {
        
        this.frequence = frequence;
        this.dose = dose;
    }

    public Ordonnance(int id, String frequence, String dose, Date date_creation) {
        this.id = id;
        this.frequence = frequence;
        this.dose = dose;
        this.date_creation = date_creation;
    }

    public Ordonnance(int id, int id_consultation_id, String frequence, String dose, Date date_creation) {
        this.id = id;
       // this.id_consultation_id = id_consultation_id;
        this.frequence = frequence;
        this.dose = dose;
        this.date_creation = date_creation;
    }

    public Ordonnance(int id, int id_consultation_id, String frequence, String dose, String Nom_Medicament, Date date_creation) {
        this.id = id;
        this.id_consultation_id = id_consultation_id;
        this.frequence = frequence;
        this.dose = dose;
        this.Nom_Medicament = Nom_Medicament;
        this.date_creation = date_creation;
    }


//    public List<PrescriptionMedicament> getPrescriptionMedicaments() {
//        return prescriptionMedicaments;
//    }
//
//    public void setPrescriptionMedicaments(List<PrescriptionMedicament> prescriptionMedicaments) {
//        this.prescriptionMedicaments = prescriptionMedicaments;
//    }

    public Ordonnance(int id_consultation_id, String frequence, String dose, String Nom_Medicament, Date date_creation) {
        this.id_consultation_id = id_consultation_id;
        this.frequence = frequence;
        this.dose = dose;
        this.Nom_Medicament = Nom_Medicament;
        this.date_creation = date_creation;
    }

     
   
    

    public void setId(int id) {
        this.id = id;
    }

    public void setFrquence(String frquence) {
        this.frequence = frquence;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

//    public void setConsultation(Consultation consultation) {
//        this.consultation = consultation;
//    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    

    public int getId() {
        return id;
    }

    public String getFrquence() {
        return frequence;
    }

    public String getDose() {
        return dose;
    }

//    public Consultation getConsultation() {
//        return consultation;
//    }

    public Date getDate_creation() {
        return date_creation;
    }

//    public int getId_consultation_id() {
//        return id_consultation_id;
//    }
//
//    public void setId_consultation_id(int id_consultation_id) {
//        this.id_consultation_id = id_consultation_id;
//    }

    public void addMedicament(Medicament medicament) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public String getNom_medicament() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    
   
    

}
