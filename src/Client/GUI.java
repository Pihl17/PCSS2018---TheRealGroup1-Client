package Client;
import java.awt.GridLayout;
import javax.swing.JPanel;

import javafx.scene.layout.Pane;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;

public class GUI {
	
	static JPanel chatPanel = new JPanel(new GridLayout(1, 1));
	static JFrame frame = new JFrame("Chat");

	public static void DisplayChat() {
		chatPanel = new JPanel();
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(chatPanel);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}
  
	public static void UpdateChat(String... chatLog) {
		chatPanel.removeAll();
		for (String string : chatLog) {
			JLabel label = new JLabel(string);
			chatPanel.add(label);
		}
		chatPanel.revalidate();
		chatPanel.updateUI();
	}

	public static void AddToChat(String s) {
		JLabel label = new JLabel(s);
		chatPanel.add(label);
		chatPanel.revalidate();
		chatPanel.updateUI();
	}
}

