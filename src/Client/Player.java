package Client;

enum Action{TALKING, JOINING, LEAVING};

public class Player implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String nickName;
	private int[] Location;
	private Action action;
	
	public Player(String userId, String nickName) {
		this.userId = userId;
		this.nickName = nickName;
	}
	
public void SetUserId() {
	
}
public void SetnickName() {
	
}
public String GetnickName() {
	return nickName;
}
public int[] GetLocation() {
	return Location;
}
public void SetLocation() {
	
}
public Action GetAction() {
	return action;
}
}