import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

public class HelloRadial extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloRadial());
		frame.setVisible(true);
	}

	Point center = new Point(0,0);

	HelloRadial()
	{
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				center = e.getPoint();
				repaint();
			}
		});
	}



	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		float[] fractions = { 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
		Color[] colors = new Color[fractions.length];
		for(int i = 0; i < colors.length; i++)
			colors[i] = Color.getHSBColor(fractions[i], 1.0f, 1.0f);

		g2d.setPaint(new RadialGradientPaint(
				getWidth()/2, getHeight()/2,	//center
				Math.min(getWidth(), getHeight()), //radius
				(float)center.getX(), (float)center.getY(), //focal point
				fractions, colors, MultipleGradientPaint.CycleMethod.REPEAT));

		g2d.fill(new Rectangle2D.Double(0,0,getWidth(), getHeight()));

	}
}
