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

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	// Declaring time variable
	Timer frameDraw;

	// Alien spawns member variable
	Timer alienSpawn;

	// Setting the state of the game
	int currentState = MENU;

	// Declaring fonts for variables
	Font titleFont, enterFont, spaceFont, scoreFont;

	// Instantiating rocket ship and passing x, y, width, and height positions
	Rocketship rocketShip = new Rocketship(250, 700, 50, 50);

	// Declaring and initializing objectManage and passing rocket Ship as a
	// parameter
	ObjectManager objectManager = new ObjectManager(rocketShip);
	

	// Initializing GamePanel. Sets the font of title, enter, space, and variables.
	public GamePanel() {

		titleFont = new Font("Arial", Font.PLAIN, 48);

		enterFont = new Font("Arial", Font.PLAIN, 30);

		spaceFont = new Font("Arial", Font.PLAIN, 30);
		
		scoreFont = new Font("Arial", Font.BOLD, 40);

		// Running the programs at 60 frames per second (1000 milliseconds = 1 second)
		// Keyword "this" points to the panel itself to be redrawn again
		
		frameDraw = new Timer(1000 / 60, this);

		// We start drawing the frame repeatedly
		frameDraw.start();

		// Calling the image to be in the background
		if (needImage) {
			loadImage("space.png");
		}

	}

	// Depending on the current state, either the program calls drawMenuState,
	// or drawGameState, or drawEndState.
	@Override
	public void paintComponent(Graphics g) {

		if (currentState == MENU) {

			drawMenuState(g);

		} else if (currentState == GAME) {

			drawGameState(g);

		} else if (currentState == END) {

			drawEndState(g);

		}
	}

	void updateMenuState() {

	}

	void updateGameState() {
				
		if( rocketShip.isActive == true) {
			
			objectManager.update();
			
		} else {
			
			currentState = END;
			
		}
	}

	void updateEndState() {

	}

	// The color of the window becomes blue when the game begins
	void drawMenuState(Graphics g) {

		// Setting the color of the window to blue
		g.setColor(Color.BLUE);

		// Drawing a rectangle for the background
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		// Sets the color of the title and the position
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 30, 200);

		// Sets the color of ENTER to start and its position
		g.setFont(enterFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 100, 400);

		// Sets the color of SPACE for instructions and its position
		g.setFont(spaceFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 50, 700);

	}

	// The color of the window becomes black when the game is in session
	void drawGameState(Graphics g) {
		
		String currentScore = Integer.toString(objectManager.getScore());
		
		if (gotImage) {
			
			// Drawing the space image in the background
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
			
		} else {
			
			// If no space image, then draw a black rectangle for the background
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		// Sets the font and string of current score
		g.setFont(scoreFont);
		g.setColor(Color.RED);
		g.drawString("Score: " + currentScore, 30, 50);
		
		
		// Drawing a rocket ship in the game panel
		objectManager.draw(g);

	}

	// The color of the window becomes red when the game ends
	void drawEndState(Graphics g) {

		// Setting the color of the window to red
		g.setColor(Color.RED);

		// Drawing a rectangle for the background
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		// Sets the color of the title and the position
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 100, 200);

		// Set the color of killed enemies and its position
		g.setFont(enterFont);
		g.setColor(Color.YELLOW);
		g.drawString("You killed " + objectManager.getScore() + " enemies", 120, 400);

		// Sets the color of Enter to restart and its position
		g.setFont(spaceFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 100, 700);

	}

	// Checks the state of the game 60 frames a second a
	// and calls the appropriate method
	@Override
	public void actionPerformed(ActionEvent e) {

		if (currentState == MENU) {

			updateMenuState();

		} else if (currentState == GAME) {

			updateGameState();

		} else if (currentState == END) {

			updateEndState();

		}

		System.out.println("action");

		// Calls repaint method and the frame becomes redrawn
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		// Increases the current state of the game and loops back to MENU
		if (key == KeyEvent.VK_ENTER) {

			if (currentState == END) {

				currentState = MENU;
				
				alienSpawn.stop();
				
				rocketShip = new Rocketship(250, 700, 50, 50);
				
				objectManager = new ObjectManager(rocketShip);
				
			} else {

				// Changes the current state to GAME
				currentState++;
				
				// game starts and aliens begin to spawn
				startGame();

			}
		}

		// Checks keys only if the current state of the game is GAME
		if (currentState == GAME) {

			if (key == KeyEvent.VK_UP) {
				System.out.println("UP");
				rocketShip.up();
			}
			
			if (key == KeyEvent.VK_DOWN) {
				System.out.println("DOWN");
				rocketShip.down();
			}
			
			if (key == KeyEvent.VK_LEFT) {
				System.out.println("LEFT");
				rocketShip.left();
			}
			
			if (key == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
				rocketShip.right();
				
			}
			
			// A projectile is created when the space bar is pressed
			if( key == KeyEvent.VK_SPACE) {
				
				objectManager.addProjectile(rocketShip.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	// Loads the space.png image into the background
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
	
	// Create aliens in the game when the game is started
	void startGame() {
		
		// spawns a new alien every second and the reference is the objectManager, 
		// where the code of the alien will is implemented
	    alienSpawn = new Timer(1000 , objectManager);
	    
	    alienSpawn.start();
	}

}
