
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by johan on 15-3-2017.
 */
public class GameObject
{
	BufferedImage[] images;
	int frame = 0;
	Point2D position;
	Point2D speed;

	int height = 16;
	public boolean alive = true;
	public boolean hasCollision = true;

	GameObject(String filename, Point2D position)
	{
		this.position = position;
		this.speed = new Point2D.Double(0,0);
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(filename));
			int frameCount = image.getWidth()/16;
			images = new BufferedImage[frameCount];
			for(int i = 0; i < frameCount; i++)
				images[i] = image.getSubimage(16*i,0,16,image.getHeight());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public void draw(Graphics2D g, double cameraX)
	{
		AffineTransform tx = new AffineTransform();

		tx.scale(3,3);
		tx.translate(position.getX(), position.getY());
		tx.translate(-cameraX, 0);
		tx.translate(0, -images[frame].getHeight());

		if(speed.getX() < 0)
		{
			tx.translate(images[frame].getWidth(),0);
			tx.scale(-1,1);
		}

		g.drawImage(images[frame], tx, null);

	}

	public void update(double elapsedTime, Level level) {
		boolean collision;

		double newX = position.getX() + speed.getX() * elapsedTime;
		collision = false;

		if(speed.getX() > 0) {
			if (level.hasCollision(newX + 15, position.getY()-1))
				collision = true;
			if (level.hasCollision(newX + 15, position.getY()-height))
				collision = true;
		}
		else if(speed.getX() < 0) {
			if (level.hasCollision(newX + 1, position.getY()-1))
				collision = true;
			if (level.hasCollision(newX + 1, position.getY()-height))
				collision = true;
		}



		if(!collision || !hasCollision)
			position = new Point2D.Double(newX, position.getY());
		else
			bumpWall();


		double newY = position.getY() + speed.getY() * elapsedTime;
		collision = false;

		//collision feet
		if(level.hasCollision(position.getX()+1, newY))
			collision = true;
		if(level.hasCollision(position.getX()+14, newY))
			collision = true;

		//collision head
		if(level.hasCollision(position.getX()+1, newY-height)) {
			collision = true;
			bumpHead(level, position.getX()+1, newY-height);
		}
		if(level.hasCollision(position.getX()+14, newY-height)) {
			collision = true;
			bumpHead(level, position.getX()+14, newY-height);
		}

		if(!collision || !hasCollision) {
			position = new Point2D.Double(position.getX(), newY);
			speed = new Point2D.Double(speed.getX(), speed.getY() + 300 * elapsedTime);
		}
		else {
			speed = new Point2D.Double(speed.getX(), 0);
			position = new Point2D.Double(position.getX(), Math.round(position.getY()/16)*16);

		}
	}

	public boolean isOnFloor(Level level) {
		if(level.hasCollision(position.getX()+1, position.getY()+1))
			return true;
		if(level.hasCollision(position.getX()+14, position.getY()+1))
			return true;
		return false;
	}


	public void bumpHead(Level level, double x, double y)
	{

	}

	public void bumpWall()
	{

	}

	public boolean collides(GameObject other) {
		return getRectangle().intersects(other.getRectangle());
	}

	public Rectangle2D getRectangle()
	{
		return new Rectangle2D.Double(position.getX(), position.getY() - height, 16, height);
	}


}
