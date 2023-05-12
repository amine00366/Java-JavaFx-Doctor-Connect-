/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Typeappoinment;
import Services.ServicetypeRDV;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.paint.Color;
import javafx.scene.Cursor;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ModifierTypeController implements Initializable {

   
    
   // private TextField tfnomtype;
    // private TextField tfdescription;
    @FXML
    private VBox vb;
    private static int a;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfdesc;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    
           
        
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
        vb.setSpacing(20);
        //vb.setAlignment(Pos.CENTER);
vb.setSpacing(10);
        for (Typeappoinment d : l) {

        
            HBox hbox = new HBox();
             hbox.setPadding(new Insets(10));
   // hbox.setStyle("-fx-background-color: #fffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");
            Label label1 = new Label(d.getNomtype());
            label1.setWrapText(true);
    label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label1.setPrefWidth(200);
            Label label2 = new Label(d.getDescription());
             label2.setWrapText(true);
    label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(400);
          

/////////////////////////////////////////////////
          
           
        /*    label2.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                 

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            */
         HBox iconsBox = new HBox(10); // contient les icônes de suppression et de modification
    iconsBox.setAlignment(Pos.CENTER_RIGHT);
    iconsBox.setPrefWidth(100);
    HBox.setHgrow(iconsBox, Priority.ALWAYS);

                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");
  FontAwesomeIconView modif = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
    modif.setFill(Color.GREEN);
    modif.setGlyphSize(25);
    modif.setCursor(Cursor.HAND);
    modif.setOnMouseClicked(event -> {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
                 a = d.getId();
                        

  

    tfdesc.setText(d.getDescription());
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   tftype.setText(d.getNomtype());


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
                     ser.supprime_reservation_cov(d.getId());
                     JOptionPane.showMessageDialog(null, "type supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
                
          iconsBox.getChildren().addAll(modif, supp);

    hbox.getChildren().addAll(label1, label2, iconsBox);
    hbox.setSpacing(20);
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(10));
    vb.getChildren().add(hbox);
}

vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment:");
    }   

    @FXML
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
          
    
   
            Typeappoinment p = new Typeappoinment(a, nomtype, description);

            try {
                ser.update_reservation_cov(p);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
       

            Actualiser();
            JOptionPane.showMessageDialog(null, "Type modifie");
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
        vb.setSpacing(20);
        //vb.setAlignment(Pos.CENTER);
vb.setSpacing(10);
        for (Typeappoinment d : l) {

        
            HBox hbox = new HBox();
             hbox.setPadding(new Insets(10));
   // hbox.setStyle("-fx-background-color: #fffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");
            Label label1 = new Label(d.getNomtype());
            label1.setWrapText(true);
    label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label1.setPrefWidth(200);
            Label label2 = new Label(d.getDescription());
             label2.setWrapText(true);
    label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(400);
          

/////////////////////////////////////////////////
          
           
        /*    label2.setStyle("-fx-font-size: 10px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                 

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            */
         HBox iconsBox = new HBox(10); // contient les icônes de suppression et de modification
    iconsBox.setAlignment(Pos.CENTER_RIGHT);
    iconsBox.setPrefWidth(100);
    HBox.setHgrow(iconsBox, Priority.ALWAYS);

                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");
  FontAwesomeIconView modif = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
    modif.setFill(Color.GREEN);
    modif.setGlyphSize(25);
    modif.setCursor(Cursor.HAND);
    modif.setOnMouseClicked(event -> {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
                 a = d.getId();
                        

  

    tfdesc.setText(d.getDescription());
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   tftype.setText(d.getNomtype());


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
                     ser.supprime_reservation_cov(d.getId());
                     JOptionPane.showMessageDialog(null, "type supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    Actualiser();

});
                
          iconsBox.getChildren().addAll(modif, supp);

    hbox.getChildren().addAll(label1, label2, iconsBox);
    hbox.setSpacing(20);
    hbox.setAlignment(Pos.CENTER_LEFT);
    hbox.setPadding(new Insets(10));
    vb.getChildren().add(hbox);
}

vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment:");
    } 

    @FXML
    private void goreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/AffichageType.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void uyuyu(ActionEvent event) {
    }
   
}