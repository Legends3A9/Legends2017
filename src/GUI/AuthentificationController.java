/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Utilisateur;
import Services.UserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Provider;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class AuthentificationController implements Initializable {

    @FXML
    private JFXTextField jlogin;
   
    @FXML
    private Button cnx;
    @FXML
    private JFXPasswordField jpass;
    @FXML
    private ImageView logo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("C:\\Users\\ali hamouda\\Documents\\NetBeansProjects\\pi_crud\\src\\image\\a.png");

                Image image = new Image(file.toURI().toString());
                logo.setImage(image);
      
    }    

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        Services.UserService us = new UserService(); 
       Utilisateur user =us.authentication(jlogin.getText(), jpass.getText()); 
        if(user.getIdUser()<1) {
           
     }else {
             Stage stage = new Stage();
             ((Node)(event.getSource())).getScene().getWindow().hide();

                
        
            if(user.getRole().toLowerCase().equals("admin"))
            {   
               Parent root = FXMLLoader.load(getClass().getResource("backMatch.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
               System.out.println(user.getRole());
            }
            else if(user.getRole().toLowerCase().equals("membre")){
               
                Parent root = FXMLLoader.load(getClass().getResource("FXMLFrontMatch.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            
        } 
        } 
        Utilisateur.setEtat_compte(1);
        Utilisateur.setIdd(user.getIdUser());
        Utilisateur.setNomParticipant(user.getNom());
        Utilisateur.setPrenomParticipant(user.getPrenom());
        Utilisateur.setEmailEv(user.getEmail());
        Utilisateur.setRoleUser(user.getRole());
     
        
        System.out.println(user.getIdd());
    }

    private void inscription(ActionEvent event) throws IOException {
        Stage stage = new Stage();
       ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}