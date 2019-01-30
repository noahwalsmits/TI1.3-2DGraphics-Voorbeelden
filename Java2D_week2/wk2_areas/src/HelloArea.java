import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class HelloArea extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Areas");
		primaryStage.show();
	}

	public void draw(FXGraphics2D g2d)
	{

		Area a1 = new Area(new Ellipse2D.Double(0,0,100,100));
		Area a2 = new Area(new Ellipse2D.Double(50,0,100,100));

		Area a3 = new Area(a1);
		a3.add(a2);

		Area a4 = new Area(a1);
		a4.subtract(a2);

		Area a5 = new Area(a1);
		a5.intersect(a2);

		Area a6 = new Area(a1);
		a6.exclusiveOr(a2);


		g2d.translate(25,25);

		g2d.setColor(Color.lightGray);
		g2d.fill(a3);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

		g2d.translate(0,150);
		g2d.setColor(Color.lightGray);
		g2d.fill(a4);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

		g2d.translate(0,150);
		g2d.setColor(Color.lightGray);
		g2d.fill(a5);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

		g2d.translate(0,150);
		g2d.setColor(Color.lightGray);
		g2d.fill(a6);
		g2d.setColor(Color.black);
		g2d.draw(a1);
		g2d.draw(a2);

	}
}
