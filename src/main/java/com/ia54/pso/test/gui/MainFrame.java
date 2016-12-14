package main.java.com.ia54.pso.test.gui;


import io.janusproject.Boot;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.java.com.ia54.pso.test.MapFunc;
import main.java.com.ia54.pso.test.util.FunctionAlpine;


public class MainFrame extends Application {
	
	public Rectangle rect = new Rectangle(50,50,2,2);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
    	FunctionAlpine alp = new FunctionAlpine();
		MapFunc map = new MapFunc(600, 400, alp);
		map.noise();
		Image img = map.draw();
		ImageView imageView = new ImageView(img);

		Boot.startJanus((Class) null, BootTestEnvironmentAgent.class, this);
    	
		Group root = new Group();
		Scene scene = new Scene(root);
		
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
		rect.setFill(Color.RED);
		
		
		root.getChildren().add(imageView);
		root.getChildren().add(rect);
		
		primaryStage.setScene(scene);
		primaryStage.setHeight(400);
		primaryStage.setWidth(600);
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
