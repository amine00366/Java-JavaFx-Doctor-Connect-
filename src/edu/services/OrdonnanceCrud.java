/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import Utils.DataSource;
import edu.entities.Ordonnance;
import edu.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SAHLI
 */
public class OrdonnanceCrud implements OrdonnanceInterface<Ordonnance> {

    Connection cnx = DataSource.getInstance().getConnection();

    @Override
    public void AjouterOrdonnance(Ordonnance O) {
        try {
            String requete = "INSERT INTO `ordonnance`( `id`, `id_consultation_id`, `frequence`, `dose`, `date_creation`, `Nom_Medicament`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, O.getId());
            pst.setInt(2, O.getId_consultation_id());
            pst.setInt(1, O.getMedicament_id());

            pst.setString(3, O.getFrquence());
            pst.setString(4, O.getDose());
            pst.setDate(5, new java.sql.Date( O.getDate_creation().getTime()));
            pst.setString(6, O.getNom_Medicament());
            //  pst.setString(6,O.getMedicament());
            pst.executeUpdate();

            System.out.println("l'ordonnance est ajouter !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `ordonnance` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ordonnance deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ordonnance O) {
        try {
            String req = " UPDATE `ordonnance` SET `id` = '" + O.getId() + "', `Nom_Medicament` = '" + O.getNom_Medicament() + "',`id_consultation_id` = '" + O.getId_consultation_id() + "', `frequence` = '" + O.getFrquence() + "', `dose` = '" + O.getDose() + "', `date_creation` = '" + O.getDate_creation() + "' WHERE `ordonnance`.`id` = " + O.getId();

            // String req = "UPDATE `ordonnance` SET `id` = '" +O.getId()+ "', `id_consultation_id` = '" +O.getId_consultation_id()+ "' `frequence` = '" + O.getFrquence() + "', `dose` = '" + O.getDose() + "', `date_creation` = '" + O.getDate_creation() + "' WHERE `ordonnance`.`id` = " + O.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ordonnance updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public ObservableList<Ordonnance> getorbyu(int id){
     ObservableList<Ordonnance> myList1 = FXCollections.observableArrayList();

        // List<Ordonnance> list = new ArrayList<>();
        try {
            String req = "Select * from ordonnance where";
            // String req2="select m.nom from doctor m join consultation c on m.id  = c.id_medcin_id ";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            //  PreparedStatement ps = cnx.prepareStatement(req2);
//ResultSet rs1 = ps.executeQuery();

            while (rs.next()) {
                Ordonnance O = new Ordonnance();
                O.setId(rs.getInt(1));
                //  Ordonnance O = new Ordonnance(rs.getInt("id"),rs.getInt("id_consultation_id"), rs.getString("frequence"), rs.getString("dose"),rs.getTimestamp("date_creation"));
                //String nomMedecin = rs.getString("nom");
                // System.out.println(nomMedecin);

                O.setId_consultation_id(rs.getInt("id_consultation_id"));

                O.setFrquence(rs.getString("frequence"));
                O.setDose(rs.getString("dose"));
                O.setDate_creation(rs.getTimestamp("date_creation"));
                O.setNom_Medicament(rs.getString("Nom_Medicament"));
                myList1.add(O);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList1;
    }
//public static void afficherMedicaments(Ordonnance ordonnance) {
//    List<Medicament> medicaments = ordonnance.getMedicaments();
//    List<String> nomsMedicaments = new ArrayList<String>();
//    for (Medicament medicament : medicaments) {
//        nomsMedicaments.add(medicament.getNom());
//    }
// return nomsMedicaments;
//}
//    public List<String> obtenirNomsMedicaments(Ordonnance ordonnance) {
//    List<Medicament> medicaments = ordonnance.getMedicaments();
//    List<String> nomsMedicaments = new ArrayList<String>();
//    for (Medicament medicament : medicaments) {
//        nomsMedicaments.add(medicament.getNom());
//    }
//    return nomsMedicaments;
//}
//  @Override
//    public List<Ordonnance> getAll() {
//        List<Ordonnance> list = new ArrayList<>();
//        
//        
//        try {
//    String req = "SELECT o.id, o.id_consultation_id, o.frequence, o.dose, o.date_creation, m.nom_medicament "
//                + "FROM ordonnance o "
//                + "INNER JOIN ordonnance_medicament om ON o.id = om.id_ordonnance "
//                + "INNER JOIN medicament m ON om.nom_medicament = m.nom_medicament";
//    Statement st = cnx.createStatement();
//    ResultSet rs = st.executeQuery(req);
//    Map<Integer, Ordonnance> ordonnances = new HashMap<>();
//    while(rs.next()) {
//        int ordonnanceId = rs.getInt("id");
//        Ordonnance ordonnance = ordonnances.get(ordonnanceId);
//        if (ordonnance == null) {
//            ordonnance = new Ordonnance();
//            ordonnance.setId(ordonnanceId);
//            ordonnance.setId_consultation_id(rs.getInt("id_consultation_id"));
//            ordonnance.setFrquence(rs.getString("frequence"));
//            ordonnance.setDose(rs.getString("dose"));
//            ordonnance.setDate_creation(rs.getTimestamp("date_creation"));
//            ordonnance.setMedicaments(new ArrayList<>());
//            ordonnances.put(ordonnanceId, ordonnance);
//        }
//        String nom  = rs.getString("nom_medicament");
//        Medicament medicament;
//        medicament = new Medicament(nom);
//        ordonnance.getMedicaments().add(medicament);
//    }
//    for (Ordonnance ordonnance : ordonnances.values()) {
//        String nomsMedicaments = String.join(", ", ordonnance.getMedicaments().stream()
//                                                      .map(m -> m.getNom())
//                                                      .collect(Collectors.toList()));
//        System.out.println("Ordonnance #" + ordonnance.getId() + ": " + nomsMedicaments);
//    }
//} catch (SQLException ex) {
//    System.out.println(ex.getMessage());
//}

    
    @Override
    public ObservableList<Ordonnance> getAll() {
        ObservableList<Ordonnance> myList1 = FXCollections.observableArrayList();

        // List<Ordonnance> list = new ArrayList<>();
        try {
            String req = "Select * from ordonnance";
            // String req2="select m.nom from doctor m join consultation c on m.id  = c.id_medcin_id ";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            //  PreparedStatement ps = cnx.prepareStatement(req2);
//ResultSet rs1 = ps.executeQuery();

            while (rs.next()) {
                Ordonnance O = new Ordonnance();
                O.setId(rs.getInt(1));
                //  Ordonnance O = new Ordonnance(rs.getInt("id"),rs.getInt("id_consultation_id"), rs.getString("frequence"), rs.getString("dose"),rs.getTimestamp("date_creation"));
                //String nomMedecin = rs.getString("nom");
                // System.out.println(nomMedecin);

                O.setId_consultation_id(rs.getInt("id_consultation_id"));

                O.setFrquence(rs.getString("frequence"));
                O.setDose(rs.getString("dose"));
                O.setDate_creation(rs.getTimestamp("date_creation"));
                O.setNom_Medicament(rs.getString("Nom_Medicament"));
                myList1.add(O);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList1;
    }

    public ObservableList<Ordonnance> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
