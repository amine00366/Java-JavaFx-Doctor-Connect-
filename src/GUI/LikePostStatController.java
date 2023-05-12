/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Util.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LikePostStatController implements Initializable {

    @FXML
    private PieChart StatCom;   
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
                 ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

int n;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MyDB.getInsatnce().getConnection();
            stat();
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
    private void stat() {
          try{
           
     
          String qquery=" SELECT Post.id,post.description, COUNT(post_like.id) AS num_comments "
                    + "FROM Post LEFT JOIN post_like ON Post.id = post_like.post_id "
                    + "GROUP BY Post.id";
           Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(qquery);
        System.out.println("STATISTIQUES DES Post Plus Active\n");
        while (rs.next()) {
             int id = rs.getInt("id");
               String title = rs.getString("post.description");
                int numComments = rs.getInt("num_comments");
                System.out.println("Article #" + id + " - " + title + " - " + numComments + " likes");
            // Créer un nouvel objet PieChart.Data avec les données récupérées
            PieChart.Data data = new PieChart.Data(title + " - " + numComments + " likes",numComments);
            // Ajouter l'objet PieChart.Data à la liste observable
            pieChartData.add(data);
        }
        System.out.println();
        rs.close();
        stmt.close();
    } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des statistiques des commentaires : " + ex.getMessage());
    }

    pieChartData.forEach(data ->
            data.nameProperty().bind(
                    Bindings.concat(
                            data.getName(), " - ", data.getPieValue(), " Comments"
                    )
            )
    );

  StatCom.getData().addAll(pieChartData);

    // Définir le rayon du graphique à une valeur plus grande pour l'agrandir
   StatCom.setStartAngle(90);
    // Mettre les sections du graphique dans le sens anti-horaire
    StatCom.setClockwise(false);
}

}
