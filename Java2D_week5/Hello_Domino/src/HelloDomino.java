import org.dyn4j.dynamics.Body;
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
public class HelloDomino extends JPanel implements ActionListener {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Physics");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloDomino());
		frame.setVisible(true);
	}

	Camera camera;
	World world;
	long lastTime;

	HelloDomino()
	{
		world = new World();
		world.setGravity(new Vector2(0,-9.8));

		Body floor = new Body();
		floor.addFixture(Geometry.createRectangle(20, 1));
		floor.getTransform().setTranslation(0, -.5);
		floor.setMass(MassType.INFINITE);
		world.addBody(floor);



		for(int y = 0; y < 10; y++) {
			for (int x = 0; x < 10-y; x++) {
				Body box = new Body();
				box.addFixture(Geometry.createRectangle(.25, .25));
				box.setMass(MassType.NORMAL);

				box.getTransform().setTranslation(5 + x * 0.25 + 0.125 * y, y*0.25);
				box.getFixture(0).setRestitution(1.1);
				world.addBody(box);

			}
		}

		Body ball = new Body();
		ball.addFixture(Geometry.createCircle(1));
		ball.getTransform().setTranslation(new Vector2(0,2));
		ball.setMass(MassType.NORMAL);
		ball.getFixture(0).setRestitution(.25);
		ball.applyImpulse(-20);
		world.addBody(ball);

		lastTime = System.nanoTime();
		new Timer(15,this).start();
		camera = new Camera(this);
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

		g2d.setTransform(camera.getTransform(getWidth(), getHeight()));
		g2d.scale(1,-1);

		DebugDraw.draw(g2d, world, 100);
	}


}
