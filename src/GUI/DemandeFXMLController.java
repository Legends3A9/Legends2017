/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.offreuser;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import Entités.participationC;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import Services.offreuserService;
import Services.participationcService;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class DemandeFXMLController implements Initializable {

    ObservableList<offreuser> data;
    @FXML
    private ListView<offreuser> list;
    @FXML
    private Label prix1;
    @FXML
    private Label type1;
    @FXML
    private Label adresse1;
    @FXML
    private Label nb_place_dispo1;
    @FXML
    private Label description1;
    @FXML
    private Label tel1;
    @FXML
    private Button demander;
    @FXML
    private Button ajout;
    private Button retour;
    @FXML
    private ImageView image;
    @FXML
    private Label cheminImage;

    @FXML
    private Label etat1;
    @FXML
    private Label id1;
    @FXML
    private Label dinar;

    @FXML
    private void demander(ActionEvent event) throws SQLException {
        offreuser ol = list.getItems().get(list.getSelectionModel().getSelectedIndex());
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Confirmation De participation");

        alerte.setContentText("etes vous sur de vouloir participer a cet collocation");

        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (etat1.getText().toLowerCase().equals("complet")) {
                demander.setDisable(true);
                TrayNotification tray = new TrayNotification();
                Image whatsAppImg = new Image("/image/image2.png");
                String text = "Cette collocation est complet ";

                tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

                tray.showAndDismiss(Duration.seconds(10));

            } else {
                TrayNotification tray = new TrayNotification();
                Image whatsAppImg = new Image("/image/image1.png");
                String text = "Demande éffectué avec succés ";

                tray.setTray("welcome", text + " ", whatsAppImg, Paint.valueOf("#2A9A84"), AnimationType.SLIDE);

                tray.showAndDismiss(Duration.seconds(10));

                int nbrPlace = Integer.parseInt(nb_place_dispo1.getText());
                // int idUser = Utilisateur.getInstance().getIdUser(); 
                int ido = Integer.parseInt(id1.getText());
                if (nbrPlace > 1) {
                    nbrPlace = nbrPlace - 1;

                    Services.offreuserService es = new offreuserService();
                    es.decrementation_nbrPlaces(ido, nbrPlace);
                    Services.participationcService ps = new participationcService();
                    participationC p = new participationC(1, ido);
                    ps.ajouterParticipation(p);

                    list.refresh();
                } else if (nbrPlace == 1) {
                    Services.offreuserService es = new offreuserService();
                    es.decrEtChangementDetat(ido);
                    Services.participationcService ps = new participationcService();
                    participationC p = new participationC(1, ido);
                    ps.ajouterParticipation(p);

                }
            }
        }
        data.clear();
        loadDataFromDatabase();
        nb_place_dispo1.setText(Integer.toString(ol.getNb_place_dispo()));
        etat1.setText(ol.getEtat());

    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ajoutFront.fxml")));
        Parent root = loader.load();
        AjoutFrontController frontControler = loader.getController();
        Scene scene = ajout.getScene();
        scene.setRoot(root);
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
                img.setFitHeight(120);
                img.setFitWidth(170);
                HBox hBox = new HBox(img, vBox);

                Text t = new Text(item.getAdresse());
                Text t2 = new Text(String.valueOf(item.getNb_place_dispo()));
                Text t3 = new Text(String.valueOf(item.getPrix()));
                Text t4 = new Text(item.getEtat());
                t.setStyle("-fx-font-size: 25 arial;");
                t2.setStyle("-fx-font-size: 20 arial;");
                t3.setStyle("-fx-font-size: 20 arial;");
                t4.setStyle("-fx-font-size: 20 arial;");
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Services.offreuserService os = new Services.offreuserService();

        data = FXCollections.observableArrayList();

        try {
            data = FXCollections.observableArrayList();
            loadDataFromDatabase();
            list.setCellFactory(lv -> new Collocation());

        } catch (SQLException ex) {
            Logger.getLogger(DemandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setcellValue();
        

    }

    private void loadDataFromDatabase() throws SQLException {
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
                String image = rs.getString("image");
                Integer telE;
                telE = rs.getInt("tel");
                String etatE = rs.getString("etat");
                data.add(new offreuser(idE, typeE, prixE, adresseE, nb_place_dispoE, descriptionE, telE, image, etatE));
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        list.setItems(data);

    }

    private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                offreuser ol = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                prix1.setText(Float.toString(ol.getPrix()));
                type1.setText(ol.getType());
                adresse1.setText((ol.getAdresse()));
                nb_place_dispo1.setText(Integer.toString(ol.getNb_place_dispo()));
                description1.setText(ol.getDescription());
                tel1.setText(Integer.toString(ol.getTel()));
                cheminImage.setText(ol.getImage());
                id1.setText(Integer.toString(ol.getId()));
                etat1.setText(ol.getEtat());
                float dinar1 = Float.parseFloat(prix1.getText());
                dinar1 = (float) (dinar1 * 0.0427884);
                dinar.setText(Float.toString(dinar1));
                Image();
            }
        });
    }

    private void Image() {

        File file = new File(cheminImage.getText());
        Image image1 = new Image(file.toURI().toString());
        image.setImage(image1);

    }

    

}
