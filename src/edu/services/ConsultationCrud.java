/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.entities.Consultation;
import edu.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAHLI
 */
public class ConsultationCrud implements FicheInterface<Consultation> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void Ajouter(Consultation c) {
        try {
            String requete = "INSERT INTO `consultation`(`id`,`id_medcin_id`, `id_user_id`, `etat_consultation`, `date_debut`, `date_fin`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getId_medcin_id());
            pst.setInt(3, c.getId_user_id());
            pst.setString(4, c.getEtat_Consultation());
            pst.setTimestamp(5, c.getDate_debut());
            pst.setTimestamp(6, c.getDate_fin());
            pst.executeUpdate();
            System.out.println("la consultation est ajouter !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
//            String req = " DELETE FROM `consultation` WHERE id = " + id;
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Consultation deleted !");
//            // Supprimer les fiches associées à cette consultation
//        String req2 = "DELETE FROM `fiche` WHERE id = (SELECT fiche_id FROM `consultation` WHERE id = " + id + ")";
//        st.executeUpdate(req2);
            Statement st = cnx.createStatement();
            // Supprimer les fiches associées à cette consultation
            String req2 = "DELETE FROM `fiche` WHERE `id_consultation_id` = " + id;
            st.executeUpdate(req2);
            System.out.println("Fiche associated with consultation deleted !");
            // Supprimer les fiches associées à cette consultation
            String req3 = "DELETE FROM `ordonnance` WHERE `id_consultation_id` = " + id;
            st.executeUpdate(req3);
            System.out.println("ordonnance associated with consultation deleted !");
            // Supprimer la consultation elle-même
            String req1 = "DELETE FROM `consultation` WHERE `id` = " + id;
            st.executeUpdate(req1);
            System.out.println("Consultation deleted !");
//        System.out.println("Fiche(s) associated with consultation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Consultation c) {
        try {
            Statement st = cnx.createStatement();
            String req = "UPDATE `consultation` SET `id` = '" + c.getId() + "', `id_medcin_id` = '" + c.getId_medcin_id() + "', `id_user_id` = '" + c.getId_user_id() + "', `etat_consultation` = '" + c.getEtat_Consultation() + "', `date_debut` = '" + c.getDate_debut() + "', `date_fin` = '" + c.getDate_fin() + "' WHERE `consultation`.`id` = " + c.getId();

            // String req = " UPDATE `consultation` SET `id` = '" +c.getId()+ "', `id_medcin_id` = '" +c.getId_medcin_id()+ "',, `id_user_id` = '" +c.getId_user_id()+ "', `etat_consultation` = '" +c.getEtat_Consultation() + "',`date_debut` = '" + c.getDate_debut() + "',`date_fin` = '" + c.getDate_fin() + "' WHERE `consultation`.`id` = " + c.getId();
            st.executeUpdate(req);
            System.out.println("Consultation updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Consultation> getAll() {
        List<Consultation> list = new ArrayList<>();
        try {
            String req = "Select * from consultation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setId(rs.getInt(1));
                c.setId_medcin_id(rs.getInt("id_medcin_id"));
                c.setId_user_id(rs.getInt("id_user_id"));

                c.setEtat_Consultation(rs.getString("etat_consultation"));
                c.setDate_debut(rs.getTimestamp("date_debut"));
                c.setDate_fin(rs.getTimestamp("date_fin"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
