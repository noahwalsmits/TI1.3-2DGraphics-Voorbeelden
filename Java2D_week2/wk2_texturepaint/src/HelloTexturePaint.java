import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HelloTexturePaint extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloTexturePaint());
		frame.setVisible(true);
	}

	BufferedImage texture;

	HelloTexturePaint()
	{
		try {
			texture = ImageIO.read(getClass().getResource("/images/texture.jpg"));
		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;


		g2d.setPaint(new TexturePaint(texture, new Rectangle2D.Double(0,0,400,400)));

		g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));

	}
}
