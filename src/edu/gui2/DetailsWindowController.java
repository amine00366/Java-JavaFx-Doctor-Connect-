/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui2;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SAHLI
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField textNom;
    @FXML
    private TextField textDose;
    @FXML
    private TextField textId;
    @FXML
    private TextField textFrequence;
    @FXML
    private DatePicker textDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextNom(String message) {
        this.textNom.setText(message);

    }

    public void setTextDose(String message) {
        this.textDose.setText(message);

    }

    public void setTextFrequence(String message) {
        this.textFrequence.setText(message);

    }

    public void setTextId(String message) {
        this.textId.setText(message);

    }

    public void setTextDate(String message) {
        this.textDate.setValue(LocalDate.parse(message));
    }

    
}
