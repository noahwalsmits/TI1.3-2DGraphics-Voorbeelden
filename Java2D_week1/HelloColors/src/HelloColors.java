import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;

public class HelloColors extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Colors");
		primaryStage.show();
	}

	public void draw(FXGraphics2D g2d)
	{
		for(int i = 0; i < 500; i++) {
			g2d.setColor(Color.getHSBColor(i/500.0f, 1, 1));
			g2d.drawLine(i, 10, i, 100);
		}
	}
}
