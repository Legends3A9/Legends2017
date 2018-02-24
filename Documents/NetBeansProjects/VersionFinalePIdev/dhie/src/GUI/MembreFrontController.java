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

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class MembreFrontController implements Initializable {

    ObservableList<Evenement> data ;
    @FXML
    private JFXListView<Evenement> list;
    private ImageView cont;
    private JFXTextField eq;
    private JFXTextField ct;
    //ObservableList<PieChart,Data> pi=FXCollections.observableArrayList();
    @FXML
    private ImageView imgg;
    @FXML
    private Rating rat;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblDest;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblDure;
    @FXML
    private Label lblType;
    @FXML
    private Label lblEtat;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPrix;
    @FXML
    private Button participer;
    @FXML
    private Label lblid;
    @FXML
    private Label lblEr;
    @FXML
    private JFXTextField jrech;
    @FXML
    private Button rechercher;
    @FXML
    private Button publier;
    @FXML
    private Button retour;
    @FXML
    private Button evaluer;
    @FXML
    private Button evtQue;

    @FXML
    private void participerAction(ActionEvent event) {
       
           
           
   
        
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
alerte.setTitle("Confirmation De participation");

alerte.setContentText("etes vous sur de vouloir participer a cet evenement");

Optional<ButtonType> result = alerte.showAndWait();
if (result.get() == ButtonType.OK){  
        if(lblEtat.getText().toLowerCase().equals("réservé")) {
            participer.setDisable(true);
            lblEr.setText("Désolé nombre de place atteint pour cet Evenement ");
            lblEr.setStyle("-fx-background-color:red;");

        } else {
            
            int nbrPlace = Integer.parseInt(lblNombre.getText());
           // int idUser = Utilisateur.getInstance().getIdUser(); 
            int idEv = Integer.parseInt(lblid.getText()) ;
            if(nbrPlace>1) {
                nbrPlace=nbrPlace-1 ; 
                
                Services.EvenementServices es = new EvenementServices(); 
                es.decrementation_nbrPlaces(idEv , nbrPlace);
                Services.ParticipationService ps = new ParticipationService(); 
                Participation p = new Participation(Utilisateur.getIdd(),idEv);
                ps.ajouterParticipation(p);
                lblEr.setText("votre participation a été enregistrer avec succées ");
                lblEr.setStyle("-fx-background-color:green;");
               
                list.refresh();
            }
            else if (nbrPlace==1) {
                Services.EvenementServices es = new EvenementServices(); 
                es.decrEtChangementDetat(idEv);
                Services.ParticipationService ps = new ParticipationService(); 
                Participation p = new Participation(Utilisateur.getIdd(), idEv);
                ps.ajouterParticipation(p);
                lblEr.setText("votre participation a été enregistrer avec succées ");
                lblEr.setStyle("-fx-background-color:green;");
            

                
                
            }}}}

    @FXML
    private void rechercherAction(ActionEvent event) {
        
 
    
   
        
    }

    @FXML
    private void key(KeyEvent event) {
      
           }

    @FXML
    private void publierAction(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("Ajout.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setTitle("publier un événement");
                
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void retourAction(ActionEvent event) {
    }

    @FXML
    private void evaluerAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("frontEvaluer.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setTitle("publier un événement");
                
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void evtQueAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("EvtQueJeParticipe.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setTitle("publier un événement");
                
                stage.initModality(Modality.APPLICATION_MODAL);

                stage.setScene(scene);
                stage.show();
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
                            //rss.getStylesheets().add("GUI/fxml1.css");
                           
                        if (item != null) {
                            Text t =new Text(item.getNomEvenement().toString());
                            Text t2 =new Text(item.getDateEvenement().toString());
                            Text t3 =new Text(item.getDestination());
                            Text t4 = new Text(item.getEtat());
                          /*  if(item.getEtat().toLowerCase().equals("réservé")){
                            t4.setStyle("-fx-text-fill: red;");
                             }*/
                            t.setStyle("-fx-font-size: 25 arial;");
                            t2.setStyle("-fx-font-size: 20 arial;");
                            t3.setStyle("-fx-font-size: 20 arial;");
                            t4.setStyle("-fx-font-size: 20 arial;");
                                  
                                
                   
                    
                    
                            
                       //   Button facebook = new Button("Partager");
                           
                            
                            double moy = ess.moyByName(item.getIdEvenement()); 
                            rss.setRating(moy);
                            
                            VBox vBox = new VBox(rss,t,t2,t3,t4);
                             File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images\\"+item.getImage());
            
        Image image = new Image(file.toURI().toString());
        ImageView img = new ImageView(image); 
        img.setFitHeight(120);
        img.setFitWidth(190);
                            
                            HBox hBox = new HBox(img, vBox);
                           
                        /* facebook.setOnAction(e->{
                                String accesToken = "EAACEdEose0cBAFsAbMfb6eFz3ZCPJqOFTOmuRJ1Q9PWqwmV3nNKuyzeWwPd3nWKXeL6ZB2DjF8Fv5yHOsrNMw72IhFYHZC4ZBXqXP64dD6zQDbRJ85LZCsZCS5noBcSVmxZCyKYNteubCuZB4TjiRGcbroFUeKDZAjNKsokWkNe65EYKaeBlyQvwXS2p3PFXoKWcZD" ;
                                 FacebookClient fb = new DefaultFacebookClient(accesToken);
                    
                               
                              FacebookType response =  fb.publish("fb.com/",FacebookType.class,Parameter.with(accesToken,ListCell.class));
                                System.out.println(response);
                    
                           
                            }); */
                            
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
            search();
                 
            participer.setDisable(true);
  
            
            
            
            
            
            
            
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
         lblDate.setText(eqp.getDateEvenement().toString());
         lblDesc.setText(eqp.getDescription());
         lblDest.setText(eqp.getDestination());
         lblDure.setText(eqp.getDuree());
         lblType.setText(eqp.getType());
         
         
         lblEtat.setText(eqp.getEtat());
                  
         lblNombre.setText(Integer.toString(eqp.getNbrPlaces()));
         lblPrix.setText(Float.toString(eqp.getPrix()));
         
         if(participeDéja()==true) {
           
           participer.setDisable(true);


         }
         
         else {
             participer.setDisable(false);
         }
        
        Services.EvaluationService ess = new EvaluationService(); 
        Double moy = ess.moyByName(eqp.getIdEvenement());
        
         rat.setRating(moy);
         rat.setPartialRating(true);
         
            
        Image image2 = new Image(file.toURI().toString());
            
            imgg.setImage(image2); 
            imgg.setFitHeight(400);
            imgg.setFitHeight(200);
            
            
            }
        });
    } 
    
      
      public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        jrech.setOnKeyReleased(e -> {
            jrech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evenement>) evenement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (evenement.getNomEvenement().contains(newValue) || (evenement.getNomEvenement().toLowerCase().contains(newValue)))
                            {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
           // sorteddata.comparatorProperty().bind(list.compa);
            list.setItems(sorteddata);
        });
    }
      
      
      public boolean participeDéja() {
          boolean res = false ; 
          
          Services.ParticipationService sp = new ParticipationService(); 
          List<Participation> liste = sp.participeDéja(Utilisateur.getIdd());
          
          for(Participation p : liste) {
              if((Integer.parseInt(lblid.getText()))==p.getIdEvenement()) {
                   res=true  ;
              } 
              
          }
         return res ;
      }
      
     
              
      
      
   
        
     }
