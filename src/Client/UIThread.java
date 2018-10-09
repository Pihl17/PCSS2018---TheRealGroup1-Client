package Client;

public class UIThread extends GUI implements Runnable {

	public void run() {
		GUI.launch();
	}
}
