

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Appointment;
import Entity.Doctor;
import Entity.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amine
 */
public class ServiceUser implements IServiceDoctors{
    
     Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;
    private Appointment p;

    public ServiceUser() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


  

     
    public User GetUserById(int Id) {
     User h =new User();
     String req = "SELECT * FROM User where `id` ="+Id+";";
     try {
      
       ResultSet rs = ste.executeQuery(req); 
       while(rs.next()){
            h.setId(rs.getInt(1));
            //  h.setName(rs.getString(2));
              h.setUserName(rs.getString(3));
              h.setEmail(rs.getString(4));
             
         }
       } catch (SQLException ex) {
           Logger.getLogger(ServiceDoctor.class.getName()).log(Level.SEVERE, null, ex);
       }
    return h ;
    }

    @Override
    public List<Doctor> readAlldoctors() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}



