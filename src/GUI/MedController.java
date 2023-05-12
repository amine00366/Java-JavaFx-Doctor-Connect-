package GUI;

import Entities.Categorie;
import Entities.Medicament;
import Entities.Stats;
import Services.ServiceCategorie;
import Services.ServiceMedicament;
import com.google.zxing.WriterException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class MedController implements Initializable {

   @FXML
    private TableView<Medicament> T;
    @FXML
    private TableColumn<Medicament, String> i;
    @FXML
    private TableColumn<Medicament, String> n;
    @FXML
    private TableColumn<Medicament, String> d;
    @FXML
    private TableColumn<Medicament, String> c;
    @FXML
    private TableColumn<Medicament, Float> p;
    @FXML
    private Button a;
    @FXML
    private TextField cher;
    @FXML
    private AnchorPane content;
    @FXML
    private Label nbrc;
    @FXML
    private Label nbrm;
    @FXML
    private Button supp;
    private ImageView imgg;
            ServiceMedicament sm = new ServiceMedicament();
           ServiceCategorie sc = new ServiceCategorie();
    @FXML
    private PieChart ch;
    @FXML
    private Button qr;
    @FXML
    private Button mod;
    
    public static int idm;
    public static String ipa;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        List<Medicament> medicamentsList = sm.afficher();
        ObservableList<Medicament> medicamentsObservableList = FXCollections.observableArrayList();
         ObservableList<ImageView> imgs = FXCollections.observableArrayList();
        for(Medicament m:medicamentsList){
             
            medicamentsObservableList.add(m);
             }
        
        n.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Nom"));
        d.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Description"));
        c.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Categorie"));
        p.setCellValueFactory(new PropertyValueFactory<Medicament, Float>("Prix"));
         i.setCellValueFactory(new PropertyValueFactory<>("Image"));
            i.setCellFactory(column -> new TableCell<Medicament, String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);

                    if (empty || imagePath == null) {
                        setGraphic(null);
                    } else {
                        Image image = new Image(new File(imagePath).toURI().toString());
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        setGraphic(imageView);
                    }
                }
            });
        T.setItems(medicamentsObservableList);
      int im=medicamentsList.size();
        System.out.println(im);
        List<String> lc = sc.getallnames();
        int ic= lc.size();
        nbrc.setText("Nombre de categories : "+ic);
        nbrm.setText("Nombre de medicaments :"+im);
        List<Stats> st = new ArrayList<>();
        if (im>0){
        for(String n : lc){
        int i =0;
        for(Medicament m: medicamentsList){
        Categorie c= m.getCategorie();   
        if (n.equals(c.getNom())){;
            i++;
        }
        }
         float p = i*100/im;
        Stats s = new Stats(n,i,p);
        st.add(s);
    }
         ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
          for (Stats stat : st) {
         pieChartData.add(new PieChart.Data(stat.getNom(), stat.getNbr()));
      }
        ch.setData(pieChartData);
        }
         }        
   @FXML
    public void chercher(){
        String ch=cher.getText();
        ServiceMedicament sm = new ServiceMedicament();
        List<Medicament> medicamentsList = sm.chercher(ch);
        i.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Image"));
        n.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Nom"));
        d.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Description"));
        c.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Categorie"));
        p.setCellValueFactory(new PropertyValueFactory<Medicament, Float>("Prix"));
        ObservableList<Medicament> medicamentsObservableList = FXCollections.observableList(medicamentsList);
        T.setItems(medicamentsObservableList);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if(event.getSource() == a){
        content.getChildren().removeAll(content.getChildren());
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMed.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
    }  

    @FXML
    private void supprimer(ActionEvent event) {
        Medicament selectedItem = T.getSelectionModel().getSelectedItem();

    if (selectedItem != null) {
       String value = n.getCellData(selectedItem);
       List<Medicament> lm = sm.afficher();
       int i=0;
       for(Medicament m:lm){
           String nom=m.getNom();
        if(value.equals(nom)){
            i = m.getId();
            break;
        }
       }
        sm.supprimer(i);
        System.out.println("jaw");
         List<Medicament> medicamentsList = sm.afficher();
        ObservableList<Medicament> medicamentsObservableList = FXCollections.observableList(medicamentsList);
        T.setItems(medicamentsObservableList);
        int im=medicamentsList.size();
        List<String> lc = sc.getallnames();
        int ic= lc.size();
        nbrc.setText("Nombre de categories : "+ic);
        nbrm.setText("Nombre de medicaments :"+im);
        List<Stats> st = new ArrayList<>();
        for(String n : lc){
        int j =0;
        for(Medicament m: medicamentsList){
        Categorie cm= m.getCategorie();   
        if (n.equals(cm.getNom())){;
            j++;
        }
        }
        float p = j*100/im;
        Stats s = new Stats(n,j,p);
        st.add(s);
    }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
          for (Stats stat : st) {
         pieChartData.add(new PieChart.Data(stat.getNom(), stat.getNbr()));
      }
        ch.setData(pieChartData);
    }
  }

    @FXML
    private void Gen(ActionEvent event) throws WriterException, IOException {
        Medicament selectedItem = T.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
           String value = n.getCellData(selectedItem);
           List<Medicament> lm = sm.afficher();
           int i=0;
           for(Medicament m:lm){
               String nom=m.getNom();
            if(value.equals(nom)){
                i = m.getId();
                break;
            }
           }
           Medicament m=sm.getbyid(i);
          String n= m.getNom();
           sm.generateQR(i);
          ipa="imgs\\QRcodes\\" + n+"_qr.png";
           Parent root=FXMLLoader.load(getClass().getResource("ImQr.fxml"));
             Stage primaryStage=new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
}

    @FXML
    private void Modifier(ActionEvent event) {
         Medicament selectedItem = T.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
           String value = n.getCellData(selectedItem);
            List<Medicament> lm = sm.afficher();
            int i=0;
            for(Medicament m:lm){
           String nom=m.getNom();
            if(value.equals(nom)){
                idm = m.getId();
                break;
            }
        }     
            content.getChildren().removeAll(content.getChildren()); 
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMed.fxml"));
                Parent root = loader.load();
                content.getChildren().add(root);
            } catch (Exception ex) {
                Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    private ImageView getImageView(String imagePath) {
    Image image = new Image(imagePath);
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(50);
    imageView.setPreserveRatio(true);
    return imageView;
    }
    }