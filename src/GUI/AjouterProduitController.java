/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Produit;
import Services.ServiceProduit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.stream.FileImageInputStream;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.apache.commons.io.FileUtils;





/**
 * FXML Controller class
 *
 * @author GlaDio007
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField descProduit;
    @FXML
    private TextField couleurProduit;
    @FXML
    private TextField marqueProduit;
    @FXML
    private TextField tailleProduit;
    @FXML
    private TextField quantiteProduit;
    @FXML
    private TextField prixProduit;
    @FXML
    private TextField nomProduit;
    @FXML
    private ComboBox<String> typeProduit;
    @FXML
    private Button btn;
    @FXML
    private Button img;
    
    private FileImageInputStream image;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField cheminImage;

    
    FileChooser fc=new FileChooser();
    File selectedFile=new File("");
    @FXML
    private Button retour;
    @FXML
    private Label nomError;
    @FXML
    private Label quantiteError;
    @FXML
    private Label prixError;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> List=FXCollections.observableArrayList(
                "Chaussures","Ballon","Maillot National","Survetement","Chaussettes","Accessires"
        );
        typeProduit.setItems(List);
        quantiteProduit.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation());
        prixProduit.addEventFilter(KeyEvent.KEY_TYPED , float_Validation());
    }
        
        
        @FXML
         
        
        
        private void AddProduit(ActionEvent event) throws IOException {
            
            
                if((nomProduit.getText().equals("")))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir le nom du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
            }
                else if(typeProduit.getValue()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait selectionner le type du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
            }
            
            else if(prixProduit.getText().equals(""))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir le prix du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
                    }
            else if(quantiteProduit.getText().equals(""))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir la quantite du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
                    }
            else if(cheminImage.getText().equals(""))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait choisissez l'image du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
                    }
            
            else{
                String nom=nomProduit.getText();
                String type=typeProduit.getValue();
                String taille=tailleProduit.getText();
                String marque = marqueProduit.getText();
                String couleur = couleurProduit.getText();
                String description = descProduit.getText();
                String ss=cheminImage.getText();
                float prix= Float.parseFloat( prixProduit.getText());
                int quantite=Integer.parseInt( quantiteProduit.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Ajout");
                alert.setContentText("Vouler vous vraiment ajouter ce produit?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) { 
                    Produit p = new Produit(nom,type,prix,quantite,ss,taille, marque, couleur, description);
                    ServiceProduit service = new ServiceProduit();
                    service.Add(p);
                }  
            }
             
        }

    
@FXML
    private void image(ActionEvent event) throws FileNotFoundException, IOException {
        File dest=new File("C:\\wamp\\www\\Images");
        
        fc.setInitialDirectory(new File("C:\\Users\\GlaDio007\\Documents\\NetBeansProjects\\PiDev_Russia2018\\Images\\"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);
        
        File newFile = new File("C:\\wamp\\www\\Images\\"+selectedFile.getName());
        
        FileInputStream input = new FileInputStream(newFile);
        Image image = new Image(input);
        cheminImage.setText(newFile.getAbsolutePath()); 
        imageView.setImage(image);
    }
    @FXML
    private void Return(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
        Parent root = loader.load();
        Scene scene = retour.getScene();
        scene.setRoot(root);
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


public EventHandler<KeyEvent> float_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if(e.getCharacter().matches("[0-9.]")){ 
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
    
    public EventHandler<KeyEvent> letter_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}   

    @FXML
    private void nomTest(KeyEvent event) {
        if(nomProduit.getText().equals(""))
            nomError.setVisible(true);
        else
            nomError.setVisible(false);
    }

    @FXML
    private void prixTest(KeyEvent event) {
        if(prixProduit.getText().equals(""))
            prixError.setVisible(true);
        else
            prixError.setVisible(false);
    }

    @FXML
    private void quantiteTest(KeyEvent event) {
        if(quantiteProduit.getText().equals(""))
            quantiteError.setVisible(true);
        else
            quantiteError.setVisible(false);
    }

    @FXML
    private void Deconnection(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Authentification.fxml"));
        Parent root = loader.load();
        Scene scene = btn.getScene();
        scene.setRoot(root);
    }

    

   
}
