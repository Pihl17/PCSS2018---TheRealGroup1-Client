package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static DataInputStream in;
	public static DataOutputStream out;
	public static Socket socket;
	
	public static void main(String[] args) {

		try {
			socket = new Socket(args[0], Integer.parseInt(args[1]));
			System.out.println("Connected to server");

			in = new DataInputStream(socket.getInputStream());

			out = new DataOutputStream(socket.getOutputStream());

			new Thread(new UIThread()).start();
			
		}

		catch (IOException e) {
		}
	}
}
