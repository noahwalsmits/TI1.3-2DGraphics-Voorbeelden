import javax.swing.*;
import java.awt.*;

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

		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.scale(1, -1);

		g2d.setColor(Color.red);
		g2d.drawLine(0,0,1000,0);
		g2d.setColor(Color.green);
		g2d.drawLine(0,0,0,1000);
	}
}
