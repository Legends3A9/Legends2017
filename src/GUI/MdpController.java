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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ali hamouda
 */
public class MdpController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Button deconnexion;
    @FXML
    private Button retour;
    @FXML
    private Button modifier;
    @FXML
    private TextField mdp;
    @FXML
    private TextField Nmdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
