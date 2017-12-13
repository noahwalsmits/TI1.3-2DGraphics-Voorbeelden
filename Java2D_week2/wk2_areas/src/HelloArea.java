import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class HelloArea extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloArea());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		Area a1 = new Area(new Ellipse2D.Double(0,0,100,100));
		Area a2 = new Area(new Ellipse2D.Double(50,0,100,100));

		Area a3 = new Area(a1);
		a3.add(a2);

		Area a4 = new Area(a1);
		a4.subtract(a2);

		Area a5 = new Area(a1);
		a5.intersect(a2);

		Area a6 = new Area(a1);
		a6.exclusiveOr(a2);


		g2d.translate(25,25);

		g2d.setColor(Color.lightGray);
		g2d.fill(a3);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

		g2d.translate(0,150);
		g2d.setColor(Color.lightGray);
		g2d.fill(a4);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

		g2d.translate(0,150);
		g2d.setColor(Color.lightGray);
		g2d.fill(a5);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

		g2d.translate(0,150);
		g2d.setColor(Color.lightGray);
		g2d.fill(a6);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

	}
}
