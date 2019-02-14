import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.jfree.fx.FXGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HelloCamera extends Application {
	Stage stage;
	MediaPlayer mediaPlayer;
	boolean musicStarted;

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("wk3_camera/summerAir.mp3");
		Media summerAir = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(summerAir);
		System.out.println(mediaPlayer.getVolume());

		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);

		camera = new Camera(canvas);
		for(int i = 0; i < 10; i++)
			blocks.add(new Block(new Point2D.Double(1000 * Math.random() - 500, 1000 * Math.random() - 500)));

		FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
		draw(g2d);
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello Camera");
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

	Camera camera;
	ArrayList<Block> blocks = new ArrayList<>();


	public void draw(FXGraphics2D g2d)
	{
		g2d.setBackground(Color.white);
		g2d.clearRect(0,0,1920,1080);
		g2d.setTransform(camera.getTransform(stage.getWidth(),stage.getHeight()));


		g2d.setColor(new Color(200,255,200));
		g2d.fill(new Rectangle2D.Double(-1000000,-1000000,2000000,2000000));

		g2d.setColor(Color.black);

		for(Block block : blocks)
			block.draw(g2d);
	}

	public void update(double frameTime)
	{
		if(!musicStarted && camera.getZoom() < 0.4) {
			System.out.println("started");
			musicStarted = true;
			mediaPlayer.play();
		}

		if (musicStarted && camera.getZoom() <= 0.1) {
			mediaPlayer.setVolume(0.1 / (camera.getZoom()));
		}

	}
}
