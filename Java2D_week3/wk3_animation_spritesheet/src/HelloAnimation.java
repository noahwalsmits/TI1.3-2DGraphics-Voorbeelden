import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class HelloAnimation extends JPanel implements ActionListener {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloAnimation());
		frame.setVisible(true);
	}

	HelloAnimation()
	{
		Timer t = new Timer(1000/60, this);
		t.start();

		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/walk.png"));
			walkCycle = new BufferedImage[6];
			for(int i = 0; i < 6; i++)
				walkCycle[i] = image.getSubimage(102*i, 0, 102, 148);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	double positionX = 0;
	BufferedImage[] walkCycle;


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		AffineTransform tx = new AffineTransform();
		tx.translate(positionX, 200);

		//dit kiezen kan op basis van de positie, of op basis van een attribuut
		int frame = ((int)(positionX / 50)) % walkCycle.length;
		g2d.drawImage(walkCycle[frame], tx, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		positionX+=4;
		if(positionX > getWidth())
			positionX = 0;
		if(positionX < 0)
			positionX = getWidth();
		repaint();
	}
}
