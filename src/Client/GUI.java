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
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.concurrent.WorkerStateEvent;


public class GUI extends Application implements GUIConstants {
	JFXPanel jfxPanel = new JFXPanel();
	GridPane gridPane = new GridPane();

	GridPane mainPane = new GridPane();
	GridPane chatPane = new GridPane();
	Scene mainScene = new Scene(mainPane); 
	VBox chatlog = new VBox();
	TextField chatField;
	Button sendButton;
	Player player = new Player("This", 1);
	ArrayList<Player> others = new ArrayList<Player>();

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

		IThread inputService = new IThread();
		inputService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			public void handle(WorkerStateEvent t) {
				String output = t.getSource().getValue().toString();
				if (output.startsWith("poschange")) {
					output = output.substring(9);
					movePlayer(output);
				} else {
					AddToChat(new Label(output));
				}
				
			}
		});
		inputService.start();
	}

	public GridPane ChatPane() {
		GridPane pane = new GridPane();
		pane.add(chatlog, 0, 0);
		pane.add(chatField, 0, 1);
		pane.add(sendButton, 1, 1);
		return pane;
	}

	public void SetUpChatlog() {
		Label[] labels = new Label[CHAT_MAX_LOGS];
		for (int i = 0; i < CHAT_MAX_LOGS; i++)
			labels[i] = new Label("");
		SetChat(labels);
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
						if(field.getText().startsWith("/avatar ")) 
							player.SetAvatar(Integer.parseInt(field.getText().substring(7)));
						else 
						Client.out.writeUTF(field.getText());
						
						field.setText("");
					} catch (Exception e) {}
				}
			}.start();
		}
	}

	public void AddToChat(Label... labels) {
		for (Label label : labels) {
			System.out.println(label);
		}
		for (int i = 0; i < labels.length; i++) { chatlog.getChildren().remove(0); }
		chatlog.getChildren().addAll(labels);
	}

	public void SetChat(Label... labels) {
		chatlog.getChildren().setAll(labels);
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
			if(gridPane.getColumnIndex(avatar) == null) 
				gridPane.add(avatar, x, y);	
			else 
				gridPane.setConstraints(avatar, x, y);
		}
	}

	public void movePlayer(String output) {
		String[] split = output.split(" ");
		int x = Integer.parseInt(split[1]);
		int y = Integer.parseInt(split[2]);
		int avatar = Integer.parseInt(split[3]);
		for (Player other : others) {
			if(split[0].equals(other.GetUserId())) {
				setLocation(ROWS, COLUMNS, gridPane, x, y, other.GetLabel());
					return;
			}		
		}
		Player newPlayer = new Player(split[0], avatar);
		setLocation(ROWS, COLUMNS, gridPane, x, y, newPlayer.GetLabel());
		others.add(newPlayer);
	}
}
