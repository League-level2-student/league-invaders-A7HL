import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	
	int currentState = MENU;
	
	Font titleFont;
	Font otherFont;
	
	ObjectManager objMan;
	
	Timer frameDraw;
	Timer alienSpawn;
	
	Rocketship player;
	
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	public GamePanel() {
		 titleFont = new Font("Arial", Font.PLAIN, 48);
		 otherFont = new Font("Arial",Font.PLAIN,24);
		 frameDraw = new Timer(1000/60,this);
		 frameDraw.start();
		 
		 player = new Rocketship(250,700,50,50);
		 objMan = new ObjectManager(player);
		 
		 if (needImage) {
			    loadImage ("space.png");
		}
	}
	public void startGame() {
		   alienSpawn = new Timer(1000 , objMan);
		    alienSpawn.start();
		   
	}
	
	 public void updateMenuState(){  
		 
	 }
	 public void updateGameState(){  
		 objMan.update();
		 if(player.isActive == false) {
				currentState = END;
		}
		 
		 
	 }
	 public void updateEndState(){ 
		 
	 }
	 public void drawMenuState(Graphics g){
		 g.setColor(Color.BLUE);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 
		 g.setFont(titleFont);
		 g.setColor(Color.YELLOW);
		 g.drawString("LEAGUE INVADERS", 23, 100);
		 g.setFont(otherFont);
		 g.drawString("Press ENTER to start",142,350);
		 g.drawString("Press SPACE for instructions", 105, 475);
	 }
	 public void drawGameState(Graphics g){
		
		 if (gotImage) {
				g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
			} else {
				 g.setColor(Color.BLACK);
				 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
			}
		 
		 
		 
		 objMan.draw(g);
		 
	 }
	 public void drawEndState(Graphics g) {
		 g.setColor(Color.RED);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setFont(titleFont);
		 g.setColor(Color.YELLOW);
		 g.drawString("GAME OVER",115, 100);
		 g.setFont(otherFont);
		 g.drawString("You killed enemies",147,350);
		 g.drawString("Press ENTER to restart", 135, 475);
		 
	 }
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(up)
					player.up();
		if(down)
					player.down();
		if(left)
					player.left();
		if(right)
					player.right();
		
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	player = new Rocketship(250,700,50,50);
		    	objMan = new ObjectManager(player);
		        currentState = MENU;
		    } else if(currentState == MENU){
		    	startGame();
		  
		        currentState++;
		    }else {
		    	alienSpawn.stop();
		    	currentState++;
		    }
		}  
		//Make boolean switches in keyPressed() and keyReleased(), which then triggers the methods on or off.
		if (arg0.getKeyCode()==KeyEvent.VK_UP) {
			up = true;
				
		}
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			down = true;
				
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			left = true;
				
		}
		if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			right = true;
				
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			objMan.addProjectile(player.getProjectile());
				
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_UP) {
			up = false;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			down = false;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			left = false;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
