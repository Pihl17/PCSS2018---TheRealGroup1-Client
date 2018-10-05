package Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage; 

public class Grid extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		int rows = 5;
		int columns = 5;
		
		Button button = new Button();	
		
		GridPane gridPane = new GridPane();
		
		Scene scene = new Scene(gridPane);
		
		// creating background color
		gridPane.setStyle("-fx-background-color: darkred; -fx-grid-lines-visible: true");
		
		//setting up the rows
		for(int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(120);
            gridPane.getRowConstraints().add(row);
		}
		
		// setting up the columns
		for(int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(120);
            gridPane.getColumnConstraints().add(column);
		}
		
		stage.setTitle("DnD Chat Map");
		stage.setScene(scene);
		stage.show();
		
	   } 

}

	


