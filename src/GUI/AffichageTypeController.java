/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Typeappoinment;
import Services.ServicetypeRDV;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import java.awt.Color;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AffichageTypeController implements Initializable {

    @FXML
    private VBox vb;
HBox column1 = new HBox();
    @FXML
    private TextField rech;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rech.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            filter(newValue);
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


        List<Typeappoinment> l = new ArrayList<Typeappoinment>();
        ServicetypeRDV ser = new ServicetypeRDV();
        try {
            l = ser.readAll_reservation_cov();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Typeappoinment d : l) {

        
              HBox hbox = new HBox();
             hbox.setPadding(new Insets(10));
   // hbox.setStyle("-fx-background-color: #fffff; -fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");
            Label label1 = new Label(d.getNomtype());
            label1.setWrapText(true);
    label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label1.setPrefWidth(300);
            Label label2 = new Label(d.getDescription());
             label2.setWrapText(true);
    label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(500);
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, label2);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }

    private void trier(ActionEvent event) {
      vb.getChildren().clear();
//column1.getChildren().addAll(new Label("depart:"), new Label("destination:"), new Label("prix:"), new Label("nombre des personnes:"), 
  //       new Label("date:"));
    //   vb.getChildren().add(column1); 
      //     column1.setStyle("  -fx-background-color: red;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
 //column1.setSpacing(100);
   //         column1.setAlignment(Pos.CENTER_LEFT);


        List<Typeappoinment> l = new ArrayList<Typeappoinment>();
        ServicetypeRDV ser = new ServicetypeRDV();
       /* try {
            l = ser.sortbydate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/
        ///////////////////////////////////////////////user
        for (Typeappoinment d : l) {

        
            HBox hbox = new HBox();
             hbox.setPadding(new Insets(10));
            Label label1 = new Label(d.getNomtype());
            label1.setWrapText(true);
             label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
            Label label2 = new Label(d.getDescription());
             label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(500);
    

/////////////////////////////////////////////////
          
           
       //    hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            hbox.getChildren().addAll(label1, label2);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }



    @FXML
    private void actualiser(ActionEvent event) {
     
    

        List<Typeappoinment> l = new ArrayList<Typeappoinment>();
        ServicetypeRDV ser = new ServicetypeRDV();
        try {
            l = ser.readAll_reservation_cov();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        for (Typeappoinment d : l) {

        
              HBox hbox = new HBox();
             hbox.setPadding(new Insets(10));
  
            Label label1 = new Label(d.getNomtype());
            label1.setWrapText(true);
    label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label1.setPrefWidth(200);
            Label label2 = new Label(d.getDescription());
             label2.setWrapText(true);
    label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(400);
        
            hbox.getChildren().addAll(label1, label2);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);
          //  hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }

    }
    
    private void filter(String searchValue) throws SQLException {
    vb.getChildren().clear(); // Clear the VBox to remove all previous entries

    ServicetypeRDV ser = new ServicetypeRDV();
    List<Typeappoinment> covoiturages = ser.readAll_reservation_cov();

    for (Typeappoinment covoiturage : covoiturages) {
        if (covoiturage.getNomtype().toLowerCase().contains(searchValue.toLowerCase())
                || covoiturage.getDescription().toLowerCase().contains(searchValue.toLowerCase())) {
            HBox hbox = new HBox();
             hbox.setPadding(new Insets(10));
            Label label1 = new Label(covoiturage.getNomtype());
             label1.setWrapText(true);
             label1.setPrefWidth(300);
    label1.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
            Label label2 = new Label(covoiturage.getDescription());
             label2.setWrapText(true);
    label2.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
    label2.setPrefWidth(500);
       //     hbox.setStyle("    -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");

            hbox.getChildren().addAll(label1, label2);
            hbox.setSpacing(120);
            hbox.setAlignment(Pos.CENTER_LEFT);

            vb.getChildren().add(hbox); // Add the HBox to the VBox
        }
    }
}


    
    private void goajout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ajoutrendezVous.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void gosupp(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/Supprimer_Covoiturage.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/ModifierType.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void nooo(ActionEvent event) {
    }

    @FXML
    private void ooo(ActionEvent event) {
    }

   



  
}
