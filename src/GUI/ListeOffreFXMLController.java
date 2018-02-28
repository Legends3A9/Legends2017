/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entités.offreuser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.textfield.TextFields;
import Services.offreuserService;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ListeOffreFXMLController implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> nb_place_dispo;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> tel;

    @FXML
    private TableView<offreuser> tableview;
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
    private Button modifier;

    private ObservableList<offreuser> data;
    @FXML
    private TextField recherche;
    private Button retour;
    private Button menu;
    @FXML
    private ImageView image;
    @FXML
    private TableColumn<?, ?> image1;
    @FXML
    private Label cheminImage;
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    @FXML
    private TextField etat1;
    @FXML
    private TableColumn<?, ?> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data = FXCollections.observableArrayList();
        setsCllTable();
        loadDataFromDatabase();
        setcellValue();
        search();
        String[] possibleWords = {"Saint-Pétersbourg", "Moscou", "Nijni Novgorod", "Kaliningrad", "Saransk", "Rostov-sur-le-Don", "Sotchi", "Volgograd", "Samara", "Kazan", "Iekaterinbourg"};
        TextFields.bindAutoCompletion(recherche, possibleWords);
        tel1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(8));
        nb_place_dispo1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(1));
        type1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        adresse1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
        description1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(300));
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

            rs = stmt.executeQuery("SELECT * FROM offreuser");
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
                data.add(new offreuser(idE, typeE, prixE, adresseE, nb_place_dispoE, descriptionE, telE, imageE, etatE));
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableview.setItems(data);
    }

    public void setsCllTable() {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        nb_place_dispo.setCellValueFactory(new PropertyValueFactory<>("nb_place_dispo"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        image1.setCellValueFactory(new PropertyValueFactory<>("image"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    private void setcellValue() {
        tableview.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                offreuser ol = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
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
        Services.offreuserService ser = new offreuserService();
        ser.DeletoffreByID(idE);
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


    public void search() {
        FilteredList<offreuser> filterdata = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super offreuser>) offre -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((offre.getAdresse().contains(newValue)) || (offre.getAdresse().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<offreuser> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sorteddata);
        });
    }


    
}
