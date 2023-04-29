import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles;
	ArrayList<Alien> aliens;
	Random random;
	public ObjectManager(Rocketship r) {
		rocket = r;
		projectiles = new ArrayList<Projectile>();
		aliens = new ArrayList<Alien>();
		random = new Random();
	}
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	public void update() {
		checkCollision();
		purgeObjects();
		if(rocket.isActive == false) {
			//GamePanel.currentState = END;
		}
		for(Alien a:aliens) {
			a.update();
			if(a.y>LeagueInvaders.HEIGHT) {
				a.isActive = false;
			}
		}
		for(Projectile p:projectiles) {
			p.update();
			if(p.y<0) {
				p.isActive = false;
			}
		}
	}
	public void draw(Graphics g) {
		rocket.draw(g);
		for(Alien a:aliens) {
			a.draw(g);
		}
		for(Projectile p:projectiles) {
				p.draw(g);
		}
	}
	public void checkCollision() {
		for(Alien a:aliens) {
			if(rocket.collisionBox.intersects(a.collisionBox)) {
				rocket.isActive = false;
				break;
			}
			for(Projectile p: projectiles) {
				if(p.collisionBox.intersects(a.collisionBox)) {
					a.isActive = false;
					p.isActive = false;
					break;
				}
			}
		}
	}
	public void purgeObjects() {
		
		for(int i =aliens.size()-1;i>=0;i--) {
			if(!(aliens.get(i).isActive)) {
				aliens.remove(i);
			}
		}
		for(int j =projectiles.size()-1;j>=0;j--) {
			if(!(projectiles.get(j).isActive)) {
				projectiles.remove(j);
			}
		}

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
