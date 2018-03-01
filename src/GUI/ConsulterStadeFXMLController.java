/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.DirectionsWaypoint;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.elevation.ElevationResult;
import com.lynden.gmapsfx.service.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.service.elevation.ElevationStatus;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import util.DB;
import entities.Stade;
import java.io.File;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class ConsulterStadeFXMLController implements Initializable,MapComponentInitializedListener, 
        ElevationServiceCallback, GeocodingServiceCallback, DirectionsServiceCallback {
    protected GoogleMapView mapComponent;
    protected GoogleMap map;
    protected DirectionsPane directions;

    private Button btnZoomIn;
    private Button btnZoomOut;
    private Label lblZoom;
    private Label lblCenter;
    private Label lblClick;
    private ComboBox<MapTypeIdEnum> mapTypeCombo;
	
	private MarkerOptions markerOptions2;
	private Marker myMarker2;
	private Button btnHideMarker;
	private Button btnDeleteMarker;
    private ObservableList<Stade> data;
     static DB ds =DB.getInstance(); 
    private HBox hbox;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;
    private VBox vbox1;
    @FXML
    private Label nom_stadeA;
    @FXML
    private Label regionA;
    @FXML
    private Label adresseA;
    @FXML
    private Label date_constructionA;
    @FXML
    private Label surfaceA;
    @FXML
    private Label capaciteA;
    @FXML
    private BorderPane pane;
    @FXML
    private ImageView imageA;
    @FXML
    private Button localiser;
    @FXML
    private Button retour;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        data = FXCollections.observableArrayList();
       addHBox();
    }    

    public VBox addHBox() {
  
    vbox.setPadding(new Insets(0, 0, 0, 0));
    vbox.setSpacing(10);
    //vbox.setStyle("-fx-background-color: #336699;");
    System.out.println("aaaaaaa"); 
    //vbox1.setPadding(new Insets(500, 500, 500, 500));
     
    //vbox1.setSpacing(10);
   // vbox1.setStyle("-fx-background-color: #336699;");
    TextField[] text =new TextField[6];
     
    
    try {
            String url = "jdbc:mysql://localhost:3306/russia2018";
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
                 File file = new File("C:\\wamp\\www\\Images",imageE);
            Button buttonCurrent = new Button("Details");
             
    buttonCurrent.setPrefSize(100, 100);
            ImageView im =new ImageView();
            
        Image image = new Image(file.toURI().toString(),200,200, false, false);
        Image image1 = new Image(file.toURI().toString(),400,400, false, false);
        im.setImage(image);
        im.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            nom_stadeA.setText(nom_stadeE);
        regionA.setText(regionE);
        adresseA.setText(adresseE);
        date_constructionA.setText(date_constructionE.toLocalDate().toString());
        surfaceA.setText(surfaceE);
        capaciteA.setText(Integer.toString(capaciteE));
        imageA.setImage(image1);
                  
            
         event.consume();
     });
         Label textField = new Label(nom_stadeE);
        textField.setMinWidth(120);
       // final Rating rating = new Rating();
    vbox.getChildren().addAll(im);
    //vbox1.getChildren().addAll(textField);
                data.add(new Stade(ref_e,nom_stadeE,regionE,adresseE,date_constructionE,surfaceE,capaciteE,imageE));
                
                
            }
            
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    
        
       scrollPane.setContent(vbox);
 
        // Always show vertical scroll bar
       scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    
    System.out.println("aea");

    return vbox;
}
    
    public void ImageList() throws SQLException{
            String url = "jdbc:mysql://localhost:3306/russia2018";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            System.out.println("recup table view ok !");
            rs = stmt.executeQuery("SELECT * FROM stade");
            while (rs.next()) {
                
               
                String nom_stadeE = rs.getString("nom_stade");
                //combobox.getItems().addAll(nom_stadeE);
                
                
                
          
                
                
            }
            conn.close();
            
            
        
        
    }

    @FXML
    private void ActionLocaliser(ActionEvent event) throws IOException {
        
        
        System.out.println("Java version: " + System.getProperty("java.home"));
        mapComponent = new GoogleMapView(Locale.getDefault().getLanguage(), null);
        mapComponent.addMapInializedListener(this);
                
        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();

        btnZoomIn = new Button("Zoom In");
        btnZoomIn.setOnAction(e -> {
            map.zoomProperty().set(map.getZoom() + 1);
        });
        btnZoomIn.setDisable(true);

        btnZoomOut = new Button("Zoom Out");
        btnZoomOut.setOnAction(e -> {
            map.zoomProperty().set(map.getZoom() - 1);
        });
        btnZoomOut.setDisable(true);

        lblZoom = new Label();
        lblCenter = new Label();
        lblClick = new Label();
        
        mapTypeCombo = new ComboBox<>();
        mapTypeCombo.setOnAction( e -> {
           map.setMapType(mapTypeCombo.getSelectionModel().getSelectedItem() );
        });
        mapTypeCombo.setDisable(true);
        
        Button btnType = new Button("Map type");
        btnType.setOnAction(e -> {
            map.setMapType(MapTypeIdEnum.ROADMAP);
        });
		
		btnHideMarker = new Button("");
		btnHideMarker.setOnAction(e -> {});
		
		btnDeleteMarker = new Button("");
		btnDeleteMarker.setOnAction(e -> {});
		
        tb.getItems().addAll(btnZoomIn, btnZoomOut, mapTypeCombo,
                new Label("Zoom: "), lblZoom,
                new Label("Center: "), lblCenter,
                new Label("Click: "), lblClick,
				btnHideMarker, btnDeleteMarker);

        bp.setTop(tb);
        
        bp.setCenter(mapComponent);
        Stage stage1 = new Stage () ;
        Scene scene1 = new Scene(bp);
        stage1.setScene(scene1);
        stage1.show();   
    }
        
     DirectionsRenderer renderer;

    @Override
    public void mapInitialized() {
 LatLong center = new LatLong(500.606189, -500.335842);
        mapComponent.addMapReadyListener(() -> {
            // This call will fail unless the map is completely ready.
            checkCenter(center);
        });
        
        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        
        //[{\"featureType\":\"landscape\",\"stylers\":[{\"saturation\":-100},{\"lightness\":65},{\"visibility\":\"on\"}]},{\"featureType\":\"poi\",\"stylers\":[{\"saturation\":-100},{\"lightness\":51},{\"visibility\":\"simplified\"}]},{\"featureType\":\"road.highway\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]
        map = mapComponent.createMap(options,false);
        directions = mapComponent.getDirec();
        
        map.setHeading(123.2);
        
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            lblClick.setText(ll.toString());
        });

        btnZoomIn.setDisable(false);
        btnZoomOut.setDisable(false);
        mapTypeCombo.setDisable(false);
        mapTypeCombo.getItems().addAll( MapTypeIdEnum.ALL );
        
        GeocodingService gs = new GeocodingService();
        
        DirectionsService ds = new DirectionsService();
        renderer = new DirectionsRenderer(true, map, directions);
        String z=nom_stadeA.getText().concat(regionA.getText());
        DirectionsWaypoint[] dw = new DirectionsWaypoint[1];
        dw[0] = new DirectionsWaypoint(z);
                System.out.println("location testing "+dw[0].getJSObject());

        //dw[1] = new DirectionsWaypoint("Juiz de Fora - MG");
        
        DirectionsRequest dr = new DirectionsRequest(
                z,
                z,
                TravelModes.DRIVING,
                dw);
        ds.getRoute(dr, this, renderer);
        

        
        
    }
    
    
    	private void hideMarker() {
//		System.out.println("deleteMarker");
		
		boolean visible = myMarker2.getVisible();
		
		//System.out.println("Marker was visible? " + visible);
		
		myMarker2.setVisible(! visible);

//				markerOptions2.visible(Boolean.FALSE);
//				myMarker2.setOptions(markerOptions2);
//		System.out.println("deleteMarker - made invisible?");
	}
	
	private void deleteMarker() {
		//System.out.println("Marker was removed?");
		map.removeMarker(myMarker2);
	}
	
    private void checkCenter(LatLong center) {
//        System.out.println("Testing fromLatLngToPoint using: " + center);
//        Point2D p = map.fromLatLngToPoint(center);
//        System.out.println("Testing fromLatLngToPoint result: " + p);
//        System.out.println("Testing fromLatLngToPoint expected: " + mapComponent.getWidth()/2 + ", " + mapComponent.getHeight()/2);
    }

    @Override
    public void elevationsReceived(ElevationResult[] ers, ElevationStatus es) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("MenuFrontStadeFXML.fxml")));
        Parent root = loader.load();
        MenuFrontStadeFXMLController MenuFrontStadeControler = loader.getController();
        Scene scene = retour.getScene();
        scene.setRoot(root);
    }
        
        
}
    


