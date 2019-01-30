import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloImage extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);

		try {
			image = ImageIO.read(getClass().getResource("/images/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
		draw(g2d);
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello images");
		primaryStage.show();

	}

	private BufferedImage image;

	public void draw(FXGraphics2D g2d)
	{

		AffineTransform tx = new AffineTransform();
		tx.translate(400,400);
		tx.rotate(Math.toRadians(45.0f), image.getWidth()/2, image.getHeight()/2);
		tx.scale(0.75f, 0.75f);
		g2d.drawImage(image, tx, null);
	}
}
