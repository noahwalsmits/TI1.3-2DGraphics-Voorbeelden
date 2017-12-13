import javax.swing.*;
import java.awt.*;

public class HelloClass extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloClass());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		for(float f = 0; f < 2*Math.PI; f+=(2*Math.PI) / 10000)
		{
			int x = (int) (getWidth()/2 + 400 * Math.cos(f) + 100 * Math.cos(f*5));
			int y = (int) (getHeight()/2 + 400 * Math.sin(f) + 100 * Math.sin(f*15));

			g2d.drawLine(x,y,x,y+1);
		}

	}
}
