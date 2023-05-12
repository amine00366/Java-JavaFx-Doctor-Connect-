/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.desktop;

/**
 *
 * @author khalil
 */

import Entity.Post;
import Entity.User;
import Service.ServicePost;
import Util.MyDB;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.stream.Collectors;
public class PidevDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       //  User u = new User( 1, 111111111,"azert",777777, "abbes525@gmail.com","azeza1","azeaze");
         Post e = new Post(1,"Voyage","putain","2023-02-16");
         ServicePost ser = new ServicePost();
       //  ser.ajouter(e);
       //  ser.modifier(e);
     //    ser.Supprimer(1);
     //      ser.recuperer();
       //    ser.ajouterlike(1, 1);
           ser.likes(1);
           System.out.println(ser.islikedbyuser(1, 1));
    }
    
}
