/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Post;
import Service.ServicePost;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import utils.SessionManager;

/**
 * FXML Controller class
 *
 * @author khalil
 */
public class AddPostController  {
    
    Post p = new Post();
  
    @FXML
    private TextArea description;
    @FXML
    private TextField hashtag;
    @FXML
    
    ImageView imagep = null;
    private String i ;
    byte [] post_image = null;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Circle cir;
    @FXML
    private ComboBox<?> visibilite;
    @FXML
    private Text username;
    @FXML
    private Button post;
    @FXML
    private FontAwesomeIconView photo1;

    

    
    /**
     * Initializes the controller class.
     */
   
    @FXML
    private void Redirect(ActionEvent event) throws IOException {
        
       ServicePost serv=new ServicePost();
       if (!(description.getText().equals("")) ){
          p.setDescriptionP(description.getText());
          p.setHashtagP(hashtag.getText());
          p.setIdc(SessionManager.getId());
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
          LocalDateTime now = LocalDateTime.now();  
          String date= dtf.format(now);
          p.setDateP(date);
          System.out.println(i);
          Notification.showNotification("New Notification", "Votre Post a été ajouter avec succées!");
              String recipient = "your email";
                 try {
                 util.Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();}
               serv.ajouter(p);
            
            JOptionPane.showMessageDialog(null,"Succés De Création ");
        
          Parent root = FXMLLoader.load(getClass().getResource("ShowPost.fxml"));
             Scene scene = new Scene(root);
                    Stage stage = new Stage();
                       stage.setTitle("DOC");
                        stage.setTitle("My Project!");
          
                    stage.setScene(scene);
      
                    stage.show();
              stage.show();
              
  
       }else{
          Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Verifier vos données ");
                alert.showAndWait();
       }
       
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("ShowPost.fxml"));
        Scene scene = new Scene(root);
                    Stage stage = new Stage();
                       stage.setTitle("DOC");
                        stage.setTitle("My Project!");
          
                    stage.setScene(scene);
      
                    stage.show();
              stage.show();
    }
    @FXML
    private void addphoto(MouseEvent event) throws FileNotFoundException, IOException {
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Ajouter une Image");
        fc.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File f = fc.showOpenDialog(null);
        String DBPath = "C:\\\\\\\\xampp\\\\\\\\htdocs\\\\\\\\Version-Integre\\\\\\\\public\\\\\\\\uploads\\\\\\\\"+f.getName();
        i=f.getName();
        p.setImageP(i);
        System.out.println(p.getImageP());
        if (f != null){
        BufferedImage bufferedImage = ImageIO.read(f);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage,null);
        ImageIO.write(bufferedImage, "jpg", new File(DBPath));
        imagep.setImage(image);
        FileInputStream fin =new FileInputStream(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte [1024];
        for (int readNum ;(readNum= fin.read(buf)) != -1 ;){
            bos.write(buf,0,readNum);
         post_image = bos.toByteArray();}
        } 
    }
   
    
}
