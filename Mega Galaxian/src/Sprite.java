import javax.swing.ImageIcon;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Sprite extends JLabel{
	
	int xpos, ypos, rad, xvel, yvel;
	
	public Sprite(String file, int a, int b, int c, int d, int e) {
		super(new ImageIcon(file));
		xpos = a;
		ypos = b;
		rad = c;
		xvel = d;
		yvel = e;
	}
	
	//method to move the sprite 
	public void move() {
		xpos = xpos + xvel;
		ypos = ypos + yvel;
	}
	
}
