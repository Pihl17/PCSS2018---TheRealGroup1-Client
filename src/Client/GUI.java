package Client;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class GUI {
	
	static JPanel panel = new JPanel(new GridLayout(1, 1));

	public static void DisplayChat(String... strings) {
		panel = new JPanel(new GridLayout(strings.length, 1));
		for (String string : strings) {
			JLabel label = new JLabel(string);
			panel.add(label);
		}
		JFrame frame = new JFrame("Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}


public void ConnectToSever() {

}

public void DisplayLobby(List <Player>) {
	
}

public void DisplayMap(<List <Player>) {
	
}
