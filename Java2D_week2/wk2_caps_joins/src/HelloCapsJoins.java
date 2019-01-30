import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class HelloCapsJoins extends Application {

	TextField width;
	ComboBox<String> cap;
	ComboBox<String> join;

	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);

		VBox vbox = new VBox();
		HBox bar = new HBox();
		vbox.getChildren().add(bar);
		vbox.getChildren().add(new Group(canvas));

		bar.getChildren().add(width = new TextField("10.0"));
		bar.getChildren().add(cap = new ComboBox<>(FXCollections.observableList(Arrays.asList("BasicStroke.CAP_BUTT", "BasicStroke.CAP_ROUND", "BasicStroke.CAP_SQUARE"))));
		bar.getChildren().add(join = new ComboBox<>(FXCollections.observableList(Arrays.asList("BasicStroke.JOIN_MITER", "BasicStroke.JOIN_ROUND", "BasicStroke.JOIN_BEVEL"))));

		cap.getSelectionModel().select(0);
		join.getSelectionModel().select(0);

		width.textProperty().addListener((observable, oldValue, newValue) -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
		cap.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
		join.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));

		draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

		primaryStage.setScene(new Scene(vbox));
		primaryStage.setTitle("Hello Caps & Joins");
		primaryStage.show();

	}

	public void draw(FXGraphics2D g2d)
	{
		g2d.setBackground(Color.white);
		g2d.clearRect(0,0,1920,1080);
		try {
			g2d.setStroke(new BasicStroke(
					Float.parseFloat(width.getText()),
					cap.getSelectionModel().getSelectedIndex(),
					join.getSelectionModel().getSelectedIndex(),
					10.0f
			));
		}catch(Exception e) {
			//oops
		}

		GeneralPath path = new GeneralPath();
		path.moveTo(500,100);
		path.lineTo(100, 500);
		path.lineTo(500,500);

		g2d.draw(path);


		g2d.setStroke(new BasicStroke(1));
	}
}
