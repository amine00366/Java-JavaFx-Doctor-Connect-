/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui2;

import edu.entities.Ordonnance;
import edu.services.OrdonnanceCrud;
import edu.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author SAHLI
 */
public class AfficherOrdonnanceController implements Initializable {

    @FXML
    private Label nomMedicamentLabel;
    @FXML
    private Label doseLabel;
    @FXML
    private Label frequenceLabel;
    @FXML
    private Label dateCreationLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

private Ordonnance ordonnance;

public void setDetailsOrdonnance(Ordonnance ordonnance) {
    this.ordonnance = ordonnance;
    initialiserVue();
}

private void initialiserVue() {
    nomMedicamentLabel.setText(ordonnance.getNom_Medicament());
    doseLabel.setText(ordonnance.getDose());
    frequenceLabel.setText(ordonnance.getFrequence());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = dateFormat.format(ordonnance.getDate_creation());
dateCreationLabel.setText(formattedDate);

    //dateCreationLabel.setText(ordonnance.getDate_creation());
}

////    public List<Ordonnance> afficher() throws SQLException {
////        Connection cnx = null;
////        Statement st = null;
////        ResultSet rs = null;
////        ObservableList<Ordonnance> liste = FXCollections.observableArrayList();
////        String requette = "SELECT * FROM Ordonnance";
////
////        try {
////            cnx = MyConnection.getInstance().getCnx();
////            st = cnx.createStatement();
////            rs = st.executeQuery(requette);
////            Ordonnance testajout;
////              
////while (rs.next()) {
////    testajout  = new Ordonnance(rs.grtInt("dose"), rs.getString("frequence"), rs.getString("Nom_Medicament"), rs.getTimestamp("date_creation"));
////    liste.add(testajout);
////}
////
////            
////
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        } finally {
////            if (rs != null) {
////                try {
////                    rs.close();
////                } catch (SQLException e) {
////                    /* Ignored */
////                }
////            }
////            if (st != null) {
////                st.close();
////                /* Ignored */
////            }
////        }
////        return liste;
////
////    }
////
//////    private void showAll(ActionEvent event) {
//////         OrdonnanceCrud sp =new OrdonnanceCrud();
////////         TableView<Ordonnance> LBShow = new TableView<>();
////////LBShow.setTableView(sp.getAll());
//////       LBShow.setText(sp.getAll().toString());
//////
////////         LBShow.setTableView(sp.getAll());
////////                             dwc.setTextDose(o.getDose());
//////
//////      // LBShow.setTableView(sp.getAll().toString());
//////        
//////    }
////}

    @FXML
    private void imprimerOrdonnance(ActionEvent event) {
    }
}