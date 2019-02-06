import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloShapes extends Application implements Resizable {
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		FlowPane bp = new FlowPane();
		ResizableCanvas canvas = new ResizableCanvas(this, bp);
		bp.getChildren().add(canvas);

		primaryStage.setScene(new Scene(bp));

		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
		primaryStage.setTitle("Hello Shapes");
		primaryStage.show();
	}
	BufferedImage texture;

	public void draw(FXGraphics2D g2d) {
		//g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_SQUARE,
		//		BasicStroke.JOIN_MITER, 10.0f, new float[]{25.0f, 20.0f}, 0.0f));

		g2d.setColor(Color.red);
		g2d.draw(new Rectangle2D.Double(100,100,100,100));

		g2d.setColor(Color.blue);
		g2d.draw(new Ellipse2D.Double(250, 100, 100, 100));

		g2d.setColor(Color.yellow);
		g2d.draw(new RoundRectangle2D.Double(400, 100, 100, 100, 5, 5));

		// Pacman
		g2d.setColor(Color.green);
		g2d.draw(new Arc2D.Double(100, 250, 100, 100, 30, 300, Arc2D.PIE));
		g2d.fill(new Arc2D.Double(100, 250, 100, 100, 30, 300, Arc2D.PIE));

		g2d.setColor(Color.blue);
		for (int i = 0; i < 10; i++) {
			g2d.draw(new Ellipse2D.Double(200+(i*25), 300, 5, 5));
		}


	}

	public static void main(String[] args) {
		Application.launch(HelloShapes.class);
	}
}
