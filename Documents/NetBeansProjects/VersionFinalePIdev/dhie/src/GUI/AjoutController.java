/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evenement;
import Services.EvenementServices;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class AjoutController implements Initializable {
 
    private String bb ; 
    @FXML
    private TextField textNom;
    @FXML
    private TextField textDestination;
    @FXML
    private TextArea textDescription;
    @FXML
    private ComboBox<String> etat;
    @FXML
    private TextField type;
    @FXML
    private JFXComboBox<String> duree;
    @FXML
    private TextField nombrePlaces;
    @FXML
    private TextField prix;
    private TextField image;
    @FXML
    private DatePicker date;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAnnuler;
    @FXML
    private ImageView imageViewAjout;
    @FXML
    private JFXButton AjoutFile;
    
     FileChooser fc = new FileChooser () ;
    File selectedFile ; 
    @FXML
    private Label lblAjoutImage;
    @FXML
    private JFXTextField fileImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etat.getItems().addAll("disponible","réserve");
        lblAjoutImage.setText("Aucun fichier n'est selctionnée !! ");
        
        duree.getItems().addAll("Quelque heures","une journée","Plus Q'une journée");
        
        prix.addEventFilter(KeyEvent.KEY_TYPED , float_Validation());
        textNom.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());
        textDestination.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());
        textDescription.addEventFilter(KeyEvent.KEY_TYPED , textArea_Validation());
        type.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());
        nombrePlaces.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation());
        
        //date.setValue(LocalDate.now());

        


       
        
                        
    }    

    @FXML
    private void AjouterEvenement(ActionEvent event) throws ParseException, IOException {
        
        if(validationChampsAjout()==false){
            
      
           
        
        }
        else {
          String iii="" ;  
            
        if(selectedFile!=null) {
        iii = selectedFile.getAbsolutePath();
        }
        String nomEvenement = textNom.getText() ; 
        String destination=textDestination.getText(); 
        String Description = this.textDescription.getText(); 
        LocalDate dateEvenement = date.getValue(); 
        String etatEvenement = etat.getValue(); 
        String typeEvenement = type.getText(); 
        String dureeEvenement=duree.getValue(); 
        String imageEvenement=fileImg.getText();
        int nbrPlaces = Integer.parseInt(nombrePlaces.getText());
        float prixEvenement = Float.parseFloat(prix.getText());
      java.sql.Date dateev = java.sql.Date.valueOf(dateEvenement);
        
        
Evenement ev = new Evenement();
ev.setNomEvenement(nomEvenement);
ev.setDescription(Description);
ev.setDateEvenement(dateev);
ev.setDestination(destination);
ev.setEtat(etatEvenement);
ev.setDuree(dureeEvenement);
ev.setImage(imageEvenement);
ev.setType(typeEvenement);

ev.setNbrPlaces(nbrPlaces);
ev.setPrix(prixEvenement);
        
        Services.EvenementServices es = new EvenementServices(); 
        es.ajouterEvenement(ev);
        
        
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merci ");
            alert.setContentText("Evaluation ajouté avec succées  ");
            alert.showAndWait();
        }
            
           /* FXMLLoader loader = new FXMLLoader(getClass().getResource(("FXMLAjout2.fxml")));
                Parent root = loader.load();
                FXMLAjout2Controller FXMLAjout2Controller = loader.getController();
                FXMLAjout2Controller.setNom(nomEvenement);
                Scene scene = textNom.getScene();
                scene.setRoot(root); */
                
                
                
                
            
            
      
        
        
        
        
        
                
                
        
    }

    @FXML
    private void annulerEvenement(ActionEvent event) throws IOException {
    
        prix.setText("");
        textNom.setText("");
        textDestination.setText("");
        textDescription.setText("");
        type.setText("");
        nombrePlaces.setText("");
      date.setValue(null);
    }

    



    
    public void setNom (String nom ) {
        this.bb=nom ; 
    }

    @FXML
    private void fileChooser(ActionEvent event) throws FileNotFoundException {
         
        fc.setInitialDirectory(new File("C:\\Users\\boussandel\\branche2\\Desktop\\images"));
       selectedFile = fc.showOpenDialog(null);
        
        if (selectedFile!= null ) {
            FileInputStream input = new FileInputStream(selectedFile);
            Image image = new Image(input);
            fileImg.setText(selectedFile.getName());
            imageViewAjout.setImage(image);
            lblAjoutImage.setText("Image ajouté avec succée ");
            //lblAjoutImage.styleProperty();
            AjoutFile.setStyle("-fx-text-fill:green;");
            
            
            
            
    }
        
    }
    
    
    private void image () {
        File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images",image.getText());
        
        Image image = new Image(file.toURI().toString());
        imageViewAjout.setImage(image);
         
        
    }
    
    private boolean validationChampsAjout() {
        boolean res = true ; 
       
        String msgTitle = "" ;
        String msgContent = "" ;
        Date dateauj = new Date();
            
//            Date daaate = Date.from(date.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        
        if((textNom.getText().compareTo("")==0))
        {

             
            msgTitle="un champ nom est vide";
             msgContent="veillez remplir le champ nom  ";
             
            res = false ;
        } 
       if(textDestination.getText().compareTo("")==0)
       {
             msgTitle="un champ nom est vide";
             msgContent="veillez remplir le champ Destination";
             
            res = false ;
           
       }
       
       if(textDescription.getText().compareTo("")==0)
       {
            msgTitle ="un champ Description est vide";
            msgContent = "veillez remplir le champ description  ";
             
            res = false ;
           
       }
       
      if(date.getValue()==null) {
            msgTitle ="un champ date est vide";
            msgContent = "veillez remplir le champ date  ";
             
            res = false ;
           
           
       }
       
       if(etat.getItems().toString().compareTo("")==0)
       {
           msgTitle ="un champ etat est vide";
            msgContent = "veillez chosir un etat ";
             
            res = false ;
           
       }
       
        if(type.getText().compareTo("")==0)
       {
           msgTitle ="un champ type est vide";
            msgContent = "veillez chosir un type ";
             
            res = false ;
           
       }
        if(fileImg.getText().compareTo("")==0) 
        {
            msgTitle ="un champ image est vide";
            msgContent = "veillez chosir une image ";
             
            res = false ;
            
        }
        
        if(nombrePlaces.getText().compareTo("")==0) {
            msgTitle ="un champ nombre de place est vide";
            msgContent = "veillez ajouté le nombre de place ";
             
            res = false ;
        }
        if(prix.getText().compareTo("")==0) {
            msgTitle ="un champ prix est vide";
            msgContent = "veillez ajouté le nombre de place ";
             
            res = false ;
        }
        
/*        if(date.getValue().isBefore(LocalDate.now())){
            msgTitle ="Date invalide";
            msgContent = "veillez ajouté une date valide ";
             
            res = false ;
            
        }*/
        
       // a compléter 
       
       
        
        
        
        
        
        
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle(msgTitle);
             alert.setContentText(msgContent);
             alert.showAndWait();
        return res ; 
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
}
