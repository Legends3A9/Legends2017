/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Produit;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GlaDio007
 */
public class MenuController implements Initializable {

    @FXML
    private Button Ajout;
    @FXML
    private Button aff;
   
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ClientProduits.fxml"));
        
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage stage= new Stage();
           
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void affich(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
        
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage stage= new Stage();
            stage.setTitle("Gestion Boutique");
            stage.setScene(scene);
            stage.show();
    }

   
    
}
