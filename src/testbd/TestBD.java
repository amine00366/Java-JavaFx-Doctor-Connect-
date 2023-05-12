/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbd;

import java.sql.*;

/**
 *
 * @author amine
 */
public class TestBD {

    private static Connection con;
    private static final String url = "jdbc:mysql://localhost:3306/pidev";
    private static final String login = "root";
    private static final String pwd = "";
    private static Statement ste;

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }
            
    }

}
