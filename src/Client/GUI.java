package Client;

import java.io.IOException;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
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
	JFXPanel jfxPanel = new JFXPanel();
	GridPane gridPane = new GridPane();

	GridPane mainPane = new GridPane();
	GridPane chatPane = new GridPane();
	Scene mainScene = new Scene(mainPane); 
	VBox chatlog = new VBox();
	TextField chatField;
	Button sendButton;
	Player player;

	public void init() throws Exception {
		// Runs before the start function
	}

	public void start(Stage stage) throws Exception {
		Main.Gui = this;
		stage.setFullScreen(true);
		
		SetUpChatlog();
		chatField = ChatField();
		sendButton = SendButton();
		chatPane = ChatPane();

		mainPane = new GridPane();
		mainScene = new Scene(mainPane);
		mainPane.setVgap(10);
		mainPane.setHgap(10);
		mainPane.add(chatPane, 0, 0);
		gridPane = CreateGrid();
		mainPane.add(gridPane, 1, 0);
		mainPane.add(StartButton(),2,0);
		
		AddToChat(new Label("Please write your nickName!"));
		
		stage.setTitle("DND");
		stage.setScene(mainScene);
		stage.show();
	
	}

	public GridPane ChatPane() {
		GridPane pane = new GridPane();
		pane.add(chatlog, 0, 0);
		pane.add(chatField, 0, 1);
		pane.add(sendButton, 1, 1);
		return pane;
	}

	public void SetUpChatlog() {
		for (int i = 0; i < CHAT_MAX_LOGS; i++)
			AddToChat(new Label());
	}

	public TextField ChatField() {
		TextField field = new TextField();
		field.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER)
				SendToChat(chatField);
		});
		return field;
	}
	public Button StartButton() {
		Button button = new Button("Start Game");
			button.setOnAction(action -> {
				try {
					Client.out.writeUTF("startgame a");
				} catch (IOException e) {

				}
			});
		return button;
		
	}
	public Button SendButton() {
		Button button = new Button("Send");
		button.setOnAction(action -> {
			SendToChat(chatField);
		});
		return button;
	}

	public void SendToChat(TextField field) {
		if (!field.getText().equals("")) {
			new Thread(){
				public void run() {
					try {
						Client.out.writeUTF(field.getText());
						field.setText("");
					} catch (IOException e) {}
				}
			}.start();
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

		int rows = ROWS;
		int columns = COLUMNS;

		GridPane gridPane = new GridPane();

		// creating background color
		gridPane.setStyle("-fx-background-color: darkred; -fx-grid-lines-visible: true");
		// setting up the rows
		for (int i = 0; i < rows; i++) {
			RowConstraints row = new RowConstraints(TILE_HEIGHT);
			gridPane.getRowConstraints().add(row);
		}

		// setting up the columns
		for (int i = 0; i < columns; i++) {
			ColumnConstraints column = new ColumnConstraints(TILE_WIDTH);
			gridPane.getColumnConstraints().add(column);
		}

		// emojis // Is this part even needed?
		Label happy = new Label("\u2603"); 
		Label whisper = new Label("\ud83d\udcac");
		Label snooping = new Label("\ud83d\udc40");
		Label shouting = new Label("\ud83d\udce2");

		GridPane.setHalignment(happy, HPos.CENTER);
		happy.setStyle("-fx-font-size:100px; ");

		gridPane.add(happy, 0, 0);
		

		GridPane.setHalignment(whisper, HPos.CENTER);
		whisper.setStyle("-fx-font-size:100px;");
		gridPane.add(whisper, 1, 0);

		GridPane.setHalignment(snooping, HPos.CENTER);
		snooping.setStyle("-fx-font-size:100px;");
		gridPane.add(snooping, 2, 0);
		
		//location of mouseclicked
				mainScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
					int x = (int) e.getSceneX();
					int y = (int) e.getSceneY();
					
					x = (int) Math.floor((x-(chatPane.getWidth() + mainPane.getHgap()))/TILE_WIDTH);
					y = (int) Math.floor(y/TILE_HEIGHT);
					
					try {
						if (x >= 0 && x < columns && y >= 0 && y < rows)
							Client.out.writeUTF("changepos " + x + " " + y + " " + player.GetAvatarNumber());
					} catch (IOException ex) {} catch (NullPointerException ex) {}
					setLocation(rows, columns, gridPane, x, y, player.GetLabel());
				});
		

		return gridPane;
	}

	public void setLocation(int rows, int columns, GridPane gridPane, int x, int y, Label avatar) {
		if (x >= 0 && x < columns && y >= 0 && y < rows && avatar != null) {
		//	TODO Wait for player function object to get called and then call GetAvatar
			if(gridPane.getColumnIndex(avatar) == null) 
				gridPane.add(avatar, x, y);	
			else 
				gridPane.setConstraints(avatar, x, y);
			//System.out.format("pressed:, x: %.2f, y: %.2f\n", x, y);
		}
	}
}
