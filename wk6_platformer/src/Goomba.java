import java.awt.geom.Point2D;

/**
 * Created by johan on 15-3-2017.
 */
public class Goomba extends Enemy {
	Goomba(Point2D position) {
		super("/goomba.png", position);
		speed = new Point2D.Double(-20, 0);
	}

	@Override
	public void bumpWall() {
		speed = new Point2D.Double(-speed.getX(), speed.getY());
	}

	@Override
	public void update(double elapsedTime, Level level) {
		super.update(elapsedTime, level);
		if(!alive) {
			speed = new Point2D.Double(0,0);
			frame = 2;
		}

		if(Math.abs(speed.getX()) > 0)
		{
			frame = (int) ((System.currentTimeMillis() / 250) % 2);
		}

	}
}
