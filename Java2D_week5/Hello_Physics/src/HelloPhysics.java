import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by johan on 2017-03-08.
 */
public class HelloPhysics extends JPanel implements ActionListener {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Physics");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloPhysics());
		frame.setVisible(true);
	}

	World world;
	long lastTime;

	HelloPhysics()
	{
		world = new World();
		world.setGravity(new Vector2(0,-9.8));

		Body floor = new Body();
		floor.addFixture(Geometry.createRectangle(10, 1));
		floor.setMass(MassType.INFINITE);
		floor.getTransform().setRotation(Math.toRadians(10.0));
		world.addBody(floor);


		Body ball = new Body();

		BodyFixture fixture = new BodyFixture(Geometry.createCircle(1));
		fixture.setRestitution(.25);
		ball.addFixture(fixture);
		ball.getTransform().setTranslation(new Vector2(0,10));
		ball.setMass(MassType.NORMAL);
//		ball.getFixture(0).setRestitution(.25);
		world.addBody(ball);

		lastTime = System.nanoTime();
		new Timer(15,this).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long time = System.nanoTime();
		double elapsedTime = (time-lastTime) / 1000000000.0;
		lastTime = time;
		world.update(elapsedTime);

		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;


		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.scale(1,-1);

		DebugDraw.draw(g2d, world, 50);
	}


}
