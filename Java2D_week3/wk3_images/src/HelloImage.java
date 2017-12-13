import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloImage extends JPanel {
	private BufferedImage image;

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloImage());
		frame.setVisible(true);
	}

	HelloImage()
	{
		try {
			image = ImageIO.read(getClass().getResource("/images/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;


		AffineTransform tx = new AffineTransform();
		tx.translate(400,400);
		tx.rotate(Math.toRadians(45.0f), image.getWidth()/2, image.getHeight()/2);
		tx.scale(0.75f, 0.75f);
		g2d.drawImage(image, tx, null);
	}
}
