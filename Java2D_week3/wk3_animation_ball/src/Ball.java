import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by johan on 15-2-2017.
 */
public class Ball {
	private Point2D position;
	private Point2D speed;

	private double friction = 0.99;
	private float size = 10;

	public Ball(Point2D position, Point2D speed)
	{
		this.position = position;
		this.speed = speed;
	}

	public void update()
	{
		position = new Point2D.Double(
				position.getX() + speed.getX(),
				position.getY() + speed.getY());
		speed = new Point2D.Double(
				speed.getX() * friction,
				speed.getY()*friction + 0.9);
	}

	public void draw(Graphics2D g2d)
	{
		g2d.fill(new Ellipse2D.Double(position.getX()-size/2, position.getY()-size/2, size, size));

	}


}
