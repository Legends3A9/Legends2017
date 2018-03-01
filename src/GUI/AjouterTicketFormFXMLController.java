/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import util.DB;
import entities.Stade;
import entities.Ticket;
import java.io.IOException;
import service.TicketService;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.StadeService;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class AjouterTicketFormFXMLController implements Initializable {
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<String> stade;
    
    @FXML
    private ComboBox<String> place;
    @FXML
    private TextField prix;
    @FXML
    private TextField date_match;
    @FXML
    private Button ajouter;
    @FXML
    private ComboBox<String> rencontre;
static DB ds =DB.getInstance(); 
    @FXML
    private Button retour;
    @FXML
    private ComboBox<String> equipe2;
    @FXML
    private TextField heure;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        place.getItems().addAll("Virage","Pelouse","Enseinte");
        
        load();

stade.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
String sql="select * from matchs where stade= ?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
            ste.setString(1,t1) ;  
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            
                                     
                                    String match1 = result.getString("equipe1");
                                    String match2 = result.getString("equipe2");
                                    String match3 =match1.concat(" vs ");
                                    String match = match3.concat(match2);
                
                boolean test=false;
                if(((stade.getValue()).equals(result.getString("stade")))) {
                    test=true;
                    System.out.println(test);
                }
                if (test==true) {
                    rencontre.getItems().clear();                    
                    rencontre.getItems().addAll(match1);
                    test=false;
                
                   
                 }
                
                else if(test==false){
                    rencontre.getItems().clear(); 
                    System.out.println(test);
                    
                }
               
               
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        }});
    rencontre.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
String sql="select * from matchs where (equipe1= ? )";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
            ste.setString(1,t1) ;  
            System.out.println("bbbb");
            ResultSet resulta =ste.executeQuery() ; 
            
            while (resulta.next()){
            
                                     System.out.println("bbbb");
                                     String equipe2A=resulta.getString("equipe2");
                                     String heureA=resulta.getString("heure");
                                    String datemacth= resulta.getDate("date_match").toString();
                                    System.out.println(datemacth);
                                    equipe2.getItems().addAll(equipe2A);
                                    
                                    
                 
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        }});
    equipe2.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
String sql="select * from matchs where (equipe1= ? AND equipe2= ? )";
        try { String equipe1A=rencontre.valueProperty().getValue();
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
             ste.setString(1,equipe1A) ;
            ste.setString(2,t1) ;  
           
            ResultSet resultas =ste.executeQuery() ; 
            System.out.println(t1);
            while (resultas.next()){
            
                                     System.out.println("bbbb");
                                     String equipe2A=resultas.getString("equipe2");
                                     String heureA=resultas.getString("heure");
                                    String datemacth= resultas.getDate("date_match").toString();
                                    System.out.println(datemacth);
                                    //equipe2.getItems().addAll(equipe2A);
                                    date_match.setText(datemacth);
                                    heure.setText(heureA);
                                    
                 
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        }}); 
    place.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
            if (place.getValue()=="Virage") {
                
              prix.setText("100");
            }
            else if (place.getValue()=="Pelouse") {
                
              prix.setText("150");
            }
            else if (place.getValue()=="Enseinte") {
                
              prix.setText("400");
            }
    }    });
    }
    @FXML
    private void ActionAjouterTicket(ActionEvent event)throws IOException {
        
        String nombreS =nombre.getText();
        String stadeS=stade.getValue();
        String matchSS=rencontre.getValue();
        String matchSSS=equipe2.getValue();
        String date_matchS = date_match.getText();
        String heureS=heure.getText();
        String placeS= place.getValue();
        
        String prixS= prix.getText();
        
        java.sql.Date datemat = java.sql.Date.valueOf(date_matchS);
        Ticket t = new Ticket(Integer.parseInt(nombreS),stadeS,matchSS,matchSSS, datemat,heureS, placeS,Float.parseFloat(prixS));
        service.TicketService tt = new TicketService(); 
        tt.insererTicket(t);
        
        
    }
    
    public void load(){
        try {
            StadeService service=new StadeService();
            List<Stade> rs = service.selectStade();
            for(Stade s : rs)
            {
                
                
                
            String nom_stadeE = s.getNom_stade();
            stade.getItems().addAll(nom_stadeE);
              
               
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
    }

    private void StadeAction(ActionEvent event) {
        
        
        String req ; 
        String stadeS=stade.getValue();
        req = "SELECT * matchs where stade =?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ste.setString(1,stadeS) ;  
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            
                                     
                                    String match = result.getString("equipe1");
                rencontre.getItems().addAll(match);
            
            }
            
        } catch (SQLException ex) {
            
        }
        
        
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
    

