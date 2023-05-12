/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tests;
import edu.entities.Consultation;
import edu.entities.Fiche;
import edu.entities.Medicament;
import edu.entities.Ordonnance;
import edu.services.ConsultationCrud;
import edu.services.FicheCrud;
import edu.services.OrdonnanceCrud;
import edu.utils.MyConnection;
import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author SAHLI
 */
public class MainClass {
    public static void main(String[] args) {
     //   MyConnection mc = new MyConnection ();
//               
                     OrdonnanceCrud sp = new OrdonnanceCrud();
                     Ordonnance O1 = new Ordonnance(100,112233,"2","1","Doliprane",Timestamp.valueOf("2023-03-27 15:30:00"));
//         sp.AjouterOrdonnance(O1);
        sp.supprimer(100);
       // sp.modifier(O1);
        //System.out.println(sp.getAll()); 
//----------------------------------------------------------------------------------------------------------
                // FicheCrud fc = new FicheCrud();
                //Fiche f1 = new Fiche(100,12345,"aa baba");
        //fc.Ajouter(f1);
        //fc.supprimer(3);
        // System.out.println(fc.getAll()); 
//----------------------------------------------------------------------------------------------------------
       // ConsultationCrud cc =new ConsultationCrud();      
       // Consultation c1 = new Consultation (6,3,3,"cv bien", Timestamp.valueOf("2023-03-27 15:30:00"),Timestamp.valueOf("2023-03-27 15:30:00"));
        //cc.Ajouter(c1);
         // cc.supprimer(12345);
     //  cc.modifier(c1);
     //   System.out.println(cc.getAll());

    }
}
