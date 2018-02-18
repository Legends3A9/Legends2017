/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evenement;
import Services.EvenementServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class GestionEveController implements Initializable {

     
    @FXML
    private TableView<Evenement> tEvenement;
    @FXML
    private TableColumn<?, ?> nomEve;
    @FXML
    private TableColumn<Evenement, Date> dateEve;
    @FXML
    private TableColumn<?, ?> TypeEve;
    @FXML
    private TableColumn<?, ?> destEve;
    @FXML
    private TableColumn<?, ?> etatEve;
    @FXML
    private TableColumn<Evenement,Integer> placeEve;
    @FXML
    private TableColumn<Evenement,Double> prixEve;
    
    private ObservableList<Evenement> data;
   
    private Label lbl1;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField jNom;
    @FXML
    private JFXTextField Jdes;
    @FXML
    private JFXTextField jDuree;
    @FXML
    private JFXTextField jType;
    @FXML
    private JFXTextField jprix;
    @FXML
    private JFXTextField jnbr;
    @FXML
    private Label lbl12;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXTextArea jDesc;
    @FXML
    private TableColumn<?, ?> duree;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private AnchorPane anchor;
    private JFXTextField jImage;
    @FXML
    private JFXTextField idEve;
    @FXML
    private JFXTextField recherchelabel;
    @FXML
    private Button ajouter;
    @FXML
    private Button fileChooser;
    @FXML
    private ImageView imageView1;
    
    
    FileChooser fc = new FileChooser () ;
    File selectedFile ; 
    @FXML
    private TableColumn<?, ?> colImaage;
    @FXML
    private JFXTextField image1;
    @FXML
    private ComboBox<String> etas;
    
    @FXML
    private Button evaluation;
    @FXML
    private TableColumn<?, ?> ideeev;
    @FXML
    private Button contacter;
       
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setsCllTable();
       data = FXCollections.observableArrayList();
       loadDataFromDatabase();
       setcellValue();
       modifier.setDisable(true);
       supprimer.setDisable(true);
       anchor.setDisable(true);
       search();
      
       image();
       
       etas.getItems().addAll("disponible" ,"réservé");
       
       Services.EvenementServices es = new EvenementServices(); 
      
       List<Evenement> list = es.findAll();
       List<String> nom = new ArrayList<String>();
       List<String> type = new ArrayList<String>();
       List<String> dest = new ArrayList<String>();
       List<String> etat = new ArrayList<String>();
       
       
       etat.add("disponible");
       etat.add("reservé");
       


       for(Evenement e : list)
       {
           nom.add(e.getNomEvenement()) ;
           type.add(e.getType());
           dest.add(e.getDestination());
           
       }
      
        TextFields.bindAutoCompletion(recherchelabel, nom);
        TextFields.bindAutoCompletion(recherchelabel,type);
        TextFields.bindAutoCompletion(recherchelabel,dest);
     //   TextFields.bindAutoCompletion(rechEtat,etat);


         
       
       
        
       
       
       
       
    }    
    
    /*public void setLabel (String text) {
        this.lbl1.setText("xxxxx");
    }*/
    
    public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        recherchelabel.setOnKeyReleased(e -> {
            recherchelabel.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evenement>) evenement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((evenement.getNomEvenement().contains(newValue)) || (evenement.getNomEvenement().toLowerCase().contains(newValue))||(evenement.getType().contains(newValue)) || (evenement.getType().toLowerCase().contains(newValue))||(evenement.getDestination().contains(newValue)) || (evenement.getDestination().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tEvenement.comparatorProperty());
            tEvenement.setItems(sorteddata);
        });
    }
    
    
    
     private void loadDataFromDatabase() {
      Services.EvenementServices es = new EvenementServices(); 
      List <Evenement> evs = es.findAll();
      
      for(Evenement ev : evs )
      {
          data.add(ev);
      }
         
         
         
        tEvenement.setItems(data);
    }
     
      public void setsCllTable() {
          ideeev.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        nomEve.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        dateEve.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        destEve.setCellValueFactory(new PropertyValueFactory<>("destination"));
        TypeEve.setCellValueFactory(new PropertyValueFactory<>("type"));
        duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colImaage.setCellValueFactory(new PropertyValueFactory<>("image"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatEve.setCellValueFactory(new PropertyValueFactory<>("etat"));
        placeEve.setCellValueFactory(new PropertyValueFactory<>("nombrePlace"));
        prixEve.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        
    }
      
      private void setcellValue() {
        tEvenement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement pl = tEvenement.getItems().get(tEvenement.getSelectionModel().getSelectedIndex());
                idEve.setText(Integer.toString(pl.getIdEvenement()));
                jNom.setText(pl.getNomEvenement());
                date.setValue(pl.getDateEvenement().toLocalDate());
                Jdes.setText((pl.getDestination()));
                jType.setText(pl.getType());
                etas.setValue(pl.getEtat());
                image1.setText(pl.getImage());
                jnbr.setText(Integer.toString(pl.getNbrPlaces()));
                jprix.setText(Float.toString(pl.getPrix()));
                jDesc.setText(pl.getDescription()) ; 
                jDuree.setText(pl.getDuree());
                
                image ();
               
            
                
                
                
                modifier.setDisable(false);
                supprimer.setDisable(false);
                anchor.setDisable(false);
                
                
                
            }
        });
    }

    @FXML
    private void deleteAction(ActionEvent event) throws SQLException {
       
        
        
        int idE = Integer.valueOf(idEve.getText());
         String nomE = (jNom.getText());
         Date datefe = (Date.valueOf(date.getValue()));
         Services.EvenementServices ser = new EvenementServices();
         ser.supprimerEvenement(idE);
        /*String url = "jdbc:mysql://localhost:3306/russia2018";
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        String sql = ("Delete From `evenement`  WHERE `evenement`.`idEvenement` = (?)");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idE);*/
        
        idEve.setText("");
        jNom.setText("");
        date.setValue(null);
        Jdes.setText("");
        jType.setText("");
        etas.setValue("");
        jnbr.setText("");
        jprix.setText("");
        jDesc.setText("") ; 
        jDuree.setText("");
        
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evenement supprimé");
        alert.setHeaderText("Suppression");
        alert.setContentText("Suppression:"+nomE+"Prévu le :"+datefe);
        alert.showAndWait();
         tEvenement.refresh();
        
         
    
    
    }

    @FXML
    private void updateAction(ActionEvent event) throws SQLException, FileNotFoundException {
       
       
       /* String iii="" ;  
            
        if(selectedFile!=null) {
        iii = selectedFile.getAbsolutePath();
        }
        else if (selectedFile == null) {
            
        }*/
        
        
        /* Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setHeaderText("Modification");
        alert.setContentText("Avez vous vraimnet sur de vouloir modifier?");
       Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==ButtonType.OK){*/
        
        int idE = Integer.parseInt(idEve.getText());
        String nomE = (jNom.getText());
        Date datefe = (Date.valueOf(date.getValue()));
        String destE = Jdes.getText() ;
        String dureeE = jDuree.getText(); 
        String typeE = jType.getText(); 
        String etatE = etas.getValue(); 
        String descE = jDesc.getText(); 
        String image = image1.getText() ; 
        float prixE = Float.parseFloat(jprix.getText());
        int nbrP = Integer.parseInt(jnbr.getText()) ;
        Evenement even = new Evenement(); 
        even.setIdEvenement(idE);
        even.setNomEvenement(nomE);
        even.setDateEvenement(datefe);
        even.setDestination(destE);
        even.setDescription(descE);
        even.setDuree(dureeE);
        even.setType(typeE);
        even.setEtat(etatE);
        even.setPrix(prixE);
        even.setImage(image);
        even.setNbrPlaces(nbrP);
       
      Services.EvenementServices se = new EvenementServices();
       se.modifierEvenement(even, idE);

     
        data.clear();
        loadDataFromDatabase();
        Alert alert2 = new Alert(AlertType.INFORMATION);
        alert2.setTitle("Modification effectué avec succées");
        alert2.setHeaderText("Modification");
        alert2.setContentText("Modification:"+nomE+"Prévu le :"+datefe);
        alert2.showAndWait();
        tEvenement.refresh();
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification effectué avec succées");
        alert.setContentText("xxxxx");
        alert.showAndWait();*/
      //  }
        
    }

/*    private void recherchebtn(ActionEvent event) {
        Evenement ev = new Evenement();
        Services.EvenementServices ser = new EvenementServices();
        ev.setNomEvenement(recherchelabel.getText());
        Evenement even = ser.recher(recherchelabel.getText());
        if (even == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message de recherche ");
            alert.setContentText(" Cete évenement n'existe pas  ");
            alert.showAndWait();
        } else {
            
                idEve.setText(Integer.toString(ev.getIdEvenement()));
                jNom.setText(ev.getNomEvenement());
                date.setValue(ev.getDateEvenement().toLocalDate());
                Jdes.setText((ev.getDestination()));
                jType.setText(ev.getType());
                etas.setValue(ev.getEtat());
                jnbr.setText(Integer.toString(ev.getNbrPlaces()));
                jprix.setText(Double.toString(ev.getPrix()));
                jDesc.setText(ev.getDescription()) ; 
                jDuree.setText(ev.getDuree());
    }
     
}*/

    @FXML
    private void Ajout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("Ajout.fxml")));
                Parent root = loader.load();
                
                

                Scene scene = new Scene(root);

                Stage stage = new Stage () ;
                stage.setTitle("publier un événement");
                
                stage.initModality(Modality.WINDOW_MODAL);

                stage.setScene(scene);
                stage.show();
        
    }

    @FXML
    private void filaAction(ActionEvent event) throws FileNotFoundException {
         
        fc.setInitialDirectory(new File("C:\\Users\\boussandel\\branche2\\Desktop\\images"));
       selectedFile = fc.showOpenDialog(null);
        
        if (selectedFile!= null ) {
            FileInputStream input = new FileInputStream(selectedFile);
            Image image = new Image(input);
            image1.setText(selectedFile.getName());
            imageView1.setImage(image);
            fileChooser.setStyle("-fx-text-fill:green;");

            
    }
        else {
            lbl12.setText("Aucun fichier n'est selctionnée !! ");
        }
    }

    @FXML
    private void fileChouserAction() throws FileNotFoundException {
        
            /*FileInputStream input = new FileInputStream(new File(iimage.getText()));
            Image image = new Image(input);
            imageView1.setImage(image);*/
    }

    @FXML
    private void mettreImages(MouseEvent event) {
    }
    
    
    private void image () {
        File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images",image1.getText());
        
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
         
        
    }

    @FXML
    private void passerEvaluationAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("ConsulterEvaluation.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setScene(scene);
                stage.show();
           }

    private void actAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("GestionEve.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setScene(scene);
                stage.show();
         
        
        
    }

    @FXML
    private void contacterAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("Contacter.fxml")));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage () ;
                stage.setScene(scene);
                stage.show();
    }

   
}