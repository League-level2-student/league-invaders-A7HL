import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Alien extends GameObject {
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	public Alien(int x, int y, int width, int height) {
		
		// Initialize all the parameters in GameObject
		super(x, y, width, height);
		
		// set the speed of the alien to 1
		super.speed = 1;
		
		// Loading background image of the alien
		if (needImage) {
			
		    loadImage ("alien.png");
		    
		}
	}
	
	// Alien moves closer downward in the game panel
	public void update() {
		
		super.y += speed;
		
		// Updates collisionBox in GameObject
		super.update();
		
	}
	
	// Draws alien into the panel
	public void draw(Graphics g) {
		
		if (gotImage) {
			
			// Draws alien.png into the panel
			g.drawImage(image, x, y, width, height, null);
			
		} else {
			
			// If not, then it draws a yellow rectangle
			g.setColor(Color.YELLOW);
			
			g.fillRect(x, y, width, height);
			
		}
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
