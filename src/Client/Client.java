package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

   	 // int port = 8000;
	 // String host = "localhost";
		DataInputStream in;
		DataOutputStream out;
		Socket socket;
		boolean cont = true;

		Scanner input = new Scanner(System.in);

		try {
			socket = new Socket("localhost", 1916);
			System.out.println("Connected to server");

			in = new DataInputStream(socket.getInputStream());

			out = new DataOutputStream(socket.getOutputStream());

			while (cont) {

				System.out.println("Welcome");

				
				
				System.out.println("Would you like to continue?");
				System.out.println("1=Yes");
				System.out.println("2 = no");
				
				double exit = input.nextDouble();
				if (exit == 2) {
					cont = false;
					System.out.println("System Terminated");
					
				}
			}
		}

		catch (IOException e) {
		}
	}
}