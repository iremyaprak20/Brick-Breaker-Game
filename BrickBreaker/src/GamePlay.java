import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GamePlay extends JPanel implements KeyListener {
	private boolean play = false;
	private int totalBricks = 32;
	private Thread timer;
	private Playable playAble;
	private int playerX = 310;
	private int ballPosX = 120; 
	private int ballPosY = 350; 
	private int ballXdir = -1;   
	private int ballYdir = -2;
	private boolean check = false;

	
	ImageIcon redBrick = new ImageIcon("img/red.png");
	ImageIcon yellowBrick = new ImageIcon("img/yellow.png");
	ImageIcon ball = new ImageIcon("img/ball.png");
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image paddle = tk.getImage("img/paddle (2).png");
	
	public GamePlay() {

		setIgnoreRepaint(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		playAble = new Playable(this);
		timer = new Thread(playAble);
		timer.start();
	} 

		
	
	public void paint(Graphics g) { 
		
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(1, 1, 692, 592);
		

		if(!check) {
			for(int i=0; i < 8 ; i++) {
				g.drawImage(yellowBrick.getImage(), (i*72)+5, 0, 70, 20, null );
			}
			for(int i=0; i < 8 ; i++) {
				g.drawImage(redBrick.getImage(), (i*72)+5, 22, 70, 20, null );
			}
			for(int i=0; i < 8 ; i++) {
				g.drawImage(yellowBrick.getImage(), (i*72)+5, 44, 70, 20, null );
			}
			for(int i=0; i < 8 ; i++) {
				g.drawImage(redBrick.getImage(), (i*72)+5, 66, 70, 20, null );
			}
		}

		
		g.drawImage(paddle, playerX, 500, 150, 50, null);

		g.drawImage(ball.getImage(),ballPosX,ballPosY,20, 20, null);
		g.dispose();
	
	}
	
	
	public void Update(){
		if(play) {
			
				if(new Rectangle(ballPosX, ballPosY, 30, 30).intersects(new Rectangle(playerX, 500, 150, 50))) {
					ballYdir = -ballYdir;
				}
				for(int i=0; i < 8 ; i++) {
					Rectangle rect = new Rectangle((i*72)+5, 0, 70, 20);
					
					if(new Rectangle(ballPosX, ballPosY, 30, 30).intersects(rect)){	
						ballYdir = -ballYdir;
					}
				}
				for(int i=0; i < 8 ; i++) {
					Rectangle rect1 = new Rectangle((i*72)+5, 22, 70, 20);
					if(new Rectangle(ballPosX, ballPosY, 30, 30).intersects(rect1)){	
						ballYdir = -ballYdir;
					}
				}
				for(int i=0; i < 8 ; i++) {
					Rectangle rect2 = new Rectangle((i*72)+5, 44, 70, 20);
					if(new Rectangle(ballPosX, ballPosY, 30, 30).intersects(rect2)){	
						ballYdir = -ballYdir;
					}
				}
				for(int i=0; i < 8 ; i++) {
					Rectangle rect3 = new Rectangle((i*72)+5, 66, 70, 20);
					if(new Rectangle(ballPosX, ballPosY, 30, 30).intersects(rect3)){	
						ballYdir = -ballYdir;
						check = true;
					}
				}
				
				
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if(ballPosX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballPosY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballPosX > 570) {    
				ballXdir = -ballXdir;
			}
		} 
		repaint();
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}
			else {
				moveLeft();
			}
		}
	}
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}


}
