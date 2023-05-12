/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entity.Appointment;
import Entity.Doctor;
import Entity.Typeappoinment;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amine
 */
public class ServicetypeRDV implements IServiceTypeRDV<Typeappoinment> {
     Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServicetypeRDV() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void ajouter_reservation_cov(Typeappoinment d) throws SQLException {
        String req = "INSERT INTO `Typeappoinment` ( `id`, `nomtype`, `description`) VALUES ( ?, ?, ?);";
    

        PreparedStatement pre = con.prepareStatement(req);
  

        pre.setInt(1, d.getId());
        pre.setString(2, d.getNomtype());
                pre.setString(3, d.getDescription());

   
       

        pre.executeUpdate();
       
    }

    @Override
    public void update_reservation_cov(Typeappoinment d) throws SQLException {
        String req = " UPDATE Typeappoinment SET  id= ? , nomtype  = ?,description = ? WHERE id= " + d.getId()+ ";";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, d.getId());
        pre.setString(2, d.getNomtype());
        pre.setString(3, d.getDescription());
     
        pre.executeUpdate();
        System.out.println("type modifié !");
    }

    public boolean supprime_reservation_cov(int id ) throws SQLException {
        String req = "DELETE FROM `Typeappoinment` WHERE id = " + id + ";";
     String req2 = "UPDATE covoiturage SET nbrplace= nbrplace+1 WHERE id_cov =" + ";";
        PreparedStatement pre = con.prepareStatement(req2);

        if ((ste.executeUpdate(req)) == 1) {
                    pre.executeUpdate(); 

            return true;
                

        }

        return false;
    }

    @Override
    public List<Typeappoinment> readAll_reservation_cov() throws SQLException {
        ArrayList<Typeappoinment> listper = new ArrayList<>();

        String req = "select * from Typeappoinment";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
            int id = res.getInt(1);
           String nomtype = res.getString(2); 
           String description = res.getString(3); 

  
            Typeappoinment p = new Typeappoinment(id, nomtype, description );
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }
    
    /////// liste des nom des types 
public List<String> getAllTypes() throws SQLException {
    List<String> types = new ArrayList<>();
    String sql = "SELECT nomtype FROM typeappoinment";
    PreparedStatement statement = con.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
        String type = resultSet.getString("nomtype");
        types.add(type);
    }
    return types;
}
    
    
    
    
    
    
    
    
    
    public Typeappoinment GetTypeById(int Id) {
     Typeappoinment h =new Typeappoinment();
     String req = "SELECT * FROM Typeappoinment where `id` ="+Id+";";
     try {
      
       ResultSet rs = ste.executeQuery(req); 
       while(rs.next()){
            h.setId(rs.getInt(1));
              h.setNomtype(rs.getString(2));
              h.setDescription(rs.getString(3));
              
             
         }
       } catch (SQLException ex) {
           Logger.getLogger(ServiceDoctor.class.getName()).log(Level.SEVERE, null, ex);
       }
    return h ;
    }
    
    
    
    
    
    
    @Override
    public List <Typeappoinment>  findById_reservation_cov(int id) throws SQLException {
                ArrayList<Typeappoinment> listper = new ArrayList<>();

        String req = "select * FROM `Typeappoinment` WHERE id= " + id + ";";

        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            int idr = res.getInt(1);
          
           String nomtype = res.getString(5); 
           String description = res.getString(6); 
             
         Typeappoinment    d = new Typeappoinment(idr, nomtype, description);
     listper.add(d);
        }

        return listper;
    }

    /*@Override
    public List<Typeappoinment> find_reservation_cov_of_user(int id) throws SQLException {
        ArrayList<Typeappoinment> listper = new ArrayList<>();

        String req = "select * from Typeappoinment where id =" + id + ";";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
          int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
            String depart = res.getString(5); 
           String destination = res.getString(6); 
               String nom = res.getString(7); 
           String telephone = res.getString(8); 
            reservation_covoiturage p = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage, depart , destination, nom , telephone);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
    }*/
    /*
public List<Typeappointment> find_reservation_cov_of_user_rech(int id , int ide ) throws SQLException {
        ArrayList<Typeappointment> listper = new ArrayList<>();

       // String req = "select * from reservation_covoiturage where id_utilisateur =" + id + "AND id_cov =" + ide + ";";
String req = "SELECT * FROM Typeappointment WHERE id = ? AND id_utilisateur = ?";
PreparedStatement stmt = con.prepareStatement(req);
stmt.setInt(1, id);
stmt.setInt(2, ide);
ResultSet res = stmt.executeQuery();
      

        while (res.next()) {
          int id_reserv_cov = res.getInt(1);
            int id_cov = res.getInt(2);
            int id_utilisateur = res.getInt(3);
            int prix_covoiturage = res.getInt(4);
            String depart = res.getString(5); 
           String destination = res.getString(6); 
               String nom = res.getString(7); 
           String telephone = res.getString(8); 
            reservation_covoiturage p = new reservation_covoiturage(id_reserv_cov, id_cov, id_utilisateur, prix_covoiturage, depart , destination, nom , telephone);
            // System.out.println(p);
            listper.add(p);
        }
        return listper;
       
    }
   */

    @Override
    public List<Typeappoinment> find_reservation_cov_of_user(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Typeappoinment> find_reservation_cov_of_user_rech(int id, int ide) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprime_reservation_cov(int id, int idc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}


