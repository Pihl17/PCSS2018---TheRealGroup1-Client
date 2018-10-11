package Client;

import java.util.Scanner;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

enum Action {
	TALKING, JOINING, LEAVING
};

public class Player implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private static String nickName;
	private int[] location;
	private Action action;
	private int avatar = 6;
	Label privateLabel = null;
	
	
	static Scanner input = new Scanner(System.in);
	
	public Player(String userId, int avatar) {
		this.userId = userId;
		this.avatar = avatar;
		SetAvatar();
	}

	public Player(String userId, String nickName, Label label) {
		this.userId = userId;
		this.nickName = nickName;
	}

	public void SetuserId() {
		String userId = input.nextLine();

	}

	public String GetuserId() {
		return userId;
	}

	public static void SetnickName(String nickName) {
		input.nextLine();
	
	}

	public static String GetnickName() {
		return nickName;
	}

	public int[] Getlocation() {
		return location;
	}
	
	
	public void SetAvatar() {
		
		Label label = null;
		switch(avatar) {
		
		case 1:
			label = new Label("\ud83d\ude4b");
			GridPane.setHalignment(label, HPos.CENTER);
			label.setStyle("-fx-font-size:100px;");
			System.out.println("You are O'Connor");
			break;
			
		case 2: 
			label = new Label("\ud83c\udf7b");
			GridPane.setHalignment(label, HPos.CENTER);
			label.setStyle("-fx-font-size:100px;");
			System.out.println("You are beer");
			break;
			
		case 3: 
			label = new Label("\u2603");
			GridPane.setHalignment(label, HPos.CENTER);
			label.setStyle("-fx-font-size:100px");
			System.out.println("You are snowman");
			break;
			
		case 4: 
			label = new Label("\u2620");
			GridPane.setHalignment(label, HPos.CENTER);
			label.setStyle("-fx-font-size:100px");
			System.out.println("You are skull");
			break;
		case 5: 
			Label weirdFace = new Label("\u3020");
			GridPane.setHalignment(weirdFace, HPos.CENTER);
			weirdFace.setStyle("-fx-font-size:100px");
			System.out.println("You are Weird Face");
			break;
		case 6: 
			Label monkey = new Label("\ud83d\udc35");
			GridPane.setHalignment(monkey, HPos.CENTER);
			monkey.setStyle("-fx-font-size:100px");
			System.out.println("You are Monkey");
			break;
		
		default: System.out.println("Invalid avatar. Follow the Rules you idiot!");
		}
		this.privateLabel = label;
	}
	
	public int GetAvatar() {
		return avatar;
	}
	
	public Label GetLabel() {
		return this.privateLabel;
	}

	public Action GetAction() {
		return action;
	}
}