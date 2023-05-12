/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomeController implements Initializable {

    @FXML
    private Button c;
    @FXML
    private Button m;
    @FXML
    private Pane p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void changePage(ActionEvent event)
    {
        if(event.getSource() == c){
        p.getChildren().removeAll(p.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Cat.fxml"));
                Parent root = loader.load();
                p.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        else if(event.getSource() == m){
        p.getChildren().removeAll(p.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Med.fxml"));
                Parent root = loader.load();
                p.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
    }
    
}
