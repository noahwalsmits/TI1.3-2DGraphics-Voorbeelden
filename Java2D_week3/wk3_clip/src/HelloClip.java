import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class HelloClip extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
		draw(g2d);
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Animation");
		primaryStage.show();

		new AnimationTimer() {
			long last = -1;
			@Override
			public void handle(long now) {
				if(last == -1)
					last = now;
	//			update((now - last) / 1000000000.0);
				last = now;
				draw(g2d);
			}
		}.start();
	}

	public void draw(FXGraphics2D g2d)
	{
		Shape shape = new Ellipse2D.Double(1920/2-100, 1080/2-100, 200, 200);
		g2d.draw(shape);
		g2d.clip(shape);

		Random r = new Random();
		for(int i = 0; i < 1000; i++) {
			g2d.setPaint(Color.getHSBColor(r.nextFloat(),1,1));
			g2d.drawLine(r.nextInt() % 1920, r.nextInt() % 1080, r.nextInt() % 1920, r.nextInt() % 1080);
		}

	}
}
