
@SuppressWarnings("serial")
public class Obstacle extends Sprite{
	int health;
	
	public Obstacle(String file, int a, int b, int c, int d, int e) {
		super(file, a, b, c, d, e);
		health = 40;
	}

}
