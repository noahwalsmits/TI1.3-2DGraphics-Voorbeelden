import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

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
	}

	double angle = 0;

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		AffineTransform tx = new AffineTransform();
		tx.translate(getWidth()/2, getHeight()/2);
		tx.rotate(angle);
		tx.translate(200, 0);

		g2d.fill(tx.createTransformedShape(new Rectangle2D.Double(-50,-50,100,100)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		angle+=0.1;
		repaint();
	}
}
