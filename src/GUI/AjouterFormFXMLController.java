/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Stade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import service.StadeService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class AjouterFormFXMLController implements Initializable {
    @FXML
    private TextField nom_stade;
    @FXML
    private TextField region;
    @FXML
    private TextField adresse;
    @FXML
    private TextField surface;
    @FXML
    private TextField capacite;
    @FXML
    private TextField image;
    @FXML
    private DatePicker date_construction;
    @FXML
    private Button ajouter;
    @FXML
    private Button imagea;
    FileChooser fc = new FileChooser () ;
    File selectedFile ; 
    @FXML
    private Button consulter;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        capacite.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
        nom_stade.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
        region.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
       // adresse.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
        surface.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));

    }    

    @FXML
    private void ActionAjouter(ActionEvent event) throws IOException {
        String nom_stadeS =nom_stade.getText();
        String regionS=region.getText();
        String adresseS=adresse.getText();
        LocalDate date_constructionS = date_construction.getValue();
        String surfaceS=surface.getText();
        String capaciteS=capacite.getText();
        String imageS=image.getText();
       
        java.sql.Date datecons = java.sql.Date.valueOf(date_constructionS);
        Stade s = new Stade(nom_stadeS,regionS,adresseS, datecons, surfaceS,Integer.parseInt(capaciteS) ,imageS);
        
         service.StadeService ss = new StadeService(); 
        ss.insererStade(s);
        
    }

    @FXML
    private void ImageAction(ActionEvent event) throws FileNotFoundException, IOException {
        
        File dest=new File("C:\\wamp\\www\\Images");
        
        fc.setInitialDirectory(new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);
        
        File newFile = new File("C:\\wamp64\\www\\Images\\"+selectedFile.getName());
       
        if (selectedFile!= null ) {
            String a;
            a=(selectedFile.getName());
            image.setText(a);
            
            
    }
        
    }

    @FXML
    private void ConsulterAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionStadeFXML.fxml")));
        Parent root = loader.load();
        GestionStadeFXMLController GererStadeControler = loader.getController();
        Scene scene = consulter.getScene();
        scene.setRoot(root);
    }
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            capacite = (TextField) e.getSource();                
            if (capacite.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
                if(capacite.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(capacite.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
    
    
}    
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
    
        TextField textfield=new TextField();
        public void handle(KeyEvent e) {
            textfield = (TextField) e.getSource();                
            if (textfield.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
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
