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
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class HelloAnimation extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
		init();
		FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
		draw(g2d);
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello AnimationTime");
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
	}

	double angle = 0;

	public void draw(Graphics2D g2d)
	{
		g2d.setBackground(Color.white);
		g2d.clearRect(0,0,1920,1080);
		AffineTransform tx = new AffineTransform();
		tx.translate(1920/2, 1080/2);
		tx.rotate(angle);
		tx.translate(200, 0);

		g2d.fill(tx.createTransformedShape(new Rectangle2D.Double(-50,-50,100,100)));
	}

	public void update(double deltaTime)
	{
		angle+=deltaTime;


		//een delay met zware berekeningen enzo
		try {
			Thread.sleep(10+(long) (Math.random()*300));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
