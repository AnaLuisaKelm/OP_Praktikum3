package gui.Warenuebersicht;
   
import business.TeeladenModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import observer.Observer;
import ownUtil.*;

public class WarenuebersichtView implements Observer {
	
	private WarenuebersichtControl
 	warenuebersichtControl;
	private TeeladenModel teeladenModel;		
    	//---Anfang Attribute der grafischen Oberflaeche---
    	private Pane pane = new  Pane();
    	private Label lblAnzeigeTees     
 		= new Label("Anzeige Tees");
    	private TextArea txtAnzeigeTees  = new TextArea();
    	private Button btnAnzeigeTees = new Button("Anzeige");
    	//-------Ende Attribute der grafischen Oberflaeche-------
    
    	public WarenuebersichtView(
 		WarenuebersichtControl 
 		warenuebersichtControl, 
   	 	Stage primaryStage, 
 		TeeladenModel teesModel){
    		Scene scene = new Scene(this.pane, 560, 340);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Anzeige der Warenübersicht");
    		primaryStage.show();
    		this.warenuebersichtControl 
 			= warenuebersichtControl;
 		this.teeladenModel = teesModel;
 		this.initKomponenten();
		this.initListener();
		this.teeladenModel.addObserver(this); // Observer-Pattern
		
    	}

 	private void initKomponenten(){
    		// Label
 		Font font = new Font("Arial", 20);
       	lblAnzeigeTees.setLayoutX(310);
    		lblAnzeigeTees.setLayoutY(40);
    		lblAnzeigeTees.setFont(font);
    		lblAnzeigeTees.setStyle("-fx-font-weight: bold;"); 
       	pane.getChildren().add(lblAnzeigeTees);           
// Textbereich	
        	txtAnzeigeTees.setEditable(false);
     		txtAnzeigeTees.setLayoutX(310);
    		txtAnzeigeTees.setLayoutY(90);
     		txtAnzeigeTees.setPrefWidth(220);
    		txtAnzeigeTees.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeigeTees);        	
        	// Button
          	btnAnzeigeTees.setLayoutX(310);
        	btnAnzeigeTees.setLayoutY(290);
        	pane.getChildren().add(btnAnzeigeTees); 
   }
   
   private void initListener() {
	    btnAnzeigeTees.setOnAction(
 			new EventHandler<ActionEvent>() {
	    		@Override
	        	public void handle(ActionEvent e) {
	            	zeigeTeesAn();
	        	} 
   	    });
    }
   
    private void zeigeTeesAn(){
    		if(teeladenModel.getTee() != null){
    			txtAnzeigeTees.setText(
    				teeladenModel.getTee()
 				.gibTeeZurueck(' '));
    		}
    		else{
    			zeigeInformationsfensterAn(
 				"Bisher wurde kein Tee aufgenommen!");
    		}
    }	
   
    private void zeigeInformationsfensterAn(String meldung){
    	  	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
               	"Information", meldung).zeigeMeldungsfensterAn();
    }

    // OBSERVER-PATTERN
    
	@Override
	public void update() {
		
		if(teeladenModel.getTee() != null){
			txtAnzeigeTees.setText(
				teeladenModel.getTee()
				.gibTeeZurueck(' '));
		}
		else{
			zeigeInformationsfensterAn(
				"Bisher wurde kein Tee aufgenommen!");
		}
		
	}	
    
}
