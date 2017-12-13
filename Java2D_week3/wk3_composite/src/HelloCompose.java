import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloCompose extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloCompose());
		frame.setVisible(true);
	}

	Image image1;
	Image image2;

	HelloCompose()
	{
		try {
			image1 = ImageIO.read(getClass().getResource("/images/image1.png"));
			image2 = ImageIO.read(getClass().getResource("/images/image2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	int[] rules = {AlphaComposite.CLEAR, AlphaComposite.SRC_OVER,
			       AlphaComposite.DST_OVER, AlphaComposite.SRC_IN,
			       AlphaComposite.DST_IN, AlphaComposite.SRC_OUT,
			       AlphaComposite.DST_OUT, AlphaComposite.SRC,
			       AlphaComposite.DST, AlphaComposite.SRC_ATOP,
			       AlphaComposite.DST_ATOP, AlphaComposite.XOR};
	String[] srules = {"AlphaComposite.CLEAR", "AlphaComposite.SRC_OVER",
			"AlphaComposite.DST_OVER", "AlphaComposite.SRC_IN",
			"AlphaComposite.DST_IN", "AlphaComposite.SRC_OUT",
			"AlphaComposite.DST_OUT", "AlphaComposite.SRC",
			"AlphaComposite.DST", "AlphaComposite.SRC_ATOP",
			"AlphaComposite.DST_ATOP", "AlphaComposite.XOR"};

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		BufferedImage bi = new BufferedImage(2000,1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) bi.getGraphics();


		for(int i = 0; i < rules.length; i++) {
			int x = 300 * (i % 5);
			int y = 300 * (i / 5);

			g2d.setPaint(Color.black);
			g2d.drawString(srules[i], x,y+20);

			g2d.setPaint(Color.blue);
			g2d.drawImage(image1, AffineTransform.getTranslateInstance(x+50,y+50), null);

			g2d.setComposite(AlphaComposite.getInstance(rules[i], 0.5f));
			g2d.drawImage(image2, AffineTransform.getTranslateInstance(x+100,y+100), null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		}

		Graphics2D g2d2 = (Graphics2D)g;
		g2d2.drawImage(bi, null, null);


	}
}
