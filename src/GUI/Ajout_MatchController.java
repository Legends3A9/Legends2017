/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entités.*;
import Services.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.print.DocFlavor;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author ali hamouda
 */
public class Ajout_MatchController implements Initializable {

   
    @FXML
    private JFXTimePicker heure;
    @FXML
    private AnchorPane poule;
   
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> equipe1;
    @FXML
    private ComboBox<String> equipe2;
    @FXML
    private ComboBox<String> poule1;
    @FXML
    private ComboBox<String> stade;
    @FXML
    private ComboBox<String> type_phase;
    @FXML
    private JFXButton Ajout_match;
    @FXML
    private AnchorPane a;
    
  
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        type_phase.getItems().addAll("poule","final");
        poule.setDisable(true);
        
        date.setValue(LocalDate.now());
        heure.setValue(LocalTime.now());
        StadeService ss = new StadeService();
        List<Stade> sta = new ArrayList<>();
        
        sta= ss.getAll();
        for(Stade s: sta){
            
            stade.getItems().addAll(s.getNom());     
        }
        
      
        
       
       
        
        
        type_phase.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            poule1.getItems().clear();
            
            if(t1.equals("poule")){
                
                poule.setDisable(false);
                equipe2.setDisable(false);
                poule1.getItems().addAll("A","B","C","D","E","F","G","H");
                
                poule1.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                equipe2.getItems().clear();
                equipe1.getItems().clear();
                 EquipeService es = new EquipeService();
        List<Equipe> eqs = new ArrayList<>();
        try {
            eqs= es.equipeParPoule(t1);
        } catch (SQLException ex) {
            Logger.getLogger(Ajout_MatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Equipe e : eqs){
           equipe1.getItems().addAll(e.getNom_equipe());
           equipe2.getItems().addAll(e.getNom_equipe());
        }
            
            }});
            }
            else if(t1.equals("final")) {
                
                poule.setDisable(false);
                equipe2.setDisable(true);
                poule1.getItems().addAll("1/8","1/4","1/2","3eme place","final");
                poule1.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                    if(t1.equals("1/8")){
                        equipe1.getItems().addAll("1er groupe A","1er groupe B","1er groupe C","1er groupe D","1er groupe E","1er groupe F","1er groupe G","1er groupe H");
                        equipe2.getItems().addAll("2eme groupe A","2eme groupe B","2eme groupe C","2eme groupe D","2eme groupe E","2eme groupe F","2eme groupe G","2eme groupe ");
                        equipe1.valueProperty().addListener(new ChangeListener<String>() {
                        @Override public void changed(ObservableValue ov, String t, String t1) {
                        switch(t1){
                            case "1er groupe A":equipe2.setValue("2eme groupe B");
                            break;
                            case "1er groupe B":equipe2.setValue("2eme groupe A");
                            break;
                            case "1er groupe C":equipe2.setValue("2eme groupe D");
                            break;
                            case "1er groupe D":equipe2.setValue("2eme groupe C");
                            break;
                            case "1er groupe E":equipe2.setValue("2eme groupe F");
                            break;
                            case "1er groupe F":equipe2.setValue("2eme groupe E");
                            break;
                            case "1er groupe G":equipe2.setValue("2eme groupe H");
                            break;
                            case "1er groupe H":equipe2.setValue("2eme groupe G");
                            break;
                            
                        }
                        
                        }});
                        
                        
                        
                    }
                    if(t1.equals("1/4")){
                        equipe1.getItems().addAll("Vainqueur H1","Vainqueur H3","Vainqueur H5","Vainqueur H7");
                        equipe2.getItems().addAll("Vainqueur H2","Vainqueur H4","Vainqueur H6","Vainqueur H8");
                        equipe1.valueProperty().addListener(new ChangeListener<String>() {
                        @Override public void changed(ObservableValue ov, String t, String t1) {
                        switch(t1){
                            case "Vainqueur H1":equipe2.setValue("Vainqueur H2");
                            break;
                             case "Vainqueur H3":equipe2.setValue("Vainqueur H4");
                            break;
                             case "Vainqueur H5":equipe2.setValue("Vainqueur H6");
                            break;
                             case "Vainqueur H7":equipe2.setValue("Vainqueur H8");
                            break;
                            
                        }
                        
                        }});
                        
                        
                        
                    }
                    if(t1.equals("1/2")){
                        equipe1.getItems().addAll("Vainqueur Q1","Vainqueur H3");
                        equipe2.getItems().addAll("Vainqueur Q2","Vainqueur Q4");
                        equipe1.valueProperty().addListener(new ChangeListener<String>() {
                        @Override public void changed(ObservableValue ov, String t, String t1) {
                        switch(t1){
                            case "Vainqueur Q1":equipe2.setValue("Vainqueur Q2");
                            break;
                             case "Vainqueur Q3":equipe2.setValue("Vainqueur Q4");
                            break;
                             
                            
                        }
                        
                        }});
                        
                        
                        
                    }
                    if(t1.equals("final")){
                        equipe1.getItems().addAll("Vainqueur D1");
                        equipe2.getItems().addAll("Vainqueur D2");
                        equipe1.valueProperty().addListener(new ChangeListener<String>() {
                        @Override public void changed(ObservableValue ov, String t, String t1) {
                        switch(t1){
                            case "Vainqueur D1":equipe2.setValue("Vainqueur D2");
                            break;
                             
                            
                        }
                        
                        }});
                        
                        
                        
                    }
                    
                    if(t1.equals("3eme place")){
                        equipe1.getItems().addAll("Perdant D1");
                        equipe2.getItems().addAll("Perdant D2");
                        equipe1.valueProperty().addListener(new ChangeListener<String>() {
                        @Override public void changed(ObservableValue ov, String t, String t1) {
                        switch(t1){
                            case "Perdant D1":equipe2.setValue("Perdant D2");
                            break;
                             
                            
                        }
                        
                        }});
                        
                        
                        
                    }
                
                }});
                
            }
          
             
        }});

        // TODO
    }    

    @FXML
     private void Ajout_match(ActionEvent event) throws IOException {
         String equipe1S = equipe1.getValue();
         String equipe2S = equipe2.getValue();
         LocalDate dateS = date.getValue();
         LocalTime heureS = heure.getValue();
         String stadeS = stade.getValue();
         String type_phaseS = type_phase.getValue();
         String type_matchS = poule1.getValue();
         
         
           java.sql.Date date_mat = java.sql.Date.valueOf(dateS);
           java.sql.Time time_mat = java.sql.Time.valueOf(heureS);
     
         
         Match m = new Match(equipe1S, equipe2S, date_mat, time_mat, stadeS, type_phaseS, type_matchS);
         
         Services.MatchService sm = new MatchService();
         List <Match> mat =sm.selectMatch();
         
         
         
         boolean test=false;
         boolean test1=false;
         boolean test2=false;
         boolean test3=false;
         boolean test4=false;
         boolean test5=false;
         
         
        
        
         
         for (Match ma : mat){
             if(ma.getEquipe1().equals(equipe1S) && ma.getEquipe2().equals(equipe2S))
             {
                 test=true;
             }
             if ((ma.getEquipe1().equals(equipe2S) && ma.getEquipe2().equals(equipe1S)))
             {
                 test=true;
             }
             if (equipe2S.equals(equipe1S))
             {
                 test1=true;
             }
             
             if((ma.getDate_match().compareTo(java.sql.Date.valueOf(dateS))==0) && (ma.getHeure().compareTo(java.sql.Time.valueOf(heureS))==0) && (ma.getStade().equals(stadeS)))
             {
                 
                    test2=true;
                    
                
             } 
             
         }
        //String st= date.getValue().toString(); 
        
        
            if ((poule1.getValue()==null) || (equipe1.getValue()==null) || (equipe2.getValue()==null) || (stade.getValue()==null)){
                test3=true;
                System.out.println(date.getValue());
         
            }
            
            if(date.getValue().isBefore(LocalDate.now())){
                test4=true;
            }
            
            if((date.getValue().compareTo(LocalDate.now())==0) && (heure.getValue().isBefore(LocalTime.now()))){
                test5=true;
            }
            
            
            if (test3==true){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("ERREUR");
             alert.setHeaderText(null);
             alert.setContentText("Merci de remplir tous les champs");
             alert.show();
            
            }
           
            else if (test==true){
             Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
             alert2.setTitle("ERREUR");
             alert2.setHeaderText(null);
             alert2.setContentText("ce match existe deja");
             alert2.show();
         }
                else if (test1==true){
             Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
             alert1.setTitle("ERREUR");
             alert1.setHeaderText(null);
             alert1.setContentText("vous avez choisi la même equipe");
             alert1.show();
         }
                else if (test5==true){
             Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
             alert5.setTitle("ERREUR");
             alert5.setHeaderText(null);
             alert5.setContentText("merci de choisir une heure valide");
             alert5.show();
         }
                else if (test4==true){
             Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
             alert4.setTitle("ERREUR");
             alert4.setHeaderText(null);
             alert4.setContentText("merci de choisir une date valide");
             alert4.show();
         }
                
             
                else if (test2==true){
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
             alert3.setTitle("ERREUR");
             alert3.setHeaderText(null);
             alert3.setContentText("Dans cette date existe un match dans le meme stade et dans la meme heure");
             alert3.show();
         }
         
         else if (test==false && test1== false && test2== false && test3==false){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout");
             alert.setHeaderText(null);
             alert.setContentText("Vous Voulez Ajouter le match?:");
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK){
             sm.insererMatch(m);
             
             }
         }
         
    
}
}

