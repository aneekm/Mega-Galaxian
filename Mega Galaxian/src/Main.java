 import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JFrame{
	HashMap<Integer, Stack<Sprite>> levels;
	ArrayList<Sprite> out;
	ArrayList<Projectile> bullets;
	ArrayList<Boolean> outbool;
	boolean rising = false;
	JLabel[] numslvl= {new JLabel(new ImageIcon("0.png")), new JLabel(new ImageIcon("1.png")),
			new JLabel(new ImageIcon("2.png")), new JLabel(new ImageIcon("3.png")),
			new JLabel(new ImageIcon("4.png")), new JLabel(new ImageIcon("5.png")),
			new JLabel(new ImageIcon("6.png")), new JLabel(new ImageIcon("7.png")),
			new JLabel(new ImageIcon("8.png")), new JLabel(new ImageIcon("9.png"))
	};
	JLabel[] numspoints= {new JLabel(new ImageIcon("0.png")), new JLabel(new ImageIcon("1.png")),
			new JLabel(new ImageIcon("2.png")), new JLabel(new ImageIcon("3.png")),
			new JLabel(new ImageIcon("4.png")), new JLabel(new ImageIcon("5.png")),
			new JLabel(new ImageIcon("6.png")), new JLabel(new ImageIcon("7.png")),
			new JLabel(new ImageIcon("8.png")), new JLabel(new ImageIcon("9.png"))
	};
	JLabel [] numslives = {new JLabel(new ImageIcon("player.png")), new JLabel(new ImageIcon("player.png")),
			new JLabel(new ImageIcon("player.png"))
	};
	JPanel window;
	JPanel main;
	Game game;
	JPanel startwindow;
	JButton start;
	JLabel title2;
	boolean pauze;
	JLabel paused;
	ImageIcon restartimg;
	ImageIcon titlepic, titlepic2;
	JPanel endwindow;
	JPanel endgameinfo;
	JPanel endlevel, endpoints;
	JButton restart;
	JLabel pointsimg;
	JLabel levelimg;
	JPanel livespanel;
	JLabel livesimg;
	BufferedImage background;
	CardLayout cards;
	int lvl;
	int pause;
	Player player;
	Random random = new Random();
	Timer timer;
	JPanel infoGame;
	JPanel infoPlayer;
	JTextArea lives;
	boolean lose = false;
	Font font = new Font("DIALOG", Font.BOLD, 16);
	
	public Main() {
		player = new Player("player.png", 500, 450, 20, 0, 0);
		levels = new HashMap<Integer, Stack<Sprite>>();
		out = new ArrayList<Sprite>();
		bullets = new ArrayList<Projectile>();
		outbool = new ArrayList<Boolean>();
		timer = new Timer(20, new TL());
		window = new JPanel(new BorderLayout());
		main = new JPanel(new BorderLayout());
		startwindow = new JPanel(new BorderLayout());
		titlepic = new ImageIcon("title.gif");
		titlepic2 = new ImageIcon("title.png");
		start = new JButton(titlepic);
		title2 = new JLabel(titlepic2);
		pointsimg = new JLabel(new ImageIcon("points.png"));
		levelimg = new JLabel(new ImageIcon("level.png"));
		livesimg = new JLabel(new ImageIcon("lives.png"));
		endwindow = new JPanel(new BorderLayout());
		restartimg = new ImageIcon("restart.gif");
		restart = new JButton(restartimg);
		try {
		    background = ImageIO.read(new File("background2.jpg"));
		} catch (IOException e) {} 
		for(int i=1; i<20; i++) {
			levels.put(i, new Stack<Sprite>());
			for (int j=0;j<i*2+5;j++) {
				levels.get(i).push(new Enemy("enemy.png", random.nextInt(1000), 10, 15, random.nextInt(10), random.nextInt(10), 5*i, 3*i));
			}
		}
		lvl = 1;
		for (int i=0;i<levels.get(lvl).size();i++) {
			out.add(levels.get(lvl).pop());
		}
		pause = 0;
		game = new Game();
		infoGame = new JPanel();
		infoPlayer = new JPanel(new GridLayout(1, 3));
		paused = new JLabel(new ImageIcon("paused.png"));
		endgameinfo = new JPanel();
		endgameinfo.setLayout(new BoxLayout(endgameinfo, BoxLayout.PAGE_AXIS));
		livespanel = new JPanel();
		endlevel = new JPanel();
		endpoints = new JPanel();
	}
	
	public Main createGUI(){
		game.addKeyListener(new KL());
		game.setFocusable(true);
		start.addActionListener(new startAL());
		restart.addActionListener(new endAL());
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		cards = new CardLayout();
		this.setLayout(new BorderLayout());
		this.add(window, BorderLayout.CENTER);
		infoGame.setBackground(Color.black);
		infoGame.add(levelimg);
		levelimg.setVisible(true);
		for (int i=0; i<numslvl.length;i++) {
			infoGame.add(numslvl[i]);
			numslvl[i].setVisible(false);
		}
		infoGame.add(pointsimg);
		for (int i=0; i<numspoints.length;i++) {
			infoGame.add(numspoints[i]);
			numspoints[i].setVisible(false);
		}
		pointsimg.setVisible(true);
		infoPlayer.setBackground(Color.black);
		livespanel.add(livesimg);
		for (int i=0; i<numslives.length;i++) {
			livespanel.add(numslives[i]);
			numslives[i].setVisible(true);
		}
		startwindow.add(start);   
		start.setLocation(-400, -400);
		livespanel.setBackground(Color.black);
		infoPlayer.add(livespanel);
		JPanel empty = new JPanel();
		empty.setBackground(Color.black);
		infoPlayer.add(empty);
		infoPlayer.add(paused);
		paused.setVisible(false);
		window.setLayout(cards);
		window.add("start", startwindow);
		window.add("game", main);
		window.add("end", endwindow);
		main.add(infoGame, BorderLayout.NORTH);
		main.add(game, BorderLayout.CENTER);
		main.add(infoPlayer, BorderLayout.SOUTH);
		endwindow.add(title2, BorderLayout.NORTH);
		endwindow.add(endgameinfo, BorderLayout.CENTER);
		endgameinfo.add(Box.createVerticalGlue());
		endgameinfo.add(endlevel);
		endgameinfo.add(endpoints);
		endgameinfo.add(restart);
		endgameinfo.add(Box.createVerticalGlue());
		cards.show(window, "start");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,700);
		this.setVisible(true);
		return this;
	}
	
	public class Game extends JPanel {
		int count=0;
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			this.removeAll();
			if (count-3500 >=0) {
				count = 0;
			}
			g.drawImage(background, 0, -3500+count, null);
			for (int i=0; i<out.size();i++) {
				this.add(out.get(i));
				out.get(i).setSize(out.get(i).rad*2,out.get(i).rad*2);
				out.get(i).setLocation(out.get(i).xpos, out.get(i).ypos);
			}
			for (int i=0;i<bullets.size();i++) {
				if(bullets.get(i).side) {
					g.setColor(Color.red);
					g.fillRect(bullets.get(i).xpos, bullets.get(i).ypos, bullets.get(i).rad, 2*bullets.get(i).rad);
				} else {
					g.setColor(Color.yellow);
					g.fillRect(bullets.get(i).xpos, bullets.get(i).ypos, bullets.get(i).rad, 2*bullets.get(i).rad);
				}
			}
			if (rising) {
				g.setColor(Color.green);
				g.drawOval(player.xpos-player.rad/2,  player.ypos-player.rad/2,  player.rad*3,  player.rad*3);
			}
			this.add(player);
			player.setSize(player.rad*2, player.rad*2);
			player.setLocation(player.xpos, player.ypos);
			if (pauze) {
				paused.setVisible(true);
			} else if (!pauze) {
				paused.setVisible(false);
				count++;
			}
			for (int i=0; i<numslvl.length;i++) {
				numslvl[i].setVisible(false);
			}
			for (int i=0; i<numspoints.length;i++) {
				numspoints[i].setVisible(false);
			}
			infoGame.removeAll();
			infoGame.add(levelimg);
			String number = String.valueOf(lvl);
			for(int i = 0; i < number.length(); i++) {
			    int j = Character.digit(number.charAt(i), 10);
			    infoGame.add(numslvl[j]);
			    numslvl[j].setVisible(true);
			}
			infoGame.add(pointsimg);
			number = String.valueOf(player.points);
			for(int i = 0; i < number.length(); i++) {
			    int j = Character.digit(number.charAt(i), 10);
			    infoGame.add(numspoints[j]);
			    numspoints[j].setVisible(true);
			}
			for (int i=0;i<numslives.length;i++) {
				if (i>=player.lives) {
					numslives[i].setVisible(false);
				}
			}
			repaint();
		}
	}
	
	public boolean dist(int w, int x, int y, int z, int r1, int r2) {
		return Math.pow(w-y, 2) + Math.pow(x-z, 2) <= Math.pow(r1+r2,2);
	}
	
	public void startgame() {
		cards.show(window, "game");
		this.validate();
		this.repaint();
		game.requestFocus();
		timer.start();
	}
	
	public void reset() {
		player.lives = 3;
		player.points = 0;
		player.xvel = 0;
		player.yvel = 0;
		out.clear();
		bullets.clear();
		outbool.clear();
		levels.clear();
		for(int i=1; i<20; i++) {
			levels.put(i, new Stack<Sprite>());
			for (int j=0;j<i*2+5;j++) {
				levels.get(i).push(new Enemy("enemy.png", random.nextInt(900), 10, 15, random.nextInt(10), random.nextInt(10), 5*i, 3*i));
			}
		}
		lvl = 1;
		for (int i=0;i<levels.get(lvl).size();i++) {
			out.add(levels.get(lvl).pop());
		}
		for (int i=0; i<numslives.length;i++) {
			livespanel.add(numslives[i]);
			numslives[i].setVisible(true);
		}
	}
	
	public void endgame() {
		timer.stop();
		cards.show(window, "end");
		endlevel.removeAll();
		endlevel.add(levelimg);
		String number = String.valueOf(lvl);
		for(int i = 0; i < number.length(); i++) {
		    int j = Character.digit(number.charAt(i), 10);
		    endlevel.add(numslvl[j]);
		    numslvl[j].setVisible(true);
		}
		endpoints.removeAll();
		endpoints.add(pointsimg);
		number = String.valueOf(player.points);
		for(int i = 0; i < number.length(); i++) {
		    int j = Character.digit(number.charAt(i), 10);
		    endpoints.add(numspoints[j]);
		    numspoints[j].setVisible(true);
		}
		this.validate();
		this.repaint();
	}
	
	public class startAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startgame();
		}
	}
	
	public class endAL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			reset();
			startgame();
		}
	}
	
	public class TL implements ActionListener {
		int count = 0;
		int riscount = 0;
		boolean tmp = false;
		public void actionPerformed(ActionEvent e) {
			for (int i=0;i<out.size();i++) {
				out.get(i).move();
				if (out.get(i).xpos + out.get(i).xvel >=  game.getWidth()-5|| out.get(i).xpos + out.get(i).xvel <= 0) {
					out.get(i).xvel = (-1*out.get(i).xvel);
				}
				if (out.get(i).ypos + out.get(i).yvel >= game.getHeight()-200 || out.get(i).ypos  + out.get(i).yvel <= 0) {
					out.get(i).yvel = (-1*out.get(i).yvel);
				}
				if (out.get(i).xpos>game.getWidth()) {
					out.get(i).xpos = 990;
					out.get(i).ypos = 20;
				}
				if (!rising) {
					if(dist(player.xpos, player.ypos, out.get(i).xpos, out.get(i).ypos, player.rad, out.get(i).rad)){
						player.die(500, 450);
						rising = true;
						riscount = 0;
						player.addPoint();
						out.remove(i);
					}
				}
				if(count>100) {
					bullets.add(((Enemy) out.get(i)).shoot());
	        		outbool.add(true);
	        		tmp = true;
				}
			}
			if (tmp) {count = 0;tmp = false;}
			for (int i=0;i<bullets.size();i++) {
				bullets.get(i).move();
				if(!bullets.get(i).side) {
					if (!rising) {
						if(dist(bullets.get(i).xpos, bullets.get(i).ypos, player.xpos, player.ypos, bullets.get(i).rad, player.rad)) {
							player.die(500, 450);
							rising = true;
							riscount = 0;
							outbool.set(i, false);
						}
					}
				} else {
					for (int j=0;j<out.size();j++) {
						if(dist(bullets.get(i).xpos, bullets.get(i).ypos, out.get(j).xpos+out.get(j).rad, out.get(j).ypos, bullets.get(i).rad, out.get(j).rad)){
							out.remove(j);
							player.addPoint();
							outbool.set(i, false);
						}
					}
					if (bullets.get(i).ypos >= game.getHeight() || bullets.get(i).ypos <= 0) {
						outbool.set(i, false);
					} 
				}
			}
			for (int i=0; i<outbool.size();i++) {
				if (!outbool.get(i)) {
					bullets.remove(i);
					outbool.remove(i);
				}
			}
			if(out.size() == 0) {
				lvl++;
				for (int i=0;i<levels.get(lvl).size();i++) {
					out.add(levels.get(lvl).pop());
					
				}
			}
			if (player.lives<0) {
				endgame();
			}
			if (rising) {
				riscount++;
			}
			if (riscount>100) {
				rising = false;
			}
			player.move();
			count++;
			repaint();
		}
	}
	
	public class KL implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		            player.yvel = -10;
		            break;
		        case KeyEvent.VK_DOWN:
		            player.yvel = 10;
		            break;
		        case KeyEvent.VK_LEFT:
		            player.xvel= -10;
		            break;
		        case KeyEvent.VK_RIGHT:
		            player.xvel = 10;
		            break;
		     }
		}
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		            player.yvel = 0;
		            break;
		        case KeyEvent.VK_DOWN:
		            player.yvel = 0;
		            break;
		        case KeyEvent.VK_LEFT:
		            player.xvel= 0;
		            break;
		        case KeyEvent.VK_RIGHT:
		            player.xvel = 0;
		            break;
		        case KeyEvent.VK_P:
					if(pause%2==0) {
						pauze = true;
	    				timer.stop();
	    			}
	    			if(pause%2==1) {
	    				pauze = false;
	    				timer.start();
	    			}
	    			pause++;
	        		break;
		        case KeyEvent.VK_SPACE:
	        		bullets.add(player.shoot());
	        		outbool.add(true);
	        		break;
		     }
		}
		public void keyTyped(KeyEvent e) {}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.createGUI();
	}
}
