/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Appointment;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amine
 *
 */
public interface IServiceRendezVous<Appointment> {

  //  boolean ajouter(Appointment t) throws SQLException;

 //   void update(Appointment t) throws SQLException;
 //  List<Appointment> sortbydate() throws SQLException;


   // boolean supprime(int t) throws SQLException;
    List<Appointment> readAll() throws SQLException;

    //List<Appointment> findById(int id) throws SQLException;
    
}
