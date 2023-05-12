/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Appointment;
import Entity.Doctor;
import Entity.Typeappoinment;
import Services.ServiceRednezVous;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Entity.User;
import Services.ServicetypeRDV;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.util.Properties;
import java.util.stream.Collectors;
import javafx.scene.control.ChoiceBox;
/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutrendezVousController implements Initializable {

    @FXML
    private TextField tftype;
    @FXML
    private TextField tfcategorie;
     private Label LBShow;
    @FXML
    private ImageView tftypeselection;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //afficher la liste des nom des typeappoinment dans le choiceBox
       /* List<String> typeNames = null;
        try {
            typeNames = typeAppointmentService.getAllTypes()
                    .stream()
                    .map(Typeappoinment::getNomtype)
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            Logger.getLogger(AjoutrendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ajouter les noms à la liste des choix du ChoiceBox
        typeAppointmentChoiceBox.getItems().addAll(typeNames);*/
    
    }    

    
    
     @FXML
     private void ajouterCov(ActionEvent event) throws SQLException {
         Typeappoinment p = new Typeappoinment();
        p.setNomtype(tftype.getText());
        p.setDescription(tfcategorie.getText());
        
        ServicetypeRDV sp =new ServicetypeRDV();
         String nomtype = tftype.getText() ; 
                String categorie = tfcategorie.getText() ; 
         if (categorie.isEmpty() || nomtype.isEmpty()   ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît remplir tous les champs !");
            alert.showAndWait();
        } else  {
        sp.ajouter_reservation_cov(p);
    }
     }
  
   
     @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/AffichageType.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
   
   /* private void ok(ActionEvent event) {
               
        ServicetypeRDV typeAppointmentService = new ServicetypeRDV();

               String type = tftype.getText() ; 
                String categorie = tfcategorie.getText() ; 
       

           
                // controle de saisie 
        if (type.isEmpty() || categorie.isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît remplir tous les champs !");
            alert.showAndWait();
        } else  {
        
  
            Typeappoinment p = new Typeappoinment(type,categorie);

            try {
                typeAppointmentService.ajouter_reservation_cov(p);
             //   String subject = "";
             // String body = "" ; 
           //     String body = "Hello " + u.getNom() + ",\n\nYour carpool from " + depart + " to " + destination + " on " + date.toString() + " has been successfully added to the system.\n\nThank you for using our service!\n\nBest regards,\nThe Carpool Team";
            
                
              
            } catch (SQLException ex) {
                System.out.println(ex);
            }

            JOptionPane.showMessageDialog(null, "type ajouté avec succes");
            tftype.clear();
                        tfcategorie.clear();
           
 
    



      
           }
        }*/
    
}
   
    
    
    
    
    

    



