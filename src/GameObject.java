import java.awt.Rectangle;

public class GameObject {
	 int x;
	 int y;
	 int width;
	 int height;
	 int speed;
	 boolean isActive;
	 Rectangle collisionBox;
	 
	 public GameObject(int x, int y, int width, int height) {
		 this.x = x;
		 this.y = y;
		 this.width = width;
		 this.height = height;
		 speed = 10;
		 collisionBox = new Rectangle(x,y,width,height);
		 isActive = true;
	 }
	 public void update() {
		 collisionBox.setBounds(x, y, width, height);
		
	 }
	 
}
