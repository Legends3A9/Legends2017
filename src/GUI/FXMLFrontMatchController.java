/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Pronostic;
import Entités.Utilisateur;
import Entités.Match;
import Services.MatchService;
import Services.PronosticService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ali hamouda
 */
 
public class FXMLFrontMatchController implements Initializable {

    @FXML
    private ListView<Match> list;
    ObservableList<Match> data ;
    ObservableList<Pronostic> data1 ;
    @FXML
    private JFXComboBox<String> phase;
    @FXML
    private JFXComboBox<String> match;
    @FXML
    private Label stade;
    @FXML
    private Label heure;
    @FXML
    private Label date;
    @FXML
    private Label equipe2;
    @FXML
    private Label equipe1;
    @FXML
    private ImageView equipe11;
    @FXML
    private ImageView equipe22;
    @FXML
    private JFXRadioButton equipe111;
    @FXML
    private JFXRadioButton nul;
    @FXML
    private JFXRadioButton equipe222;
    @FXML
    private Button valider;
    @FXML
    private PieChart pie;
    @FXML
    private ToggleGroup gender;
    private String radio;
    @FXML
    private Label id;
    @FXML
    private JFXButton deco;
    @FXML
    private Button poule;
    @FXML
    private Button stade1;
    @FXML
    private Button event;
    @FXML
    private Button store;
    @FXML
    private Button match1;
    @FXML
    private Button ticket;
    @FXML
    private Button colocation;

     @FXML
    private void deco(ActionEvent event) throws IOException {
        Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();   
    }

    @FXML
    private void poul(ActionEvent event) throws IOException {
        
           Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("FXMLFront.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  
    }

    @FXML
    private void stad(ActionEvent event) throws IOException {
           Stage stage = (Stage) stade.getScene().getWindow();
        stage.close();
    
        BorderPane parentContent= FXMLLoader.load(getClass().getResource("ConsulterStadeFXML.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
           Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("ConsulterStadeFXML.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  
    }

    @FXML
    private void stor(ActionEvent event) throws IOException {
      /*     Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("ClientProduits.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  */
    }

    @FXML
    private void matc(ActionEvent event) throws IOException {
           Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("FXMLFrontMatch.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  
    
    }

    @FXML
    private void ticke(ActionEvent event) throws IOException {
           Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("AcheterTicketFXML.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  
    
        
    }

    @FXML
    private void colo(ActionEvent event) throws IOException {
            Stage stage = (Stage) deco.getScene().getWindow();
        stage.close();
    
        AnchorPane parentContent= FXMLLoader.load(getClass().getResource("demandeFXML.fxml"));
        Scene scene = new Scene(parentContent); 
        stage.setScene(scene);
  
        stage.sizeToScene();
        stage.show();  
    
    }

   

    /**
     * Initializes the controller class.
     */
    static public class Matchs extends ListCell<Match> { 
  
   
  
    public Matchs(){ 
        
    } 
  
  
    @Override
    protected void updateItem(Match item, boolean bln) { 
         super.updateItem(item, bln);
                       
                           
                        if (item != null) { 
                            
            File file = new File("C:\\xampp\\htdocs\\image\\"+item.getEquipe1().toLowerCase()+".jpg");
        Image image = new Image(file.toURI().toString());
        ImageView img = new ImageView(image); 
        img.setFitHeight(50);
        img.setFitWidth(90);
         File file2 = new File("C:\\xampp\\htdocs\\image\\"+item.getEquipe2().toLowerCase()+".jpg");
        Image image2 = new Image(file2.toURI().toString());
        ImageView img2 = new ImageView(image2); 
        img2.setFitHeight(50);
        img2.setFitWidth(90);
        /*VBox vBox = new VBox(new Text(item.getStade()),new Text(item.getDate_match().toString()),new Text(item.getHeure().toString()));*/
                            HBox nomEquipe1 = new HBox(new Text(item.getEquipe1()));
                            
                            HBox nomEquipe2 = new HBox(new Text(item.getEquipe2()));
                            
                            VBox equipe1 = new VBox(nomEquipe1,img);
                            
                            VBox equipe2 = new VBox(nomEquipe2,img2);
                            VBox vs = new VBox(new Text(" "),new Text("VS"),new Text(" "));
                            
                            vs.setAlignment(Pos.CENTER);
                            HBox hBox = new HBox(equipe1,vs,equipe2);
                             nomEquipe1.setAlignment(Pos.CENTER);
                             nomEquipe2.setAlignment(Pos.CENTER);
                           
                            Text t =new Text(item.getStade());
                            Text t2 =new Text(item.getDate_match().toString());
                            Text t3 =new Text(item.getHeure().toString());
                            Text t4 = new Text(item.getEquipe2());
                            t.setStyle("-fx-font-size: 18 arial;");
                            t2.setStyle("-fx-font-size: 25 arial;");
                            t3.setStyle("-fx-font-size: 20 arial;");
                            
                            hBox.setSpacing(15);
                            vs.setSpacing(10);
                            nomEquipe1.setSpacing(10);
                            nomEquipe2.setSpacing(10);
                            
                            setGraphic(hBox);
    } 
}   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         data = FXCollections.observableArrayList();
         id.setVisible(false);
        // data1 = FXCollections.observableArrayList();
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
                            data.clear();
                            list.refresh();
                            data = FXCollections.observableArrayList();
                            loadDataFromDatabase(t1);
                            setcellValue();
                            list.setCellFactory(lv -> new Matchs());
                           

            } catch (SQLException ex) {
                Logger.getLogger(FXMLFrontMatchController.class.getName()).log(Level.SEVERE, null, ex);
            }        
             }});
          
            equipe111.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e) {
                 radio=equipe111.getText();
             }
         });
            
            equipe222.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e) {
                 radio=equipe222.getText();
             }
         });
            nul.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e) {
                 radio=nul.getText();
             }
         });
            ObservableList<PieChart.Data> pieChar;
                pieChar = FXCollections.observableArrayList(
                new PieChart.Data("Equipe1",(double)33.33 ),
                new PieChart.Data("Egalite",(double)33.33 ),
                new PieChart.Data("Equipe2",(double)33.33)
                );
                pie.setData(pieChar);
            
            
            
            
            
            
            
    }
    
     
     private void loadDataFromDatabase(String match) throws SQLException {
      
            List<Match> matchs = new ArrayList<>();
            MatchService se=new MatchService();

        matchs =   se.equipeParTypeMatch(match);
         System.out.println(matchs);
        //data.clear();
       for(Match m : matchs)
            {   
                
                data.add(m);
                //System.out.println(data);
               
            }
       
        list.setItems(data);
    
    }
     
 
     
     private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent event) {
                
                Match match = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                    File file = new File("C:\\xampp\\htdocs\\image\\"+match.getEquipe1().toLowerCase()+".jpg");
                    Image image = new Image(file.toURI().toString());
                    
                    File file1 = new File("C:\\xampp\\htdocs\\image\\"+match.getEquipe2().toLowerCase()+".jpg");
                    Image image1 = new Image(file1.toURI().toString());
            equipe11.setImage(image); 
            equipe22.setImage(image1); 
            equipe1.setText(match.getEquipe1());
            equipe2.setText(match.getEquipe2());
            date.setText(match.getDate_match().toString());
            heure.setText(match.getHeure().toString());
            stade.setText(match.getStade());
            id.setText(Integer.toString(match.getId()));
            
                stat();
            
            
            
   
            }
        });
    }
     
      @FXML
    private void vote(ActionEvent event) 
    {
         PronosticService pr=new PronosticService();
         String b = radio;
         int c =  Integer.parseInt(id.getText());
         int a = pr.verif(Utilisateur.getIdd(), c);
          
         Pronostic p = new Pronostic(Utilisateur.getIdd(), c, b);
         Pronostic p1 =new Pronostic(Utilisateur.getIdd(), b);
         
         if(a==0)
         {
             pr.insererPronostic(p);
         }
         else
         {
             pr.updatePronostic(p1);
         }
         
         stat();
        
    }
    
    private void stat () {
                try {
                    PronosticService pr=new PronosticService();
                    List<Pronostic> a= new ArrayList<Pronostic>();
                    List<Pronostic> b= new ArrayList<Pronostic>();
                    List<Pronostic> f= new ArrayList<Pronostic>();
                    List<Pronostic> k= new ArrayList<Pronostic>();
                    
                    try {
                        a= pr.somme(Integer.parseInt(id.getText()),"1");
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLFrontMatchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    b=pr.somme1(Integer.parseInt(id.getText()));
                    
                    double c=a.size();
                    double d=b.size();
                    double e = ((double)(c/d)*100);
                    
                    try {
                        f = pr.somme(Integer.parseInt(id.getText()), "2");
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLFrontMatchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    double g= f.size();
                    
                    double h= ((double)(g/d)*100);
                    try {
                        k = pr.somme(Integer.parseInt(id.getText()), "x");
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLFrontMatchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    double l = k.size();
                    double m= ((double)(l/d)*100);
                    
                    
                    
                    ObservableList<PieChart.Data> pieChart;
                    pieChart = FXCollections.observableArrayList(
                            new PieChart.Data(equipe1.getText()+e+"%",e ),
                            new PieChart.Data("Nul"+m+"%",m),
                            new PieChart.Data(equipe2.getText()+h+"%",h)
                            
                    );
                    pie.setData(pieChart);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLFrontMatchController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }

    
}    
    

