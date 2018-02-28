/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.Utilisateur;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dambo
 */
public class FXMLmainController implements Initializable {

    @FXML
    private Button ajoutPoole;
    @FXML
    private Button pooles;
    @FXML
    private ImageView imageUser;
    @FXML
    private JFXButton deco;
    @FXML
    private Label username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(Utilisateur.getNomParticipant());
        // TODO
    }    
    @FXML
     public void addPoule () throws SQLException, IOException {
FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAjoutPoole.fxml"));
          Parent  root = loader.load();
          Scene scene = new Scene(root);
          Stage stage=new Stage();
          stage.setScene(scene);
          stage.show();

        
    }
    @FXML
     public void consulterPool() throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLConsulter.fxml"));
          Parent  root = loader.load();
          Scene scene = new Scene(root);
          Stage stage=new Stage();
          stage.setScene(scene);
          stage.show();
        

     }

    private void front(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLFront_1.fxml"));
          Parent  root = loader.load();
          Scene scene = new Scene(root);
          Stage stage=new Stage();
          stage.setScene(scene);
          stage.show();
        
    }

  

    @FXML
       private void deco(ActionEvent event) throws IOException {
          Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();   
    }
}
