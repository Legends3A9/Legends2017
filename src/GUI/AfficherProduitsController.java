/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entités.Produit;
import Services.ServiceProduit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author GlaDio007
 */
public class AfficherProduitsController implements Initializable {

    
   
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField prix;
    @FXML
    private TextField quantite;
    @FXML
    private TextField taille;
    @FXML
    private TextField marque;
    @FXML
    private TextField description;
    @FXML
    private TextField couleur;
    @FXML
    private Button ajout;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    
    private ObservableList<Produit> data;
    @FXML
    private TableView<Produit> view;
    
    
    
    @FXML
    private TableColumn<?, ?> id_e;
    @FXML
    private TableColumn<?, ?> label_e;
    @FXML
    private TableColumn<?, ?> type_e;
    @FXML
    private TableColumn<?, ?> prix_e;
    @FXML
    private TableColumn<?, ?> quantite_e;
    @FXML
    private TableColumn<?, ?> image_e;
    @FXML
    private TableColumn<?, ?> taille_e;
    @FXML
    private TableColumn<?, ?> marque_e;
    @FXML
    private TableColumn<?, ?> couleur_e;
    @FXML
    private TableColumn<?, ?> description_e;
    /**
     * Initializes the controller class.
     */
    
    
     
    @FXML
    private TextField label;
    @FXML
    private TextField id;
    @FXML
    private ImageView imageView;
    @FXML   
    private TextField cheminImage;
    
    
    boolean selected = false;
    
    FileChooser fc=new FileChooser();
    File selectedFile=new File("");
    @FXML
    private TextField rech;
    @FXML
    private Label nomErreur;
    @FXML
    private Label prixErreur;
    @FXML
    private Label quantiteErreur;
    @FXML
    private Button browse;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cheminImage.setVisible(false);
        data = FXCollections.observableArrayList();
        setsCllTable();
        loadDataFromDatabase();
        
        modifier.setDisable(false);
        supprimer.setDisable(false);   
        setcellValue();
        ObservableList<String> List=FXCollections.observableArrayList(
                "Chaussures","Ballon","Maillot National","Survetement","Chaussettes","Accessoires"
        );
        type.setItems(List);
        ServiceProduit service = new ServiceProduit();
        List<Produit> listP=service.selectProduits();
        List<String> listS = new ArrayList<>();
        for(Produit p : listP)
        {
            listS.add(p.getLabel());
            listS.add(p.getMarque());
        }
        TextFields.bindAutoCompletion(rech, listS);
        search();
        
        
        quantite.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation());
        marque.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());
        couleur.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation());
        prix.addEventFilter(KeyEvent.KEY_TYPED , float_Validation());
        
        
        
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
        Parent root = loader.load();
        Scene scene = label.getScene();
        scene.setRoot(root);
    }
    
        
    public void search() {
        FilteredList<Produit> filterdata = new FilteredList<>(data, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Produit>) produit -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((produit.getLabel().contains(newValue)) || (produit.getLabel().toLowerCase().contains(newValue)) || ((produit.getMarque().contains(newValue)) || (produit.getMarque().toLowerCase().contains(newValue)))){
                        return true;
                    }
                    return false;
                }) ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
                        
            });
            SortedList<Produit> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(view.comparatorProperty());
            view.setItems(sorteddata);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  });
    }
    
    


    
    
    @FXML
    private void modifierProduit(ActionEvent event) {
        String idE=id.getText();
        if (idE.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Aucun objet selectionnée");
            alert.setContentText("S'il vous plait selectionner un produit a modifier.");
            alert.showAndWait();
        } else {
            if((label.getText().equals("")))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir le nom du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
            }
                else if(type.getValue()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait selectionner le type du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
            }
            
            else if(prix.getText().equals(""))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir le prix du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
                    }
            else if(quantite.getText().equals(""))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait saisir la quantite du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
                    }
            else if(cheminImage.getText().equals(""))
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de Saisie");
                alert.setHeaderText("Erreur");
                alert.setContentText("S'il vous plait choisissez l'image du produit");
                Optional<ButtonType> result = alert.showAndWait(); 
                    }
            else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification");
            alert.setContentText("Vouler vous vraiment modifier ce produit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
        int id1=Integer.valueOf(id.getText());
        String label1=label.getText();
        String type1=type.getValue();
        float prix1=Float.parseFloat(prix.getText());
        int quantite1=Integer.valueOf(quantite.getText());
        String taille1=taille.getText();
        String marque1=marque.getText();
        String couleur1=couleur.getText();
        String image1=cheminImage.getText();
        String desctiption1=description.getText();
        Produit p =new Produit(id1, label1, type1, prix1, quantite1,image1, taille1, marque1, couleur1, desctiption1);
        ServiceProduit service=new ServiceProduit();
        service.modfierProduit(p);
        data.clear();
        loadDataFromDatabase();
        }}}
    }
    private void setcellValue() {
        view.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Produit p = view.getItems().get(view.getSelectionModel().getSelectedIndex());
                id.setText(Integer.toString(p.getId()));
                label.setText(p.getLabel());
                prix.setText(String.valueOf(p.getPrix()));
                quantite.setText(Integer.toString(p.getQuantite()));
                taille.setText(p.getTaille());
                cheminImage.setText(p.getImage());
                marque.setText(p.getMarque());
                couleur.setText(p.getCouleur());
                description.setText(p.getDescription());
                modifier.setDisable(false);
                supprimer.setDisable(false);      
                String t=p.getType();
                type.setValue(t);
                Image();
                selected=true;
                
            }
        });
    }
    
     private void loadDataFromDatabase() {
        try {
            ServiceProduit service=new ServiceProduit();
            List<Produit> rs = service.selectProduits();
            for(Produit p : rs)
            {
                
                
                
                data.add(p);
                System.out.println("recup table view ok !");
               
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        view.setItems(data);
        
    }
    public void setsCllTable() {
        id_e.setCellValueFactory(new PropertyValueFactory<>("id"));
        label_e.setCellValueFactory(new PropertyValueFactory<>("label"));
        type_e.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix_e.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite_e.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        image_e.setCellValueFactory(new PropertyValueFactory<>("image"));
        taille_e.setCellValueFactory(new PropertyValueFactory<>("taille"));
        marque_e.setCellValueFactory(new PropertyValueFactory<>("marque"));
        couleur_e.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        description_e.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
    }
    private void Image(){
           
        File file = new File(cheminImage.getText());    
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Object selected");
            alert.setContentText("S'il vous plait selectionner un produit a supprimer.");
            alert.showAndWait();
        } else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression");
            alert.setContentText("Vouler vous vraiment supprimer ce produit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ServiceProduit service=new ServiceProduit();
        int var = Integer.valueOf(id.getText());
        
        service.Remove(var);
        id.setText("");
        label.setText("");
        type.getSelectionModel().selectFirst();
        prix.setText("");
        quantite.setText("");
        taille.setText("");
        marque.setText("");
        description.setText("");
        cheminImage.setText("");
        couleur.setText("");
        data.clear();
        loadDataFromDatabase();
        selected=false;
            }
        
       
    
    }
    }

    @FXML
    
    private void image(ActionEvent event) throws FileNotFoundException, IOException {
        File dest=new File("C:\\wamp\\www\\Images");
        
        fc.setInitialDirectory(new File("C:\\Users\\GlaDio007\\Documents\\NetBeansProjects\\PiDev_Russia2018\\Images\\"));
        selectedFile = fc.showOpenDialog(null);
        FileUtils.copyFileToDirectory(selectedFile, dest);
        
        File newFile = new File("C:\\wamp\\www\\Images\\"+selectedFile.getName());
        
        FileInputStream input = new FileInputStream(newFile);
        Image image = new Image(input);
        cheminImage.setText(newFile.getAbsolutePath()); 
        imageView.setImage(image);
    }
    
    
        
public EventHandler<KeyEvent> numeric_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if(e.getCharacter().matches("[0-9]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}  


public EventHandler<KeyEvent> float_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if(e.getCharacter().matches("[0-9.]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
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
            
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}   

    @FXML
    private void nomTest(KeyEvent event) {
        if(label.getText().equals(""))
            nomErreur.setVisible(true);
        else
            nomErreur.setVisible(false);
    }

    @FXML
    private void prixTest(KeyEvent event) {
        if(prix.getText().equals(""))
            prixErreur.setVisible(true);
        else
            prixErreur.setVisible(false);
    }

    @FXML
    private void quantiteTest(KeyEvent event) {
    if(quantite.getText().equals(""))
            quantiteErreur.setVisible(true);
        else
            quantiteErreur.setVisible(false);
    }

    
}
