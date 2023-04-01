import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	// Crating constructor and passing values to super()
	public Rocketship(int x, int y, int width, int height) {

		super(x, y, width, height);

		// Setting the speed of the rocket ship to 10
		super.speed = 20;

		// Calling rocket.png to be in the background
		if (needImage) {

			loadImage("rocket.png");

		}

	}

	// Creating a blue rectangle at a specific position
	public void draw(Graphics g) {

		// Draw the background image. Otherwise, draw a blue rectangle if there is no image present
		if (gotImage) {

			g.drawImage(image, x, y, width, height, null);

		} else {

			g.setColor(Color.BLUE);

			g.fillRect(x, y, width, height);
		}

	}

	// Rocket ship moves to the right
	public void right() {

		// Checks if the rocket ship is not outside of the panel (+60). 
		// Allows the rocket to be within the panel
		if (super.x + 60 <= LeagueInvaders.WIDTH) {

			super.x += speed;

			// Updates collision box
			super.update();
		}

	}

	// Rocket ship moves to the left
	public void left() {

		if (super.x >= 0) {

			super.x -= speed;

			// Updates collision box
			super.update();
		}
	}

	// Rocket ship moves upward
	public void up() {

		super.y -= speed;

		// updates collision box
		super.update();
	}

	// Rocket ship moves down ward
	public void down() {

		super.y += speed;

		// updates collision box
		super.update();
	}

	// Loads image
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

	// Creates a projectile from the center of the rocket
	public Projectile getProjectile() {

		return new Projectile(x + width / 2, y, 10, 10);

	}
}
