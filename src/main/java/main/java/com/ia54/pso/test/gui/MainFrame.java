package main.java.com.ia54.pso.test.gui;

import io.janusproject.Boot;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainFrame extends Application {

	public Rectangle rect = new Rectangle(50,50,2,2);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		
		root.getChildren().add(rect);
		rect.xProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("X a changé de " + oldValue + " à " + newValue);
				
			}
		});
		
		rect.yProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("Y a changé de " + oldValue + " à " + newValue);
				
			}
		});
	
		Boot.startJanus((Class) null, BootTestEnvironmentAgent.class, this);
		
		primaryStage.setScene(scene);
		primaryStage.setHeight(400);
		primaryStage.setWidth(600);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}