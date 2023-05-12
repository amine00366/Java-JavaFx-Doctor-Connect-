/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entity.Doctor;
import Entity.User;
import static Service.Crud.hashPassword;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Util.MyDB;
import java.net.Authenticator;
import javafx.scene.Node;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class SignInController implements Initializable {

    @FXML
    private Label TravelMe;
    @FXML
    private TextField email_signin;
    @FXML
    private PasswordField password_signin;
    @FXML
    private Button login_btn;
    @FXML
    private Hyperlink create_acc;
    @FXML
    private Label TravelMe2;
    @FXML
    private TextField cin;
    @FXML
    private TextField username;
    @FXML
    private Button signup_btn;
    @FXML
    private Hyperlink login_acc;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private AnchorPane login_form;
    @FXML
    private PasswordField confirm_password;
    @FXML
    private TextField numero;
    @FXML
    private PasswordField password_signup;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email_signup;
    @FXML
    private Hyperlink mdp_oub;

    /**
     * Initializes the controller class.
     */
    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    @FXML
     public void exit(){
        System.exit(0);
    }   
     
    @FXML
    public void textfieldDesign(){
        if (email_signin.isFocused()){
            email_signin.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            password_signin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            
            
        }else if (password_signin.isFocused()){
            email_signin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signin.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
        } 
    }
    @FXML
     public void textfieldDesign1(){
     if(email_signup.isFocused()){
         email_signup.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            password_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            cin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            adresse.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            numero.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            confirm_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
     } else if(password_signup.isFocused()){
         email_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signup.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            cin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            adresse.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            numero.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            confirm_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
     }  else if(cin.isFocused()){
         email_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            cin.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            adresse.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            numero.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            confirm_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
     } else if(username.isFocused()){
         email_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            cin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            adresse.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            numero.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            confirm_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
     } else if(adresse.isFocused()){
         email_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            cin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            adresse.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            numero.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            confirm_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
     } else if(numero.isFocused()){
         email_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            cin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            adresse.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            numero.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
            confirm_password.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
     }  else if(confirm_password.isFocused()){
         email_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            password_signup.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            cin.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            username.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            adresse.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            numero.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-width:1px");
            confirm_password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:2px");
     }
     
     }
    public void dropShadowEffect(){
        DropShadow original = new DropShadow(20,Color.valueOf("#ae44a5"));
        original.setRadius(30);
        TravelMe.setEffect(original);
        
        TravelMe2.setEffect(original);
        TravelMe.setOnMouseEntered((MouseEvent event)->{       
        DropShadow shadow = new DropShadow(60,Color.valueOf("#ae44a5"));
        TravelMe.setCursor(Cursor.HAND);
        TravelMe.setStyle("-fx-text-fill:#517ab5");
        TravelMe.setEffect(shadow);
        
    });
    TravelMe.setOnMouseExited((MouseEvent event)->{
        DropShadow shadow = new DropShadow(20,Color.valueOf("#ae44a5"));
        shadow.setRadius(30);
        TravelMe.setStyle("-fx-text-fill:#000");
        TravelMe.setEffect(shadow);
    });
    TravelMe2.setOnMouseEntered((MouseEvent event)->{       
        DropShadow shadow = new DropShadow(60,Color.valueOf("#ae44a5"));
        TravelMe2.setCursor(Cursor.HAND);
        TravelMe2.setStyle("-fx-text-fill:#517ab5");
        TravelMe2.setEffect(shadow);
        
    });
    TravelMe2.setOnMouseExited((MouseEvent event)->{
        DropShadow shadow = new DropShadow(20,Color.valueOf("#ae44a5"));
        shadow.setRadius(30);
        TravelMe2.setStyle("-fx-text-fill:#000");
        TravelMe2.setEffect(shadow);
    });
}
    @FXML
    public void changeForm(ActionEvent event){
        if(event.getSource() == create_acc){
            signup_form.setVisible(true);
             login_form.setVisible(false);
        }else if(event.getSource()==login_acc){
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }
    
    public boolean ValidationEmail(){ 
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(email_signup.getText());
        
        if(match.find() && match.group().equals(email_signup.getText()))
        {
            return true;
        }else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            
            return false;
        }
    }
    
    @FXML
    public void login() throws IOException{

        
             if(email_signin.getText().equals("svasty@admin.com") && password_signin.getText().equals("adminadmin") )
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
                     alert.setTitle("Travel Me :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Bienvenu Admin");
                     alert.showAndWait();
                     
                  Parent root = FXMLLoader.load(getClass().getResource("/Gui/AffichageType.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();}
             
             else{
            String query2="select * from user where email=?  ";
            cnx = MyDB.getInsatnce().getConnection();
          
           try{
              PreparedStatement smt = cnx.prepareStatement(query2);
       
               smt.setString(1,email_signin.getText());
           //   smt.setString(2,password_signin.getText());
               ResultSet rs= smt.executeQuery();
               System.out.println(rs);
               User p;
                if(rs.next()){
                    System.out.println("if rs ");
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                     if( !passwordEncoder.matches(password_signin.getText(),  rs.getString("password")))
                       {
                       System.out.println("ok");}
                        else  if ( passwordEncoder.matches(password_signin.getText() ,  rs.getString("password"))) { 
                               System.out.println("matchhhhhhh");
                     p=new User(rs.getInt("cin"),rs.getString("nom"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("password"));
                     User.setCurrent_User(p);
                     SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("nom"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"));
                     System.out.println(p.getRoleperm());
                     if (rs.getString("roleperm").equals("ADMIN")){
                        
                         login_btn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/Gui/BACKsidebar.fxml"));
                     Scene scene;
                    scene = new Scene(root);
                    Stage stage = new Stage();
                       stage.setTitle("DOC");
                        stage.setTitle("My Project!");
          
                    stage.setScene(scene);
      
                    stage.show();

                      
                     }    
                     
                     else {
                     
                     System.out.println(User.Current_User.getEmail());
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("DOC Me :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Vous etes connecté");
                     alert.showAndWait();
                    login_btn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/Gui/rendezVousAffiche.fxml"));
                     Scene scene;
                    scene = new Scene(root);
                    Stage stage = new Stage();
                       stage.setTitle("DOC");
                        stage.setTitle("My Project!");
          
                    stage.setScene(scene);
      
                    stage.show();
                
                     }  
                }   
                }
                else {
                       System.out.println("else lekhra ");
                    
                  /* Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("doc Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();  */
                  
                   // Check if the email and password belong to a doctor
    String queryDoctor = "select * from doctor where email=? ";
    try {
        PreparedStatement smtd = cnx.prepareStatement(queryDoctor);
        smtd.setString(1, email_signin.getText());
    
      // smtd.setString(2, password_signin.getText());
        ResultSet rsd = smtd.executeQuery();
           System.out.println(rsd);
          if (rsd.next()) {
        //   Doctor doctor = new Doctor(rsd.getInt("id"), rsd.getString("nom"), rsd.getString("prenom"), rsd.getInt("cin"), rsd.getString("email"), rsd.getString("Adresse"), rsd.getInt("age"), rsd.getString("password"), rsd.getString("Roleperm"), rsd.getString("diplome"), rsd.getString("specialite"));

        /*  String pass = doctor.getPassword();*/
          
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                     if( !passwordEncoder.matches(password_signin.getText(),  rsd.getString("password")))
                       {
                       System.out.println("ok doctor ");}
                        else  if ( passwordEncoder.matches(password_signin.getText() ,  rsd.getString("password"))) { 
                               System.out.println("matchhhhhhh doctor");
          System.out.println("okkkkkkkk");
           Doctor doctor = new Doctor(rsd.getInt("id"), rsd.getString("nom"), rsd.getString("prenom"), rsd.getInt("cin"), rsd.getString("email"), rsd.getString("Adresse"), rsd.getInt("age"), rsd.getString("password"), rsd.getString("Roleperm"), rsd.getString("diplome"), rsd.getString("specialite"));
            doctor.setCurrent_doctor(doctor);
               
            SessionManager.getInstaced(rsd.getInt("id"), rsd.getString("nom"), rsd.getString("prenom"), rsd.getInt("cin"), rsd.getString("email"), rsd.getString("Adresse"), rsd.getInt("age"), rsd.getString("password"), rsd.getString("Roleperm"), rsd.getString("diplome"), rsd.getString("specialite"));
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenue Docteur " + doctor.getFirstName());
            alert.showAndWait();
            
            login_btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/AppointmentForDoctor.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("DOC");
            stage.setScene(scene);
            stage.show();
            
          
        }
          }
          
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
                }
          
      }catch(Exception ex){
           System.out.println(ex.getMessage());
      }
           }
             
    }
         
    
     @FXML
    public void  signUp(){
      
    cnx = MyDB.getInsatnce().getConnection();
    String query="INSERT INTO user (cin, nom, numero, email, adresse, password, roleperm )"
                    + "VALUES (?, ?, ?, ?, ?, ?,?)";
   
    try{
        
          if( username.getText().isEmpty()
                    | numero.getText().isEmpty()
                    | email_signup.getText().isEmpty()
                    | password_signup.getText().isEmpty()
                    | adresse.getText().isEmpty()
                    |cin.getText().isEmpty()
                    | confirm_password.getText().isEmpty()) 
          {
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("doc Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
    
        
         
          }else if(confirm_password.getText().length() < 8 | confirm_password.getText()==password_signup.getText() ){
              
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("doc Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password doit etre sup 8 caractéres !!");
                alert.showAndWait();
                
          }
          else{
               if(ValidationEmail()){
             PreparedStatement smt = cnx.prepareStatement(query);
        
            smt.setString(1, cin.getText());
            smt.setString(2, username.getText());
            smt.setString(3, numero.getText());
            smt.setString(4, email_signup.getText());
            smt.setString(5, adresse.getText());
         //   smt.setString(6, password_signup.getText());
             smt.setString(6, hashPassword(password_signup.getText()));
            smt.setString(7, "patient");
            smt.executeUpdate();
             
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("doc Me :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                login_form.setVisible(true);
                signup_form.setVisible(false);
                
          }
        
            
          }}catch(SQLException ex){
         System.out.println(ex.getMessage());
    }
    
 }
    
    // public static String hashPassword(String password) {
   // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   // return passwordEncoder.encode(password);
   //  }
    
     
     public void  signUpDoctor(){
      
    cnx = MyDB.getInsatnce().getConnection();
    String query="INSERT INTO doctor (cin, user_name, numero, email, adresse, password, roleperm )"
                    + "VALUES (?, ?, ?, ?, ?, ?,?)";
    
    try{
        
          if( username.getText().isEmpty()
                    | numero.getText().isEmpty()
                    | email_signup.getText().isEmpty()
                    | password_signup.getText().isEmpty()
                    | adresse.getText().isEmpty()
                    |cin.getText().isEmpty()
                    | confirm_password.getText().isEmpty()){
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("doc Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
    
        
         
          }else if(confirm_password.getText().length() < 8 | confirm_password.getText()==password_signup.getText() ){
              
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("doc Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password doit etre sup 8 caractéres !!");
                alert.showAndWait();
                
          }
          else{
               if(ValidationEmail()){
             PreparedStatement smt = cnx.prepareStatement(query);
        
            smt.setString(1, cin.getText());
            smt.setString(2, username.getText());
            smt.setString(3, numero.getText());
            smt.setString(4, email_signup.getText());
            smt.setString(5, adresse.getText());
            smt.setString(6, password_signup.getText());
            smt.setString(7, "patient");
            smt.executeUpdate();
             
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("doc Me :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                login_form.setVisible(true);
                signup_form.setVisible(false);
                
          }
        
            
          }}catch(SQLException ex){
         System.out.println(ex.getMessage());
    }
 }
    void sendPassword(){
        System.out.println("cxcccccccccccccccccc");
                String query2="select * from user where email=? ";
                String email1="empty";
                 try {
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, email_signin.getText());
             ResultSet rs= smt.executeQuery();
                if(rs.next()){
                   email1=rs.getString("email");
                     System.out.println(email1);
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

     
    @FXML
    void sendPaswword_btn(ActionEvent event) {
        //sendMail(email_signin.getText());
       sendPassword();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropShadowEffect();
    }    

   /* @FXML
     public void changetoDoctor(ActionEvent event){
        if(event.getSource() == create_acc){
            signup_formdoctor.setVisible(true);
             login_form.setVisible(false);
        }else if(event.getSource()==login_acc){
            login_form.setVisible(true);
            signup_formdoctor.setVisible(false);
        }
    }*/

    @FXML
    private void adddoc(ActionEvent event) throws IOException {     
   Parent root = FXMLLoader.load(getClass().getResource("/Gui/signupDoctor.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
            }
    
}
