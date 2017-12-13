import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Block {
	Point2D position;
	double rotation;

	Block(Point2D position)
	{
		this.position = position;
		this.rotation = Math.random()*2*Math.PI;
	}

	void update()
	{

	}

	void draw(Graphics2D g2d)
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(position.getX(), position.getY());
		tx.rotate(rotation);
		g2d.fill(tx.createTransformedShape(new Rectangle2D.Double(-50,-50,100,100)));

	}


}
