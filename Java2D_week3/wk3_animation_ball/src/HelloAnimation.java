import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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

	ArrayList<Ball> balls = new ArrayList<>();

	HelloAnimation()
	{
		Timer t = new Timer(1000/60, this);
		t.start();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Point2D speed = new Point2D.Double(50.0 * (Math.random() - 0.5), 50.0 * (Math.random() - 0.5));
				balls.add(new Ball(e.getPoint(), speed));
			}
		});
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		for(Ball ball : balls)
			ball.draw(g2d);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Ball ball : balls)
			ball.update();
		repaint();
	}
}
