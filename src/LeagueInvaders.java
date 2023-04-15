import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	JFrame frame;
	
	GamePanel p;
	
	
	public LeagueInvaders() {
		frame = new JFrame();
		p = new GamePanel();
	
	}
	
	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}
	
	
	public void setup() {
		frame.add(p);
		frame.addKeyListener(p);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(WIDTH,HEIGHT));
		frame.setVisible(true);
	}
	
}
