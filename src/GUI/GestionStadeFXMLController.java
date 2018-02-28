/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Stade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import service.StadeService;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class GestionStadeFXMLController implements Initializable {
    @FXML
    private TableColumn<?, ?> ref;
    @FXML
    private TableColumn<?, ?> nom_stade;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> date_construction;
    @FXML
    private TableColumn<?, ?> surface;
    @FXML
    private TableColumn<?, ?> capacite;
    @FXML
    private TableColumn<?, ?> image;
    private ObservableList<Stade> data;
    @FXML
    private TableView<Stade> tableview;
    @FXML
    private TextField gref;
    @FXML
    private TextField gnom_stade;
    @FXML
    private TextField gregion;
    @FXML
    private TextField gadresse;
    @FXML
    private TextField g_surface;
    @FXML
    private TextField g_capacite;
    @FXML
    private TextField gimage;
    @FXML
    private DatePicker gdate_construction;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private ImageView imageview;
    @FXML
    private Button imagea;
    FileChooser fc = new FileChooser () ;
    File selectedFile ; 
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setsCllTable();
        loadDataFromDatabase();
        setcellValue();
        search();
        
        
            
        
        
        
    }    
    
    
    private void loadDataFromDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/russie2018";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM stade");
            while (rs.next()) {
                
                int ref_e = rs.getInt("ref");
                String nom_stadeE = rs.getString("nom_stade");
                
                String regionE = rs.getString("region"); 
                String adresseE = rs.getString("adresse"); 
                Date date_constructionE = rs.getDate("date_construction");
                
                
                String surfaceE = rs.getString("surface");
                Integer capaciteE ;
                capaciteE = rs.getInt("capacite");
                String imageE = rs.getString("image");
                
                
                data.add(new Stade(ref_e,nom_stadeE,regionE,adresseE,date_constructionE,surfaceE,capaciteE,imageE));
                System.out.println("recup table view ok !");
                
            }
            
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        tableview.setItems(data);
         
            
        
        
    }
    public void setsCllTable() {
        ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        nom_stade.setCellValueFactory(new PropertyValueFactory<>("nom_stade"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        date_construction.setCellValueFactory(new PropertyValueFactory<>("date_construction"));
        surface.setCellValueFactory(new PropertyValueFactory<>("surface"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
    }
    private void setcellValue() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
             
            
            @Override
            public void handle(MouseEvent event) {
                Stade pl = tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
                gref.setText(Integer.toString(pl.getRef()));
                gnom_stade.setText(pl.getNom_stade());
                
                gregion.setText((pl.getRegion()));
                gadresse.setText(pl.getAdresse());
                gdate_construction.setValue(pl.getDate_construction().toLocalDate());
                g_surface.setText(pl.getSurface());
                g_capacite.setText(Integer.toString(pl.getCapacite()));
                gimage.setText((pl.getImage()));
                Image();
               
            }
        }); }
    private void Image(){
         
       
           
            File file = new File("C:\\Users\\melek\\Desktop\\Images\\",gimage.getText());
            
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
        

    
    }
    
    
    @FXML
    private void ActionSupprimerStade(ActionEvent event) {
        String nom_stadeE = (gnom_stade.getText());
         
         service.StadeService ser = new StadeService();
         ser.DeletStadeByNom(nom_stadeE);
        
        
        gref.setText("");
        gnom_stade.setText("");
        gregion.setText("");
        gadresse.setText("");
        gdate_construction.setValue(null);
        g_surface.setText("");
        g_capacite.setText("");
        gimage.setText("");
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stade supprimé");
        alert.setHeaderText("Suppression");
      
        alert.showAndWait();
    }

    @FXML
    private void ActionModifierStade(ActionEvent event) throws SQLException {
       
        
         
        
        int refE = Integer.parseInt(gref.getText());
        String nom_stadeE = (gnom_stade.getText());
        
        String regionE = gregion.getText() ;
        String adresseE = gadresse.getText(); 
        Date date_constructionE = (Date.valueOf(gdate_construction.getValue()));
        String surfaceE = g_surface.getText(); 
        int capaciteE = Integer.parseInt(g_capacite.getText());
        String imageE = gimage.getText(); 
        Stade s1 =new Stade(refE,nom_stadeE,regionE,adresseE,date_constructionE,surfaceE,capaciteE,imageE);
        
 
        service.StadeService ser = new StadeService();
        ser.updateStade(s1);
        
        gref.setText("");
        gnom_stade.setText("");
        gregion.setText("");
        gadresse.setText("");
        gdate_construction.setValue(null);
        g_surface.setText("");
        g_capacite.setText("");
        gimage.setText("");
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setHeaderText("Modification");
       
        alert.showAndWait();
    }

    @FXML
    private void ImageAction(ActionEvent event) {
        
        fc.setInitialDirectory(new File("C:\\Users\\melek\\Desktop\\Images"));
       selectedFile = fc.showOpenDialog(null);
        
        if (selectedFile!= null ) {
            String a;
            a=(selectedFile.getName());
            gimage.setText(a);
            
            
        }
        Image();
        
    }
    
    public void search() {
        FilteredList<Stade> filterdata = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Stade>) stade -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (((stade.getNom_stade().contains(newValue)) || (stade.getNom_stade().toLowerCase().contains(newValue)))||((stade.getAdresse().contains(newValue)) || (stade.getAdresse().toLowerCase().contains(newValue)))) {
                        return true;
                    }
                    
                    return false;
                });
            });
            SortedList<Stade> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableview.comparatorProperty());
            tableview.setItems(sorteddata);
        });
    }
    
    
}

    

