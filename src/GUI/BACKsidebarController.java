/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Doctor;
import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EMNA
 */
public class BACKsidebarController implements Initializable {

    @FXML
        private AnchorPane contenu;

    @FXML
    private Button gestionRV;
    @FXML
    private Button ModifierRDV;
    @FXML
    private Button AjouterRDV;
    @FXML
    private Button StRDV;
    @FXML
    private Button Rec;
    @FXML
    private Button med;
    @FXML
    private Button Post;
    @FXML
    private Button logoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
        @FXML
    private void GoRV(ActionEvent event) {
        contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageType.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going gestion rv");            }
    }

    @FXML
    private void ModifierRDV(ActionEvent event) {
        contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierType.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going modif rv");            }
    }
    

    @FXML
    private void AjouterRDV(ActionEvent event) {
        contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutrendezVous.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going ajout rv");            }
    }
    

    @FXML
    private void StRDV(ActionEvent event) {
    contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Stat.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going stat rv");            }
    }

    @FXML
    private void GoPost(ActionEvent event) {
        contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPostback.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going Post ");  
    }
    }

    @FXML
    private void GoRec(ActionEvent event) {
        contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTypeReclamation.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going Reclamation ");  
    }
    }

     @FXML
private void handleLogOut(ActionEvent event) throws IOException {
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


    @FXML
    private void GoMed(ActionEvent event) {
        contenu.getChildren().removeAll(contenu.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = loader.load();
                contenu.getChildren().add(root);
            } catch (Exception ex) {
            System.out.println("going medicaments");  
            }
    }
    
}
