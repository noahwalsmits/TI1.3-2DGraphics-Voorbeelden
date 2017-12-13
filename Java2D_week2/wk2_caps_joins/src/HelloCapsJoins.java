import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class HelloCapsJoins extends JPanel {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Hello Java2D");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(new HelloCapsJoins());
		frame.setVisible(true);
	}

	JTextField width;
	JComboBox<String> cap;
	JComboBox<String> join;

	HelloCapsJoins()
	{
		super();
		add(width = new JTextField("10.0"));

		cap = new JComboBox<>(new String[] { "BasicStroke.CAP_BUTT", "BasicStroke.CAP_ROUND", "BasicStroke.CAP_SQUARE"});
		add(cap);

		join = new JComboBox<>(new String[] { "BasicStroke.JOIN_MITER", "BasicStroke.JOIN_ROUND", "BasicStroke.JOIN_BEVEL"});
		add(join);

	}



	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		try {
			g2d.setStroke(new BasicStroke(
					Float.parseFloat(width.getText()),
					cap.getSelectedIndex(),
					join.getSelectedIndex(),
					10.0f
			));
		}catch(Exception e) {
			//oops
		}

		GeneralPath path = new GeneralPath();
		path.moveTo(500,100);
		path.lineTo(100, 500);
		path.lineTo(500,500);

		g2d.draw(path);


		g2d.setStroke(new BasicStroke(1));
		repaint();
	}
}
