import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;

public class HelloGraph extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Java2D");
		primaryStage.show();
	}

	public void draw(FXGraphics2D g2d)
	{
		g2d.translate(1920/2, 1080/2);
		g2d.scale( 1, -1);


		g2d.setColor(Color.red);
		g2d.drawLine(0,0,1000,0);
		g2d.setColor(Color.green);
		g2d.drawLine(0,0,0,1000);
		g2d.setColor(Color.black);

		float resolution = 0.1f;
		float scale = 100;
		for(float x1 = -10; x1 < 10; x1 += resolution)
		{
			float x2 = x1 + resolution;
			float y1 = formule(x1);
			float y2 = formule(x2);
			g2d.drawLine((int)(x1*scale), (int)(y1*scale), (int)(x2*scale), (int)(y2*scale));
		}
	}

	private float formule(float x) {
		return (float) (Math.sin(x) + Math.cos(x*2));
	}
}
