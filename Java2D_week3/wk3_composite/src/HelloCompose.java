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
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloCompose extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);

		try {
			image1 = ImageIO.read(getClass().getResource("/images/image1.png"));
			image2 = ImageIO.read(getClass().getResource("/images/image2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


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

	Image image1;
	Image image2;

	int[] rules = {AlphaComposite.CLEAR, AlphaComposite.SRC_OVER,
			       AlphaComposite.DST_OVER, AlphaComposite.SRC_IN,
			       AlphaComposite.DST_IN, AlphaComposite.SRC_OUT,
			       AlphaComposite.DST_OUT, AlphaComposite.SRC,
			       AlphaComposite.DST, AlphaComposite.SRC_ATOP,
			       AlphaComposite.DST_ATOP, AlphaComposite.XOR};
	String[] srules = {"AlphaComposite.CLEAR", "AlphaComposite.SRC_OVER",
			"AlphaComposite.DST_OVER", "AlphaComposite.SRC_IN",
			"AlphaComposite.DST_IN", "AlphaComposite.SRC_OUT",
			"AlphaComposite.DST_OUT", "AlphaComposite.SRC",
			"AlphaComposite.DST", "AlphaComposite.SRC_ATOP",
			"AlphaComposite.DST_ATOP", "AlphaComposite.XOR"};

	public void draw(FXGraphics2D g2d)
	{
		BufferedImage bi = new BufferedImage(2000,1000, BufferedImage.TYPE_INT_ARGB);


		for(int i = 0; i < rules.length; i++) {
			int x = 300 * (i % 5);
			int y = 300 * (i / 5);

			g2d.setPaint(Color.black);
			g2d.drawString(srules[i], x,y+20);

			g2d.setPaint(Color.blue);
			g2d.drawImage(image1, AffineTransform.getTranslateInstance(x+50,y+50), null);

			g2d.setComposite(AlphaComposite.getInstance(rules[i], 0.5f));
			g2d.drawImage(image2, AffineTransform.getTranslateInstance(x+100,y+100), null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		}

		g2d.drawImage(bi, null, null);


	}
}
