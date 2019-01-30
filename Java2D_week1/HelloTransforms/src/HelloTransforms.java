import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;


public class HelloTransforms extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Transforms");
		primaryStage.show();
	}

	public void draw(FXGraphics2D g2d)
	{
		g2d.translate(1920/2, 1080/2);
		g2d.scale(1, -1);

		g2d.setColor(Color.red);
		g2d.drawLine(0,0,1000,0);
		g2d.setColor(Color.green);
		g2d.drawLine(0,0,0,1000);

	}
}
