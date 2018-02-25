/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evaluation;
import Entités.Evenement;
import Entités.Participation;
import Entités.Utilisateur;
import Services.EvaluationService;
import Services.EvenementServices;
import Services.ParticipationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class FrontMembrePubController implements Initializable {

    @FXML
    private ListView<Evenement> list;
    ObservableList<Evenement> data ;
    @FXML
    private Button modif;
    @FXML
    private Button supprimer;
    @FXML
    private JFXTextField jnom;
    @FXML
    private DatePicker jdate;
    @FXML
    private ComboBox<String> jduree;
    @FXML
    private JFXTextField jtype;
    @FXML
    private JFXTextField jnombre;
    @FXML
    private JFXTextField jprix;
    @FXML
    private JFXTextField jdest;
    @FXML
    private JFXTextArea jdesc;
    @FXML
    private ImageView jimage;
    @FXML
    private Button retour;
    @FXML
    private JFXButton filechooser;
    @FXML
    private ComboBox<String> jetatt;
    @FXML
    private Label lblid;
    @FXML
    private JFXTextField path;
    
        FileChooser fc = new FileChooser () ;
         File selectedFile ; 


    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        
        jetatt.getItems().addAll("disponible" ,"réservé");
        jduree.getItems().addAll("Quelque heures","une journée","Plus Q'une journée");
        
        Services.EvenementServices es = new Services.EvenementServices();
        
        
       
                  data = FXCollections.observableArrayList();
       

            try {
                      data = FXCollections.observableArrayList();
                       loadDataFromDatabase();
                      // setcellValue();
                            list.setCellFactory(lv -> new MembreFrontController.Evenements());

            } catch (SQLException ex) {
                Logger.getLogger(MembreFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }        
            
      setcellValue();
      
      
        //    search();
                 
           // participer.setDisable(true);
  
            
            
            
            
            
            
            
    }
    
    
            
    private void loadDataFromDatabase() throws SQLException {
       List<Evenement> evt = new ArrayList<>();
      
           EvenementServices se=new EvenementServices();
     int id = Utilisateur.getIdd();
        evt=   se.mesPubs(id);
       for(Evenement e : evt)
            {
                data.add(e); 
               
            }
        list.setItems(data);
       
      
                }
    private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement eqp = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                
                    File file = new File("C:\\Users\\boussandel\\branche2\\Desktop\\images\\"+eqp.getImage());
            
        Image image = new Image(file.toURI().toString());
        lblid.setText(Integer.toString(eqp.getIdEvenement()));
         jnom.setText(eqp.getNomEvenement());
         jdate.setValue(eqp.getDateEvenement().toLocalDate());
         jdesc.setText(eqp.getDescription());
         jdest.setText(eqp.getDestination());
         jduree.setValue(eqp.getDuree());
         jtype.setText(eqp.getType());
          path.setText(file.getAbsolutePath()); 
         
         jetatt.setValue(eqp.getEtat());
                  
         jnombre.setText(Integer.toString(eqp.getNbrPlaces()));
         jprix.setText(Float.toString(eqp.getPrix()));
         
       /*  if(participeDéja()==true) {
           
           participer.setDisable(true);


         }
         
         else {
             participer.setDisable(false);
         }
        */
        Services.EvaluationService ess = new EvaluationService(); 
        Double moy = ess.moyByName(eqp.getIdEvenement());
        
       //  rat.setRating(moy);
       //  rat.setPartialRating(true);
         
            
        Image image2 = new Image(file.toURI().toString());
            
            jimage.setImage(image2); 
            jimage.setFitHeight(400);
            jimage.setFitHeight(200);
            
            
            }
        });
    } 
    
      
   /*   public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        jrech.setOnKeyReleased(e -> {
            jrech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evenement>) evenement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if (evenement.getNomEvenement().contains(newValue) || (evenement.getNomEvenement().toLowerCase().contains(newValue)))
                            {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
           // sorteddata.comparatorProperty().bind(list.compa);
            list.setItems(sorteddata);
        });
    }
      */
      
     /*  public boolean participeDéja() {
          boolean res = false ; 
          
          Services.ParticipationService sp = new ParticipationService(); 
          List<Participation> liste = sp.participeDéja(Utilisateur.getIdd());
          
          for(Participation p : liste) {
              if((Integer.parseInt(lblid.getText()))==p.getIdEvenement()) {
                   res=true  ;
              } 
              
          }
         return res ;
      }
      
     
              
      
      
   
        
     } */

    @FXML
    private void modifierAction(ActionEvent event) throws SQLException {
        int idE = Integer.parseInt(lblid.getText());
        String nomE = (jnom.getText());
        Date datefe = (Date.valueOf(jdate.getValue()));
        String destE = jdest.getText() ;
        String dureeE = jduree.getValue(); 
        String typeE = jtype.getText(); 
        String etatE = jetatt.getValue(); 
        String descE = jdesc.getText(); 
        String image = path.getText() ; 
        float prixE = Float.parseFloat(jprix.getText());
        int nbrP = Integer.parseInt(jnombre.getText()) ;
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
        even.setId_user(Utilisateur.getIdd());
       
      Services.EvenementServices se = new EvenementServices();
       se.modifierEvenement(even, idE);

     
        data.clear();
        loadDataFromDatabase();
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Modification effectué avec succées");
        alert2.setHeaderText("Modification");
        alert2.setContentText("Modification:"+nomE+"Prévu le :"+datefe);
        alert2.showAndWait();
        list.refresh();
    }

    @FXML
    private void supprimerAction(ActionEvent event) throws SQLException {
           
        int idE = Integer.valueOf(lblid.getText());
         String nomE = (jnom.getText());
         Date datefe = (Date.valueOf(jdate.getValue()));
         Services.EvenementServices ser = new EvenementServices();
         ser.supprimerEvenement(idE);
       jnom.setText("");
        jdate.setValue(null);
        jdest.setText("");
        jtype.setText("");
        jetatt.setValue("");
        jnombre.setText("");
        jprix.setText("");
        jdesc.setText("") ; 
        jduree.setValue("");
        
        data.clear();
        loadDataFromDatabase();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evenement supprimé");
        alert.setHeaderText("Suppression");
        alert.setContentText("Suppression:"+nomE+"Prévu le :"+datefe);
        alert.showAndWait();
         list.refresh();
        
         
    }

    @FXML
    private void filechooseAction(ActionEvent event) throws IOException {
        File dest=new File("C:\\wamp\\www\\Images");
        
        fc.setInitialDirectory(new File("C:\\Users\\boussandel\\branche2\\Desktop\\images"));
        selectedFile = fc.showOpenDialog(null);
        if(selectedFile!=null){
        FileUtils.copyFileToDirectory(selectedFile, dest);
        
        File newFile = new File("C:\\wamp\\www\\Images\\"+selectedFile.getName());
        
        FileInputStream input = new FileInputStream(newFile);
        Image image = new Image(input);
        path.setText(newFile.getAbsolutePath()); 
        jimage.setImage(image); 
       // lbl12.setText("image ajouté ");
            filechooser.setStyle("-fx-text-fill:green;");

            
    }
        else {
           filechooser.setStyle("-fx-text-fill:red;");
        }
    }
    }

