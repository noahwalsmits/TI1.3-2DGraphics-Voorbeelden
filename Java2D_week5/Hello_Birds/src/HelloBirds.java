import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.joint.DistanceJoint;
import org.dyn4j.dynamics.joint.PinJoint;
import org.dyn4j.dynamics.joint.RopeJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by johan on 2017-03-08.
 */
public class HelloBirds extends JPanel implements ActionListener {

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Physics");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloBirds());
		frame.setVisible(true);
	}

	Camera camera;
	World world;
	MousePicker mousePicker;
	long lastTime;
	Vector2 ballPosition = new Vector2(-5, 1.5);
	BufferedImage slingShot;
	Body ball;

	double gameTime = 0;

	JCheckBox showDebug;

	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	HelloBirds()
	{
		try {
			slingShot = ImageIO.read(getClass().getResource("/images/slingshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(showDebug = new JCheckBox("Debug"));
		world = new World();
		world.setGravity(new Vector2(0,-9.8));


		Body floor = new Body();
		floor.addFixture(Geometry.createRectangle(20, 1));
		floor.getTransform().setTranslation(0, -0.5);
		floor.setMass(MassType.INFINITE);
		world.addBody(floor);
		gameObjects.add(new GameObject("/images/floor.png", floor, new Vector2(0,0), 1));


		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(2.05, 0.21));
			block.getTransform().setTranslation(4.05, 1);
			block.getTransform().setRotation(Math.toRadians(90));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block3.png", block, new Vector2(0, 0), 1));
		}
		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(2.03, 0.19));
			block.getTransform().setTranslation(5.95, 1);
			block.getTransform().setRotation(Math.toRadians(90));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block3.png", block, new Vector2(0, 0), 1));
		}
		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(2.03, 0.19));
			block.getTransform().setTranslation(5, 2);
			block.getTransform().setRotation(Math.toRadians(0));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block3.png", block, new Vector2(0, 0), 1));
		}
		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(2.03, 0.19));
			block.getTransform().setTranslation(4.65, 3);
			block.getTransform().setRotation(Math.toRadians(90));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block3.png", block, new Vector2(0, 0), 1));
		}
		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(2.03, 0.19));
			block.getTransform().setTranslation(5.35, 3);
			block.getTransform().setRotation(Math.toRadians(90));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block3.png", block, new Vector2(0, 0), 1));
		}

		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(0.39, 0.18));
			block.getTransform().setTranslation(5, 4);
			block.getTransform().setRotation(Math.toRadians(90));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block5.png", block, new Vector2(0, 0), 1));
		}



		{
			Body block = new Body();
			block.addFixture(Geometry.createRectangle(0.81, 0.18));
			block.getTransform().setTranslation(5, 4);
			block.getTransform().setRotation(Math.toRadians(0));
			block.setMass(MassType.NORMAL);
			world.addBody(block);
			gameObjects.add(new GameObject("/images/block4.png", block, new Vector2(0, 0), 1));
		}

		ball = new Body();
		ball.addFixture(Geometry.createCircle(0.15));
		ball.getTransform().setTranslation(new Vector2(ballPosition));
		ball.setMass(MassType.NORMAL);
		ball.getFixture(0).setRestitution(0.75);
		ball.setBullet(true);
		ball.setGravityScale(0);
		ball.setUserData("Ball");
		world.addBody(ball);
		gameObjects.add(new GameObject("/images/basketball.png", ball, new Vector2(0,0), 0.05));


		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
					Vector2 force = ballPosition.subtract(ball.getWorldCenter());
					if(force.getMagnitude() > 0.1 && ball.getGravityScale() == 0) {
						System.out.println(force);
						force.multiply(30);
						ball.setGravityScale(1);
						System.out.println(force);
						ball.applyForce(force);
					}
			}
		});


		lastTime = System.nanoTime();
		new Timer(15,this).start();
		camera = new Camera(this);
		mousePicker = new MousePicker(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long time = System.nanoTime();
		double elapsedTime = (time-lastTime) / 1000000000.0;
		lastTime = time;


		gameTime += elapsedTime;
		if(gameTime > 3 && ball.getGravityScale() == 0)
		{
			double angle = Math.random();

			Vector2 force = new Vector2(Math.cos(angle), Math.sin(angle)).multiply(60);
			ball.applyForce(force);
			ball.setGravityScale(1);
		}


		mousePicker.update(world, camera.getTransform(getWidth(), getHeight()), 100);

		world.update(elapsedTime);




		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform originalTransform = g2d.getTransform();

		g2d.setTransform(camera.getTransform(getWidth(), getHeight()));
		g2d.scale(1,-1);


		AffineTransform tx = new AffineTransform();
		tx.translate(-500, 100);
		tx.scale(1,-1);
		tx.scale(0.25, 0.25);
		tx.translate(-slingShot.getWidth()/2, -slingShot.getHeight()/2);

		if(ball.getJoints().size() > 0) {
			g2d.setStroke(new BasicStroke(10));
			g2d.draw(new Line2D.Double(
					100 * ball.getWorldCenter().x, 100 * ball.getWorldCenter().y,
					100 * ballPosition.x, 100 * ballPosition.y));
		}
		g2d.drawImage(slingShot, tx, null);

		for(GameObject go : gameObjects)
		{
			go.draw(g2d);
		}

		if(showDebug.isSelected()) {
			g2d.setColor(Color.blue);
			DebugDraw.draw(g2d, world, 100);
		}
		g2d.setTransform(originalTransform);
	}


}
