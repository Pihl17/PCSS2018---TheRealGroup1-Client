package Client;

import java.util.Scanner;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

enum Action {
	TALKING, JOINING, LEAVING
};

public class Player implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String nickName = "";
	private int[] location = new int[2];
	private Action action;
	private Label privateLabel = null;
	
	
	public Player(String userId, int avatar) {
		this.userId = userId;
		SetAvatar(avatar);
	}

	public Player(String userId, String nickName, int avatar) {
		this(userId, avatar);
		nickName = nickName;
	}

	public Player(String userId, String nickName, Label label) {
		this.userId = userId;
		this.nickName = nickName;
		SetLabel(label);
	}

	public void SetuserId(String id) {
		userId = id;
	}

	public String GetuserId() {
		return userId;
	}

	public void SetnickName(String nickName) {
		this.nickName = nickName;
	
	}

	public String GetnickName() {
		return nickName;
	}

	public int[] Getlocation() {
		return location;
	}
	
	
	public void SetAvatar(int avatar) {
		
		switch(avatar) {
		
		case 1:
			SetLabel(new Label("\ud83d\ude4b"));
			System.out.println("You are O'Connor");
			break;
			
		case 2: 
			SetLabel(new Label("\ud83c\udf7b"));
			System.out.println("You are beer");
			break;
			
		case 3: 
			SetLabel(new Label("\u2603"));
			System.out.println("You are snowman");
			break;
			
		case 4: 
			SetLabel(new Label("\u2620"));
			System.out.println("You are skull");
			break;
		case 5: 
			SetLabel(new Label("\u3020"));
			System.out.println("You are Weird Face");
			break;
		case 6: 
			SetLabel(new Label("\ud83d\udc35"));
			System.out.println("You are Monkey");
			break;
		
		default: System.out.println("Invalid avatar.");
		}
	}

	public int GetAvatarNumber() {
		switch (privateLabel.getText()) {
			case "\ud83d\ude4b":
				return 1;
			case "\ud83c\udf7b":
				return 2;
			case "\u2603":
				return 3;
			case "\u2620":
				return 4;
			case "\u3020":
				return 5;
			case "\ud83d\udc35":
				return 6;
		}
		return 1;
	}
	
	public Label GetLabel() {
		return this.privateLabel;
	}

	public void SetLabel(Label label) {
		privateLabel = label;
		GridPane.setHalignment(privateLabel, HPos.CENTER);
		label.setStyle("-fx-font-size:100px");
	}

	public Action GetAction() { 
		return action;
	}

	public void SetAction(Action action) {
		this.action = action;
	}
}
