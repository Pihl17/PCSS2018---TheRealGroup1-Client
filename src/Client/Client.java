package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

   
		DataInputStream in;
		DataOutputStream out;
		Socket socket;
		boolean cont = true;

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		try {
			socket = new Socket("192.168.43.43", 1916);
			System.out.println("Connected to server");

			in = new DataInputStream(socket.getInputStream());

			out = new DataOutputStream(socket.getOutputStream());

		while (cont) {

				System.out.println("Welcome");
				new Player(null, null, null);
			//	Player.SetnickName(null);
				
				String nickName = input.nextLine();
				out.writeUTF(nickName);
			//	System.out.println(in.readInt());
			//	stuff needs to go here
			
				
				
			}
		}

		catch (IOException e) {
		}
	}
}