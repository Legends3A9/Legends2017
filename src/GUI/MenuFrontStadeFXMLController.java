/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class MenuFrontStadeFXMLController implements Initializable {
    @FXML
    private Button image;
    @FXML
    private Button ticket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConsulterImageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ConsulterStadeFXML.fxml")));
        Parent root = loader.load();
        ConsulterStadeFXMLController ConsulterStadeControler = loader.getController();
        Scene scene = image.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void AcheterTicketAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("AcheterTicketFXML.fxml")));
        Parent root = loader.load();
        AcheterTicketFXMLController AcheterTicketControler = loader.getController();
        Scene scene = image.getScene();
        scene.setRoot(root);
    }
    
}
