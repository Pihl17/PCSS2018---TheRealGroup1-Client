package Client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class GUI extends Application implements GUIConstants {

	Scene chatScene = new Scene(new VBox());
	VBox chatlayout = new VBox();
	
	public void init() throws Exception {
		//Runs before the start function
	}

	public void start(Stage stage) throws Exception {
		chatScene = new Scene(chatlayout);
		chatlayout.getChildren().addAll(new Label("label 1"), new Label("label 2"));

		//TODO Include grid window

		stage.setTitle("DND and stuff!");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);
		stage.setScene(chatScene);
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

