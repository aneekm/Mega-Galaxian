
@SuppressWarnings("serial")
public class Player extends Sprite{
	int health, dmg, lives;
	int points = 0;
	
	public Player(String file, int a, int b, int c, int d, int e) {
		super(file, a, b, c, d, e);
		lives = 3;
		health = 100;
		dmg = 20;
	}
	
	public void die(int x, int y) {
		lives--;
		xpos = x;
		ypos = y;
	}
	
	public void addPoint() {
		points++;
	}
	
	public Projectile shoot() {
		Projectile p = new Projectile("projectile.jpg", xpos+rad, ypos, 7, 0, -12, dmg, true);
		return p;
	}
}
