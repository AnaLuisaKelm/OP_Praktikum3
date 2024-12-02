package gui.Warenuebersicht;
import business.TeeladenModel;
import javafx.stage.Stage;

public class WarenuebersichtControl {	
	private WarenuebersichtView
 		warenuebersichtView;
	private TeeladenModel teeladenModel;
	public WarenuebersichtControl(Stage primaryStage){
 		//this.teeladenModel = new TeeladenModel(); 		
		this.teeladenModel = TeeladenModel.getInstance();
		this.warenuebersichtView 
		 	= new WarenuebersichtView(this, primaryStage,
			teeladenModel);
	}
}
