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
import Services.EvaluationService;
import Services.EvenementServices;
import Services.ParticipationService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class FrontEvaluerController implements Initializable {

    ObservableList<Evenement> data ;
    @FXML
    private ListView<Evenement> list;
    private ImageView cont;
    private JFXTextField eq;
    private JFXTextField ct;
    @FXML
    private Label lblnom;
    @FXML
    private Label lbldest;
    @FXML
    private Label lbldate;
    @FXML
    private Label lblduree;
    @FXML
    private Label lbltype;
    @FXML
    private Label lbletat;
    @FXML
    private Label lblnombre;
    @FXML
    private Label lblPrix;
    @FXML
    private Label jdesc;
    @FXML
    private Rating rat;
    @FXML
    private Label lblid;
    @FXML
    private ImageView imggg;
    @FXML
    private Label lblPrix1;
    @FXML
    private Button evaluer;

    @FXML
    private void evaluerAction(ActionEvent event) {
        String nomEven = lblnom.getText();
        String  nomEval = Utilisateur.getNomParticipant();
        String prenolEval = Utilisateur.getPrenomParticipant();
        String email = Utilisateur.getEmailEv();
        int idUser = Utilisateur.getIdd();
        int idEve = (Integer.parseInt(lblid.getText()));
        double note = rat.getRating();
        
        Evaluation e = new Evaluation();
        e.setIdUser(idUser);
        e.setIdEvenement(idEve);
        e.setEmailParticipant(email);
        e.setNoteEvenement(note);
        e.setPrenomParticipant(prenolEval);
        e.setNomEvenement(nomEven);
        e.setNomParticipant(nomEval);
        Services.EvaluationService ess = new EvaluationService() ; 
        ess.ajouterEvaluation(e);
        
        System.out.println("evaluation ajouté");
        System.out.println("sssssss"+Utilisateur.getNomParticipant());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merci ");
            alert.setContentText("Evaluation ajouté avec succées  ");
            alert.showAndWait();
          
           /* Parent pagereclamationfront = FXMLLoader.load(getClass().getResource("EvenMembre.fxml"));
        Scene reclamationfront_scene = new Scene(pagereclamationfront);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(reclamationfront_scene);
        app_stage.show();*/
    }

 

            
    



    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   static public class Evenements extends ListCell<Evenement> { 
  
   
  
    public Evenements(){ 
        
    } 
  
  
    @Override
    protected void updateItem(Evenement item, boolean bln) { 
         super.updateItem(item, bln);
                        Rating rss = new Rating();
                        rss.setPartialRating(true);
                       // Button delete =new Button("supprimer");
                        // Button update =new Button("modifier");
                        Services.EvaluationService ess = new EvaluationService(); 
                       //double moy = ess.moyByName(item.getNomEvenement()); 
                            rss.getStylesheets().add("GUI/fxml1.css");
                           
                        if (item != null) {
                            double moy = ess.moyByName(item.getIdEvenement()); 
                            rss.setRating(moy);
                            
                            VBox vBox = new VBox(rss,new Text(item.getNomEvenement()),new Text(item.getNomEvenement()),new Text(item.getDestination()));
                             File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images\\"+item.getImage());
            
        Image image = new Image(file.toURI().toString());
        ImageView img = new ImageView(image); 
        img.setFitHeight(100);
        img.setFitWidth(170);
                            
                            HBox hBox = new HBox(img, vBox);
                           
                            Text t =new Text(item.getDateEvenement().toString());
                            Text t2 =new Text(item.getNomEvenement());
                            Text t3 =new Text(item.getDestination());
                            t.setStyle("-fx-font-size: 18 arial;");
                            t2.setStyle("-fx-font-size: 25 arial;");
                            t3.setStyle("-fx-font-size: 20 arial;");
                            hBox.setSpacing(10);
                            setGraphic(hBox);
    } 
}   }
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
            //participer.setDisable(true);
            rat.setDisable(true);
            rat.setPartialRating(true);
 
  
    }    
    private void loadDataFromDatabase() throws SQLException {
       List<Evenement> equipes = new ArrayList<>();
       List<Evaluation> evaluations = new ArrayList<>();
           EvenementServices se=new EvenementServices();

        equipes=   se.findAll();
       for(Evenement e : equipes)
            {
                data.add(e); 
               
            }
        list.setItems(data);
        
        evaluer.setDisable(true);
       
      
                }
      private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement eqp = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                
                    File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images\\"+eqp.getImage());
                    evaluer.setDisable(false);
            
        Image image = new Image(file.toURI().toString());
        lblid.setText(Integer.toString(eqp.getIdEvenement()));
         lblnom.setText(eqp.getNomEvenement());
         lbldate.setText(eqp.getDateEvenement().toString());
         jdesc.setText(eqp.getDescription());
         lbldest.setText(eqp.getDestination());
         lblduree.setText(eqp.getDuree());
         lbltype.setText(eqp.getType());
         lbletat.setText(eqp.getEtat());
         lblnombre.setText(Integer.toString(eqp.getNbrPlaces()));
         lblPrix.setText(Float.toString(eqp.getPrix()));
         if(evaluerDéja()==true) {
           
           evaluer.setDisable(true);
           rat.setDisable(true);
            

         }
         
         else {
             evaluer.setDisable(false);
             rat.setDisable(false);
         }
        // participer.setDisable(false); 
        
        Services.EvaluationService ess = new EvaluationService(); 
        Double moy = ess.moyByName(eqp.getIdEvenement());
        
        //rat.setRating(moy);
        
         
            
        Image image2 = new Image(file.toURI().toString());
            
          imggg.setImage(image2); 
           /* imgg.setFitHeight(400);
            imgg.setFitHeight(200);*/
            
            
            }
        });
    } 
      
      public boolean evaluerDéja() {
          boolean res = false ; 
          
          Services.EvaluationService sp = new EvaluationService(); 
          List<Evaluation> liste = sp.evalueDéja(Utilisateur.getIdd());
          
          for(Evaluation p : liste) {
              if((Integer.parseInt(lblid.getText()))==p.getIdEvenement()) {
                   res=true  ;
              } 
              
          }
         return res ;
      }
    
}

