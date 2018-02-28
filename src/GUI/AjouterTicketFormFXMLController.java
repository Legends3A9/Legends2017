/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Ticket;
import java.io.IOException;
import service.TicketService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class AjouterTicketFormFXMLController implements Initializable {
    @FXML
    private TextField nombre;
    @FXML
    private TextField stade;
    @FXML
    private TextField matchs;
    @FXML
    private TextField place;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker date_match;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionAjouterTicket(ActionEvent event)throws IOException {
        
        String nombreS =nombre.getText();
        String stadeS=stade.getText();
        String matchSS=stade.getText();
        LocalDate date_matchS = date_match.getValue();
        String placeS= place.getText();
        String prixS= prix.getText();
        
        java.sql.Date datemat = java.sql.Date.valueOf(date_matchS);
        Ticket t = new Ticket(Integer.parseInt(nombreS),stadeS,matchSS, datemat, placeS,Float.parseFloat(prixS));
        service.TicketService tt = new TicketService(); 
        tt.insererTicket(t);
        
        
    }
    
}
