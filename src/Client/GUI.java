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
import javafx.scene.input.KeyCode;

public class GUI extends Application implements GUIConstants {

	GridPane mainPane = new GridPane();
	GridPane chatPane = new GridPane();
	Scene mainScene = new Scene(mainPane);
	VBox chatlog = new VBox();
	TextField chatField;
	Button sendButton;

	
	public void init() throws Exception {
		//Runs before the start function
	}

	public void start(Stage stage) throws Exception {
		SetUpChatlog();
		chatField = ChatField();
		sendButton = SendButton();
		chatPane = ChatPane();

		mainPane = new GridPane();
		mainScene = new Scene(mainPane);
		mainPane.setVgap(10);
		mainPane.setHgap(10);
		mainPane.add(chatPane, 0, 0);
		//TODO Include grid pane
		//TODO Include Lobby pane

		stage.setTitle("DND");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);
		stage.setScene(mainScene);
		stage.show();
	}

	public GridPane ChatPane() {
		GridPane pane = new GridPane();
		pane.setMinSize(CHAT_WIDTH, CHAT_HEIGHT);
		pane.add(chatlog, 0, 0);
		pane.add(chatField, 0, 1);
		pane.add(sendButton, 1, 1);
		return pane;
	}

	public void SetUpChatlog() {
		chatlog.setMaxHeight(CHAT_HEIGHT - CHATBUTTON_HEIGHT);
		for (int i = 0; i < CHAT_MAX_LOGS; i++)
			AddToChat(new Label());
	}

	public TextField ChatField() {
		TextField field = new TextField();
		field.setMaxHeight(CHATBUTTON_HEIGHT);
		field.setMinWidth(CHAT_WIDTH - CHATBUTTON_HEIGHT);
		field.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER)
				SendToChat(chatField);
		});
		return field;
	}

	public Button SendButton() {
		Button button = new Button("Send");
		button.setMinSize(CHATBUTTON_WIDTH, CHATBUTTON_HEIGHT);
		button.setOnAction(action -> {
			SendToChat(chatField);
		});
		return button;
	}

	public void SendToChat(TextField field) {
		if (!field.getText().equals("")) {
				AddToChat(new Label(field.getText())); //TODO Make it send it to the network/Client class
				field.setText("");
		}
	}


	public void AddToChat(Label... labels) {
		chatlog.getChildren().addAll(labels);
		RemoveOldLogs();
	}

	public void SetChat(Label... labels) {
		chatlog.getChildren().setAll(labels);
		RemoveOldLogs();
	}

	void RemoveOldLogs() {
		while (chatlog.getChildren().size() > CHAT_MAX_LOGS) {
			chatlog.getChildren().remove(0);
		}
	}
}

