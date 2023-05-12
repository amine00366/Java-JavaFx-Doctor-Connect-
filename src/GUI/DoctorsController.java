
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Typeappoinment;
import Entity.Doctor;
import Services.ServiceDoctor;
import Services.ServicetypeRDV;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class DoctorsController implements Initializable {

   
    
   // private TextField tfnomtype;
    // private TextField tfdescription;
    @FXML
    private VBox vb;
    private static int a;
    @FXML
    private ImageView Image;
    private TextField tftype;
    private TextField tfdesc;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    
           
        
      vb.getChildren().clear();
        List<Doctor> l = new ArrayList<Doctor>();
        ServiceDoctor ser = new ServiceDoctor();
        l = ser.readAlldoctors();
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Doctor d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getFirstName());
            Label label2 = new Label(d.getEmail());
            
          

/////////////////////////////////////////////////
          
           
               hbox.setStyle("  -fx-background-color: #BDECB6;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                 

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            
                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

  button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId();
                

  

    tfdesc.setText(d.getFirstName());
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   tftype.setText(d.getEmail());


                 });
  
  
    Button button2 = new Button("Supprimer !");
                                     button2.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprime_reservation_cov(d.getId());
                     JOptionPane.showMessageDialog(null, "type supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
Button button3 = new Button("réserver !");
button3.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");
button3.setOnAction(event -> {
    try {
        // Récupérer l'id du docteur
        int doctorId = d.getId();
        System.out.println("Id du docteur : " + doctorId);
                    
        // Charger la vue de formulaire de réservation et passer l'id du docteur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoixBox.fxml"));
        Parent root = loader.load();
        ChoixBoxController choixBoxController = loader.getController();
        choixBoxController.setIdDocteur(doctorId);
                    
        // Afficher la vue de formulaire de réservation
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(DoctorsController.class.getName()).log(Level.SEVERE, null, ex);
    }
});

hbox.getChildren().addAll(label1, label2, button , button2 ,button3);
hbox.setSpacing(20);
hbox.setAlignment(Pos.CENTER_LEFT);
hbox.setPadding(new Insets(10));

vb.getChildren().add(hbox);


                
           
        }
               vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");


    } 

    private void modifier(ActionEvent event) {
   
        ServicetypeRDV ser = new ServicetypeRDV();

        String nomtype = (String) tftype.getText();
        String description = (String) tfdesc.getText();
       
        

if ( a == 0 ) { 
     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("s'il vous plaît selectionner un type à modifier!");
            alert.showAndWait();
} else 

        if (nomtype.isEmpty() || description.isEmpty()  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît il faut remplir tous les champs ! ");
            alert.showAndWait();
        } else  {
          
    
   
            Doctor p = new Doctor(a, nomtype, description);

           
       

            Actualiser();
            JOptionPane.showMessageDialog(null, "covoiturage modifie");
            tftype.clear();
                        tfdesc.clear();
         
    
        }
    }


    private void goaffi(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/affichageType.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ajoutrendezVous.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/SupprimerType.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void Actualiser() {
        vb.getChildren().clear();
          List<Typeappoinment> l = new ArrayList<Typeappoinment>();
        ServicetypeRDV ser = new ServicetypeRDV();
        try {
            l = ser.readAll_reservation_cov();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Typeappoinment d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getNomtype());
            Label label2 = new Label(d.getDescription());
          

/////////////////////////////////////////////////
          
           
               hbox.setStyle("  -fx-background-color: #BDECB6;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                 

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            
                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

  button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
 a = d.getId();
                

  

    tfdesc.setText(d.getDescription());
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   tftype.setText(d.getNomtype());


                 });
  
  
    Button button2 = new Button("Supprimer !");
                                     button2.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprime_reservation_cov(d.getId());
                     JOptionPane.showMessageDialog(null, "type supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
                
            hbox.getChildren().addAll(label1, label2, button , button2);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
               vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");

    } 

    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui_handiny/reserver_covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/RendezVousAffiche.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}