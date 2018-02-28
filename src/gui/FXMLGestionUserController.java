/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.Utilisateur;
import Services.UserService;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dambo
 */
public class FXMLGestionUserController implements Initializable {

    @FXML
    private TableView<Utilisateur> users;
    @FXML
    private TableColumn<Utilisateur, ?> id;
    @FXML
    private TableColumn<Utilisateur, ?> nom;
    @FXML
    private TableColumn<Utilisateur, ?> prenom;
    @FXML
    private TableColumn<Utilisateur, ?> email;
    @FXML
    private TableColumn<Utilisateur, ?> mdp;
    @FXML
    private TableColumn<Utilisateur, ?> tel;
    @FXML
    private TableColumn<Utilisateur, ?> nat;
    @FXML
    private TableColumn<Utilisateur, ?> role;
    @FXML
    private TableColumn<Utilisateur, ?> login;
    @FXML
    private TableColumn<Utilisateur, ?> photo;
    private ObservableList<Utilisateur> data;
    @FXML
    private AnchorPane a;
    @FXML
    private ImageView img;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tid;
    @FXML
    private TextField ttel;
    @FXML
    private TextField tnat;
    @FXML
    private Button del;
    @FXML
    private TextField tprenom;
    @FXML
    private TextField temail;
    @FXML
    private TextField tmdp;
    @FXML
    private ComboBox<String> trole;
    @FXML
    private TextField tlogin;
    @FXML
    private Label tphoto;
    @FXML
    private JFXTextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trole.getItems().addAll("admin", "membre");
        a.setDisable(true);
        try {
            // TODO
            search();
            data = FXCollections.observableArrayList();
            setcellValue();
            loadDataFromDatabase();
            setsCllTable();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDataFromDatabase() throws SQLException {
        List<Utilisateur> equipes = new ArrayList<>();
        UserService se = new UserService();

        equipes = se.getAll();
        for (Utilisateur e : equipes) {
            data.add(e);
        }
        users.setItems(data);
    }

    public void setsCllTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        nat.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));

        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));

    }

    private void setcellValue() {
        users.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Utilisateur usr = users.getItems().get(users.getSelectionModel().getSelectedIndex());
                tid.setText(Integer.toString(usr.getIdUser()));
                tnom.setText(usr.getNom());
                tprenom.setText(usr.getPrenom());
                temail.setText(usr.getEmail());
                tmdp.setText(usr.getPassword());
                ttel.setText(usr.getTelephone());
                tnat.setText(usr.getNationalite());
                trole.getSelectionModel().select(usr.getRole());
                tlogin.setText(usr.getLogin());

                del.setDisable(false);
                a.setDisable(false);
                Image();

            }

        });
    }

    private void Image() {

        File file = new File(tphoto.getText());

        Image image = new Image(file.toURI().toString());
        img.setImage(image);

    }

    public void search() {
        FilteredList<Utilisateur> filterdata = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Utilisateur>) nom -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    return nom.getPrenom().contains(newValue);
                });
            });
            SortedList<Utilisateur> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(users.comparatorProperty());
            users.setItems(sorteddata);
        });
    }

    @FXML
    private void del(ActionEvent event) throws SQLException {
        UserService se = new UserService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprission");
        alert.setContentText("Vous Voulez SUPPRIMER Equipe et ses JOUEURS:" + tnom.getText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id = Integer.parseInt(tid.getText());
            se.delete(id);

            a.setDisable(true);
            Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setTitle("Equipe supprimé");
            b.setHeaderText("Suppression");
            b.setContentText("Suppression:" + tnom.getText());
            b.showAndWait();
            data.clear();
            loadDataFromDatabase();
        }
    }
}
