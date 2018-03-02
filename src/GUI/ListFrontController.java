/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Utilisateur;
import Entités.offreuser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import Services.offreuserService;
import javafx.scene.Node;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListFrontController implements Initializable {

    @FXML
    private TextField id1;
    @FXML
    private TextField type1;
    @FXML
    private TextField prix1;
    @FXML
    private TextField adresse1;
    @FXML
    private TextField nb_place_dispo1;
    @FXML
    private TextField description1;
    @FXML
    private TextField tel1;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Label cheminImage;
    @FXML
    private Button img;
    @FXML
    private ImageView image;
    @FXML
    private ListView<offreuser> list;
    private ObservableList<offreuser> data;
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    @FXML
    private Button retour;
    @FXML
    private TextField etat1;
    @FXML
    private Button decon;

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajoutFront.fxml")));
        Parent root = loader.load();
        AjoutFrontController frontControler = loader.getController();
        Scene scene = retour.getScene();
        scene.setRoot(root);
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

    static public class Collocation extends ListCell<offreuser> {

        public Collocation() {

        }

        @Override
        protected void updateItem(offreuser item, boolean bln) {
            super.updateItem(item, bln);

            if (item != null) {
                Label type = new Label(item.getType());
                type.setStyle("-fx-font-size: 30 arial;");
                VBox vBox = new VBox(type, new Text("Adresse: " + item.getAdresse()), new Text("Nombre de Place disponible:  " + item.getNb_place_dispo()), new Text("Prix:  " + item.getPrix() + " Pуб"), new Text("Etat :  " + item.getEtat()));
                File file = new File(item.getImage());

                Image image = new Image(file.toURI().toString());
                ImageView img = new ImageView(image);
                img.setFitHeight(100);
                img.setFitWidth(170);
                HBox hBox = new HBox(img, vBox);

                Text t = new Text(item.getAdresse());
                Text t2 = new Text(String.valueOf(item.getNb_place_dispo()));
                Text t3 = new Text(String.valueOf(item.getPrix()));
                Text t4 = new Text(item.getEtat());
                t.setStyle("-fx-font-size: 18 arial;");
                t2.setStyle("-fx-font-size: 25 arial;");
                t3.setStyle("-fx-font-size: 20 arial;");
                t4.setStyle("-fx-font-size: 20 arial;");
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data = FXCollections.observableArrayList();
        setcellValue();
        tel1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(8));
        nb_place_dispo1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(1));
        type1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        adresse1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        prix1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        etat1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        Services.offreuserService os = new Services.offreuserService();
        loadDataFromDatabase();
        list.setCellFactory(lv -> new Collocation());

        setcellValue();
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

    private void loadDataFromDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/russia18";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM offreuser WHERE iduser=" + Utilisateur.getIdd());
            while (rs.next()) {
                int idE = rs.getInt("id");
                String typeE = rs.getString("type");
                float prixE;
                prixE = rs.getFloat("prix");
                String adresseE = rs.getString("adresse");
                Integer nb_place_dispoE;
                nb_place_dispoE = rs.getInt("nb_place_dispo");
                String descriptionE = rs.getString("description");
                Integer telE;
                telE = rs.getInt("tel");
                String imageE = rs.getString("image");
                String etatE = rs.getString("etat");
                int iduserE = rs.getInt("iduser");
                data.add(new offreuser(idE, typeE, prixE, adresseE, nb_place_dispoE, descriptionE, telE, imageE, etatE, iduserE));
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        list.setItems(data);
    }

    private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                offreuser ol = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                id1.setText(Integer.toString(ol.getId()));
                prix1.setText(Float.toString(ol.getPrix()));
                type1.setText(ol.getType());
                adresse1.setText(ol.getAdresse());
                nb_place_dispo1.setText(Integer.toString(ol.getNb_place_dispo()));
                description1.setText(ol.getDescription());
                tel1.setText(Integer.toString(ol.getTel()));
                cheminImage.setText(ol.getImage());
                etat1.setText(ol.getEtat());
                Image();
                modifier.setDisable(false);
                supprimer.setDisable(false);
            }
        });
    }

    private void Image() {

        File file = new File(cheminImage.getText());
        Image image1 = new Image(file.toURI().toString());
        image.setImage(image1);

    }

    @FXML
    private void supprimer(ActionEvent event) {

        int idE = Integer.valueOf(id1.getText());

        offreuserService.DeletoffreByID(idE);
        id1.setText("");
        prix1.setText("");
        type1.setText("");
        adresse1.setText("");
        nb_place_dispo1.setText("");
        description1.setText("");
        tel1.setText("");
        etat1.setText("");
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("offre supprimé");
        alert.setHeaderText("Suppression");
        alert.showAndWait();
        TrayNotification tray = new TrayNotification();
        Image whatsAppImg = new Image("/image/image2.png");
        String text = "Offre supprimé avec succés ";

        tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

        tray.showAndDismiss(Duration.seconds(10));
    }

    @FXML
    private void modifier(ActionEvent event) {
        int idE = Integer.parseInt(id1.getText());
        String typeE = (type1.getText());
        float prixE = Float.parseFloat(prix1.getText());
        String adresseE = (adresse1.getText());
        int nb_place_dispoE = Integer.parseInt(nb_place_dispo1.getText());
        String descriptionE = description1.getText();
        int telE = Integer.parseInt(tel1.getText());
        String imageE = (cheminImage.getText());
        String etatE = etat1.getText();
        offreuser o = new offreuser(idE, typeE, prixE, adresseE, nb_place_dispoE, descriptionE, telE, imageE, etatE);
        Services.offreuserService ser = new offreuserService();
        ser.updateoffre(o);
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setHeaderText("Modification");
        alert.showAndWait();
        TrayNotification tray = new TrayNotification();
        Image whatsAppImg = new Image("/image/image1.png");
        String text = "Offre modifié avec succés ";

        tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

        tray.showAndDismiss(Duration.seconds(10));
    }

    @FXML
    private void source(ActionEvent event) throws IOException {
        File dest = new File("C:\\wamp64\\www\\image");

        fc.setInitialDirectory(new File("C:\\Users\\Alaa\\Desktop\\image"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);

        File newFile = new File("C:\\wamp64\\www\\image\\" + selectedFile.getName());

        FileInputStream input = new FileInputStream(newFile);
        Image image1 = new Image(input);
        cheminImage.setText(newFile.getAbsolutePath());
        image.setImage(image1);
    }

}
