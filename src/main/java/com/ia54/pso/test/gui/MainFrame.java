package com.ia54.pso.test.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainFrame extends Application {

	public Rectangle rect = new Rectangle();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		root.getChildren().add(rect);
	
		
		
		primaryStage.setScene(scene);

	}

	public static void main(String[] args) {
		launch(args);

	}

}
