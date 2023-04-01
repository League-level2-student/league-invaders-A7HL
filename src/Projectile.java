import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	// Calling a Projectile object and passing values to super
	public Projectile(int x, int y, int width, int height) {

		super(x, y, width, height);

		super.speed = 10;
		loadImage("rocket.png");
	}

	public void update() {

		super.y -= speed;

		// Updates collisionBox in GameObject
		super.update();

	}

	// Draws alien into the panel
	public void draw(Graphics g) {

		// Draw the background image. Otherwise, draw a blue rectangle if there is no
		// image present
		if (gotImage) {

			g.drawImage(image, x, y, width, height, null);

		} else {

			g.setColor(Color.RED);

			g.fillRect(x, y, width, height);
		}
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

}
