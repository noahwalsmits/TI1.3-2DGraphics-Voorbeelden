import javax.swing.*;
import java.awt.*;

public class HelloGraph extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloGraph());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.scale( 1, -1);


		g2d.setColor(Color.red);
		g2d.drawLine(0,0,1000,0);
		g2d.setColor(Color.green);
		g2d.drawLine(0,0,0,1000);
		g2d.setColor(Color.black);

		float resolution = 0.1f;
		float scale = 100;
		for(float x1 = -10; x1 < 10; x1 += resolution)
		{
			float x2 = x1 + resolution;
			float y1 = formule(x1);
			float y2 = formule(x2);
			g2d.drawLine((int)(x1*scale), (int)(y1*scale), (int)(x2*scale), (int)(y2*scale));
		}
	}

	private float formule(float x) {
		return (float) (Math.sin(x) + Math.cos(x*2));
	}
}
