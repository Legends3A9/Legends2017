/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evaluation;
import Entités.Evenement;
import Services.EvaluationService;
import Services.EvenementServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class EvaluerController implements Initializable {

    @FXML
    private AnchorPane tableEva;
    @FXML
    private TableColumn<?, ?> nomEve;
    @FXML
    private TableColumn<?, ?> dateEve;
    @FXML
    private TableColumn<?, ?> typeEvee;
    @FXML
    private TableColumn<?, ?> etatEvee;
    @FXML
    private TableColumn<?, ?> prixEvaa;
    @FXML
    private TableView<Evenement> tableRser;
    @FXML
    private TableColumn<?, ?> desEvaaa;
    private ObservableList<Evenement> data;
    @FXML
    private AnchorPane anchorRes;
    private JFXTextField nomRes;
    @FXML
    private Label lblRes;
    @FXML
    private Label lbldate;
    @FXML
    private Label lblType;
    @FXML
    private Label lbldest;
    @FXML
    private Label lbletat;
    @FXML
    private Label lblprix;
    @FXML
    private JFXTextField jNomEv;
    private JFXTextField jnomEvEv;
    @FXML
    private Button envoyerEv;
    @FXML
    private JFXTextField jnomEvaluateur;
    @FXML
    private JFXTextField jPrenomEva;
    @FXML
    private Rating jnoteEvaluation;
    @FXML
    private JFXTextField rrechAvance;
    @FXML
    private JFXButton rechBtn;
    
    @FXML
    private JFXTextField emailParticipant;
   
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setsCllTable();               
        data = FXCollections.observableArrayList();
        jNomEv.setDisable(true);
        jPrenomEva.setDisable(true);
        jnomEvaluateur.setDisable(true);
        jnoteEvaluation.setDisable(true);
        emailParticipant.setDisable(true);
        jnoteEvaluation.setDisable(true);
        jnoteEvaluation.setRating(2);
        
        //jnoteEvaluation.getItems().addAll("1","2","3","4","5");

       loadDataFromDatabase();
       setcellValue();
       search();
      // searchDest(); 
       //searchEtat();
     //  searchType();
       
       Services.EvenementServices es = new EvenementServices(); 
      
       List<Evenement> list = es.findAll();
       List<String> nom = new ArrayList<String>();
       List<String> type = new ArrayList<String>();
       List<String> dest = new ArrayList<String>();
       List<String> etat = new ArrayList<String>();
       etat.add("disponible");
       etat.add("reservé");


       for(Evenement e : list)
       {
           nom.add(e.getNomEvenement()) ;
           type.add(e.getType());
           dest.add(e.getDestination());
           
       }
      
        TextFields.bindAutoCompletion(rrechAvance, nom);
        //TextFields.bindAutoCompletion(rechType,type);
       // TextFields.bindAutoCompletion(rechDest,dest);
       // TextFields.bindAutoCompletion(rechEtaaa,etat);
     //  anchorRes.setDisable(true);
        jnomEvaluateur.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());
        jPrenomEva.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());


        
    }    
     public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        rrechAvance.setOnKeyReleased(e -> {
            rrechAvance.textProperty().addListener((observableValue, oldValue, newValue) -> {
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
            sorteddata.comparatorProperty().bind(tableRser.comparatorProperty());
            tableRser.setItems(sorteddata);
        });
        
    }
  
    private void loadDataFromDatabase() {
        Services.EvenementServices es = new EvenementServices(); 
      List <Evenement> evs = es.findAll();
      
      for(Evenement ev : evs )
      {
          data.add(ev);
      }
        tableRser.setItems(data);
    }
    
    public void setsCllTable() {
        nomEve.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        dateEve.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        typeEvee.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        desEvaaa.setCellValueFactory(new PropertyValueFactory<>("destination"));

        etatEvee.setCellValueFactory(new PropertyValueFactory<>("etat"));
                prixEvaa.setCellValueFactory(new PropertyValueFactory<>("prix"));
                

    }
    
    private void setcellValue() {
        tableRser.setOnMouseClicked((MouseEvent event) -> {
          Evenement pl = tableRser.getItems().get(tableRser.getSelectionModel().getSelectedIndex());
             /* nomRes.setText(pl.getNomEvenement());
            dateRes.setValue(pl.getDateEvenement().toLocalDate());
            destRes.setText((pl.getDestination()));
            typeRes.setText(pl.getType());
            etatRes.setText(pl.getEtat());
            prixRes.setText(Float.toString(pl.getPrix()));*/
           
           lblRes.setText(pl.getNomEvenement());
           lbldate.setText(pl.getDateEvenement().toString());
           lblType.setText(pl.getType());
           lbldest.setText(pl.getDestination());
           lbletat.setText(pl.getEtat());
           lblprix.setText(String.valueOf(pl.getPrix()));
           
           
            jNomEv.setText(pl.getNomEvenement());
            
            jPrenomEva.setDisable(false);
        jnomEvaluateur.setDisable(false);
        jnoteEvaluation.setDisable(false);
        emailParticipant.setDisable(false);
        jnoteEvaluation.setDisable(false);
            
            
            
            
            
            
            /*modifier.setDisable(false);
            supprimer.setDisable(false);
            anchor.setDisable(false);*/
        });
    }

    @FXML
    private void envoyerEvaluationAction(ActionEvent event) throws IOException {
         
        if(validationChampsAjout()==false) {
            
        }
        else {
        
        String nomEven = jNomEv.getText(); 
        String  nomEval = jnomEvaluateur.getText(); 
        String prenolEval = jPrenomEva.getText(); 
        String email = emailParticipant.getText();
        double note = jnoteEvaluation.getRating();
       // String noteEv = jnoteEvaluation.getValue();
        
        Evaluation e = new Evaluation(note,email, prenolEval, nomEval, nomEven);
        Services.EvaluationService ess = new EvaluationService() ; 
        ess.ajouterEvaluation(e);
        System.out.println("evaluation ajouté");
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merci ");
            alert.setContentText("Evaluation ajouté avec succées  ");
            alert.showAndWait();
          
            Parent pagereclamationfront = FXMLLoader.load(getClass().getResource("EvenMembre.fxml"));
        Scene reclamationfront_scene = new Scene(pagereclamationfront);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(reclamationfront_scene);
        app_stage.show();
        }
        
    }
    
    public EventHandler<KeyEvent> textArea_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            JFXTextArea txt_TextField = (JFXTextArea) e.getSource();                
            
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}

public EventHandler<KeyEvent> letter_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            JFXTextField txt_TextField = (JFXTextField) e.getSource();                
            
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}   









public EventHandler<KeyEvent> numeric_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if(e.getCharacter().matches("[0-9]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}  


private boolean validationChampsAjout() {
        boolean res = true ; 
       
        String msgTitle = "" ;
        String msgContent = "" ;
        //java.util.Date dateauj = new java.util.Date();
            
//            Date daaate = Date.from(date.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        
        if((jPrenomEva.getText().compareTo("")==0))
        {

             
            msgTitle="un champ prenom est vide";
             msgContent="veillez remplir le champ prenom  ";
             
            res = false ;
        } 
       if(jnomEvaluateur.getText().compareTo("")==0)
       {
             msgTitle="un champ nom est vide";
             msgContent="veillez remplir le champ nom";
             
            res = false ;
           
       }
       
       if(emailParticipant.getText().compareTo("")==0)
       {
            msgTitle ="un champ email est vide";
            msgContent = "veillez remplir le champ email  ";
             
            res = false ;
           
       }/*else if(  emailParticipant.getText().contains("@") && emailParticipant.getText().contains(".")){
           
           msgTitle ="un champ email est invalide";
            msgContent = "like russia@russia.com  ";
             
            res = false ;
           
       }*/
       
       

        
        
        
        
        
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle(msgTitle);
             alert.setContentText(msgContent);
             alert.showAndWait();
        return res ; 
    }

    
    
    
    
    
}
