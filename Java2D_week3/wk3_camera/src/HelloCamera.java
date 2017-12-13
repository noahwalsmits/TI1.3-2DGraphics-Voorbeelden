import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class HelloCamera extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloCamera());
		frame.setVisible(true);
	}

	Camera camera;
	ArrayList<Block> blocks = new ArrayList<>();
	HelloCamera()
	{
		camera = new Camera(this);
		for(int i = 0; i < 10; i++)
		{
			blocks.add(new Block(new Point2D.Double(1000 * Math.random() - 500, 1000 * Math.random() - 500)));
		}
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.setTransform(camera.getTransform(getWidth(),getHeight()));


		g2d.setColor(new Color(200,255,200));
		g2d.fill(new Rectangle2D.Double(-1000000,-1000000,2000000,2000000));

		g2d.setColor(Color.black);

		for(Block block : blocks)
			block.draw(g2d);



	}
}
