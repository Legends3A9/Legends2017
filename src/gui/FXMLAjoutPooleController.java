package gui;

import javafx.event.EventHandler;
import Entités.Equipe;
import Entités.Joueur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import Services.ServiceEquipe;
import org.controlsfx.control.textfield.TextFields;
import Services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author Dambo
 */
public class FXMLAjoutPooleController implements Initializable {

    @FXML
    private ComboBox<String> pooles;
    @FXML
    private TextField nome1;
    @FXML
    private TextField p1;
    @FXML
    private TextField c1;
    @FXML
    private ComboBox<String> con1;
    @FXML
    private Button add1;
    @FXML
    private TextField nome2;
    @FXML
    private TextField p2;
    @FXML
    private TextField c2;
    @FXML
    private ComboBox<String> con2;
    @FXML
    private Button add2;
    @FXML
    private TextField nome3;
    @FXML
    private TextField p3;
    @FXML
    private TextField c3;
    @FXML
    private ComboBox<String> con3;
    @FXML
    private Button add3;
    @FXML
    private TextField nome4;
    @FXML
    private TextField p4;
    @FXML
    private TextField c4;
    @FXML
    private ComboBox<String> con4;
    @FXML
    private Button add4;
    @FXML
    private TextField n1;
    @FXML
    private TextField n2;
    @FXML
    private TextField n3;
    @FXML
    private TextField n4;
    @FXML
    private HBox h4;
    @FXML
    private HBox h3;
    @FXML
    private HBox h2;
    @FXML
    private HBox h1;
    boolean ex = false;

    public List<String> liste = Arrays.asList("Egypte", "Maroc", "Nijeria", "Senegal", "Tunisie", "Arabie Saoudite", "Australie", "Japon", "Republique de Corée", "Iran", "Allemagne", "Angleterre", "Belgique", "Croatie", "Danemark", "Danemark", "Espagne", "Costa Rica", "Mexique", "Panama", "France", "Islande", "Pologne", "Portugal", "Russie", "Serbie", "Suede", "Suisse", "Argentine", "Bresil", "Peru", "Uruguay");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        n1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        n2.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        n3.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        n4.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        p1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        p2.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        p3.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        p4.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        c1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        c2.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        c3.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        c4.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation());
        nome1.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation());
        nome3.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation());
        nome2.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation());
        nome4.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation());

        pooles.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
        con1.getItems().addAll("Afrique", "Asie", "Europe", "Amérique du Nord", "Amérique du Sud");
        con1.getSelectionModel().select("Afrique");
        con2.getItems().addAll("Afrique", "Asie", "Europe", "Amérique du Nord", "Amérique du Sud");
        con2.getSelectionModel().select("Afrique");
        con3.getItems().addAll("Afrique", "Asie", "Europe", "Amérique du Nord", "Amérique du Sud");
        con3.getSelectionModel().select("Afrique");
        con4.getItems().addAll("Afrique", "Asie", "Europe", "Amérique du Nord", "Amérique du Sud");
        con4.getSelectionModel().select("Afrique");
        TextFields.bindAutoCompletion(nome1, liste);
        TextFields.bindAutoCompletion(nome2, liste);
        TextFields.bindAutoCompletion(nome3, liste);
        TextFields.bindAutoCompletion(nome4, liste);
        pooles.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    nome1.setStyle("-fx-text-fill: black;");
                    c1.setStyle("-fx-text-fill: black;");
                    n1.setStyle("-fx-text-fill: black;");
                    p1.setStyle("-fx-text-fill: black;");
                    c1.clear();
                    nome1.clear();
                    p1.clear();
                    n1.clear();
                    add1.setStyle(" -fx-base: #ebebeb;");
                    add1.setText("Ajouter");
                    nome2.setStyle("-fx-text-fill: black;");
                    c2.setStyle("-fx-text-fill: black;");
                    n2.setStyle("-fx-text-fill: black;");
                    p2.setStyle("-fx-text-fill: black;");
                    c2.clear();
                    nome2.clear();
                    p2.clear();
                    n2.clear();
                    add2.setStyle(" -fx-base: #ebebeb;");
                    add2.setText("Ajouter");
                    nome3.setStyle("-fx-text-fill: black;");
                    c3.setStyle("-fx-text-fill: black;");
                    n3.setStyle("-fx-text-fill: black;");
                    p3.setStyle("-fx-text-fill: black;");
                    c3.clear();
                    nome3.clear();
                    p3.clear();
                    n3.clear();
                    add3.setStyle(" -fx-base: #ebebeb;");
                    add3.setText("Ajouter");
                    nome4.setStyle("-fx-text-fill: black;");
                    c4.setStyle("-fx-text-fill: black;");
                    n4.setStyle("-fx-text-fill: black;");
                    p4.setStyle("-fx-text-fill: black;");
                    c4.clear();
                    nome4.clear();
                    p4.clear();
                    n4.clear();
                    add4.setStyle(" -fx-base: #ebebeb;");
                    add4.setText("Ajouter");
                    nome4.setEditable(true);
                    con4.setEditable(true);
                    n4.setEditable(true);
                    p4.setEditable(true);
                    c4.setEditable(true);
                    add4.setDisable(false);
                    nome3.setEditable(true);
                    con3.setEditable(true);
                    n3.setEditable(true);
                    p3.setEditable(true);
                    c3.setEditable(true);
                    add3.setDisable(false);
                    nome1.setEditable(true);
                    con1.setEditable(true);
                    n1.setEditable(true);
                    p1.setEditable(true);
                    c1.setEditable(true);
                    add1.setDisable(false);
                    nome2.setEditable(true);
                    con2.setEditable(true);
                    n2.setEditable(true);
                    p2.setEditable(true);
                    c2.setEditable(true);
                    add2.setDisable(false);
                    ServiceEquipe se = new ServiceEquipe();
                    int i = se.nbrequipePoule(t1);
                    switch (i) {
                        case 4:
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Dialog");
                            alert.setContentText("Poule FULL !!");
                            alert.showAndWait();
                            h4.setVisible(false);
                            h3.setVisible(false);
                            h2.setVisible(false);
                            h1.setVisible(false);
                            break;
                        case 1:
                            h4.setVisible(false);
                            h3.setVisible(true);
                            h2.setVisible(true);
                            h1.setVisible(true);
                            break;
                        case 2:
                            h4.setVisible(false);
                            h3.setVisible(false);
                            h2.setVisible(true);
                            h1.setVisible(true);
                            break;
                        case 3:
                            h4.setVisible(false);
                            h3.setVisible(false);
                            h2.setVisible(false);
                            h1.setVisible(true);
                            break;
                        case 0:
                            h4.setVisible(true);
                            h3.setVisible(true);
                            h2.setVisible(true);
                            h1.setVisible(true);
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLAjoutPooleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // TODO
    }

    @FXML
    public void add1(ActionEvent event) throws IOException {
        String nom_poule = pooles.getValue();
        String nom_equipe = nome1.getText();
        String continent = con1.getValue();
        int nbr_joueur = Integer.parseInt(n1.getText());
        int participation = Integer.parseInt(p1.getText());
        int coupes = Integer.parseInt(c1.getText());
        ServiceEquipe se = new ServiceEquipe();
        Equipe e1 = new Equipe(nom_equipe, nom_poule, continent, nbr_joueur, coupes, participation);
        List<Equipe> eqps = se.getAll();
        boolean ha = false;

        for (Equipe e : eqps) {
            if (e.getNom_equipe().equals(e1.getNom_equipe()) == true) {
                ha = true;
            }
        }
        if (nome1.getText().isEmpty() || n1.getText().isEmpty() || p1.getText().isEmpty() || c1.getText().isEmpty()) {
            Alert D = new Alert(Alert.AlertType.WARNING);
            D.setTitle("Remplir les Champs");
            D.setContentText("SVP Remplissez touts les champs");
            D.showAndWait();
        } else if (ha == false && liste.contains(nome1.getText())) {

            se.add(e1);
            parsing(nome1.getText().toString());
            nome1.setEditable(false);
            nome1.setStyle("-fx-text-fill: green;");
            add1.setStyle(" -fx-base: #b6e7c9;");
            add1.setText("Ajouté !");
            con1.setEditable(false);
            n1.setEditable(false);
            n1.setStyle("-fx-text-fill: green;");
            p1.setStyle("-fx-text-fill: green;");
            p1.setEditable(false);
            c1.setEditable(false);
            c1.setStyle("-fx-text-fill: green;");
            add1.setDisable(true);
        } else {

            if (liste.contains(nome1.getText()) == false) {
                Alert b = new Alert(Alert.AlertType.WARNING);
                b.setTitle("Equipe inValide");
                b.setContentText("Equipe Invaldie Choisir une Equipe du Liste");
                b.showAndWait();
            } else {
                if (ha == true) {
                    Alert C = new Alert(Alert.AlertType.WARNING);
                    C.setTitle("Equipe EXISTANTE");
                    C.setContentText("Equipe Existe Déja ");
                    C.showAndWait();
                }
            }

        }
    }

    @FXML
    public void add2(ActionEvent event) {
        String nom_poule = pooles.getValue();
        String nom_equipe = nome2.getText();
        String continent = con2.getValue();
        int nbr_joueur = Integer.parseInt(n2.getText());
        int participation = Integer.parseInt(p2.getText());
        int coupes = Integer.parseInt(c2.getText());
        ServiceEquipe se = new ServiceEquipe();
        Equipe e1 = new Equipe(nom_equipe, nom_poule, continent, nbr_joueur, coupes, participation);
        List<Equipe> eqps = se.getAll();
        boolean ha = false;

        for (Equipe e : eqps) {
            if (e.getNom_equipe().equals(e1.getNom_equipe()) == true) {
                ha = true;
            }
        }
        if (ha == false && liste.contains(nome2.getText())) {

            se.add(e1);
            nome2.setEditable(false);
            nome2.setStyle("-fx-text-fill: green;");
            add2.setStyle(" -fx-base: #b6e7c9;");
            add2.setText("Ajouté !");
            con2.setEditable(false);
            n2.setEditable(false);
            n2.setStyle("-fx-text-fill: green;");
            p2.setStyle("-fx-text-fill: green;");
            p2.setEditable(false);
            c2.setEditable(false);
            c2.setStyle("-fx-text-fill: green;");
            add2.setDisable(true);
        } else {
            if (liste.contains(nome1.getText()) == false) {
                Alert b = new Alert(Alert.AlertType.WARNING);
                b.setTitle("Equipe inValide");
                b.setContentText("Equipe Invaldie Choisir une Equipe du Liste");
                b.showAndWait();
            } else {
                if (ha == true) {
                    Alert C = new Alert(Alert.AlertType.WARNING);
                    C.setTitle("Equipe EXISTANTE");
                    C.setContentText("Equipe Existe Déja ");
                    C.showAndWait();
                }
            }

        }

    }

    @FXML
    public void add3(ActionEvent event) {
        String nom_poule = pooles.getValue();
        String nom_equipe = nome3.getText();
        String continent = con3.getValue();
        int nbr_joueur = Integer.parseInt(n3.getText());
        int participation = Integer.parseInt(p3.getText());
        int coupes = Integer.parseInt(c3.getText());
        ServiceEquipe se = new ServiceEquipe();
        Equipe e1 = new Equipe(nom_equipe, nom_poule, continent, nbr_joueur, coupes, participation);
        List<Equipe> eqps = se.getAll();
        boolean ha = false;

        for (Equipe e : eqps) {
            if (e.getNom_equipe().equals(e1.getNom_equipe()) == true) {
                ha = true;
            }
        }
        if (ha == false && liste.contains(nome3.getText())) {

            se.add(e1);
            nome3.setEditable(false);
            nome3.setStyle("-fx-text-fill: green;");
            add3.setStyle(" -fx-base: #b6e7c9;");
            add3.setText("Ajouté !");
            con3.setEditable(false);
            n3.setEditable(false);
            n3.setStyle("-fx-text-fill: green;");
            p3.setStyle("-fx-text-fill: green;");
            p3.setEditable(false);
            c3.setEditable(false);
            c3.setStyle("-fx-text-fill: green;");
            add3.setDisable(true);
        } else {
            if (liste.contains(nome1.getText()) == false) {
                Alert b = new Alert(Alert.AlertType.WARNING);
                b.setTitle("Equipe inValide");
                b.setContentText("Equipe Invaldie Choisir une Equipe du Liste");
                b.showAndWait();
            } else {
                if (ha == true) {
                    Alert C = new Alert(Alert.AlertType.WARNING);
                    C.setTitle("Equipe EXISTANTE");
                    C.setContentText("Equipe Existe Déja ");
                    C.showAndWait();
                }
            }

        }
    }

    @FXML
    public void add4(ActionEvent event) {
        String nom_poule = pooles.getValue();
        String nom_equipe = nome4.getText();
        String continent = con4.getValue();
        int nbr_joueur = Integer.parseInt(n4.getText());
        int participation = Integer.parseInt(p4.getText());
        int coupes = Integer.parseInt(c4.getText());
        ServiceEquipe se = new ServiceEquipe();
        Equipe e1 = new Equipe(nom_equipe, nom_poule, continent, nbr_joueur, coupes, participation);
        List<Equipe> eqps = se.getAll();
        boolean ha = false;

        for (Equipe e : eqps) {
            if (e.getNom_equipe().equals(e1.getNom_equipe()) == true) {
                ha = true;
            }
        }
        if (ha == false && liste.contains(nome4.getText())) {

            se.add(e1);
            nome4.setEditable(false);
            nome4.setStyle("-fx-text-fill: green;");
            add4.setStyle(" -fx-base: #b6e7c9;");
            add4.setText("Ajouté !");
            con4.setEditable(false);
            n4.setEditable(false);
            n4.setStyle("-fx-text-fill: green;");
            p4.setStyle("-fx-text-fill: green;");
            p4.setEditable(false);
            c4.setEditable(false);
            c4.setStyle("-fx-text-fill: green;");
            add4.setDisable(true);
        } else {
            if (liste.contains(nome1.getText()) == false) {
                Alert b = new Alert(Alert.AlertType.WARNING);
                b.setTitle("Equipe inValide");
                b.setContentText("Equipe Invaldie Choisir une Equipe du Liste");
                b.showAndWait();
            } else {
                if (ha == true) {
                    Alert C = new Alert(Alert.AlertType.WARNING);
                    C.setTitle("Equipe EXISTANTE");
                    C.setContentText("Equipe Existe Déja ");
                    C.showAndWait();
                }
            }

        }
    }

    @FXML
    private void poolesAction(ActionEvent event) {
    }

    public EventHandler<KeyEvent> numeric_Validation() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (e.getCharacter().matches("[0-9]")) {
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

    public EventHandler<KeyEvent> letter_Validation() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();

                if (e.getCharacter().matches("[A-Za-z]")) {
                } else {
                    e.consume();
                }
            }
        };
    }
    public void parsing(String nom_equipe) throws IOException{
         FileInputStream file = new FileInputStream(new File("C:\\Users\\Dambo\\Desktop\\joueur.xls")); 
		HSSFWorkbook workbook = new HSSFWorkbook(file); 
		HSSFSheet sheet = workbook.getSheetAt(0); 
        Row row;
        boolean t=false;
        ServiceJoueur sj=new ServiceJoueur();
        for(int i=1; i<=sheet.getLastRowNum(); i++){  //points to the starting of excel i.e excel first row
            row = (Row) sheet.getRow(i);  //sheet number
            
            if(row.getCell(2).toString().equals(nom_equipe)){
                sj.add(new Joueur(row.getCell(2).toString(), row.getCell(9).toString(), row.getCell(10).toString()));
          
                t=true;
            }
          
	  		
        }
         if(t==true){
             Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("Les Joueurs d'Esuipe ont été ajouté");
                b.setContentText("Les Joueurs d'Esuipe ont été ajouté");
                b.showAndWait();}
           else{
             Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setTitle("L'Esuipe a été ajouté");
                b.setContentText("l'Equipe a été ajouté mais les informations des joueurs ne sont pas disponible pour le moment ");
                b.showAndWait();
           }
		file.close();
	}

    
    }
