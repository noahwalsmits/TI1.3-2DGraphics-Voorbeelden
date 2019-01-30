import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloFonts extends Application {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);


		FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
		draw(g2d);
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Hello fonts");
		primaryStage.show();

	}

	public void draw(FXGraphics2D g2d)
	{

		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("c:/windows/fonts/segoesc.ttf"));

			font = font.deriveFont(152.0f);

			Shape s = font.createGlyphVector(g2d.getFontRenderContext(), "Hello World").getOutline();

			g2d.translate(0,150);
			g2d.draw(s);

			g2d.setPaint(new GradientPaint(0,0,Color.blue, 1000,0,Color.orange));
			g2d.translate(0,150);
			g2d.fill(s);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
