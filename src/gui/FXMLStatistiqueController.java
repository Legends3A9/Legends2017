/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Services.ServiceEquipe;
import Services.ServicePari;

/**
 * FXML Controller class
 *
 * @author Dambo
 */
public class FXMLStatistiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> ContinentBar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Label userlogo;
    @FXML
    private ImageView imageUser;
    @FXML
    private JFXButton deco;
    @FXML
    private Label username;
    @FXML
    private BarChart<?, ?> paris;
    @FXML
    private NumberAxis y2;
    @FXML
    private CategoryAxis x2;
    public List<String> liste = Arrays.asList("Egypte", "Maroc", "Nijeria", "Senegal", "Tunisie", "Arabie Saoudite", "Australie", "Japon", "Republique de Corée", "Iran", "Allemagne", "Angleterre", "Belgique", "Croatie", "Danemark", "Danemark", "Espagne", "Costa Rica", "Mexique", "Panama", "France", "Islande", "Pologne", "Portugal", "Russie", "Serbie", "Suede", "Suisse", "Argentine", "Bresil", "Peru", "Uruguay");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           XYChart.Series set = new XYChart.Series<>();
        ServiceEquipe se = new ServiceEquipe();

        try {
            set.getData().add(new XYChart.Data("Afrique", se.nbrequipeContinant("Afrique")));
            set.getData().add(new XYChart.Data("Asie", se.nbrequipeContinant("Asie")));
            set.getData().add(new XYChart.Data("Europe", se.nbrequipeContinant("Europe")));
            set.getData().add(new XYChart.Data("Amérique du Sud", se.nbrequipeContinant("Amérique du Sud")));
            set.getData().add(new XYChart.Data("Amérique du Nord", se.nbrequipeContinant("Amérique du Nord")));

            ContinentBar.getData().addAll(set);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
           XYChart.Series set2 = new XYChart.Series<>();

           for(String v : liste){
               ServicePari sp = new ServicePari();
               int votePareqp = (int) sp.getAll().stream().filter(e -> e.getNom_equipe().equals(v)).count();
               int votetot = (int) sp.getAll().stream().count();
               
               set2.getData().add(new XYChart.Data(v, (((double) votePareqp / (double) votetot) * 100)));
           }
           paris.getData().addAll(set2);
           username.setText("Iheb ynik el kol ");
        
    }    

    @FXML
    private void deco(ActionEvent event) throws IOException {
          Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();   
    }
    
}
