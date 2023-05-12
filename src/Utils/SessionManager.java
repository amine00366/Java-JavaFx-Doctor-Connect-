/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;



/**
 *
 * @author Khalil
 */
public final class SessionManager {
 
    private static SessionManager instance;
 
     private static int id;
    private static int CIN;
    private static String UserName;
    private static int Numero;
    private static String Email;
    private static String Adresse;
    private static int cin;
    private static String firstName;
    private static String lastName;
    private static int age;
    private static String email;
    private static String adresse;
    private static String password;
    private static String roleperm;
         private static String specialite;
               private static String diplome;


   

   
  //SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
    private SessionManager(int id , int cin , String user_name , int numero , String email ,String address ) {
    SessionManager.id=id;
    SessionManager.CIN=cin;
    SessionManager.UserName=user_name;
    SessionManager.Numero=numero;
    SessionManager.Email=email;
    SessionManager.Adresse=address;
    }
 
    
    public SessionManager(int id, int cin, String firstName, String lastName, String email, String adresse, int age, String password, String roleperm ,String specialite,String diplome) {
    SessionManager.id=id;
    SessionManager.cin=cin;
    SessionManager.firstName=firstName;
    SessionManager.age=age;
    SessionManager.email=email;
    SessionManager.adresse=adresse;
     SessionManager.lastName=lastName;
     SessionManager.password=password;
     SessionManager.roleperm=roleperm;
    SessionManager.specialite=specialite;
    SessionManager.diplome=diplome;
    }
 
    /**
    *
    * @param userName
    * @param employeeId
    * @param privileges
    * @return
    */
    public static SessionManager getInstace(int id , int cin , String user_name , int numero , String email ,String address) {
        if(instance == null) {
            instance = new SessionManager( id , cin ,  user_name ,  numero ,  email ,address);
        }
        return instance;
    }
    
    
     public static SessionManager getInstaced(int id , String firstName , String lastName , int cin ,String email , String adresse , int age , String password , String roleperm ,String specialite,String diplome ) {
        if(instance == null) {
            instance = new SessionManager( id , cin ,  firstName ,  lastName ,  email ,adresse , age ,password , roleperm, specialite, diplome);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static int getCIN() {
        return CIN;
    }

    public static void setCIN(int CIN) {
        SessionManager.CIN = CIN;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String UserName) {
        SessionManager.UserName = UserName;
    }

    public static int getNumero() {
        return Numero;
    }

    public static void setNumero(int Numero) {
        SessionManager.Numero = Numero;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        SessionManager.Email = Email;
    }

    public static String getAdresse() {
        return Adresse;
    }

    public static void setAdresse(String Adresse) {
        SessionManager.Adresse = Adresse;
    }

    
    public static void cleanUserSession() {
    id=0;
    CIN=0;
     UserName="";
     Numero=0;
     Email="";
     Adresse="";
    }
 
    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + UserName + '\'' +
                "email='" + Email + '\'' +
               
                "id = '" + id + '\'' +
           
                
            '}';
    }
 
    
    static class cleanUserSession {
 
        public cleanUserSession() {
          id=0;
         CIN=0;
     UserName="";
     Numero=0;
     Email="";
     Adresse="";
        }
    }
}