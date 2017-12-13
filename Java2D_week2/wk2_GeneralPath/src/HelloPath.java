import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class HelloPath extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloPath());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		GeneralPath path = new GeneralPath();
		path.moveTo(100, 100);
		path.lineTo(200,100);
		path.lineTo(100,200);
		path.closePath();

		g2d.setColor(Color.green);
		g2d.fill(path);
		g2d.setColor(Color.black);
		g2d.draw(path);

	}
}
