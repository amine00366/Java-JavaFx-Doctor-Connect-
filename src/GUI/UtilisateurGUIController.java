/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Medicament;
import Entity.Doctor;
import Entity.User;
import static GUI.MedController.ipa;
import Services.ServiceCategorie;
import Services.ServiceMedicament;
import com.google.zxing.WriterException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UtilisateurGUIController implements Initializable {

    @FXML
    private TableView<Medicament> T;
    @FXML
    private TableColumn<Medicament, String> i;
    @FXML
    private TableColumn<Medicament, String> n;
    @FXML
    private TableColumn<Medicament, String> d;
    @FXML
    private TableColumn<Medicament, String> c;
    @FXML
    private TableColumn<Medicament, Float> p;
    @FXML
    private TextField cher;
  ServiceMedicament sm = new ServiceMedicament();
           ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private Button qr;
    @FXML
    private Button logoutButton;
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
         List<Medicament> medicamentsList = sm.afficher();
        ObservableList<Medicament> medicamentsObservableList = FXCollections.observableArrayList();
         ObservableList<ImageView> imgs = FXCollections.observableArrayList();
        for(Medicament m:medicamentsList){
             
            medicamentsObservableList.add(m);
             }
        
        n.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Nom"));
        d.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Description"));
        c.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Categorie"));
        p.setCellValueFactory(new PropertyValueFactory<Medicament, Float>("Prix"));
         i.setCellValueFactory(new PropertyValueFactory<>("Image"));
            i.setCellFactory(column -> new TableCell<Medicament, String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {
                        Image image = new Image(new File(imagePath).toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        setGraphic(imageView);
                    }
                }
            });
        T.setItems(medicamentsObservableList);
    }    

    @FXML
    private void chercher(KeyEvent event) {
          String ch=cher.getText();
        ServiceMedicament sm = new ServiceMedicament();
        List<Medicament> medicamentsList = sm.chercher(ch);
        i.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Image"));
        n.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Nom"));
        d.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Description"));
        c.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Categorie"));
        p.setCellValueFactory(new PropertyValueFactory<Medicament, Float>("Prix"));
        ObservableList<Medicament> medicamentsObservableList = FXCollections.observableList(medicamentsList);
        T.setItems(medicamentsObservableList);
    }

    @FXML
    private void Gen(ActionEvent event) throws WriterException, IOException {
          Medicament selectedItem = T.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
           String value = n.getCellData(selectedItem);
           List<Medicament> lm = sm.afficher();
           int i=0;
           for(Medicament m:lm){
               String nom=m.getNom();
            if(value.equals(nom)){
                i = m.getId();
                break;
            }
           }
           Medicament m=sm.getbyid(i);
          String n= m.getNom();
           sm.generateQR(i);
          ipa="imgs\\QRcodes\\" + n+"_qr.png";
           Parent root=FXMLLoader.load(getClass().getResource("ImQr.fxml"));
             Stage primaryStage=new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
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
    private void gomedica(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/UtilisateurGUI.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goRDV(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/RendezVousAffiche.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private void goRec(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLAjoutReclamation.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
