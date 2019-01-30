import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class HelloTransforms extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello transforms");
		primaryStage.show();
	}

	public void draw(FXGraphics2D g2d)
	{
		g2d.setStroke(new BasicStroke(5));

		AffineTransform tx = new AffineTransform();
		g2d.setColor(Color.red);
		g2d.draw(tx.createTransformedShape(new Rectangle2D.Double(0,0,100,100)));
		tx.translate(300,300);
		g2d.setColor(Color.green);
		g2d.draw(tx.createTransformedShape(new Rectangle2D.Double(0,0,100,100)));
		tx.translate(50,50);
		tx.rotate(Math.toRadians(45.0f));
		tx.translate(-50,-50);
		g2d.setColor(Color.blue);
		g2d.draw(tx.createTransformedShape(new Rectangle2D.Double(0,0,100,100)));


		g2d.setStroke(new BasicStroke(0));
	}
}
