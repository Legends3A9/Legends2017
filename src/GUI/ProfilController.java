/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ali hamouda
 */
public class ProfilController implements Initializable {

    @FXML
    private Label prenom;
    @FXML
    private Label nationalite;
    @FXML
    private Label login;
    @FXML
    private Label mail;
    @FXML
    private Label telephone;
    @FXML
    private Label nom;
    @FXML
    private Button deconnexion;
    @FXML
    private ImageView photo;
    @FXML
    private Button modifier;
    @FXML
    private Button changerMdp;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
