/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entités.Equipe;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Services.ServiceEquipe;
import javafx.scene.image.Image;
import Services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author Dambo
 */
public class FXMLConsulterController implements Initializable {

    @FXML
    private TableColumn<Equipe, ?> cid;
    @FXML
    private TableColumn<Equipe, ?> cequipe;
    @FXML
    private TableColumn<Equipe, ?> cpoule;
    @FXML
    private TableColumn<Equipe, ?> ccon;
    @FXML
    private TableColumn<Equipe, ?> cnbr;
    @FXML
    private TableColumn<Equipe, ?> cpar;
    @FXML
    private TableColumn<Equipe, ?> ccou;
    @FXML
    private TableColumn<Equipe, ?> ct;
    private ObservableList<Equipe> data;
    @FXML
    private TableView<Equipe> tequipes;
    @FXML
    private TextField teq;
    @FXML
    private TextField tid;
    @FXML
    private ComboBox<String> tpou;
    @FXML
    private TextField tnj;
    @FXML
    private TextField tp;
    @FXML
    private TextField tc;
    @FXML
    private Button mod;
    @FXML
    private Button del;
    @FXML
    private ComboBox<String> tcon;
    @FXML
    private AnchorPane anc;
    @FXML
    private ComboBox<String> poules;
    @FXML
    private AnchorPane a;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  Application.setUserAgentStylesheet(getClass().getResource("custom.css")
        //          .toExternalForm());

        a.setDisable(true);

        data = FXCollections.observableArrayList();
        poules.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
        tcon.getItems().addAll("Afrique", "Asie", "Europe", "Amérique du Nord", "Amérique du Sud");
        tpou.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
        poules.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {

                    loadDataFromDatabase(t1);
                    setsCllTable();
                    setcellValue();
                    data = FXCollections.observableArrayList();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLConsulterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void loadDataFromDatabase(String poule) throws SQLException {
        List<Equipe> equipes = new ArrayList<>();
        ServiceEquipe se = new ServiceEquipe();

        equipes = se.equipeParPoule(poule);
        for (Equipe e : equipes) {
            data.add(e);
        }
        tequipes.setItems(data);
    }

    public void setsCllTable() {
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cequipe.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        cpoule.setCellValueFactory(new PropertyValueFactory<>("nom_poule"));
        ccon.setCellValueFactory(new PropertyValueFactory<>("continent"));
        cnbr.setCellValueFactory(new PropertyValueFactory<>("nbr_joueur"));
        cpar.setCellValueFactory(new PropertyValueFactory<>("nbr_participation"));
        ccou.setCellValueFactory(new PropertyValueFactory<>("nbr_coupe"));
        ct.setCellValueFactory(new PropertyValueFactory<>("taux_victoire"));
    }

    private void setcellValue() {
        tequipes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Equipe eqp = tequipes.getItems().get(tequipes.getSelectionModel().getSelectedIndex());
                tid.setText(Integer.toString(eqp.getId()));
                teq.setText(eqp.getNom_equipe());
                tpou.getSelectionModel().select(eqp.getNom_poule());
                tcon.getSelectionModel().select(eqp.getContinent());
                tnj.setText(Integer.toString(eqp.getNbr_joueur()));
                tp.setText(Integer.toString(eqp.getNbr_participation()));
                tc.setText(Integer.toString(eqp.getNbr_coupe()));
                mod.setDisable(false);
                del.setDisable(false);
                a.setDisable(false);
                Image();

            }
        });
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(tid.getText());
        String nom_equipe = teq.getText();
        String nom_poule = tpou.getValue();
        String con = tcon.getValue();

        int nbr_joueur = Integer.parseInt(tnj.getText());
        int nbr_participation = Integer.parseInt(tp.getText());
        int nbr_coupe = Integer.parseInt(tc.getText());
        Equipe e = new Equipe(id, nom_equipe, nom_poule, con, nbr_joueur, nbr_participation, nbr_coupe, ((nbr_coupe / nbr_participation) * 100));
        ServiceEquipe se = new ServiceEquipe();
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setTitle("Equipe Modifié");
        b.setContentText("Equipe Modifié");
        b.showAndWait();
        se.Update(e);
        data.clear();
        loadDataFromDatabase(poules.getValue());
    }

    @FXML
    private void del(ActionEvent event) throws SQLException {
        ServiceEquipe se = new ServiceEquipe();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Supprission");
        alert.setContentText("Vous Voulez SUPPRIMER Equipe et ses JOUEURS:" + teq.getText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int id = Integer.parseInt(tid.getText());
            ServiceJoueur sj=new ServiceJoueur();
            sj.delete(teq.getText().toString());
            se.delete(id);
            
            tid.setText("");
            teq.setText("");
            tnj.setText("");
            tp.setText("");
            tc.setText("");
            a.setDisable(true);
            Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setTitle("Equipe supprimé");
            b.setHeaderText("Suppression");
            b.setContentText("Suppression:" + teq.getText());
            b.showAndWait();
            data.clear();
            loadDataFromDatabase(poules.getValue());
        }
    }

    private void Image() {

        File file = new File("C:\\wamp64\\www\\image\\" + teq.getText().toLowerCase() + ".jpg");

        Image image = new Image(file.toURI().toString());
        img.setImage(image);

    }

}
