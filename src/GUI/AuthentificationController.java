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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField jlogin;
    @FXML
    private TextField jpass;
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
        Utilisateur user = us.authentication(jlogin.getText(), jpass.getText());
        if (user.getIdUser() < 1) {

        } else {
            Stage stage = new Stage();
            ((Node) (event.getSource())).getScene().getWindow().hide();

            if (user.getRole().toLowerCase().equals("admin")) {
                Parent root = FXMLLoader.load(getClass().getResource("listeOffreFXML.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                TrayNotification tray = new TrayNotification();
                Image whatsAppImg = new Image("/image/logo.jpg");
                String text = " ";

                tray.setTray("welcome "+user.getNom()+" "+user.getPrenom(), text + " Russia 2018" , whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

                tray.showAndDismiss(Duration.seconds(10));
            } else if (user.getRole().toLowerCase().equals("membre")) {

                Parent root = FXMLLoader.load(getClass().getResource("demandeFXML.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                TrayNotification tray = new TrayNotification();
                Image whatsAppImg = new Image("/image/logo.jpg");
                String text = " ";
                tray.setTray("welcome "+user.getNom()+" "+user.getPrenom(), text + " Russia 2018" , whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

                tray.showAndDismiss(Duration.seconds(10));
            }
        }
        Utilisateur.setEtat_compte(1);
        Utilisateur.setIdd(user.getIdUser());
        Utilisateur.setNomParticipant(user.getNom());
        Utilisateur.setPrenomParticipant(user.getPrenom());
        Utilisateur.setEmailEv(user.getEmail());
        Utilisateur.setRoleUser(user.getRole());

    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionUser.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
