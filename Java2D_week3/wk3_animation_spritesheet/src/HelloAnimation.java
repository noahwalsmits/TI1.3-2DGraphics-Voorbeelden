import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

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
		primaryStage.setTitle("Hello Spritesheet");
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


	double positionX = 0;
	BufferedImage[] walkCycle;

	public void init()
	{
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("images/walk.png"));
			walkCycle = new BufferedImage[6];
			for(int i = 0; i < 6; i++)
				walkCycle[i] = image.getSubimage(102*i, 0, 102, 148);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void draw(FXGraphics2D g2d)
	{
		g2d.setBackground(Color.white);
		g2d.clearRect(0,0,1920,1080);

		AffineTransform tx = new AffineTransform();
		tx.translate(positionX, 200);

		//dit kiezen kan op basis van de positie, of op basis van een attribuut
		int frame = ((int)(positionX / 50)) % walkCycle.length;
		g2d.drawImage(walkCycle[frame], tx, null);
	}
	public void update(double deltaTime)
	{
		positionX+=4;
		if(positionX > 1920)
			positionX = 0;
		if(positionX < 0)
			positionX = 1920;

	}
}
