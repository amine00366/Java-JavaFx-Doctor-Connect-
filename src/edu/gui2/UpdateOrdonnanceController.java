/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui2;

import edu.entities.Consultation;
import edu.entities.Ordonnance;
import edu.services.ConsultationCrud;
import edu.services.OrdonnanceCrud;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAHLI
 */
public class UpdateOrdonnanceController implements Initializable {

    @FXML
    private TextField tfDose1;
    @FXML
    private TextField tfNom1;
    @FXML
    private TextField tfFrequence1;
    @FXML
    private DatePicker tfDateCreation1;

    private TableColumn<Ordonnance, String> tbNo1;
    private TableColumn<Ordonnance, String> tbdos1;
    private TableColumn<Ordonnance, String> tbFrequence1;
    private TableColumn<Ordonnance, LocalDate> tbDate1;
    private TableView<Ordonnance> testajout1;
    private TextField tfId;
/**
     * Initializes the controller class.
     */
    ConsultationCrud cs = new ConsultationCrud();
    List<Consultation> consultation = cs.getAll();
    private int id_consultation_id = -1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Map<String, Integer> valuesMap = new HashMap<>();
//        for(Consultation c : consultation){
//            tfId.getItems().add(c.getEtat_Consultation());
//            valuesMap.put(c.getEtat_Consultation(),c.getId());
//        }
//        
//        tfId.setOnAction(event ->{
//            String SelectedOption = null;
//            SelectedOption = tfId.getValue();
//            int SelectedValue = 0;
//            SelectedValue = valuesMap.get(SelectedOption);
//            id_consultation_id = SelectedValue;
//        });
    }
        private Ordonnance ordonnance;

public void setOrdonnance(Ordonnance ordonnance) throws ParseException{
        this.ordonnance = ordonnance;
        tfNom1.setText(ordonnance.getNom_Medicament());
        tfDose1.setText(String.valueOf(ordonnance.getDose()));
        tfFrequence1.setText(ordonnance.getFrequence());
//        tfDateCreation1.setValue(LocalDate.parse(ordonnance.getDate_creation()));
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate dateCreation = LocalDate.parse((CharSequence) ordonnance.getDate_creation(), formatter);
tfDateCreation1.setValue(dateCreation);

}
    private void handleEnregistrer(ActionEvent event) {
        // Mise à jour de l'ordonnance
        ordonnance.setNom_Medicament(tfNom1.getText());
        ordonnance.setDose((tfDose1.getText()));
        ordonnance.setFrequence(tfFrequence1.getText());
//        ordonnance.setDate_creation(tfDateCreation1.getValue().toString());
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date dateCreation = formatter.parse(tfDateCreation1.getValue().toString());
//          ordonnance.setDate_creation(dateCreation);

        // Sauvegarde de l'ordonnance modifiée dans la base de données
        OrdonnanceCrud oc = new OrdonnanceCrud();
        oc.modifier(ordonnance);
        
        // Fermeture de la fenêtre
        ((Stage) tfNom1.getScene().getWindow()).close();
    }
    
    private void handleAnnuler(ActionEvent event) {
        // Fermeture de la fenêtre
        ((Stage) tfNom1.getScene().getWindow()).close();
    }
    

    
    private void Afficherord(ActionEvent event) {
        OrdonnanceCrud pc = new OrdonnanceCrud();

        ObservableList<Ordonnance> liste = pc.getAll();

        tbNo1.setCellValueFactory(new PropertyValueFactory<>("Nom_Medicament"));
        tbdos1.setCellValueFactory(new PropertyValueFactory<>("dose"));
        tbFrequence1.setCellValueFactory(new PropertyValueFactory<>("frequence"));
        tbDate1.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
//tbaction.cellValueFactoryProperty(new PropertyValueFactory<>("supprimer()"));
        testajout1.setItems(liste);

    }

    @FXML
    private void Modifordo(ActionEvent event) {
        if (tfDose1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" Veuillez saisir une valeur pour la dose. Ce champ ne peut pas être laissé vide! ");
            alert.showAndWait();
        } else if (tfNom1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" Entrez les noms des médicaments. Ce champ est obligatoire! ");
            alert.showAndWait();
        } else if (tfFrequence1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir une valeur pour la frequence. Ce champ ne peut pas être laissé vide! ");
            alert.showAndWait();
        } else if (tfDateCreation1.getValue().getYear() > 2023) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("vérifier votre date ");
            alert.showAndWait();
        } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("La modification d'un new Ordonnance a été effectué avec succées");
            alert.showAndWait();
            OrdonnanceCrud sa = new OrdonnanceCrud();
            int id = Integer.parseInt(tfId.getText());

            String Nom_Medicament = tfNom1.getText();
            String dose = tfDose1.getText();
            String frequence = tfFrequence1.getText();
//Timestamp date_creation = tfDateCreation1.getValue();
            LocalDate date = tfDateCreation1.getValue();
            LocalDateTime dateTime = date.atStartOfDay();
            Timestamp date_creation = Timestamp.valueOf(dateTime);

            Ordonnance re;
            re = (Ordonnance) testajout1.getSelectionModel().getSelectedItem();
            re.setId(id);

            re.setNom_Medicament(Nom_Medicament);
            re.setDose(dose);
            re.setFrquence(frequence);
            re.setDate_creation(date_creation);

            sa.modifier(re);
            sa.getAll();

        }

//    private void Modifordo(ActionEvent event) {
//        if (tfDose1.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" Veuillez saisir une valeur pour la dose. Ce champ ne peut pas être laissé vide! ");
//            alert.showAndWait();
//        } else if (tfNom1.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" Entrez les noms des médicaments. Ce champ est obligatoire! ");
//            alert.showAndWait();
//        } else if (tfFrequence1.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Veuillez saisir une valeur pour la frequence. Ce champ ne peut pas être laissé vide! ");
//            alert.showAndWait();
//        } else if (tfDateCreation1.getValue().getYear() > 2023) {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("vérifier votre date ");
//            alert.showAndWait();
//        } else {
//
//            OrdonnanceCrud sa = new OrdonnanceCrud();
//
//            String Nom_Medicament = tfNom1.getText();
//                        String dose = tfDose1.getText();
//            String frequence = tfFrequence1.getText();
//
//            LocalDate date = tfDateCreation1.getValue();
//            LocalDateTime dateTime = date.atStartOfDay();
//            Timestamp date_creation = Timestamp.valueOf(dateTime);
//
//            Ordonnance re;
//            re = (Ordonnance) testajout1.getSelectionModel().getSelectedItem();
//            re.setNom_Medicament(Nom_Medicament);
//            re.setDose(dose);
//            re.setFrquence(frequence);
//            re.setDate_creation(date_creation);
//
//            sa.modifier(re);
//            sa.getAll();
////            Stage stage = (Stage) btnValider1.getScene().getWindow();
////    stage.setResult(ordonnance);
////    stage.close();
//        }
//
//    }
    }
}
