package Client;

public class Main {

	public static void main(String[] args) {
		GUI.DisplayChat();
		try {
			String[] chatLog = {"Test1", "Test2", "Test3", "Test4"};
			GUI.UpdateChat("Test1","Test2");
			Thread.sleep(2000);
			GUI.UpdateChat("Test1","Test2","Test3");
			Thread.sleep(1000);
			GUI.UpdateChat(chatLog);
			Thread.sleep(500);
			GUI.AddToChat("Test5");
		} catch (InterruptedException e) {}
	}
}
