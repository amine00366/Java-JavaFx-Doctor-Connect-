/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categorie;
import Entities.Medicament;
import Services.ServiceCategorie;
import Services.ServiceMedicament;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierMedController implements Initializable {

    @FXML
    private TextField i;
    @FXML
    private TextField p;
    @FXML
    private ChoiceBox<String> c;
    @FXML
    private TextField d;
    @FXML
    private TextField n;
    @FXML
    private Button annuler;
    @FXML
    private Button enr;

     ServiceMedicament sm = new ServiceMedicament();
     ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private AnchorPane content;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Medicament m =new Medicament();
        m=sm.getbyid(MedController.idm);
        i.setText(m.getImage());
        p.setText(""+m.getPrix());
        List<String> lc = sc.getallnames();
      c.setItems(FXCollections.observableArrayList(lc));
        d.setText(m.getDescription());
        n.setText(m.getNom());
    }    

    @FXML
    private void GoBack(ActionEvent event) {
            content.getChildren().removeAll(content.getChildren()); 
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Med.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

    @FXML
    private void Update(ActionEvent event) {
        String image=i.getText();
        String nom=n.getText();
        String description=d.getText();
        String prix= p.getText();
        System.out.println(prix);
         Categorie caaa = new Categorie();
       String categorie=c.getValue();
       List<Categorie> lc = sc.afficher();
       for (Categorie a:lc){
           if (categorie.equals(a.getNom())){
               caaa=a;
               break;
           }
       }
        ServiceMedicament sm = new ServiceMedicament();
        float prixf=Float.parseFloat(prix);
        Medicament m = new Medicament(nom,caaa,description,image,prixf);
        sm.modifier(m);
        content.getChildren().removeAll(content.getChildren()); 
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Med.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
}