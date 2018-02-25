/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evaluation;
import Entités.Evenement;
import Entités.Participation;
import Entités.Utilisateur;
import GUI.MembreFrontController.Evenements;
import Services.EvaluationService;
import Services.EvenementServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class EvtQueJeParticipeController implements Initializable {

    @FXML
    private ListView<Evenement> list;
    
 ObservableList<Evenement> data ;
    @FXML
    private Label lblNom;
    @FXML
    private Label lbldest;
    @FXML
    private Label lbldesc;
    @FXML
    private Label lblDuree;
    @FXML
    private Label lbldate;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPrix;
    @FXML
    private ImageView imgg;
    @FXML
    private Rating rat;
    @FXML
    private Label lblEtat;
    @FXML
    private Label lblType;
    @FXML
    private Label lblid;
    @FXML
    private Label num;
    
    
    /**
     * Initializes the controller class.
     */

 
 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        Services.EvenementServices es = new Services.EvenementServices();
        
        
       
                  data = FXCollections.observableArrayList();
       

            try {
                      data = FXCollections.observableArrayList();
                       loadDataFromDatabase();
                      // setcellValue();
                            list.setCellFactory(lv -> new Evenements());

            } catch (SQLException ex) {
                Logger.getLogger(MembreFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }        
            
            setcellValue();
            //search();
                 
    }  
    
     private void loadDataFromDatabase() throws SQLException {
       List<Evenement> equipes = new ArrayList<>();
       List<Evaluation> evaluations = new ArrayList<>();
           EvenementServices se=new EvenementServices();
         
           Services.ParticipationService sp = new Services.ParticipationService();
           List<Participation> listep =sp.participeDéja(Utilisateur.getIdd());
           List<Evenement> listeE = se.findAll();
           List<Evenement> listeRes = new ArrayList<>() ;
           for(Participation p : listep) {
               for(Evenement e : listeE){
                   if(e.getIdEvenement()==p.getIdEvenement()) listeRes.add(e);
               }
               
               
           }
       for(Evenement e : listeRes)
            {
                data.add(e); 
               
            }
        list.setItems(data);
       
      
                }
      private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement eqp = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                
                    File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images\\"+eqp.getImage());
            
        Image image = new Image(file.toURI().toString());
        lblid.setText(Integer.toString(eqp.getIdEvenement()));
         lblNom.setText(eqp.getNomEvenement());
         lbldate.setText(eqp.getDateEvenement().toString());
         lbldesc.setText(eqp.getDescription());
         lbldest.setText(eqp.getDestination());
         lblDuree.setText(eqp.getDuree());
         lblType.setText(eqp.getType());
         
         
         lblEtat.setText(eqp.getEtat());
                  
         lblNombre.setText(Integer.toString(eqp.getNbrPlaces()));
         lblPrix.setText(Float.toString(eqp.getPrix()));
        /* 
         if(participeDéja()==true) {
           
           participer.setDisable(true);


         }
         
         else {
             //participer.setDisable(false);
         }*/
        
        Services.EvaluationService ess = new EvaluationService(); 
        Double moy = ess.moyByName(eqp.getIdEvenement());
        
         rat.setRating(moy);
         
            
        Image image2 = new Image(file.toURI().toString());
            
            imgg.setImage(image2); 
            imgg.setFitHeight(400);
            imgg.setFitHeight(200);
            
            
            }
        });
    } 

    private void retourAction(ActionEvent event) throws IOException {
         Parent blah = FXMLLoader.load(getClass().getResource("membreFront.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }
    
    
    
}
