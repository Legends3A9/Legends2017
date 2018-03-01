/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Stade;
import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.StadeService;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class GestionTicketFXMLController implements Initializable {
    @FXML
    private TableColumn<?, ?> ref;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private TableColumn<?, ?> stade;
     @FXML
    private TableColumn<?, ?> rencontre;
    @FXML
    private TableColumn<?, ?> date_match;
    @FXML
    private TableColumn<?, ?> place;
    @FXML
    private TableColumn<?, ?> prix;
     private ObservableList<Ticket> data;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TableView<Ticket> tableticket;
    @FXML
    private TextField gref;
    @FXML
    private TextField gnombre;
    @FXML
    private TextField gstade;
    @FXML
    private DatePicker gdate_match;
    @FXML
    private TextField gplace;
    @FXML
    private TextField gprix;
    @FXML
    private TextField gmatch;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<?, ?> equipe2;
    @FXML
    private TableColumn<?, ?> heure;
    @FXML
    private TextField gmatch1;
    @FXML
    private TextField gheure;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setsCllTable();
        loadDataFromDatabase();
        
        setcellValue();
    }
    
     private void loadDataFromDatabase() {
        try {
            TicketService service=new TicketService();
            List<Ticket> rs = service.selectTicket();
            for(Ticket t : rs)
            {
                
                
                
                data.add(t);
                System.out.println("recup table view ok !");
               
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableticket.setItems(data);
    }
     
     public void setsCllTable() {
        ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        stade.setCellValueFactory(new PropertyValueFactory<>("stade"));
        rencontre.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
        equipe2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
        date_match.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        place.setCellValueFactory(new PropertyValueFactory<>("place"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
    }

     private void setcellValue() {
        tableticket.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Ticket pl = tableticket.getItems().get(tableticket.getSelectionModel().getSelectedIndex());
                gref.setText(Integer.toString(pl.getRef()));
                gnombre.setText(Integer.toString(pl.getNombre()));
                
                gstade.setText((pl.getStade()));
                gmatch.setText(pl.getEquipe1());
                gmatch1.setText(pl.getEquipe2());
                gdate_match.setValue(pl.getDate_match().toLocalDate());
                gheure.setText(pl.getHeure());
                gplace.setText(pl.getPlace());
                gprix.setText(Float.toString(pl.getPrix()));
                
                
               
            
                
                
                
                
                
                
                
            }
        }); }
    
    @FXML
    private void ActionSupprimerTicket(ActionEvent event) {
        
        int refE = Integer.valueOf(gref.getText());
         
         service.TicketService ser = new TicketService();
         ser.DeletTicketByRef(refE);
        
        
        gref.setText("");
        gnombre.setText("");
        gstade.setText("");
        gmatch.setText("");
        gdate_match.setValue(null);
        gplace.setText("");
        gprix.setText("");
        
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stade supprimé");
        alert.setHeaderText("Suppression");
      
        alert.showAndWait();
    }

    @FXML
    private void ActionModifierTicket(ActionEvent event) {
        int refE = Integer.parseInt(gref.getText());
        int nombreE = Integer.parseInt(gnombre.getText());
        
        String stadeE = gstade.getText() ;
        String matchE = gmatch.getText(); 
        String matchEE = gmatch1.getText();
        Date date_matchE = (Date.valueOf(gdate_match.getValue()));
         String heureE = gheure.getText();
        String placeE = gplace.getText(); 
        float prixE = Float.parseFloat(gprix.getText());
         
        Ticket t1 =new Ticket(refE,nombreE,stadeE,matchE,matchEE,date_matchE,heureE,placeE,prixE);
        
 
        service.TicketService ser = new TicketService();
        ser.updateTicket(t1);
        
        gref.setText("");
        gnombre.setText("");
        gstade.setText("");
        gmatch.setText("");
        gmatch1.setText("");
        gdate_match.setValue(null);
        gheure.setText("");
        gplace.setText("");
        gprix.setText("");
        
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setHeaderText("Modification");
       
        alert.showAndWait();
    }

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("MenuStadeFXML.fxml")));
        Parent root = loader.load();
        MenuStadeFXMLController MenuStadeControler = loader.getController();
        Scene scene = retour.getScene();
        scene.setRoot(root);
    }
    
}
