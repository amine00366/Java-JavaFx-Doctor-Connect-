/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import Entities.Categorie;
import Entities.Medicament;
import Services.ServiceCategorie;
import Services.ServiceMedicament;
import java.awt.FileDialog;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjoutMedController implements Initializable {

    private TextField i;
    @FXML
    private TextField n;
    @FXML
    private TextField d;
    @FXML
    private TextField p;
    @FXML
    private ChoiceBox<String> c;
    @FXML
    private Button a;
    @FXML
    private Button r;
    @FXML
    private AnchorPane content;
    private ImageView imgg;
    @FXML
    private Label path;
    @FXML
    private Button Image;

     private File selectedFile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ServiceCategorie sc = new ServiceCategorie();
      List<String> lc = sc.getallnames();
      c.setItems(FXCollections.observableArrayList(lc));
    }    
ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private void ajouter(ActionEvent event) {
        String image=path.getText();
        String nom=n.getText();
        String description=d.getText();
        String prix= p.getText();
        System.out.println(prix);
       Categorie caaa = new Categorie();
       String categorie=c.getValue();
       List<Categorie> lc = sc.afficher();
       for (Categorie a:lc){
           if (categorie.equals(a.getNom())){
               caaa=a;
               break;
           }
       }
        ServiceMedicament sm = new ServiceMedicament();
        float prixf=Float.parseFloat(prix);
        Medicament m = new Medicament(nom,caaa,description,image,prixf);
        sm.ajouter(m);
        content.getChildren().removeAll(content.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Med.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
    @FXML
    private void rein(ActionEvent event) {
        i.setText("");
        n.setText("");
        d.setText("");
        p.setText("");
        c.setValue("");
    }
    @FXML
      void addimgcours(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedFile = fileChooser.showOpenDialog(Image.getScene().getWindow());
        if (selectedFile != null) {
            saveImage(selectedFile);
        }
    }

    private void saveImage(File file) {
        Path destinationFolderPath = Paths.get("imgs/imgs");
        if (!Files.exists(destinationFolderPath)) {
            try {
                Files.createDirectories(destinationFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path destinationFilePath = destinationFolderPath.resolve(file.getName());
        path.setText(file.getName());
        try {
            Files.copy(file.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
}
    }
}