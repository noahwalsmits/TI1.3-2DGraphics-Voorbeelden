import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by johan on 15-3-2017.
 */
public class Platformer extends JPanel implements ActionListener {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Platformer");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(900,710));
		frame.setContentPane(new Platformer());
		frame.setVisible(true);
	}

	Level level;
	double cameraX;
	ArrayList<GameObject> gameObjects = new ArrayList<>();
	Player player;
	boolean keys[] = new boolean[255];

	Platformer()
	{
		level = new Level("/level1.json", gameObjects);
		cameraX = 0;
		gameObjects.add(player = new Player(new Point2D.Double(20,50)));

		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				keys[e.getKeyCode()] = true;
			}
			public void keyReleased(KeyEvent e) {
				keys[e.getKeyCode()] = false;
			}
		});

		new Timer(1, this).start();
	}


	double jumpTime;
	long lastTime = System.nanoTime();
	@Override
	public void actionPerformed(ActionEvent e) {
		long time = System.nanoTime();
		double elapsedTime = (time-lastTime)/1e9;
		lastTime = time;


		if(player.alive) {
			if (keys[KeyEvent.VK_RIGHT]) {
				double newX = Math.min(150, player.speed.getX() + 5000 * elapsedTime);
				player.speed = new Point2D.Double(newX, player.speed.getY());
			} else if (keys[KeyEvent.VK_LEFT]) {
				double newX = Math.max(-150, player.speed.getX() - 5000 * elapsedTime);
				player.speed = new Point2D.Double(newX, player.speed.getY());
			} else {
				double newX = player.speed.getX() * 0.9;
				player.speed = new Point2D.Double(newX, player.speed.getY());
			}


			if (keys[KeyEvent.VK_UP]) {
				if (player.isOnFloor(level)) {
					player.speed = new Point2D.Double(player.speed.getX(), -150);
					jumpTime = 0.25;
				} else if (jumpTime > 0 && player.speed.getY() < 0) {
					jumpTime -= elapsedTime;
					player.speed = new Point2D.Double(player.speed.getX(), -150);
				}
			} else
				jumpTime = 0;

			if (Math.abs(player.speed.getY()) > 0.1)
				player.frame = 5;
			else
				player.frame = 0;
		}



		for(GameObject object : gameObjects)
			object.update(elapsedTime, level);



		if(player.position.getX() - cameraX > 140)
			cameraX = player.position.getX() - 140;


		if(player.alive) {
			for (GameObject o : gameObjects) {
				if (o == player)
					continue;
				if (!o.alive)
					continue;

				if (o.collides(player)) {
					if (o.position.getY() - player.position.getY() > 14)
						o.alive = false;
					else {
						player.speed = new Point2D.Double(0, -200);
						player.alive = false;
						player.hasCollision = false;
					}
				}
			}
		}


		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		level.draw(g2d, cameraX);

		for(GameObject gameObject : gameObjects)
			gameObject.draw(g2d, cameraX);
	}
}
