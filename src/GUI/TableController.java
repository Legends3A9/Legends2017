/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import utils.EnvoyerEmail;
import Entites.*;
import Service.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ali hamouda
 */
public class TableController implements Initializable {

    @FXML
    private TableView<Match> table;
    
    @FXML
    private TableColumn<?, ?> equipe1;
    @FXML
    private TableColumn<?, ?> equipe2;
    @FXML
    private TableColumn<?, ?> date_match;
    @FXML
    private TableColumn<?, ?> heure_match;
    @FXML
    private TableColumn<?, ?> stade;
 
    @FXML
    private ImageView drapeau1;
    @FXML
    private ImageView drapau2;
   
    @FXML
    private Label equipe11;
    @FXML
    private Label equipe21;
    @FXML
    private JFXTimePicker heure1;
   
    
    
    private ObservableList<Match> data;
    @FXML
    private JFXButton envoyer;
    @FXML
    private JFXComboBox<String> phase;
    @FXML
    private JFXComboBox<String> match;
    @FXML
    private JFXDatePicker date1;
    @FXML
    private JFXComboBox<String> stade1;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton supprimer;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Label id1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        data = FXCollections.observableArrayList();
        id.setVisible(false);
        id1.setVisible(false);
        phase.getItems().addAll("poule","final");
        phase.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {

            if(t1.equals("poule")){
                match.getItems().clear();
                match.getItems().addAll("A","B","C","D","E","F","G","H");
            }
            else if(t1.equals("final")){
                match.getItems().clear();
                match.getItems().addAll("1/8","1/4","1/2","3eme place","final");
            }
            }});
        match.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            try {
                data = FXCollections.observableArrayList();
                loadDataFromDatabase1(t1);
                setsCllTable();
                setcellValue();
                LesStades();
               
                
                System.out.println(data);
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }});
        
    }


    
    
    private void loadDataFromDatabase1(String match) throws SQLException {
      
            List<Match> matchs = new ArrayList<>();
            MatchService se=new MatchService();

        matchs =   se.equipeParTypeMatch(match);
       for(Match m : matchs)
            {
                data.add(m);
                System.out.println(m);
               
            }
        table.setItems(data);
    
    }

    
    
    
    
    public void setsCllTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        equipe1.setCellValueFactory(new PropertyValueFactory<>("equipe1"));
        equipe2.setCellValueFactory(new PropertyValueFactory<>("equipe2"));
        date_match.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        heure_match.setCellValueFactory(new PropertyValueFactory<>("heure"));
        stade.setCellValueFactory(new PropertyValueFactory<>("stade"));
        
        
    }
    
    
    
    private void setcellValue() {
        table.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Match pl = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                id1.setText(Integer.toString(pl.getId()));
                System.out.println(id1);
                equipe11.setText(pl.getEquipe1());
                date1.setValue(pl.getDate_match().toLocalDate());
                heure1.setValue(pl.getHeure().toLocalTime());
                equipe21.setText(pl.getEquipe2());
                stade1.setValue(pl.getStade());
                Image();
   
                
                
                
            }
        });
    }
    
    private void Image(){
         
       
           
        File file = new File("G:\\"+equipe11.getText().toLowerCase()+".png");
        File file1 = new File("G:\\"+equipe21.getText().toLowerCase()+".png");
        Image image = new Image(file.toURI().toString());
        Image image1 = new Image(file1.toURI().toString());
        drapeau1.setImage(image);
        drapau2.setImage(image1);
        

    
    }
    
    private void LesStades(){
        stade1.getItems().clear();
        StadeService ss = new StadeService();
        List<Stade> sta = new ArrayList<>();
        sta= ss.getAll();
        for (Stade s : sta){
           stade1.getItems().addAll(s.getNom());
           
        }
    }

   

    private void ActionSupprimrMatch(ActionEvent event) throws SQLException {
        
        
        MatchService se=new MatchService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprission");
        alert.setHeaderText("Supprission");
        alert.setContentText("Vous Voulez SUPPRIMER le match?:");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int id= Integer.valueOf(id1.getText());
            Service.MatchService ser = new MatchService();
            ser.DeletAebitreByID(id);  
            id1.setText("");
            equipe11.setText("");
            equipe21.setText("");
            date1.setValue(null);
            heure1.setValue(null);
            stade1.setValue(null);
            data.clear();
            loadDataFromDatabase1(match.getValue());
        
    }
    }

    private void ActionModifirMatch(ActionEvent event) throws SQLException {
        int id_m = Integer.valueOf(id1.getText());
        String equipe1_m = equipe11.getText();
        String equipe2_m = equipe21.getText();
        LocalTime heure_m = heure1.getValue();
        String stade_m= stade1.getValue(); 
        String phase_m = phase.getValue(); 
        String match_m  = match.getValue(); 
        LocalDate date_m = date1.getValue();
        java.sql.Date date_mat = java.sql.Date.valueOf(date_m);
        java.sql.Time time_mat = java.sql.Time.valueOf(heure_m);
        Match m = new Match(id_m, equipe1_m, equipe2_m, date_mat, time_mat, stade_m, phase_m, match_m);
           MatchService sm = new MatchService();
          
         List <Match> mat =sm.selectMatch();
         
         boolean test2=false;
         boolean test3=false;
         
         
        
        
         
         for (Match ma : mat){
             
             if((ma.getDate_match().compareTo(java.sql.Date.valueOf(date_m))==0) && (ma.getHeure().equals(heure_m)) && (ma.getStade().equals(stade_m)))
             {
                 
                    test2=true;
                    
                
             } 
             
         }
        
        
            if ((date1.getValue()==null) || (heure1.getValue()==null) || (stade1.getValue()==null)){
                test3=true;
            }
            
            if (test3==true){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("ERREUR");
             alert.setHeaderText(null);
             alert.setContentText("Merci de remplir tous les champs");
             alert.show();
            
            }
           
           
                else if (test2==true){
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
             alert3.setTitle("ERREUR");
             alert3.setHeaderText(null);
             alert3.setContentText("Dans cette date existe un match dans le meme stade et dans la meme heure");
             alert3.show();
         }
         
         else if (test2== false && test3==false){
            sm.updateMatch(m);
        data.clear();
        loadDataFromDatabase1(match.getValue());
         }

        
        
    }

   
    @FXML
    private void supprimerAction(ActionEvent event) throws SQLException {
         MatchService se=new MatchService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprission");
        alert.setHeaderText("Supprission");
        alert.setContentText("Vous Voulez SUPPRIMER le match?:");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int id= Integer.valueOf(id1.getText());
            Service.MatchService ser = new MatchService();
            ser.DeletAebitreByID(id);  
            id1.setText("");
            equipe11.setText("");
            equipe21.setText("");
            date1.setValue(LocalDate.now());
            heure1.setValue(LocalTime.now());
            stade1.setValue(null);
            data.clear();
            loadDataFromDatabase1(match.getValue());
        
    }
        
    }

    @FXML
    private void modifierAction(ActionEvent event) throws SQLException {
        int id_m = Integer.valueOf(id1.getText());
        String equipe1_m = equipe11.getText();
        String equipe2_m = equipe21.getText();
        LocalTime heure_m = heure1.getValue();
        String stade_m= stade1.getValue(); 
        String phase_m = phase.getValue(); 
        String match_m  = match.getValue(); 
        LocalDate date_m = date1.getValue();
        java.sql.Date date_mat = java.sql.Date.valueOf(date_m);
        java.sql.Time time_mat = java.sql.Time.valueOf(heure_m);
        Match m = new Match(id_m, equipe1_m, equipe2_m, date_mat, time_mat, stade_m, phase_m, match_m);
           MatchService sm = new MatchService();
          
         List <Match> mat =sm.selectMatch();
         
         boolean test2=false;
         boolean test3=false;
         boolean test4=false;
         boolean test5=false;
         
         
         
        
        
         
         for (Match ma : mat){
             
             if((ma.getDate_match().compareTo(java.sql.Date.valueOf(date_m))==0) && (ma.getHeure().equals(heure_m)) && (ma.getStade().equals(stade_m)))
             {
                 
                    test2=true;
                    
                
             } 
             
         }
        
        
            if ( (stade1.getValue()==null)){
                test3=true;
            }
            if(date1.getValue().isBefore(LocalDate.now())){
                test4=true;
            }
            
            if((date1.getValue().compareTo(LocalDate.now())==0) && (heure1.getValue().isBefore(LocalTime.now()))){
                test5=true;
            }
            
            if (test3==true){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("ERREUR");
             alert.setHeaderText(null);
             alert.setContentText("Merci de remplir tous les champs");
             alert.show();
            
            }
           
           
                else if (test2==true){
             Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
             alert3.setTitle("ERREUR");
             alert3.setHeaderText(null);
             alert3.setContentText("Dans cette date existe un match dans le meme stade et dans la meme heure");
             alert3.show();
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
         
         else if (test2== false && test3==false && test4==false && test5==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Modification");
             alert.setHeaderText(null);
             alert.setContentText("Vous Voulez SUPPRIMER le match?:");
             Optional<ButtonType> result = alert.showAndWait();
             if (result.get() == ButtonType.OK){
                sm.updateMatch(m);
                data.clear();
                loadDataFromDatabase1(match.getValue());
             }
         }

    }

    @FXML
    private void envoyerAction(ActionEvent event) {
        EnvoyerEmail en = new EnvoyerEmail();
        UserService es = new UserService();
        List<Utilisateur> l =new ArrayList<>();
        l= es.getAll();
        System.out.println(l);
        
        en.EnvoyerMail(l,equipe11.getText(),equipe21.getText(), stade1.getValue().toString(),date1.getValue().toString() , heure1.getValue().toString());
                
    }
      
}
