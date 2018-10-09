package Client;

import java.util.Scanner;

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

	static Scanner input = new Scanner(System.in);

	public Player(String userId, String nickName, int[] location) {
		this.userId = userId;
		this.nickName = nickName;
	}

	public void SetuserId() {
		String userId = input.nextLine();
		this.nickName = userId;

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
		// switch case 
	}
	
	

	public Action GetAction() {
		return action;
	}
}