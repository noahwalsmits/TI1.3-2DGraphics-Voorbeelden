import javax.swing.*;
import java.awt.*;

public class HelloLine extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(80, 60));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloLine());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.drawLine(10,10,100,100);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawLine(30,10,130,100);
	}
}
