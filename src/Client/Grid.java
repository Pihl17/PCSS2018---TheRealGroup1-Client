package Client;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.input.*;



public class Grid extends Application {

	@Override
	public void start(Stage stage) throws Exception  {
		int rows = 6;
		int columns = 6;

		GridPane gridPane = new GridPane();

		Scene scene = new Scene(gridPane);

		// creating background color
		gridPane.setStyle("-fx-background-color: darkred; -fx-grid-lines-visible: true");
		// setting up the rows
		for (int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(120);
			gridPane.getRowConstraints().add(row);
		}

		// setting up the columns
		for (int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(120);
			gridPane.getColumnConstraints().add(column);
		}
		
		
		//emojis
		Label happy = new Label("\ud83d\ude02"); 
		Label whisper = new Label("\ud83d\udcac");
		Label snooping = new Label("\ud83d\udc40"); 
		Label shouting = new Label("\ud83d\udce2"); 
		Label oconnor = new Label("\ud83d\ude4b");
		
		GridPane.setHalignment(oconnor, HPos.CENTER);
		oconnor.setStyle("-fx-font-size:100px;");
		gridPane.add(oconnor, 0, 1);
		
		GridPane.setHalignment(happy, HPos.CENTER);
		happy.setStyle("-fx-font-size:100px; ");
		gridPane.add(happy, 0, 0);
		
		GridPane.setHalignment(whisper, HPos.CENTER);
		whisper.setStyle("-fx-font-size:100px;");
		gridPane.add(whisper, 1, 0);
		
		GridPane.setHalignment(snooping, HPos.CENTER);
		snooping.setStyle("-fx-font-size:100px;");
		gridPane.add(snooping, 2, 0);
		
		GridPane.setHalignment(shouting, HPos.CENTER);
		shouting.setStyle("-fx-font-size:100px;");
		gridPane.add(shouting, 3, 0);
		
		//location of mouseclicked
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			System.out.format("pressed:, x: %.2f, y: %.2f\n", e.getSceneX(), e.getSceneY());
		});
		
        
		stage.setTitle("DnD Chat Map");
		stage.setScene(scene);
		stage.show();

	}



}
