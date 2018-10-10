package Client;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.RowConstraints;

public class GUI extends Application implements GUIConstants {
	GridPane gridPane = new GridPane();

	GridPane mainPane = new GridPane();
	GridPane chatPane = new GridPane();
	Scene mainScene = new Scene(mainPane);
	VBox chatlog = new VBox();
	TextField chatField;
	Button sendButton;

	public void init() throws Exception {
		// Runs before the start function
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
		// TODO Include grid pane
		gridPane = CreateGrid();
		mainPane.add(gridPane, 1, 0);
		// TODO Include Lobby pane

		stage.setTitle("DND");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);
		stage.setScene(mainScene);
		stage.show();
	}

	public GridPane ChatPane() {
		GridPane pane = new GridPane();
		pane.setMinSize(CHAT_WIDTH, CHAT_HEIGHT);
		pane.setMaxSize(CHAT_WIDTH, CHAT_HEIGHT);
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
		field.setMinWidth(CHAT_WIDTH - CHATBUTTON_WIDTH);
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
			AddToChat(new Label(field.getText())); // TODO Make it send it to the network/Client class
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

	public GridPane CreateGrid() {

		int rows = 7;
		int columns = 7;

		GridPane gridPane = new GridPane();

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

		// emojis
		Label happy = new Label("\u2603");
		Label whisper = new Label("\ud83d\udcac");
		Label snooping = new Label("\ud83d\udc40");
		Label shouting = new Label("\ud83d\udce2");

		GridPane.setHalignment(happy, HPos.CENTER);
		happy.setStyle("-fx-font-size:100px; ");

		gridPane.add(happy, 0, 0);


		//gridPane.add(happy, 0, 0);
		

		GridPane.setHalignment(whisper, HPos.CENTER);
		whisper.setStyle("-fx-font-size:100px;");
		gridPane.add(whisper, 1, 0);

		GridPane.setHalignment(snooping, HPos.CENTER);
		snooping.setStyle("-fx-font-size:100px;");
		gridPane.add(snooping, 2, 0);


		// location of mouseclicked
		mainScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			int x = (int) e.getSceneX();
			int y = (int) e.getSceneY();

			x = (int) Math.floor((x - CHAT_WIDTH) / 120);
			y = (int) Math.floor(y / 120);

			// TODO Wait for player function object to get called and then call GetAvatar
			/*
			 * if(gridPane.getColumnIndex(avatar) == null) gridPane.add(, x, y);
			 * 
			 * else gridPane.setConstraints(beer, x, y);
			 */
			// System.out.format("pressed:, x: %.2f, y: %.2f\n", x, y);

		});


		
		
		
		//location of mouseclicked
				mainScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
					int x = (int) e.getSceneX();
					int y = (int) e.getSceneY();
					
					x = (int) Math.floor((x-(CHAT_WIDTH + mainPane.getHgap()))/120);
					y = (int) Math.floor(y/120);
					
					if (x >= 0 && x < columns && y >= 0 && y < rows) {
					//	TODO Wait for player function object to get called and then call GetAvatar
						if(gridPane.getColumnIndex(happy) == null) 
							gridPane.add(happy, x, y);	
						else 
							gridPane.setConstraints(happy, x, y);
						//System.out.format("pressed:, x: %.2f, y: %.2f\n", x, y);
					}
				});
		

		return gridPane;
	}
}
