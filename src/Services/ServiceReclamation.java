/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.reclamation;
import Entities.type_reclamation;
import Entity.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author EMNA
 */
public class ServiceReclamation implements IService<reclamation>{
        Connection cnx;
    
    public  ServiceReclamation(){
        cnx =DataSource.getInstance().getConnection();
    }
    List<type_reclamation>type_reclamation=new ArrayList<>();
    public final String SELECT_type_BY_ID = "SELECT * FROM type_reclamation WHERE id = ? LIMIT 1";
    public final String SELECT_user_BY_ID = "SELECT * FROM user WHERE id = ? LIMIT 1";


    @Override
    public void ajouter(reclamation r) {
 try {
            String qry = "INSERT INTO `reclamation`( `id_user_id`,`id_tr_id`,`date`,`email`,`telephone`,`cmnt`,`etat`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, r.getId_user_id().getId());
            ps.setInt(2, r.getId_tr_id().getId());
            ps.setTimestamp(3, r.getCurrentTimestamp());
            ps.setString(4, r.getEmail());
            ps.setInt(5, r.getTelephone());
            ps.setString(6, r.getCmnt());
            ps.setString(7, r.getEtat());
            ps.executeUpdate();
            System.out.println("succès ");
        } catch (SQLException ex) {
            System.out.println("erreur ");

            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<reclamation> afficher() {
              List<reclamation>reclamation = new ArrayList<>();

    try {
             String req ="SELECT * FROM `reclamation` ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                reclamation r = new reclamation();
                r.setId(rs.getInt(1));
                r.setId_user_id(getUserById(rs.getInt(2)));
              r.setId_tr_id(gettypeById(rs.getInt(3)));
                r.setDate(rs.getTimestamp(4));
                r.setEmail(rs.getString(5));
                r.setTelephone(rs.getInt(6));
                r.setCmnt(rs.getString(7));
                r.setEtat(rs.getString(8));

                reclamation.add(r);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
     return reclamation;
    }
   

   public type_reclamation gettypeById(int id) {
        
        type_reclamation type = new type_reclamation();
        
        try{
        //    st = cnx.createStatement();
            PreparedStatement st = cnx.prepareStatement(SELECT_type_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                type.setId(rs.getInt("id"));
                type.setNom(rs.getString("nom"));
            }
            
        }catch(SQLException sQLException){
            System.out.println(sQLException.getMessage());
        }catch(IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage());
        }catch(NullPointerException nullPointerException){
            System.out.println(nullPointerException.getMessage());
        }
        
        return type;
    }
     public User getUserById(int id) {
        
        User ur = new User();
        
        try{
            PreparedStatement st = cnx.prepareStatement(SELECT_user_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ur.setId(rs.getInt("id"));
                ur.setUserName(rs.getString("user_name"));
        //        ur.setEmail(rs.getString("email"));
        //        user.setCin(rs.getInt("cin"));
        //      user.setEmail(rs.getString("email"));
        //        user.setAge(rs.getString("age"));
        //      user.setMdp(rs.getString("mdp"));

            }
            
        }catch(SQLException sQLException){
            System.out.println(sQLException.getMessage());
        }catch(IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage());
        }catch(NullPointerException nullPointerException){
            System.out.println(nullPointerException.getMessage());
        }
        
        return ur;
    }
    @Override
    public void modifier(reclamation r) {
   try {
           String req = " UPDATE `reclamation` SET `id` = '" +r.getId()+ "',  `Id_tr_id` = '" +r.getId_tr_id()  +"', `Date` = '" +r.getDate()  +"', `Email` = '" +r.getEmail()  + "', `Telephone` = '" +r.getTelephone()  + "', `cmnt` = '" +r.getCmnt()  +"', `etat` = '" +r.getEtat()  +"' WHERE `reclamation`.`id` = " + r.getId();

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" reclamation updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public reclamation GetById(int id){
    reclamation r = new reclamation();
    try{
    String req = "SELECT * FROM `reclamation` WHERE id = "+id;
     Statement st = cnx.createStatement();
     ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                r.setId(rs.getInt(1));
                r.setId_user_id(getUserById(rs.getInt(2)));
              r.setId_tr_id(gettypeById(rs.getInt(3)));
                r.setDate(rs.getTimestamp(4));
                r.setEmail(rs.getString(5));
                r.setTelephone(rs.getInt(6));
                r.setCmnt(rs.getString(7));
                r.setEtat(rs.getString(8));
    }
    }
    catch(SQLException ex){
        System.out.println("ahla");}
    return r;
    }
    
    public void TrRec(int id){
    String req="UPDATE `reclamation` SET `etat` = 'Reclamation Traité' WHERE `reclamation`.`id` ="+id;
    try{
        
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" reclamation traite!");
    }catch(SQLException ex){
        System.out.println("ya");}
    }
}
