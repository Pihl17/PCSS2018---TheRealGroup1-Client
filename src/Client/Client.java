package Client;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import java.util.Date;

Socket socket = new Socket(host, 1916);

int port = 1916;
String host = "localhost"
DataInputStream in;
DataOutputStream out;



in = new DataInputStream
	(socket.getInputStream());
out = new DataOutputStream
	(socket.getOutputStream());
out.writeDouble(aNumber);
System.out.println(in.readDouble());

