/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class MenuStadeFXMLController implements Initializable {
    @FXML
    private Button stade;
    @FXML
    private Button ticket;
    @FXML
    private Button gererstade;
    @FXML
    private Button gererticket;
    @FXML
    private ImageView stadea;
    @FXML
    private ImageView ticketa;
    @FXML
    private ImageView stadet;
    @FXML
    private ImageView tickett;
    @FXML
    private ImageView plus;
    @FXML
    private ImageView plusa;
    @FXML
    private ImageView ges;
    @FXML
    private ImageView gesa;
    @FXML
    private Button deco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("aaaaaaaaa");
         File files = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\stadede.png");
        Image image = new Image(files.toURI().toString());
        stadea.setImage(image);
       
        stadea.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajouterFormFXML.fxml")));
                Parent root = loader.load();
                AjouterFormFXMLController StadeControler = loader.getController();
                Scene scene = stade.getScene();
                scene.setRoot(root);
                
                event.consume();
            } catch (IOException ex) {
                Logger.getLogger(MenuStadeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });
         File filet = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\002-ticket.png");
        Image imaget = new Image(filet.toURI().toString());
        ticketa.setImage(imaget);
        ticketa.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajouterTicketFormFXML.fxml")));
                Parent root = loader.load();
                AjouterTicketFormFXMLController TicketControler = loader.getController();
                Scene scene = ticket.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(MenuStadeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });
        File filesg = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\stadede.png");
        Image imagesg = new Image(filesg.toURI().toString());
        stadet.setImage(imagesg);
        stadet.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionStadeFXML.fxml")));
                Parent root = loader.load();
                GestionStadeFXMLController GererStadeControler = loader.getController();
                Scene scene = gererstade.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(MenuStadeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });
        File filetg = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\002-ticket.png");
        Image imagetg = new Image(filetg.toURI().toString());
        tickett.setImage(imagetg);
        tickett.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionTicketFXML.fxml")));
                Parent root = loader.load();
                GestionTicketFXMLController GererTicketControler = loader.getController();
                Scene scene = gererticket.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(MenuStadeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });
        File filep = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\003-add.png");
        Image imagep = new Image(filep.toURI().toString());
        plus.setImage(imagep);
        plusa.setImage(imagep);
        File fileg = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\001-teamwork.png");
        Image imageg = new Image(fileg.toURI().toString());
       ges.setImage(imageg);
        gesa.setImage(imageg);
    }    

    @FXML
    private void ActionStade(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajouterFormFXML.fxml")));
        Parent root = loader.load();
        AjouterFormFXMLController StadeControler = loader.getController();
        Scene scene = stade.getScene();
        scene.setRoot(root);
        
    }

    @FXML
    private void ActionTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajouterTicketFormFXML.fxml")));
        Parent root = loader.load();
        AjouterTicketFormFXMLController TicketControler = loader.getController();
        Scene scene = ticket.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void ActionGererStade(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionStadeFXML.fxml")));
        Parent root = loader.load();
        GestionStadeFXMLController GererStadeControler = loader.getController();
        Scene scene = gererstade.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void ActionGererTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionTicketFXML.fxml")));
        Parent root = loader.load();
        GestionTicketFXMLController GererTicketControler = loader.getController();
        Scene scene = gererticket.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void decoAction(ActionEvent event) throws IOException {
          Stage stage = new Stage();
       ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
