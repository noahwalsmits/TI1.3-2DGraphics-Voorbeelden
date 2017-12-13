import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class HelloTransforms extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloTransforms());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.setStroke(new BasicStroke(5));

		AffineTransform tx = new AffineTransform();
		g2d.setColor(Color.red);
		g2d.draw(tx.createTransformedShape(new Rectangle2D.Double(0,0,100,100)));
		tx.translate(300,300);
		g2d.setColor(Color.green);
		g2d.draw(tx.createTransformedShape(new Rectangle2D.Double(0,0,100,100)));
		tx.translate(50,50);
		tx.rotate(Math.toRadians(45.0f));
		tx.translate(-50,-50);
		g2d.setColor(Color.blue);
		g2d.draw(tx.createTransformedShape(new Rectangle2D.Double(0,0,100,100)));


		g2d.setStroke(new BasicStroke(0));
	}
}
