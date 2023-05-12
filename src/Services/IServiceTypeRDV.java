/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface IServiceTypeRDV<T> {
     void ajouter_reservation_cov(T t) throws SQLException;

    void update_reservation_cov(T t) throws SQLException;

    boolean supprime_reservation_cov(int id,int idc) throws SQLException;

    List<T> readAll_reservation_cov() throws SQLException;

    List<T> findById_reservation_cov(int id) throws SQLException;
    
      List<T> find_reservation_cov_of_user(int id) throws SQLException;
 //boolean does_reservation_cov_exist(int id_utilisateur, int id_cov) throws SQLException ;
 List<T> find_reservation_cov_of_user_rech(int id , int ide )  throws SQLException ;
}


