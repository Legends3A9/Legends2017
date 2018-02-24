/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Utilisateur;
import Services.UserService;
import com.jfoenix.controls.JFXTextField;
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
    private JFXTextField jpass;
    @FXML
    private Button cnx;
    @FXML
    private Button inscri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        Services.UserService us = new UserService(); 
       Utilisateur user =us.authentication(jlogin.getText(), jpass.getText()); 
        if(user.getIdUser()<1) {
           
     }else {
             Stage stage = new Stage();
             ((Node)(event.getSource())).getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("membreFront.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            /*
            if(user.getRole().equals("admin"))
            {    Stage stage = new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
               System.out.println("connexion avec succées");
            }
            else if(user.getRole().equals("membre")){
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("EvenMembre.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            System.out.println("erreeeur");
        } */
        } 
        Utilisateur.setEtat_compte(1);
        Utilisateur.setIdd(user.getIdUser());
        Utilisateur.setNomParticipant(user.getNom());
        Utilisateur.setPrenomParticipant(user.getPrenom());
        Utilisateur.setEmailEv(user.getEmail());
        Utilisateur.setRoleUser(user.getRole());
        System.out.println(user.getIdd());
    }

    @FXML
    private void inscription(ActionEvent event) {
    }
    
}
