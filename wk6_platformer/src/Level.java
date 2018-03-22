import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by johan on 15-3-2017.
 */
public class Level {
	BufferedImage[] tiles;
	int[][] data;
	int width;
	int height;

	public Level(String fileName, ArrayList<GameObject> gameObjects) {
		JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));

		JsonObject levelObject = reader.readObject();

		width = levelObject.getInt("width");
		height = levelObject.getInt("height");

		try {
			BufferedImage image = ImageIO.read(getClass().getResource("/smb.png"));
			int tileCount = image.getWidth()/16 * image.getHeight()/16;
			tiles = new BufferedImage[tileCount];
			int i = 0;
			for(int y = 0; y < image.getHeight(); y+=16)
				for(int x = 0; x < image.getWidth(); x+=16)
					tiles[i++] = image.getSubimage(x,y,16,16);
		} catch (IOException e) {
			e.printStackTrace();
		}

		data = new int[width][height];
		int i = 0;
		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++)
				data[x][y] = levelObject.getJsonArray("layers").getJsonObject(0).getJsonArray("data").getInt(i++)-1;

		JsonArray objects = levelObject.getJsonArray("layers").getJsonObject(1).getJsonArray("objects");

		for(i = 0; i < objects.size(); i++)
		{
			JsonObject o = objects.getJsonObject(i);

			if(o.getString("name").equals("goomba"))
			{
				gameObjects.add(
						new Goomba(
								new Point2D.Double(
										o.getJsonNumber("x").doubleValue(),
										o.getJsonNumber("y").doubleValue())));

			}

		}
	}

	public void draw(Graphics2D g2d, double cameraX) {
		int tileStart = (int) (cameraX / 16);
		for(int x = tileStart; x < tileStart + 20; x++)
		{
			for(int y = 0; y < height; y++)
			{
				AffineTransform tx = new AffineTransform();
				tx.scale(3,3);
				tx.translate(16*x - cameraX, 16*y);
				g2d.drawImage(tiles[data[x][y]], tx, null);
			}
		}
	}

	int[] blocking = { 0, 1, 2, 3, 10, 11, 26, 27, 32 };
	public boolean hasCollision(double x, double y) {
		int tileX = (int)(x / 16);
		int tileY = (int)(y / 16);

		if(tileX < 0 || tileX >= width || tileY < 0 || tileY >= height)
			return false;
		int tile = data[tileX][tileY];

		int index = Arrays.binarySearch(blocking, tile);
		return index >= 0 && index < blocking.length;
	}
}
