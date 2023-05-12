/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.entities.Fiche;

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
public class FicheCrud implements FicheInterface<Fiche> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void Ajouter(Fiche f) {
        try {
            String requete = "INSERT INTO `fiche`(`id`, `id_consultation_id`, `note`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, f.getId());
            pst.setInt(2, f.getId_consultation_id());
            pst.setString(3, f.getNote());
            pst.executeUpdate();
            System.out.println("la fiche est ajouter !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `fiche` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Fiche deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Fiche f) {
        try {
            String req = " UPDATE `fiche` SET `id` = '" + f.getId() + "', `id_consultation_id` = '" + f.getId_consultation_id() + "', `note` = '" + f.getNote() + "' WHERE `fiche`.`id` = " + f.getId();

            // String req = "UPDATE `ordonnance` SET `id` = '" +O.getId()+ "', `id_consultation_id` = '" +O.getId_consultation_id()+ "' `frequence` = '" + O.getFrquence() + "', `dose` = '" + O.getDose() + "', `date_creation` = '" + O.getDate_creation() + "' WHERE `ordonnance`.`id` = " + O.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Fiche updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Fiche> getAll() {
        List<Fiche> list = new ArrayList<>();
        try {
            String req = "Select * from fiche";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Fiche p = new Fiche();
                p.setId(rs.getInt(1));
                p.setId_consultation_id(rs.getInt("id_consultation_id"));
                p.setNote(rs.getString("note"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
