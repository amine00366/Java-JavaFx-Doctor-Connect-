/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Appointment;
import Entity.Doctor;
import Entity.Typeappoinment;
import Entity.User;
import static Gui.SupprimerReservationController.ACCOUNT_SID;
import static Gui.SupprimerReservationController.AUTH_TOKEN;
import Services.ServiceRednezVous;
import Services.ServicetypeRDV;
import com.jfoenix.controls.JFXTimePicker;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class UpdateRDVController implements Initializable {

   @FXML
    private ChoiceBox<Typeappoinment> choixType;

    private List<Typeappoinment> types = new ArrayList<>();
    @FXML
    private ChoiceBox<String> choixcat;
       private static int a;
       Doctor c ; 
       User b ; 
    @FXML
    
    private DatePicker datedebut;
    //@FXML
   // private DatePicker datefin;
    @FXML
    private VBox vb;



    /**
     * Initializes the controller class.
     */
    
     public static final String ACCOUNT_SID = "AC29f89cff9b5786c7eeb6ea8ed4bb7487";
  // Your Auth Token from twilio.com/console
  public static final String AUTH_TOKEN = "f66e989f775e5d08aceafda1d4c730ae";
    @FXML
    private JFXTimePicker time;
    @FXML
    private Button goreserv;
    @FXML
    private Button logoutButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         vb.getChildren().clear();
           List<String> modes = new ArrayList<>();
modes.add("En ligne");
modes.add("Présentielle");
choixcat.getItems().addAll(modes);

 ServicetypeRDV serviceTypeRDV = new ServicetypeRDV();

        try {
            types = serviceTypeRDV.readAll_reservation_cov();
        } catch (SQLException ex) {
            Logger.getLogger(ChoixBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ajout des noms des types dans la choiceBox
        choixType.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        choixType.setConverter(new StringConverter<Typeappoinment>() {
            public String toString(Typeappoinment type) {
                return type.getNomtype();
            }

            public Typeappoinment fromString(String string) {
                return choixType.getItems().stream().filter(type ->
                        type.getNomtype().equals(string)).findFirst().orElse(null);
            }
        });
        
        
          List<Appointment> l = new ArrayList<Appointment>();
          ServiceRednezVous ser = new ServiceRednezVous();
          int idc = SessionManager.getId();
        try {
            l = ser.UserAppointment(idc);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
           vb.setSpacing(10);
          


for (Appointment d : l) {
    HBox hbox = new HBox(10);
    hbox.setPadding(new Insets(10));
   // hbox.setStyle("-fx-background-color: #fffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");

   // Label label1 = new Label(d.getUser().getUserName());
   // label1.setWrapText(true);
   // label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label1.setPrefWidth(100);

    Label label2 = new Label(d.getDoctor().getFirstName());
    label2.setWrapText(true);
   // label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(100);

    Label label3 = new Label(d.getAppointment_date().toString());
    label3.setWrapText(true);
   // label3.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label3.setPrefWidth(100);

    Label label4 = new Label(d.getDatefin().toString());
   label4.setWrapText(true);
    label4.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   label4.setPrefWidth(100);

    Label label5 = new Label(String.valueOf(d.getType().getNomtype()));
    label5.setWrapText(true);
    label5.setPrefWidth(200);
    label5.setMinWidth(Region.USE_PREF_SIZE);
  //  label5.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label5.setPrefWidth(100);

    Label label7 = new Label(d.getCategorie());
    label7.setWrapText(true);
  //  label7.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label7.setPrefWidth(100);

    HBox iconsBox = new HBox(10); // contient les icônes de suppression et de modification
    iconsBox.setAlignment(Pos.CENTER_RIGHT);
    iconsBox.setPrefWidth(100);

    FontAwesomeIconView modif = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
    modif.setFill(Color.GREEN);
    modif.setGlyphSize(25);
    modif.setCursor(Cursor.HAND);
    modif.setOnMouseClicked(event -> {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
                 a = d.getId();
                 b = d.getUser() ; 
                 c = d.getDoctor() ; 
                  datedebut.setValue(d.getAppointment_date().toLocalDate());
            // datefin.setValue(d.getDatefin().toLocalDate());
          //   time.setValue(LocalTime.MAX);
   
  

     choixType.setOnAction(o -> {
            Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
            int selectedTypeId = selectedType.getId();
            System.out.println("Type sélectionné : " + selectedType.getNomtype() + ", id : " + selectedTypeId);
        });
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   choixcat.setOnAction(o -> {
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
    System.out.println("Mode sélectionné : " + selectedMode);
});


                 });
    FontAwesomeIconView supp = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
    supp.setFill(Color.RED);
    supp.setGlyphSize(25);
    supp.setCursor(Cursor.HAND);
    supp.setOnMouseClicked(event -> {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprimer(d.getId());
                     // sendSms( "52505517","salut Votre  rendez vous chez "+ d.getDoctor().getFirstName() +  " est annulé !") ;
                     JOptionPane.showMessageDialog(null, "type supprimé");
                     Actualiser();
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
    iconsBox.getChildren().addAll(modif, supp);

    hbox.getChildren().addAll( label2, label3,label4,  label5, label7, iconsBox);  //label1, 
    hbox.setSpacing(20);
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(10));
    vb.getChildren().add(hbox);
}

vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment:");
    }   

    @FXML
    private void modifier(ActionEvent event) {
      //  System.out.println("GUI.UpdateRDVController.modifier()");
   int idDocteur =7;
        ServiceRednezVous ser = new ServiceRednezVous();

         Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
   
// Date selectedStartDate = Date.valueOf(datedebut.getValue());
    //Date selectedEndDate = Date.valueOf(datefin.getValue());
    
    LocalDate startDate = datedebut.getValue();
LocalTime startTime = time.getValue();
LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
    
       
        

if ( a == 0 ) { 
     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("s'il vous plaît selectionner un type à modifier!");
            alert.showAndWait();
} else 

        if (selectedType == null || selectedMode.isEmpty() || startDate == null ||startTime==null   ) { //||selectedEndDate== null
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît il faut remplir tous les champs ! ");
            alert.showAndWait();
        } else  {
          
    
   
           Appointment p = new Appointment(a, b, c, startDateTime, selectedType,selectedMode, false);


            try {
                ser.update(p);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
       

            Actualiser();
            JOptionPane.showMessageDialog(null, "bien modifie");
            //tftype.clear();
                       // tfdesc.clear();
         
    
        }
    }
      public void Actualiser() {
               vb.getChildren().clear();
           List<String> modes = new ArrayList<>();
modes.add("En ligne");
modes.add("Présentielle");
choixcat.getItems().addAll(modes);

 ServicetypeRDV serviceTypeRDV = new ServicetypeRDV();

        try {
            types = serviceTypeRDV.readAll_reservation_cov();
        } catch (SQLException ex) {
            Logger.getLogger(ChoixBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ajout des noms des types dans la choiceBox
        choixType.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        choixType.setConverter(new StringConverter<Typeappoinment>() {
            public String toString(Typeappoinment type) {
                return type.getNomtype();
            }

            public Typeappoinment fromString(String string) {
                return choixType.getItems().stream().filter(type ->
                        type.getNomtype().equals(string)).findFirst().orElse(null);
            }
        });
        
        
          List<Appointment> l = new ArrayList<Appointment>();
          ServiceRednezVous ser = new ServiceRednezVous();
          int idc = SessionManager.getId();
        try {
            l = ser.UserAppointment(idc);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
           vb.setSpacing(10);
          


for (Appointment d : l) {
    HBox hbox = new HBox(10);
    hbox.setPadding(new Insets(10));
   // hbox.setStyle("-fx-background-color: #fffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");

   // Label label1 = new Label(d.getUser().getUserName());
   // label1.setWrapText(true);
   // label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   // label1.setPrefWidth(100);

    Label label2 = new Label(d.getDoctor().getFirstName());
    label2.setWrapText(true);
   // label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(100);

    Label label3 = new Label(d.getAppointment_date().toString());
    label3.setWrapText(true);
   // label3.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label3.setPrefWidth(100);

    Label label4 = new Label(d.getDatefin().toString());
   label4.setWrapText(true);
    label4.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
   label4.setPrefWidth(100);

    Label label5 = new Label(String.valueOf(d.getType().getNomtype()));
    label5.setWrapText(true);
    label5.setPrefWidth(200);
    label5.setMinWidth(Region.USE_PREF_SIZE);
  //  label5.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label5.setPrefWidth(100);

    Label label7 = new Label(d.getCategorie());
    label7.setWrapText(true);
  //  label7.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label7.setPrefWidth(100);

    HBox iconsBox = new HBox(10); // contient les icônes de suppression et de modification
    iconsBox.setAlignment(Pos.CENTER_RIGHT);
    iconsBox.setPrefWidth(100);

    FontAwesomeIconView modif = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
    modif.setFill(Color.GREEN);
    modif.setGlyphSize(25);
    modif.setCursor(Cursor.HAND);
    modif.setOnMouseClicked(event -> {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
                 a = d.getId();
                 b = d.getUser() ; 
                 c = d.getDoctor() ; 
                  datedebut.setValue(d.getAppointment_date().toLocalDate());
            // datefin.setValue(d.getDatefin().toLocalDate());
          //   time.setValue(LocalTime.MAX);
   
  

     choixType.setOnAction(o -> {
            Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
            int selectedTypeId = selectedType.getId();
            System.out.println("Type sélectionné : " + selectedType.getNomtype() + ", id : " + selectedTypeId);
        });
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   choixcat.setOnAction(o -> {
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
    System.out.println("Mode sélectionné : " + selectedMode);
});


                 });
    FontAwesomeIconView supp = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
    supp.setFill(Color.RED);
    supp.setGlyphSize(25);
    supp.setCursor(Cursor.HAND);
    supp.setOnMouseClicked(event -> {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprimer(d.getId());
                      //sendSms( "52505517","salut Votre  rendez vous chez "+ d.getDoctor().getFirstName() +  " est annulé !") ;
                     JOptionPane.showMessageDialog(null, "type supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
    iconsBox.getChildren().addAll(modif, supp);

    hbox.getChildren().addAll( label2, label3,label4,  label5, label7, iconsBox);  //label1, 
    hbox.setSpacing(20);
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(10));
    vb.getChildren().add(hbox);
}

vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment:");

    
    }    

  
    public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber("+216"+recipient), // To number
            new PhoneNumber("++16813233822"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }

      @FXML
    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/RendezVousAffiche.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void godoctors(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/Doctors.fxml")) ; 
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

    private void goord(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/gui2/AjouterOrdonnance.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goRec(ActionEvent event) {
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