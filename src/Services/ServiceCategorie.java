/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Util.MyDB;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ServiceCategorie implements IService<Categorie> {

       Connection con=DataSource.getInstance().getConnection();
    public  ServiceCategorie(){
        Connection con=DataSource.getInstance().getConnection();
    }
    @Override
    public void ajouter(Categorie t) {
         try {
            String qry = "INSERT INTO `categorie`(`nom`) VALUES (?);";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println("haithem 9al jawek behi ");
        } catch (SQLException ex) {
            System.out.println("haithem 9al mouch jawek behi ");

            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Categorie> afficher() {
        List<Categorie> categories = new ArrayList<>();
         
        try {
             String req ="SELECT * FROM categorie ";
            
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                categories.add(c);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return categories;
    }

    @Override
    public void modifier(Categorie t) {
        try {
           String req = " UPDATE categorie SET id = '" +t.getId()+ "', Nom = '" +t.getNom() + "' WHERE categorie.`id` = " + t.getId();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
  
           
    @Override
    public void supprimer(int id) {
         try {
            String req = "DELETE FROM categorie WHERE id = " + id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<String> getallnames() {
        List<String> categories = new ArrayList<>();
         
        try {
             String req ="SELECT nom FROM categorie ";
       
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                String n=(rs.getString("nom"));
                categories.add(n);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return categories;
    }
    public Categorie getbyid(int id){
    Categorie c = new Categorie();
     try {
             String req ="SELECT * FROM categorie where id = '"+id+"%' ";
            
            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){     
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return c;
    }
}