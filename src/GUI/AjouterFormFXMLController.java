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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void ImageAction(ActionEvent event) throws FileNotFoundException {
        
        fc.setInitialDirectory(new File("C:\\Users\\melek\\Desktop\\Images"));
       selectedFile = fc.showOpenDialog(null);
        
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
    
}
