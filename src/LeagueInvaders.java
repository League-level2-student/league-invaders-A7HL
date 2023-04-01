import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	JFrame frame;
	
	
	public LeagueInvaders() {
		frame = new JFrame();
	
	}
	
	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}
	
	
	public void setup() {
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(WIDTH,HEIGHT));
		frame.setVisible(true);
	}
	
}
