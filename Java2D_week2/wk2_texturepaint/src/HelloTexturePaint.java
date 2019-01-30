import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HelloTexturePaint extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Paths");
		primaryStage.show();
	}
	BufferedImage texture;

	public void draw(FXGraphics2D g2d)
	{
		try {
			texture = ImageIO.read(getClass().getResource("/images/texture.jpg"));
		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		g2d.setPaint(new TexturePaint(texture, new Rectangle2D.Double(0,0,400,400)));
		g2d.fill(new Rectangle2D.Double(0,0,1920, 1080));

	}
}
