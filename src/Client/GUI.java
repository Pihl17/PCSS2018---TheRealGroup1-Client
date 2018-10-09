package Client;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
//import javafx.scene.layout.AnchorPane;

public class GUI extends Application implements GUIConstants {

	GridPane mainPane = new GridPane();
	GridPane chatPane = new GridPane();
	GridPane inputPane = new GridPane();
	Scene mainScene = new Scene(mainPane);
	VBox chatlog = new VBox();
	TextField chatField;
	Button sendButton;

	
	public void init() throws Exception {
		//Runs before the start function
	}

	public void start(Stage stage) throws Exception {
		//TODO Delete these 3 lines! (only used for testing)
		VBox box2 = new VBox();
		box2.getChildren().addAll(new Label("label 1-2"), new Label("label 2-2"));
		box2.setMinSize(100, 100);

		//TODO Include write to chat pane
		chatPane.setMaxSize(CHAT_WIDTH, CHAT_HEIGHT);
		chatPane.setMinSize(CHAT_WIDTH, CHAT_HEIGHT);
		chatlog.setMaxHeight(CHAT_HEIGHT - CHATBUTTON_HEIGHT);
		for (int i = 0; i < CHAT_MAX_LOGS; i++)
			AddToChat(new Label());
		chatField = new TextField();
		chatField.setMaxHeight(CHATBUTTON_HEIGHT);
		chatField.setMaxWidth(CHAT_WIDTH - CHATBUTTON_HEIGHT);
		sendButton = new Button("Send");
		sendButton.setMinSize(CHATBUTTON_WIDTH, CHATBUTTON_HEIGHT);
		sendButton.setOnAction(action -> {
			if (!chatField.getText().equals("")) {
				AddToChat(new Label(chatField.getText()));
				chatField.setText("");
			}
		});
		
		/*inputPane.add(chatField, 0, 0);
		inputPane.add(sendButton, 1, 0);
		chatPane.setTopAnchor(chatlog, 5.0);
		chatPane.setBottomAnchor(inputPane, 5.0);
		chatPane.getChildren().addAll(inputPane, chatlog);*/

		chatPane.add(chatlog, 0, 0);
		chatPane.add(chatField, 0, 1);
		chatPane.add(sendButton, 1, 1);

		//TODO Include grid pane
		
		//TODO Include Lobby pane

		mainPane = new GridPane();
		mainScene = new Scene(mainPane);
		mainPane.setVgap(10);
		mainPane.setHgap(10);
		mainPane.add(chatPane, 0, 0);
		mainPane.add(box2, 1, 0); //TODO Delete this! (used for testing)

		stage.setTitle("DND and stuff!");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);
		stage.setScene(mainScene);
		stage.show();

		//AddToChat(new Label("label 3"), new Label("label 4"));
		//SetChat(new Label("label 5"));
	}

	public void AddToChat(Label... labels) {
		chatlog.getChildren().addAll(labels);
		RemoveOldLogs();
	}

	public void SetChat(Label... labels) {
		chatlog.getChildren().setAll(labels);
		RemoveOldLogs();
	}

	public void RemoveOldLogs() {
		while (chatlog.getChildren().size() > CHAT_MAX_LOGS) {
			chatlog.getChildren().remove(0);
		}
	}
}

