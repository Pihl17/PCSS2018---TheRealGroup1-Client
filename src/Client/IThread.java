package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class IThread extends Thread {

	public void run() {
		try {
			while (true) {
				String output = Client.in.readUTF();
				//"poschange"+ this.threadName + " " + playerPos (String that is broadcast whenever any player changes position)
			}
		} catch (Exception e) {}
	}

}
