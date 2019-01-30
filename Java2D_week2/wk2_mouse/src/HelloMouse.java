import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class HelloMouse extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

		canvas.setOnMouseDragged(e ->
		{
			position = new Point2D.Double(e.getX(), e.getY());
			draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		});

		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Mouse");
		primaryStage.show();
	}
	public Point2D position = new Point2D.Double(100,100);

	public void draw(FXGraphics2D g2d)
	{
		g2d.setBackground(Color.white);
		g2d.clearRect(0,0,1920,1080);
		g2d.setStroke(new BasicStroke(20));
		g2d.draw(new Rectangle2D.Double(position.getX()-50, position.getY()-50, 100, 100));
	}

}
