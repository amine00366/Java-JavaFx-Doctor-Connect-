
package Gui;

import Entity.Appointment;
import Entity.Doctor;
import Entity.User;
import Services.ServiceRednezVous;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Pdf;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class RendezVousAfficheController implements Initializable {

    @FXML
    private VBox vb;
HBox column1 = new HBox();
    @FXML
    private TextField rech;
    @FXML
    private Button logoutButton;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rech.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            filterCovoiturages(newValue);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    });
        
     //    vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<Appointment> l = new ArrayList<Appointment>();
        ServiceRednezVous ser = new ServiceRednezVous();
        int idc = SessionManager.getId();
        try {
            l = ser.UserAppointment(idc);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
      

        ///////////////////////////////////////////////user
   for (Appointment d : l) {
    HBox hbox = new HBox(90); // Increase the spacing between labels
    hbox.setPadding(new Insets(10, 0, 10, 0)); // Add more top and bottom padding
   //box.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");

   // Label label1 = new Label( d.getUser().getUserName()); // Add a prefix to the label text
   // label1.setWrapText(true);
  //label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label1.setPrefWidth(200);

    Label label2 = new Label( d.getDoctor().getFirstName());
    label2.setWrapText(true);
   //abel2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(200);

    Label label3 = new Label( d.getAppointment_date().toString());
    label3.setWrapText(true);
  //label3.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label3.setPrefWidth(200);

   // Label label4 = new Label( d.getDatefin().toString());
   // label4.setWrapText(true);
   //abel4.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label4.setPrefWidth(200);

    Label label5 = new Label( String.valueOf(d.getType().getNomtype()));
    label5.setWrapText(true);
    label5.setPrefWidth(200);
    label5.setMinWidth(Region.USE_PREF_SIZE);
   //abel5.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   

    Label label7 = new Label( d.getCategorie());
    label7.setWrapText(true);
   //abel7.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label7.setPrefWidth(200);
    
    hbox.getChildren().addAll( label2, label3,  label5, label7);   //label1,label4,
    hbox.setAlignment(Pos.CENTER_LEFT);
    vb.getChildren().add(hbox);
}


    }

    /* @FXML
    private void trier(ActionEvent event) {
      vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<Appointment> l = new ArrayList<Appointment>();
        ServiceRednezVous ser = new ServiceRednezVous();
        try {
            l = ser.sortbydate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Covoiturage d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getDepart());
            Label label2 = new Label(d.getDestination());
            Label label3 = new Label(String.valueOf(d.getNbrplace()));
Label label4 = new Label(String.valueOf(d.getPrix()));
       //     label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            Label label5 = new Label(d.getDate_covoiturage().toString());
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }*/



    @FXML
    private void actualiser(ActionEvent event) {
            rech.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            filterCovoiturages(newValue);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    });
        
     //    vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<Appointment> l = new ArrayList<Appointment>();
        ServiceRednezVous ser = new ServiceRednezVous();
       
        int idc = SessionManager.getId();
        try {
            l = ser.UserAppointment(idc);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
      

        ///////////////////////////////////////////////user
   for (Appointment d : l) {
    HBox hbox = new HBox(90); // Increase the spacing between labels
    hbox.setPadding(new Insets(10, 0, 10, 0)); // Add more top and bottom padding
   //box.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");

   // Label label1 = new Label( d.getUser().getUserName()); // Add a prefix to the label text
   // label1.setWrapText(true);
  //label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label1.setPrefWidth(200);

    Label label2 = new Label( d.getDoctor().getFirstName());
    label2.setWrapText(true);
   //abel2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(200);

    Label label3 = new Label( d.getAppointment_date().toString());
    label3.setWrapText(true);
  //label3.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label3.setPrefWidth(200);

  //  Label label4 = new Label( d.getDatefin().toString());
   // label4.setWrapText(true);
   //abel4.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label4.setPrefWidth(200);

    Label label5 = new Label( String.valueOf(d.getType().getNomtype()));
    label5.setWrapText(true);
    label5.setPrefWidth(200);
    label5.setMinWidth(Region.USE_PREF_SIZE);
   //abel5.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   

    Label label7 = new Label( d.getCategorie());
    label7.setWrapText(true);
   //abel7.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label7.setPrefWidth(200);
    
    hbox.getChildren().addAll( label2, label3,  label5, label7);   //label1, label4,
    hbox.setAlignment(Pos.CENTER_LEFT);
    vb.getChildren().add(hbox);
}

    }
    
    private void filterCovoiturages(String searchValue) throws SQLException {
    vb.getChildren().clear(); // Clear the VBox to remove all previous entries

    ServiceRednezVous ser = new ServiceRednezVous();
     int idc = SessionManager.getId();
    List<Appointment> RDVE= ser.UserAppointment(idc);
  

    for (Appointment RDV : RDVE) {
        
        if (RDV.getUser().getUserName().toLowerCase().contains(searchValue.toLowerCase())
                || RDV.getDoctor().getFirstName().toLowerCase().contains(searchValue.toLowerCase())) {
           HBox hbox = new HBox(90); // Increase the spacing between labels
    hbox.setPadding(new Insets(10, 0, 10, 0)); // Add more top and bottom padding
           // Label label1 = new Label( RDV.getUser().getUserName()); // Add a prefix to the label text
   // label1.setWrapText(true);
  //label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label1.setPrefWidth(200);

    Label label2 = new Label( RDV.getDoctor().getFirstName());
    label2.setWrapText(true);
   //abel2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(200);

    Label label3 = new Label( RDV.getAppointment_date().toString());
    label3.setWrapText(true);
  //label3.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label3.setPrefWidth(200);

   // Label label4 = new Label( RDV.getDatefin().toString());
   // label4.setWrapText(true);
   //abel4.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label4.setPrefWidth(200);

    Label label5 = new Label( String.valueOf(RDV.getType().getNomtype()));
    label5.setWrapText(true);
    label5.setPrefWidth(200);
    label5.setMinWidth(Region.USE_PREF_SIZE);
   //abel5.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   

    Label label7 = new Label( RDV.getCategorie());
    label7.setWrapText(true);
   //abel7.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label7.setPrefWidth(200);
    
    hbox.getChildren().addAll( label2, label3,  label5, label7); //label1, label4,
    hbox.setAlignment(Pos.CENTER_LEFT);
    vb.getChildren().add(hbox);
        }
    }
}


    
    /* @FXML
    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/ChoixBox.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }*/

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/SupprimerReservation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/UpdateRDV.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    
        @FXML
    private void pdf(ActionEvent event) {
                     Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("Liste des Réservation");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceRednezVous.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


     @FXML
    private void gostat(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Stat.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void goPost(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowPost.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   

    @FXML
    private void goRec(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLAjoutReclamation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gomedica(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/UtilisateurGUI.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void goRDV(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         // clear current user/doctor
    User.setCurrent_User(null);
    Doctor.setCurrent_doctor(null);
    
    // close current window or navigate back to login page
    Stage stage = (Stage) logoutButton.getScene().getWindow();
    stage.close(); // close current window
    
    // navigate back to login page
    Parent root = FXMLLoader.load(getClass().getResource("/Gui/start.fxml"));
    Scene scene = new Scene(root);
    Stage loginStage = new Stage();
    loginStage.setScene(scene);
    loginStage.show();
    }
    
}




// problem dans ll affichage de tri 
// chnager vbox to tableview