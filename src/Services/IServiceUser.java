

package Services;

import Entity.Appointment;
import Entity.Doctor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amine
 *
 */
public interface IServiceUser {

  //  boolean ajouter(Appointment t) throws SQLException;

 //   void update(Appointment t) throws SQLException;
 //  List<Appointment> sortbydate() throws SQLException;


   // boolean supprime(int t) throws SQLException;
    List<Doctor> readAllusers() throws SQLException;

    //List<Appointment> findById(int id) throws SQLException;
    
}
