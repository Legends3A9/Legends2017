/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evenement;
import Entités.Participation;
import Entités.User;
import Services.EvenementServices;
import Services.ParticipationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class EvenMembreController implements Initializable {

    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> ColDesc;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colDure;
    @FXML
    private TableColumn<?, ?> colDest;
    @FXML
    private TableColumn<?, ?> colEtat;
    @FXML
    private TableColumn<?, ?> colnbr;
    @FXML
    private TableColumn<?, ?> colimage;
    @FXML
    private TableColumn<?, ?> colPrix;
    @FXML
    private TableColumn<?, ?> colUser;
    @FXML
    private TableView<Evenement> tableMem;
    
        private ObservableList<Evenement> data;
    @FXML
    private TableColumn<?, ?> colNom;
   
   
    @FXML
    private JFXTextField rechhhh5;
    
    @FXML
    private AnchorPane anchor1;
    @FXML
    private ImageView imggg;
    @FXML
    private Button participer;
    @FXML
    private ImageView imggview;
    @FXML
    private Label lbluser;
    @FXML
    private Label lbleven;
    @FXML
    private Label lbldate;
    @FXML
    private Label lbldesc;
    @FXML
    private Label lbltype;
    @FXML
    private Label lblprix;
    @FXML
    private Label lbllnom;
    @FXML
    private Label lblduree;
    @FXML
    private Label lbldest;
    @FXML
    private Label lbletat;
    @FXML
    private Label lblnombre;
    @FXML
    private JFXTextField jmgsgs;
    @FXML
    private Button evaluer;
  
    @FXML
    private Button publier;
    
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 setsCllTable();               
        data = FXCollections.observableArrayList();

       loadDataFromDatabase();
       setcellValue();
       
        if(lbletat.getText().equals("Reservé")) 
            participer.setDisable(true);
       
      
       //anchor1.setDisable(true);
       search();
      
       
       Services.EvenementServices es = new EvenementServices(); 

       List<Evenement> list = es.findAll();

       List<String> nom = new ArrayList<String>();
       List<String> type = new ArrayList<String>();
       List<String> dest = new ArrayList<String>();
       List<String> etat = new ArrayList<String>();
       etat.add("disponible");
       etat.add("reservé");
       participer.setDisable(true);


       for(Evenement e : list)
       {
           nom.add(e.getNomEvenement()) ;
           type.add(e.getType());
           dest.add(e.getDestination());
           
       }
      
        TextFields.bindAutoCompletion(rechhhh5, nom);
       
       
    }    
    
    
    
    
    
    
   
    
    
    
    private void loadDataFromDatabase() {
      
      Services.EvenementServices es = new EvenementServices(); 
      List <Evenement> evs = es.findAll();
      
      for(Evenement ev : evs )
      {
          data.add(ev);
      }
        tableMem.setItems(data);
    }
    
    public void setsCllTable() {
        colid.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        colDest.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDure.setCellValueFactory(new PropertyValueFactory<>("duree"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colnbr.setCellValueFactory(new PropertyValueFactory<>("nombrePlace"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
    }
    
    
    private void setcellValue() {
        tableMem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement pl = tableMem.getItems().get(tableMem.getSelectionModel().getSelectedIndex());
                lbleven.setText(Integer.toString(pl.getIdEvenement()));
                lbllnom.setText(pl.getNomEvenement());
                lbldate.setText(pl.getDateEvenement().toLocalDate().toString());
                lbldest.setText((pl.getDestination()));
                lbltype.setText(pl.getType());
                lbletat.setText(pl.getEtat());
                lblnombre.setText(Integer.toString(pl.getNbrPlaces()));
                lblprix.setText(Float.toString(pl.getPrix()));
                lbldesc.setText(pl.getDescription()) ; 
                lblduree.setText(pl.getDuree());
                lbluser.setText(Integer.toString(pl.getId_user()));
                jmgsgs.setText(pl.getImage());
                image ();
                participer.setDisable(false);
               
       
            
                
                
                
                /*modifier.setDisable(false);
                supprimer.setDisable(false);
                anchor.setDisable(false);*/
                
                
                
            }
        });
    }
    
     public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        rechhhh5.setOnKeyReleased(e -> {
            rechhhh5.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evenement>) evenement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((evenement.getNomEvenement().contains(newValue)) || (evenement.getNomEvenement().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableMem.comparatorProperty());
            tableMem.setItems(sorteddata);
        });
    }

    @FXML
    private void participerAction(ActionEvent event) throws IOException {
Alert alerte = new Alert(AlertType.CONFIRMATION);
alerte.setTitle("Confirmation De participation");

alerte.setContentText("etes vous sur de vouloir participer a cet evenement");

Optional<ButtonType> result = alerte.showAndWait();
if (result.get() == ButtonType.OK){  
        if(lbletat.getText().equals("Reservé")) {
            participer.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Merci ");
            alert.setContentText("Nombre de place atteint pour cet Evenement ");
            alert.showAndWait();
        } else {
            
            int nbrPlace = Integer.parseInt(lblnombre.getText());
            int idUser = User.getInstance().getIdUser(); 
            int idEv = Integer.parseInt(lbleven.getText()) ;
            if(nbrPlace>1) {
                nbrPlace=nbrPlace-1 ; 
                
                Services.EvenementServices es = new EvenementServices(); 
                es.decrementation_nbrPlaces(idEv , nbrPlace);
                Services.ParticipationService ps = new ParticipationService(); 
                Participation p = new Participation(idUser, idEv);
                ps.ajouterParticipation(p);
                
               
                tableMem.refresh();
            }
            else if (nbrPlace==1) {
                Services.EvenementServices es = new EvenementServices(); 
                es.decrEtChangementDetat(idEv);
                Services.ParticipationService ps = new ParticipationService(); 
                Participation p = new Participation(idUser, idEv);
                ps.ajouterParticipation(p);
                
                
            }
            
        }}
           

      
    }

    @FXML
    private void evaluerAction(ActionEvent event) throws IOException {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("Evaluer.fxml"));
        Parent root = loader.load();
        Scene scene = lbldesc.getScene();
        scene.setRoot(root);
    }
    
     private void image () {
        File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images",jmgsgs.getText());
        
        Image image = new Image(file.toURI().toString());
        imggview.setImage(image);
         
        
    }

    @FXML
    private void publierEvtAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("Ajout.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setScene(scene);
                stage.show();
    }
     
     
    
    
    
    
}
