/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.Evaluation;
import Entités.Evenement;
import Services.EvaluationService;
import Services.EvenementServices;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author boussandel
 */

public class ConsulterEvaluationController implements Initializable {

    @FXML
    private TableView<Evaluation> tableEvaluation;
    @FXML
    private TableColumn<?, ?> idEvaluation;
    @FXML
    private TableColumn<Evaluation, String> nomEven;
    @FXML
    private TableColumn<?, ?> nomEvaluateur;
    @FXML
    private TableColumn<?, ?> prenomEval;
    @FXML
    private TableColumn<?, ?> emailEval;
    @FXML
    private TableColumn<?, ?> noteEvaluation;
    
        private ObservableList<Evaluation> data;
    private JFXTextField emailContact;
    @FXML
    private JFXTextField rechnomEvt;
    @FXML
    private Button retour;
    @FXML
    private Rating rating;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
setsCllTable();
data = FXCollections.observableArrayList();
       loadDataFromDatabase();
       setcellValue();
       search();
       Services.EvaluationService es = new EvaluationService(); 
      
       List<Evaluation> list = es.findAll();
       List<String> nomE = new ArrayList<String>();
       List<String> nomP = new ArrayList<String>();
       /*List<String> dest = new ArrayList<String>();
       List<String> etat = new ArrayList<String>();*/
       
       
        for(Evaluation e : list)
       {
           nomE.add(e.getNomEvenement()) ;
           nomP.add(e.getNomParticipant());
           
           
         //  dest.add(e.getDestination());
           
       }
      
        TextFields.bindAutoCompletion(rechnomEvt, nomE);
        TextFields.bindAutoCompletion(rechnomEvt,nomP);
        //TextFields.bindAutoCompletion(recherchelabel,dest);
       
       
    }    
     public void search() {
        FilteredList<Evaluation> filterdata = new FilteredList<>(data, e -> true);
        rechnomEvt.setOnKeyReleased(e -> {
            rechnomEvt.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evaluation>) evaluation -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((evaluation.getNomEvenement().contains(newValue)) || (evaluation.getNomEvenement().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evaluation> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableEvaluation.comparatorProperty());
            tableEvaluation.setItems(sorteddata);
        });
    }
     
     
     
      
     
     private void loadDataFromDatabase() {
        Services.EvaluationService ess = new EvaluationService();
        List <Evaluation> evs = ess.findAll();
      
      for(Evaluation ev : evs )
      {
          data.add(ev);
      }
         
         
          
        tableEvaluation.setItems(data);
    }
     
     public void setsCllTable() {
        idEvaluation.setCellValueFactory(new PropertyValueFactory<>("evaluationId"));
        nomEven.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        nomEvaluateur.setCellValueFactory(new PropertyValueFactory<>("nomParticipant"));

        prenomEval.setCellValueFactory(new PropertyValueFactory<>("prenomParticipant"));
        emailEval.setCellValueFactory(new PropertyValueFactory<>("emailParticipant"));
        noteEvaluation.setCellValueFactory(new PropertyValueFactory<>("noteEvenement"));

    }
     
     private void setcellValue() {
        tableEvaluation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evaluation pl = tableEvaluation.getItems().get(tableEvaluation.getSelectionModel().getSelectedIndex());
                //emailContact.setText((pl.getEmailParticipant()));
                double noteEvt = pl.getNoteEvenement();
                rating.setRating(noteEvt);
                
             }
        });
    }

    @FXML
    private void retour(ActionEvent event) {
    }
     
     
    
}
