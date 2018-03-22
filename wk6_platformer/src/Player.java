import java.awt.geom.Point2D;

/**
 * Created by johan on 15-3-2017.
 */
public class Player extends GameObject {

	Player(Point2D position) {
		super("/player.png", position);
	}

	@Override
	public void bumpHead(Level level, double x, double y) {
		if(!alive)
			return;
		int tileX = (int) (x / 16);
		int tileY = (int) (y / 16);

		int tile = level.data[tileX][tileY];

		if(tile == 3)
			level.data[tileX][tileY] = 32;
		if(tile == 2)
			level.data[tileX][tileY] = 7;
		speed = new Point2D.Double(speed.getX(), 0);
	}

	@Override
	public void update(double elapsedTime, Level level) {
		super.update(elapsedTime, level);
		if(!alive)
		{
			frame = 4;
		}
	}
}
