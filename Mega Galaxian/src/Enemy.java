
@SuppressWarnings("serial")
public class Enemy extends Sprite{
	int health, dmg;

	public Enemy(String file, int a, int b, int c, int d, int e, int f, int g) {
		super(file, a, b, c, d, e);
		dmg = f;
		health = g;
	}

	public Projectile shoot() {
		Projectile p = new Projectile("projectile.jpg", xpos+rad, ypos+rad, 7, 0, 12, dmg, false);
		return p;
	}
}
