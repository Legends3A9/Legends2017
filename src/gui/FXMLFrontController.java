/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import Entités.Equipe;
import Entités.Joueur;
import Entités.Pari;
import Entités.Utilisateur;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

import Services.ServiceEquipe;
import Services.ServiceJoueur;
import Services.ServicePari;

/**
 * FXML Controller class
 *
 * @author Dambo
 */
public class FXMLFrontController extends ListView<Equipe> implements Initializable {

    ObservableList<Equipe> data;
    ObservableList<Joueur> dataj;

    @FXML
    private JFXListView<Equipe> list;
    @FXML
    private JFXComboBox<String> poules;
    private BarChart<?, ?> ContinentBar;
    @FXML
    private ImageView flag;
    private ImageView cont;
    @FXML
    private Label eq;
    private Label ct;
    @FXML
    private PieChart pie;
    @FXML
    private ImageView i;
    @FXML
    private Label vote;
    @FXML
    private Label ev;
    @FXML
    private JFXListView<Joueur> listjoueur;
    @FXML
    private Label tj;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public class Poules extends ListCell<Equipe> {

        private final GridPane gridPane = new GridPane();
        private final ImageView continent = new ImageView();
        private final Label continents = new Label();
        private final Label equipe = new Label();
        private final ImageView cup = new ImageView();
        private final Label descriptionLabel = new Label();
        private final ImageView flag = new ImageView();
        private final AnchorPane content = new AnchorPane();
        Button pari = new Button("VOTEZ");

        public Poules() {
            flag.setFitWidth(75);
            flag.setPreserveRatio(true);
            GridPane.setConstraints(flag, 0, 0, 1, 3);
            GridPane.setValignment(flag, VPos.TOP);
            GridPane.setConstraints(pari, 3, 1);
            GridPane.setValignment(pari, VPos.CENTER);
            equipe.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
            GridPane.setConstraints(equipe, 1, 0);
            continents.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
            GridPane.setConstraints(continents, 2, 0);
            continent.setFitWidth(22);
            continent.setPreserveRatio(true);
            GridPane.setConstraints(continent, 3, 0);
            GridPane.setValignment(continent, VPos.CENTER);
            File file = new File("C:\\wamp64\\www\\image\\cup.png");
            Image image = new Image(file.toURI().toString());
            cup.setImage(image);
            cup.setFitWidth(15);
            cup.setFitHeight(15);
            descriptionLabel.setStyle("-fx-opacity: 0.75;");
            descriptionLabel.setGraphic(cup);
            GridPane.setConstraints(descriptionLabel, 1, 1);
            GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE);

            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
            gridPane.setHgap(6);
            gridPane.setVgap(6);
            gridPane.getChildren().setAll(flag, equipe, continents, continent, descriptionLabel, pari);
            AnchorPane.setTopAnchor(gridPane, 0d);
            AnchorPane.setLeftAnchor(gridPane, 0d);
            AnchorPane.setBottomAnchor(gridPane, 0d);
            AnchorPane.setRightAnchor(gridPane, 0d);

            content.getChildren().add(gridPane);
        }

        @Override
        protected void updateItem(Equipe item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(null);
            setText(null);
            setContentDisplay(ContentDisplay.LEFT);

            if (!empty && item != null) {
                continents.setText(item.getContinent());
                equipe.setText(item.getNom_equipe());
                File file = new File("C:\\wamp64\\www\\image\\" + item.getNom_equipe().toLowerCase() + ".jpg");

                Image image = new Image(file.toURI().toString());
                File file2 = new File("C:\\wamp64\\www\\image\\" + item.getContinent().toLowerCase() + ".jpg");

                Image image2 = new Image(file2.toURI().toString());
                continent.setImage(image2);
                flag.setImage(image);
                descriptionLabel.setText(String.format(", %d Participation, %d Coupes", item.getNbr_participation(), item.getNbr_coupe()));
                ServicePari pr = new ServicePari();
                List<Pari> paris = new ArrayList();
                boolean p = false;
                paris = pr.getAllparuser(Utilisateur.getIdd());
                for (Pari a : paris) {
                    if (a.getNom_equipe().equals(item.getNom_equipe())) {
                        p = true;
                        pari.setStyle(" -fx-base: green;");

                    }
                }

                if (p == true) {
                    pari.setDisable(true);
                }
                pari.setOnAction((e) -> {
                    ServicePari ps = new ServicePari();
                    Equipe ab = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                    String nom = ab.getNom_equipe();
                    ps.add(new Pari(Utilisateur.getIdd(), nom));
                    pari.setDisable(true);
                    pari.setStyle(" -fx-base: green;");

                });
                setText(null);
                setGraphic(content);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    public class Joueurs extends ListCell<Joueur> {

        private final GridPane gridPane = new GridPane();
        private final ImageView continent = new ImageView();
        private final Label continents = new Label();
        private final Label equipe = new Label();
        private final ImageView cup = new ImageView();
        private final Label descriptionLabel = new Label();
        private final ImageView flag = new ImageView();
        private final AnchorPane content = new AnchorPane();
        

        public Joueurs() {
            flag.setFitWidth(75);
            flag.setPreserveRatio(true);
            GridPane.setConstraints(flag, 0, 0, 1, 3);
            GridPane.setValignment(flag, VPos.TOP);
            equipe.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
            GridPane.setConstraints(equipe, 1, 0);
            continents.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;");
            GridPane.setConstraints(continents, 2, 0);
            continent.setFitWidth(22);
            continent.setPreserveRatio(true);
            GridPane.setConstraints(continent, 3, 0);
            GridPane.setValignment(continent, VPos.CENTER);
            File file = new File("C:\\wamp64\\www\\image\\cup.png");
            Image image = new Image(file.toURI().toString());
            cup.setImage(image);
            cup.setFitWidth(15);
            cup.setFitHeight(15);
            descriptionLabel.setStyle("-fx-opacity: 0.75;");
            GridPane.setConstraints(descriptionLabel, 1, 1);
            GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE);

            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
            gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
            gridPane.setHgap(6);
            gridPane.setVgap(6);
            gridPane.getChildren().setAll(flag, equipe, continents, continent, descriptionLabel);
            AnchorPane.setTopAnchor(gridPane, 0d);
            AnchorPane.setLeftAnchor(gridPane, 0d);
            AnchorPane.setBottomAnchor(gridPane, 0d);
            AnchorPane.setRightAnchor(gridPane, 0d);

            content.getChildren().add(gridPane);
        }

        @Override
        protected void updateItem(Joueur item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(null);
            setText(null);
            setContentDisplay(ContentDisplay.LEFT);

            if (!empty && item != null) {

                continents.setText(item.getNom_equipe());
                equipe.setText(item.getNom_joueur());
                File file = new File("C:\\wamp64\\www\\image\\" + item.getPost() + ".png");

                Image image = new Image(file.toURI().toString());
                File file2 = new File("C:\\wamp64\\www\\image\\" + item.getNom_equipe().toLowerCase() + ".jpg");

                Image image2 = new Image(file2.toURI().toString());
                continent.setImage(image2);
                flag.setImage(image);
                descriptionLabel.setText(item.getPost());

                setText(null);
                setGraphic(content);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        poules.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
        data = FXCollections.observableArrayList();
        dataj = FXCollections.observableArrayList();

        poules.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                try {
                    data = FXCollections.observableArrayList();
                    loadDataFromDatabase(t1);
                    setcellValue();
                    list.setCellFactory(lv -> new Poules());

                } catch (SQLException ex) {
                    Logger.getLogger(FXMLFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void loadDataFromDatabase(String Poule) throws SQLException {
        List<Equipe> equipes = new ArrayList<>();
        ServiceEquipe se = new ServiceEquipe();

        equipes = se.equipeParPoule(Poule);
        for (Equipe e : equipes) {
            data.add(e);
        }
        list.setItems(data);
    }

    private void loadDataFromDatabaseJoueur(String nom_equipe) throws SQLException {
        List<Joueur> jour = new ArrayList<>();
        ServiceJoueur js = new ServiceJoueur();

        jour = js.getParEquipe(nom_equipe);
        for (Joueur j : jour) {
            dataj.add(j);
        }
        listjoueur.setItems(dataj);
    }

    private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Equipe eqp = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                File file = new File("C:\\wamp64\\www\\image\\" + eqp.getNom_equipe().toLowerCase() + ".jpg");
                tj.setText(eqp.getNom_equipe());
                Image image = new Image(file.toURI().toString());
                //File file2 = new File("C:\\wamp64\\www\\image\\" + eqp.getContinent().toLowerCase() + ".jpg");

                //  Image image2 = new Image(file2.toURI().toString());
                //cont.setImage(image2);
                flag.setImage(image);
                eq.setText(eqp.getNom_equipe());
                ServicePari sp = new ServicePari();
                int votePareqp = (int) sp.getAll().stream().filter(e -> e.getNom_equipe().equals(eqp.getNom_equipe())).count();
                int votetot = (int) sp.getAll().stream().count();
                vote.setText(" gagnera Russia2018:(Vote)  " + (((double) votePareqp / (double) votetot) * 100) + "%");
                //ct.setText(eqp.getContinent());
                ObservableList<PieChart.Data> pieChart;
                pieChart = FXCollections.observableArrayList(
                        new PieChart.Data("Gagné" + (double) ((double) eqp.getNbr_coupe() / (double) eqp.getNbr_participation()) * 100 + "%", (double) ((double) eqp.getNbr_coupe() / (double) eqp.getNbr_participation()) * 100),
                        new PieChart.Data("Perdu", (double) ((double) (eqp.getNbr_participation() - eqp.getNbr_coupe()) / (double) eqp.getNbr_participation()) * 100)
                );
                pie.setData(pieChart);
                File file3 = new File("C:\\wamp64\\www\\image\\cup.png");
                Image image3 = new Image(file3.toURI().toString());
                i.setImage(image3);
                i.setFitWidth(25);
                vote.setGraphic(i);
                dataj = FXCollections.observableArrayList();
                try {
                    dataj = FXCollections.observableArrayList();
                    loadDataFromDatabaseJoueur(eqp.getNom_equipe());
                    setcellValue();
                    listjoueur.setCellFactory(lv -> new Joueurs());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
}
