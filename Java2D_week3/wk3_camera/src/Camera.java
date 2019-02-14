
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by johan on 15-2-2017.
 */
public class Camera {
	private Point2D centerPoint = new Point2D.Double(0,0);
	private double zoom = 1;
	private double rotation = 0;

	Point2D lastMousePos;
	Camera(Node node)
	{
		node.setOnScroll(e -> {
			zoom *= (1 + e.getDeltaY()/150.0f);
		});

		node.setOnMouseDragged(e -> {
			if(e.getButton() == MouseButton.MIDDLE) {
				centerPoint = new Point2D.Double(
						centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom,
						centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom
				);
			}
			lastMousePos = new Point2D.Double(e.getX(), e.getY());
		});

		node.setOnMousePressed(e -> lastMousePos = new Point2D.Double(e.getX(), e.getY()));

	}

	public void setCenter(double x, double y)
	{
		centerPoint = new Point2D.Double(x, y);
	}

	public AffineTransform getTransform(double windowWidth, double windowHeight)
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(windowWidth/2, windowHeight/2);
		tx.scale(zoom, zoom);
		tx.translate(centerPoint.getX(), centerPoint.getY());
		tx.rotate(rotation);
		return tx;
	}

	public double getZoom() {
		return zoom;
	}
}
