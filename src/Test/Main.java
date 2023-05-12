/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entity.Appointment;
import Entities.Typeappoinment;
import Entity.User;
import Entities.Doctor;
import Services.ServiceDoctor;
import Services.ServiceRednezVous;
import Services.ServicetypeRDV;
//import ch.qos.logback.core.CoreConstants;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author amine
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
              // User u = new User( "mohamed", "benabbes", "abbes525@gmail.com",1);
              // Doctor d = new Doctor (3,"mohamed", "benabbes", "abbes525@gmail.com");

          /*     User user = new User("nom", "prenom", "abbes525@gmail.com", 2);
              Doctor doctor = new Doctor(2, "prenom", "abbes525@gmail.com", "specialite");
              Typeappoinment typeAppointment = new Typeappoinment(1,"aaa", "consultation");

Appointment appointment = new Appointment(82, user, doctor, typeAppointment,java.sql.Date.valueOf("2023-02-16"),java.sql.Date.valueOf("2025-02-16"), "eeeee", false);


   
       // Appointment c3 = new Appointment(157,3,1,java.sql.Date.valueOf("2023-02-16"),java.sql.Date.valueOf("2025-02-16"),2,"mohamed",false);
    
       //Typeappoinment c3 = new Typeappoinment ("aaa","bbbb");

        ServiceRednezVous ser = new ServiceRednezVous();
        ServicetypeRDV serv = new ServicetypeRDV();
        ServiceDoctor servt = new ServiceDoctor();
        
        
        /*
              
        List<Appointment> l1 = null;
       
            l1 = ser.readAll();
      
            System.out.println(l1);*/
////// affichage appointment yekhdem b jointures 
        /*List<Appointment> l1 = null;
       
            l1 = ser.readAll();
      
            System.out.println(l1);*/
       // supprimer temchi b jointure 
      /* boolean isDeleted = ser.supprimer(82);
if (isDeleted) {
    System.out.println("Le rendez-vous a été supprimé avec succès !");
} else {
    System.out.println("Impossible de supprimer le rendez-vous.");
}*/ 
       
       //// ajout Appointment tekhdem b jointure 
       /* try {
            ser.ajouter(appointment);
      } catch (SQLException ex) {
           System.out.println(ex);
       }*/
        
       
   
       
       //// update tekhdem baad jointure 
/*appointment.setCategorie("Controle");
ser.update(appointment);

       // Appeler la fonction update pour enregistrer les modifications dans la base de données




       /* try{
           serv.supprime_reservation_cov(48 );
        }catch (SQLException ex) {
            System.out.println(ex);}
         //  does_reservation_cov_exist(3, 827);
         */
         /*List<Typeappointment> l1 = null;
         try {
                    l1 =     serv.find_reservation_cov_of_user_rech(827,3);
             System.out.println("HAHAHAHAH");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         if (l1.size() == 0 )  { 
          System.out.println("zzaaa")    ; 
         }
        
          l1.forEach(e -> {
            System.out.println(e);
        });*/
     /*     try {
        serv.ajouter_reservation_cov(c8);
 } catch (SQLException ex) {
            System.out.println(ex);
                        System.out.println("letsgoooo");

        } */

         //Covoiturage c2 = new Covoiturage(7,1 ,"rades ", "gahzela","22-02-2021"  ,5,5);

        
       /* try {
            ser.ajouter(c3);
        } catch (SQLException ex) {
            System.out.println(ex);
        } */
        
       
    //   try{
      //            ser.supprime(c2);
       // }catch (SQLException ex) {
         //   System.out.println(ex);}
       
        
     //   List<Covoiturage> l1 = null;

    //    try {
      //      l1 = ser.readAll();
       // } catch (SQLException ex) {
         //   System.out.println(ex);
       // }
     //   l1.forEach(e -> {
       //     System.out.println(e);
       // });*/
    }

   

}
