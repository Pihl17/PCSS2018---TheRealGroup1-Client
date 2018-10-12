package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
//import java.util.ArrayList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javafx.scene.control.Label;

public class IThread extends Service<String> implements GUIConstants{

	protected Task<String> createTask() {
		return new Task<String>() {
			protected String call() {
				try {
					return Client.in.readUTF();
				} catch (Exception e) {
					e.printStackTrace();
					return "";
				}
			}
		};
	}

	protected void succeeded() {
		this.restart();
		this.start();
	}

}
