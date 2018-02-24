/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author boussandel
 */
public class TestGui extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("membreFront.fxml"));
     // FXMLLoader loader = new FXMLLoader(getClass().getResource("frontEvaluer.fxml"));
    //FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentification.fxml"));
      // FXMLLoader loader = new FXMLLoader(getClass().getResource("EvtQueJeParticipe.fxml"));
    //  FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionEve.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("FRONTmembre.fxml"));
    //  FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterEvaluation.fxml"));
        
        try {
           Parent root =  loader.load() ; 
           Scene scene = new Scene(root);
           scene.getStylesheets().add("/CSS/ComboBox.css");
           Stage stage = new Stage () ; 
           stage.setScene(scene);
           //stage.sizeToScene();
           stage.show();
         }
        catch(IOException ex) {
       
        
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
