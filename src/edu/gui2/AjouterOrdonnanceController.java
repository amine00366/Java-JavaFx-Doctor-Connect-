/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui2;

//import com.mysql.cj.protocol.Message;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.entities.Consultation;
import edu.entities.Ordonnance;
import edu.services.ConsultationCrud;
import edu.services.OrdonnanceCrud;
import edu.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.DriverManager;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import static org.apache.commons.io.IOUtils.writer;

/**
 * FXML Controller class
 *
 * @author SAHLI
 */
public class AjouterOrdonnanceController implements Initializable {

    @FXML
    private Button btnValider;
    @FXML
    private TextField tfDose;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfFrequence;
    @FXML
    private DatePicker tfDateCreation;
    @FXML
    private TableView<Ordonnance> testajout;
    @FXML
    private TableColumn<Ordonnance, String> tbNo;
    @FXML
    private TableColumn<Ordonnance, String> tbdos;
    @FXML
    private TableColumn<Ordonnance, String> tbFrequence;
    @FXML
    private TableColumn<Ordonnance, Date> tbDate;
    private Label LBShow;
    @FXML
    private ComboBox<String> tfIdConsultation;
    
    @FXML
    private TextField numeroSMS;

    /**
     * Initializes the controller class.
     */
    ConsultationCrud cs = new ConsultationCrud();
    List<Consultation> consultation = cs.getAll();
    private int id_consultation_id = -1;
    @FXML
    private Button btnValider1;
    @FXML
    private ChoiceBox<?> chox;
    @FXML
    private TextField trrrr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Consultation c : consultation){
            tfIdConsultation.getItems().add(c.getEtat_Consultation());
            valuesMap.put(c.getEtat_Consultation(),c.getId());
        }
        
        tfIdConsultation.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = tfIdConsultation.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            id_consultation_id = SelectedValue;
        });
//        testajout.setRowFactory(tv -> {
//    TableRow<Ordonnance> row = new TableRow<>();
//    row.setOnMouseClicked(event -> {
//        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2 && !row.isEmpty()) {
//            Ordonnance ordonnanceSelectionnee = row.getItem();
//            // Afficher les détails de l'ordonnance sélectionnée
//            System.out.println("Nom du médicament : " + ordonnanceSelectionnee.getNom_Medicament());
//            System.out.println("Dose : " + ordonnanceSelectionnee.getDose());
//            System.out.println("Fréquence : " + ordonnanceSelectionnee.getFrequence());
//            System.out.println("Date de création : " + ordonnanceSelectionnee.getDate_creation());
//        }
//    });
//    return row ;
//});
// Ajouter un listener pour détecter les double-clics sur les lignes de la table
    testajout.setRowFactory(tv -> {
        TableRow<Ordonnance> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !row.isEmpty()) {
                Ordonnance ordonnanceSelectionnee = row.getItem();
                afficherDetailsOrdonnance(ordonnanceSelectionnee);
            }
        });
        return row;
    });
    }
private static List<Ordonnance> ordonnances = new ArrayList<>();
private void afficherDetailsOrdonnance(Ordonnance ordonnance) {
    try {
        // Charger le fichier FXML de la nouvelle fenêtre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOrdonnance.fxml"));
        Parent root = loader.load();

        // Récupérer le contrôleur de la nouvelle fenêtre
        AfficherOrdonnanceController controller = loader.getController();

        // Passer les détails de l'ordonnance sélectionnée au contrôleur de la nouvelle fenêtre
        controller.setDetailsOrdonnance(ordonnance);

        // Afficher la nouvelle fenêtre
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @FXML
private void SaveOrdonnance(ActionEvent event) {
    String dose = tfDose.getText();
    String frequence = tfFrequence.getText();
    String Nom_Medicament = tfNom.getText();
    Date date_creation = null;
    try {
        LocalDate localDate = tfDateCreation.getValue();
        if (localDate != null) {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date_creation = Date.from(instant);
        }
    }catch (Exception e) {
        e.printStackTrace();
    }

   if (tfDose.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(" Veuillez saisir une valeur pour la dose. Ce champ ne peut pas être laissé vide! ");
    alert.showAndWait();
} else if (tfNom.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Entrez les noms des médicaments. Ce champ est obligatoire!");
    alert.showAndWait();
} else if (tfFrequence.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(" Veuillez saisir une valeur pour la frequence. Ce champ ne peut pas être laissé vide! ");
    alert.showAndWait();
} else if (tfDateCreation.getValue().getYear() < 2023) {
    Alert al2 = new Alert(Alert.AlertType.ERROR);
    al2.setHeaderText(null);
    al2.setContentText("Veuillez choisir une date courante");
    al2.showAndWait();
} else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succées");
    alert.setHeaderText(null);
    alert.setContentText("Ajouter le medicament suivant svp");
    alert.showAndWait();
    Ordonnance o = new Ordonnance(id_consultation_id, frequence, dose, Nom_Medicament, date_creation);
ordonnances.add(o);

    tfDose.setText("");
    tfFrequence.setText("");
    tfNom.setText("");
   

}
}
@FXML
private void valider(ActionEvent event) {
    MyConnection myConnection = MyConnection.getInstance();
    Connection connection = myConnection.getCnx();

    // Créer une instance d'Ordonnance qui contiendra toutes les informations saisies pour chaque ordonnance
    Ordonnance ordonnance = new Ordonnance(id_consultation_id, "", "", "", null);

    for (Ordonnance o : ordonnances) {
        // Ajouter les informations de chaque ordonnance à l'instance créée
        ordonnance.setFrquence(ordonnance.getFrquence() + o.getFrquence() + "\n");
        ordonnance.setDose(ordonnance.getDose() + o.getDose() + "\n");
        ordonnance.setNom_Medicament(ordonnance.getNom_Medicament() + o.getNom_Medicament() + "\n");
        ordonnance.setDate_creation(o.getDate_creation());
    }

    PreparedStatement stmt = null;
    try {
        connection.setAutoCommit(false);
        stmt = connection.prepareStatement("INSERT INTO `ordonnance` (`id_consultation_id`, `frequence`, `dose`, `Nom_Medicament`, `date_creation`) VALUES (?, ?, ?, ?, ?)");

        // Insérer l'instance d'Ordonnance dans la base de données
        stmt.setInt(1, ordonnance.getId_consultation_id());
        stmt.setString(2, ordonnance.getFrquence());
        stmt.setString(3, ordonnance.getDose());
        stmt.setString(4, ordonnance.getNom_Medicament());
        stmt.setDate(5, new java.sql.Date(ordonnance.getDate_creation().getTime()));
        stmt.executeUpdate();

        connection.commit();
        ordonnances.clear(); // pour vider la liste après l'insertion à la base de données
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex2) {
            System.err.println(ex2.getMessage());
        }
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
                        send_SMS();

}





    @FXML
    private void ModifierPub(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateOrdonnance.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    }

    private void showAllPersonnefilBDbyhaithem(ActionEvent event) {
        OrdonnanceCrud oc = new OrdonnanceCrud();

        LBShow.setText(oc.getAll().toString());

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        OrdonnanceCrud oc = new OrdonnanceCrud();
        Ordonnance o = testajout.getSelectionModel().getSelectedItem();
        //    o.getId();
        int ordonnanceId = o.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER UNE Publication ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            oc.supprimer(ordonnanceId);

            JOptionPane.showMessageDialog(null, " publication SUPPRIME ");
        } else {
            JOptionPane.showMessageDialog(null, " publication NON SUPPRIME ");
        }

        oc.getAll();
    }

    private void OnClickedPrint(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.testajout;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void AfficherPub(ActionEvent event) {

        OrdonnanceCrud oc = new OrdonnanceCrud();

        ObservableList<Ordonnance> liste = oc.getAll();

        tbNo.setCellValueFactory(new PropertyValueFactory<>("Nom_Medicament"));
        tbdos.setCellValueFactory(new PropertyValueFactory<>("dose"));
        tbFrequence.setCellValueFactory(new PropertyValueFactory<>("frequence"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
//tbaction.cellValueFactoryProperty(new PropertyValueFactory<>("supprimer()"));
        testajout.setItems(liste);

    }
  

    
//    public List<Ordonnance> afficher() throws SQLException {
//        Connection cnx = null;
//        Statement st = null;
//        ResultSet rs = null;
//        ObservableList<Ordonnance> liste = FXCollections.observableArrayList();
//        String requette = "SELECT * FROM Ordonnance";
//
//        try {
//            cnx = MyConnection.getInstance().getCnx();
//            st = cnx.createStatement();
//            rs = st.executeQuery(requette);
//            Ordonnance testajout;
//              
//while (rs.next()) {
//    testajout  = new Ordonnance(rs.getInt("dose"), rs.getString("frequence"), rs.getString("Nom_Medicament"), rs.getTimestamp("date_creation"));
//    liste.add(testajout);
//}
//
//            
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    /* Ignored */
//                }
//            }
//            if (st != null) {
//                st.close();
//                /* Ignored */
//            }
//        }
//        return liste;
//
//    }
//     private ObservableList<Ordonnance> getData() {
//        OrdonnanceCrud cp = new OrdonnanceCrud();
//        ObservableList<Ordonnance> myProds = cp.getAll();
//        return myProds;
//    }
//     
//    private void select(MouseEvent event) {
//        Ordonnance M = testajout.getSelectionModel().getSelectedItem();
//
//        tbNo.setText(M.getNom_Medicament());
//        tbdos.setText(M.getDose());
//        tbFrequence.setText(M.getFrquence());
//       // tbDate.setText(M.getDate_creation());
//tbDate.setText(M.getDate_creation().toString());
//
//    }
    void send_SMS (){
        
        String ACCOUNT_SID = "ACf6d2d23f0bd5d98998d28c3c8be3e17b";
        String AUTH_TOKEN = "1d85b0b025db167336a4a849b6df27e0";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+216" + numeroSMS.getText();
            String message = "Bonjour ,\n"
                    + "Nous sommes ravis de vous informer qu'une ordonnance a été ajoutée.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt .\n"
                    + "Cordialement,\n" 
                     + "Doc 2023";
                    
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("++15746525201"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());
          

        
         
     }
   

    @FXML
    private void PDF(ActionEvent event) throws SQLException {

       try {
       com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\MSI\\Documents\\NetBeansProjects\\PiDevProject\\imgs\\Reclamation\\reclamation.pdf"));  
       doc.open();
       
     // Image img = Image.getInstance("C:\\Users\\CC\\3D Objects\\JDBCJAVAFX\\Evenement.pdf");
       //img.scaleAbsoluteHeight(60);
       //img.scaleAbsoluteWidth(100);
       //img.setAlignment(Image.ALIGN_RIGHT);
       //doc.open();
       //doc.add(img);
   
       doc.add(new Paragraph(" "));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("liste des Ordonnances  ", font);
       p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));

       PdfPTable tabpdf = new PdfPTable(8);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("Nom de medicament", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("dose ", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       cell = new PdfPCell(new Phrase("frequence", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
       
       
       cell = new PdfPCell(new Phrase("date de creation", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
       
      MyConnection myConnection = MyConnection.getInstance();
    Connection connection = myConnection.getCnx();
       
      // Connection conn = getCnx();
        String query;
           query = " SELECT * FROM ordonnance ";
       
          Statement st;
          ResultSet rs;
          st = connection.createStatement();
          rs = st.executeQuery(query);
     // PreparedStatement pst = cnx.prepareStatement(requete);
      // ResultSet rs = pst.executeQuery();
      while (rs.next()) {
           cell = new PdfPCell(new Phrase(rs.getString("Nom_Medicament"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
           cell = new PdfPCell(new Phrase(rs.getString("dose"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);

           cell = new PdfPCell(new Phrase(rs.getString("frequence"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);

           cell = new PdfPCell(new Phrase(rs.getString("date_creation"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
             }
     
   
          doc.add(tabpdf);
          JOptionPane.showMessageDialog(null, "Success !!");
          doc.close();
          Desktop.getDesktop().open(new File("C:\\Users\\amine\\Bureau\\pidev desktop\\reclamation.pdf"));
       }
         catch (DocumentException  | IOException e) {

            System.out.println("ERROR PDF");
            //System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }
 }

    @FXML
private void imprimerOrdonnance(ActionEvent event) throws SQLException {
    // Vérifier si une ordonnance est sélectionnée
    Ordonnance ordonnance = testajout.getSelectionModel().getSelectedItem();
    if (ordonnance == null) {
        // Si aucune ordonnance n'est sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune ordonnance sélectionnée");
        alert.setContentText("Veuillez sélectionner une ordonnance à imprimer.");
        alert.showAndWait();
        return;
    }
       // Image backgroundImage = Image.getInstance("chemin/vers/images.jpg");
 //writer.setBackgroundImage(backgroundImage);

   // doc.open();
    try {
        // Créer le document PDF
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\amine\\Bureau\\pidev desktop\\reclamation.pdf"));
       // Image backgroundImage = Image.getInstance("chemin/vers/images.jpg");
// writer.setBackgroundImage(backgroundImage);

        doc.open();

        // Ajouter les détails de l'ordonnance au document
        Font font = new Font(Font.FontFamily.COURIER, 28, Font.BOLD, BaseColor.WHITE);

//PdfPCell cell = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.WHITE)));
//cell.setBackgroundColor(new BaseColor(0x4d, 0xae, 0xa7));
//cell.setBorder(Rectangle.NO_BORDER);
//cell.setFixedHeight(130f); // Set the fixed height of the cell to 100 units
//PdfPTable table = new PdfPTable(1);
//table.setWidthPercentage(130);
//table.addCell(cell);
//doc.add(table);

// Ajouter le titre "Ordonnance"

Paragraph p11 = new Paragraph("VOTRE ORDONNANCE ", font);
p11.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

// Créer la cellule avec une hauteur fixe de 130 unités et ajouter le titre à la cellule
PdfPCell cell = new PdfPCell();
cell.setFixedHeight(120f);
cell.addElement(p11);
cell.setBackgroundColor(new BaseColor(0x4d, 0xae, 0xa7));
cell.setBorder(Rectangle.NO_BORDER);

// Créer la table et ajouter la cellule à la table
PdfPTable table = new PdfPTable(1);
table.setWidthPercentage(114);
table.addCell(cell);

// Ajouter la table au document
doc.add(table);


//Paragraph p1 = new Paragraph("Ordonnance", font);
//        p1.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
//        doc.add(p1);
        doc.add(new Paragraph(" "));
        Paragraph p2 = new Paragraph();
p2.add(new Chunk("Nom du médicament : ", new Font(Font.FontFamily.COURIER , 20, Font.BOLD, BaseColor.GRAY)));
//p2.add(new Chunk(ordonnance.getNom_Medicament(), new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK)));
doc.add(p2);
doc.add(new Paragraph("\n"));

 Paragraph p22 = new Paragraph();
//p22.add(new Chunk(ordonnance.getNom_Medicament().replace("\n", " | "), new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK)));
//p22.add(new Chunk(ordonnance.getNom_Medicament(), new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, BaseColor.BLACK)));
p22.add(new Chunk(ordonnance.getNom_Medicament(), new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, new BaseColor(0x6b, 0xd3, 0xcf)  )));

doc.add(p22);

doc.add(new Paragraph("\n"));

Paragraph p3 = new Paragraph();
p3.add(new Chunk("Dose : ", new Font(Font.FontFamily.COURIER, 20, Font.BOLD, BaseColor.GRAY)));
//p3.add(new Chunk(ordonnance.getDose(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
doc.add(p3);
doc.add(new Paragraph("\n"));

Paragraph p33 = new Paragraph();
p33.add(new Chunk(ordonnance.getDose(), new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, new BaseColor(0x6b, 0xd3, 0xcf))));
doc.add(p33);

doc.add(new Paragraph("\n"));

Paragraph p4 = new Paragraph();
p4.add(new Chunk("Fréquence :", new Font(Font.FontFamily.COURIER, 20, Font.BOLD, BaseColor.GRAY)));
//p4.add(new Chunk(ordonnance.getFrequence(), new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK)));

doc.add(p4);
doc.add(new Paragraph("\n"));

Paragraph p44 = new Paragraph();
p44.add(new Chunk(ordonnance.getFrequence(), new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL, new BaseColor(0x6b, 0xd3, 0xcf))));

doc.add(p44);

doc.add(new Paragraph("\n"));

Paragraph p5 = new Paragraph();
p5.add(new Chunk("Date de création : " , new Font(Font.FontFamily.COURIER, 20, Font.BOLD, BaseColor.GRAY)));
//Date date = ordonnance.getDate_creation();
//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//String formattedDate = sdf.format(date);
//p5.add(new Chunk(formattedDate, new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK)));
Paragraph p55 = new Paragraph();
Date date = ordonnance.getDate_creation();
doc.add(p5);

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
String formattedDate = sdf.format(date);
p55.add(new Chunk(formattedDate, new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL,new BaseColor(0x6b, 0xd3, 0xcf))));
doc.add(new Paragraph("\n"));
//p5.add(new Chunk(ordonnance.getDate_creation(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK)));
doc.add(p55);
        doc.add(new Paragraph(" "));

//doc.setBackgroundColor(new BaseColor(245, 245, 245)); // gris très clair

        // Fermer le document PDF
        doc.close();

        
        // Ouvrir le document PDF avec l'application par défaut du système
        Desktop.getDesktop().open(new File("C:\\Users\\amine\\Bureau\\pidev desktop\\reclamation.pdf"));
    } catch (DocumentException | IOException e) {
        System.out.println("Erreur lors de la création du document PDF");
        System.out.println(e.getMessage());
   
    }

}
//public void filteredSearch() {
//  OrdonnanceCrud oc = new OrdonnanceCrud();
//
//        ObservableList<Ordonnance> liste = oc.getAll();
//      //  List<Ordonnance> l = cs.getAll();
//        //ObservableList<Ordonnance> list = FXCollections.observableArrayList(List_event);
//        FilteredList<Ordonnance> oordo = new FilteredList(liste, p -> true);
//        trrrr.textProperty().addListener((obs, oldValue, newValue) -> {
//            //switch (chox.getValue()) {
//                switch ((int) chox.getValue()) {
//
//                case "Nom du médicament":
//                    oordo.setPredicate(p -> p.getNom_Medicament().toLowerCase().contains(newValue.toLowerCase().trim()));
//                    break;
//                    
//                
//            }
//               case "Dose":
//                    oordo.setPredicate(p -> p.getDose().toLowerCase().contains(newValue.toLowerCase().trim()));
//                    break;
//            }
//
//        });
//        testajout.setItems(oordo);
//        chox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
//                -> {
//            if (newVal != null) {
//                trrrr.setText("");
//            }
//        });
//
//    }
private void handleTableClick(MouseEvent event) throws ParseException {
    if (event.getClickCount() == 3) {
        // Récupération de l'ordonnance sélectionnée
        Ordonnance ordonnanceSelectionnee = testajout.getSelectionModel().getSelectedItem();
        
        // Vérification que l'ordonnance sélectionnée existe
        if (ordonnanceSelectionnee != null) {
            try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateOrdonnance.fxml"));
    Parent root = loader.load();
    UpdateOrdonnanceController modifierOrdonnanceController = loader.getController();
    modifierOrdonnanceController.setOrdonnance(ordonnanceSelectionnee);
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
} catch (IOException e) {
    e.printStackTrace();
}

        }
    }
}

    @FXML
    private void goPost(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowPost.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    private void goord(ActionEvent event) throws IOException{
 
    }

    

  

  

    @FXML
    private void goRDV(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/AppointmentForDoctor.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
