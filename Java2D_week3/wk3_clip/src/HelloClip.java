import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class HelloClip extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloClip());
		frame.setVisible(true);
	}

	Point2D point = new Point2D.Double(100,100);
	HelloClip()
	{
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				point = e.getPoint();
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;


		GeneralPath star = new GeneralPath();
		int rad = 100;
		star.moveTo(point.getX()+100,point.getY());
		for(double a = 0; a < 2 * Math.PI; a += Math.PI / 6)
		{
			star.lineTo(
					point.getX() + rad * Math.cos(a),
					point.getY() + rad * Math.sin(a));
			rad = 150 - rad;
		}
		star.closePath();

		g2d.draw(star);

		g2d.clip(star);
		Random r = new Random(0);
		for(int i = 0; i < 1000; i++) {
			g2d.setPaint(Color.getHSBColor(r.nextFloat(),1,1));
			g2d.drawLine(r.nextInt() % getWidth(), r.nextInt() % getHeight(), r.nextInt() % getWidth(), r.nextInt() % getHeight());
		}

	}
}
