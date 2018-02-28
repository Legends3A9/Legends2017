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
public class MenuFXMLController implements Initializable {
    @FXML
    private Button stade;
    @FXML
    private Button ticket;
    @FXML
    private Button gererstade;
    @FXML
    private Button gererticket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionStade(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajouterFormFXML.fxml")));
        Parent root = loader.load();
        AjouterFormFXMLController StadeControler = loader.getController();
        Scene scene = stade.getScene();
        scene.setRoot(root);
        
    }

    @FXML
    private void ActionTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajouterTicketFormFXML.fxml")));
        Parent root = loader.load();
        AjouterTicketFormFXMLController TicketControler = loader.getController();
        Scene scene = ticket.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void ActionGererStade(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionStadeFXML.fxml")));
        Parent root = loader.load();
        GestionStadeFXMLController GererStadeControler = loader.getController();
        Scene scene = gererstade.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void ActionGererTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionTicketFXML.fxml")));
        Parent root = loader.load();
        GestionTicketFXMLController GererTicketControler = loader.getController();
        Scene scene = gererticket.getScene();
        scene.setRoot(root);
    }
    
}
