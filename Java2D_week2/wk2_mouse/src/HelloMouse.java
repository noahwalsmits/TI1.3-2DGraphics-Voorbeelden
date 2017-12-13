import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class HelloMouse extends JPanel implements MouseListener, MouseMotionListener {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloMouse());
		frame.setVisible(true);
	}

	HelloMouse()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Point2D position = new Point2D.Double(100,100);

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(20));
		g2d.draw(new Rectangle2D.Double(position.getX()-50, position.getY()-50, 100, 100));
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		position = e.getPoint();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
