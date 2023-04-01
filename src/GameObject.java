import java.awt.Rectangle;

public class GameObject {
	
	int x, y , width, height;
	
	int speed = 0;
	
	boolean isActive = true;
	
	Rectangle collisionBox;
	
	// Initializing variables in the game object
	public GameObject(int x, int y, int width, int height) {
		
		this.x = x;
		
		this.y = y;
		
		this.width = width;
		
		this.height = height;
		
		// Initializes collision box
		collisionBox = new Rectangle( x, y, width, height);
		
	}
	
	public void update() {
		
        collisionBox.setBounds(x, y, width, height);
		
	}
}
