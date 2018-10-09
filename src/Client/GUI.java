package Client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class GUI extends Application implements GUIConstants {

	GridPane mainPane = new GridPane();
	Scene mainScene = new Scene(mainPane);
	VBox chatlayout = new VBox();

	
	public void init() throws Exception {
		//Runs before the start function
	}

	public void start(Stage stage) throws Exception {
		AddToChat(new Label("label 1"), new Label("label 2"));

		//VBox box2 = new VBox();
		//box2.getChildren().addAll(new Label("label 1-2"), new Label("label 2-2"));

		//TODO Include write to chat pane

		//TODO Include grid pane
		
		//TODO Include Lobby pane

		mainPane = new GridPane();
		mainScene = new Scene(mainPane);
		mainPane.setVgap(10);
		mainPane.setHgap(10);
		mainPane.add(chatlayout, 0, 0);
		//mainPane.add(box2, 1, 0);

		stage.setTitle("DND and stuff!");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);
		stage.setScene(mainScene);
		stage.show();

		//AddToChat(new Label("label 3"), new Label("label 4"));
		//SetChat(new Label("label 5"));
	}

	public void AddToChat(Label... labels) {
		chatlayout.getChildren().addAll(labels);
	}

	public void SetChat(Label... labels) {
		chatlayout.getChildren().setAll(labels);
	}
}

