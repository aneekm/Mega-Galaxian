
@SuppressWarnings("serial")
public class Projectile extends Sprite{
	int dmg;
	boolean side; 
	public Projectile(String file, int a, int b, int c, int d, int e, int f, boolean s) {
		super(file, a, b, c, d, e);
		dmg = f;
		side = s;
	}

}
