package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javafx.scene.control.Label;

public class IThread extends Thread implements GUIConstants{

	ArrayList<Player> others = new ArrayList<Player>();	
	
	public void run() {
		try {
			while (true) {
				String output = Client.in.readUTF();
				//"poschange"+ this.threadName + " " + playerPos (String that is broadcast whenever any player changes position)
				if (output.startsWith("poschange")) {
					movePlayer(output);
				} else {
					Main.Gui.AddToChat(new Label(output));
				}
			}
		} catch (Exception e) {}
	}

	public void movePlayer(String output) {
		output = output.substring(9);
		String[] split = output.split(" ");
		int x = Integer.parseInt(split[1]);
		int y = Integer.parseInt(split[2]);
		for (Player other : others) {
			if(split[0].equals(other.GetuserId())) {
				Main.Gui.setLocation(ROWS, COLUMNS, Main.Gui.gridPane, x, y, other.GetLabel());
					return;
			}		
		}
		Player newPlayer = new Player(split[0], 6);
		Main.Gui.setLocation(ROWS, COLUMNS, Main.Gui.gridPane, x, y, newPlayer.GetLabel());
		others.add(newPlayer);
	}

}
