import javax.swing.*;
import java.awt.*;

public class HelloRainbow extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloRainbow());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.scale(1,-1);
		float innerRadius = 400;
		float outerRadius = 300;

		for(float f = 0; f < 2*Math.PI; f+=(2*Math.PI) / 5000000)
		{
			int x1 = (int) (innerRadius * Math.cos(f/2));
			int y1 = (int) (innerRadius * Math.sin(f/2));
			int x2 = (int) (outerRadius * Math.cos(f/2));
			int y2 = (int) (outerRadius * Math.sin(f/2));

			g2d.setColor(Color.getHSBColor(f/(2*(float)Math.PI), 1,1));
			g2d.drawLine(x1,y1,x2,y2);
		}

	}
}
