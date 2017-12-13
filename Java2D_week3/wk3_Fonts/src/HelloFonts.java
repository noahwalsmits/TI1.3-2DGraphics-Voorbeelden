import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloFonts extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloFonts());
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("c:/windows/fonts/segoesc.ttf"));

			font = font.deriveFont(152.0f);

			Shape s = font.createGlyphVector(g2d.getFontRenderContext(), "Hello World").getOutline();

			g2d.translate(0,150);
			g2d.draw(s);

			g2d.setPaint(new GradientPaint(0,0,Color.blue, 1000,0,Color.orange));
			g2d.translate(0,150);
			g2d.fill(s);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
