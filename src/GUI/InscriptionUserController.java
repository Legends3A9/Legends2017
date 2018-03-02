/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Utilisateur;
import Services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author GlaDio007
 */
public class InscriptionUserController implements Initializable {

    @FXML
    private ComboBox<String> nationaliteU;
    @FXML
    private TextField nomU;
    @FXML
    private TextField comfirmerPassU;
    @FXML
    private TextField emailU;
    @FXML
    private TextField prenomU;
    @FXML
    private TextField TelU;
    @FXML
    private TextField passU;
    @FXML
    private TextField loginU;
    @FXML
    private Button btncreeruser;
    @FXML
    private ImageView imageView;
    @FXML
    private Label cheminImage;
    
    FileChooser fc=new FileChooser();
    File selectedFile=new File("");
    @FXML
    private Button btnimage;
    @FXML
    private Button decon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> List=FXCollections.observableArrayList(
                "Tunisie","France","Russie","Brazil","Italie","Allemagne","Algerie","Egypte"
        );
        nationaliteU.setItems(List);
        TelU.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
        // TODO
    }    

    @FXML
    private void ajouterUser(ActionEvent event) {
        
       
        
        
        String nom=nomU.getText();
        String prenom=prenomU.getText();
        String login=loginU.getText();
        String email=emailU.getText();
        String pass=passU.getText();
        String Cpass=comfirmerPassU.getText();
        String tel=TelU.getText();
        String nat=nationaliteU.getValue();
        
         
        UserService userS = new UserService();
       
        
        if((nom.equals("")) || (prenom.equals("")) || (login.equals("")) || (email.equals("")) || (pass.equals("")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Champs vides");
            alert.setContentText("S'il vous plait saisir tout les champs");
            alert.showAndWait();
        }
        
        else if (pass.equals(Cpass)==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Mot de passe invalide");
            alert.setContentText("les champs de mots de passes doive etre identiques");
            alert.showAndWait();
        }
        else if((email.contains("@")==false)&&email.contains(".")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Email invalide");
            alert.setContentText("S'il vous plait saisir un email valide");
            alert.showAndWait();
        }
        else{
        Utilisateur user = new Utilisateur(nom, prenom, email, pass, tel, nat, login, "Membre",cheminImage.getText());
                
        userS.add(user);
        
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
    
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource(); 
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
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

    @FXML
    private void deconn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
