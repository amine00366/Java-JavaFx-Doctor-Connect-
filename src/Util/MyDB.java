/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author khalil
 */
public class MyDB {
      final String url="jdbc:mysql://localhost:3306/your dataBase";
    
    final String Username ="root";
    
    final String Password ="";
    
    private Connection connection;
    
    private static MyDB insatnce;  
    
    private MyDB() {   
        try {
            connection = DriverManager.getConnection(url,Username,Password);
            System.out.println("Connection etablie avec Succ");
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }

    public static MyDB getInsatnce() { 
        if(insatnce==null)
        {
            insatnce= new MyDB();
        }
        return insatnce;
    }

    public Connection getConnection() {
        return connection;
    }
}
