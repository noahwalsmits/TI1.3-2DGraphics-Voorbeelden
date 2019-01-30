import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class HelloAnimation extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
		draw(g2d);
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Ball");
		primaryStage.show();

		new AnimationTimer() {
			long last = -1;
			@Override
			public void handle(long now) {
				if(last == -1)
					last = now;
				update((now - last) / 1000000000.0);
				last = now;
				draw(g2d);
			}
		}.start();


		canvas.setOnMouseClicked(e -> {
			balls.add(new Ball(new Point2D.Double(e.getX(), e.getY()), new Point2D.Double(Math.random()*20-10, Math.random()*20-10)));
		});
	}

	ArrayList<Ball> balls = new ArrayList<>();


	public void update(double deltaTime)
	{
		for(Ball ball : balls)
			ball.update();
	}



	public void draw(Graphics2D g2d)
	{
		for(Ball ball : balls)
			ball.draw(g2d);

	}
}
