import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;


public class HelloPath extends Application {
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

	public void draw(FXGraphics2D g2d)
	{
		GeneralPath path = new GeneralPath();
		path.moveTo(100, 100);
		path.lineTo(200,100);
		path.lineTo(100,200);
		path.closePath();

		g2d.setColor(Color.green);
		g2d.fill(path);
		g2d.setColor(Color.black);
		g2d.draw(path);

	}
}
