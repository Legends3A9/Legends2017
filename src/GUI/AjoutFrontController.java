/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.offreuser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import service.offreuserService;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AjoutFrontController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField prix;
    @FXML
    private TextField adresse;
    @FXML
    private TextField nb_place_dispo;
    @FXML
    private TextField description;
    @FXML
    private Button ajouter;
    @FXML
    private TextField tel;
    private Button afficher;
    private Button menu;
    @FXML
    private Button img;
    @FXML
    private Label cheminImage;
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageview;
    @FXML
    private Button retour;
    @FXML
    private Button affichage;
    @FXML
    private TextField etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tel.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(8));
        nb_place_dispo.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(1));
        type.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        adresse.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        prix.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        etat.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
    }

    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }

        };

    }

    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[A-Za-z]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        if ((type.getText().equals("")) || (prix.getText().equals("")) || (adresse.getText().equals("")) || (nb_place_dispo.getText().equals("")) || (description.getText().equals("")) || (tel.getText().equals("")) || (cheminImage.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de Saisie");
            alert.setHeaderText("Erreur");
            alert.setContentText("S'il vous plait rempli tous les champs");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            String typeS = type.getText();
            String prixS = prix.getText();
            String adresseS = adresse.getText();
            String nb_place_dispoS = nb_place_dispo.getText();
            String descriptionS = description.getText();
            String telS = tel.getText();
            String imageS = cheminImage.getText();
            String etatS = etat.getText();
            offreuser ou = new offreuser(typeS, Float.parseFloat(prixS), adresseS, Integer.parseInt(nb_place_dispoS), descriptionS, Integer.parseInt(telS), imageS, etatS, 1);
            service.offreuserService su = new offreuserService();
            su.insereroffre(ou);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("offre ajouté");
            alert.setHeaderText("Ajout");
            alert.showAndWait();
            TrayNotification tray = new TrayNotification();
            Image whatsAppImg = new Image("/image/image1.png");
            String text = "Offre ajouté avec succés ";

            tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

            tray.showAndDismiss(Duration.seconds(10));
        }

    }

    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("listeOffreFXML.fxml")));
        Parent root = loader.load();
        ListeOffreFXMLController offreControler = loader.getController();
        Scene scene = afficher.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void source(ActionEvent event) throws FileNotFoundException, IOException {
        File dest = new File("C:\\wamp64\\www\\image");

        fc.setInitialDirectory(new File("C:\\Users\\Alaa\\Desktop\\image"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);

        File newFile = new File("C:\\wamp64\\www\\image\\" + selectedFile.getName());

        FileInputStream input = new FileInputStream(newFile);
        Image image = new Image(input);
        cheminImage.setText("C:\\wamp64\\www\\image\\" + selectedFile.getName());
        imageView.setImage(image);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("demandeFXML.fxml")));
        Parent root = loader.load();
        DemandeFXMLController demandeControler = loader.getController();
        Scene scene = retour.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void affichage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("listFront.fxml")));
        Parent root = loader.load();
        ListFrontController listControler = loader.getController();
        Scene scene = affichage.getScene();
        scene.setRoot(root);
    }

}
