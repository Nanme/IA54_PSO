package main.java.com.ia54.pso.mas;


import java.util.ArrayList;

import io.janusproject.Boot;
import io.sarl.util.OpenEventSpace;
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
import main.java.com.ia54.pso.test.util.FunctionPSO;


public class MainFrame extends Application {
	
	public ArrayList<Rectangle> particleBodys = new ArrayList<>();
	public static Integer NB_SWARM = 1;
	public static Integer NB_PARTICLES_PER_LINES = 10;
	public static float HEIGHT = 400;
	public static float WIDTH = 600;
	public static int NB_LINES = 10;
	public static FunctionPSO FUNCTION = new FunctionAlpine();
	
	public OpenEventSpace space;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MapFunc map = new MapFunc(600, 400, FUNCTION, 0,60,0,40);
//		map.noise();
		Image img = map.draw();
		ImageView imageView = new ImageView(img);
		
		for (int i = 0 ; i<NB_PARTICLES_PER_LINES*NB_LINES ; i++){
			particleBodys.add(new Rectangle(0,0,3,3));
		}
		
		for (Rectangle rect : particleBodys){
			rect.xProperty().addListener(new ChangeListener<Number>() {
	
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//					System.out.println("X a changé de " + oldValue + " à " + newValue);
					
				}
			});
			rect.yProperty().addListener(new ChangeListener<Number>() {
	
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//					System.out.println("Y a changé de " + oldValue + " à " + newValue);
					
				}
			});
			rect.setFill(Color.RED);
		}
		
		Boot.startJanus((Class) null, BootAgent.class, this);
    	
		Group root = new Group();
		Scene scene = new Scene(root);
		
//		rect.xProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				System.out.println("X a changé de " + oldValue + " à " + newValue);
//				
//			}
//		});
//		rect.yProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				System.out.println("Y a changé de " + oldValue + " à " + newValue);
//				
//			}
//		});
//		rect.setFill(Color.RED);
		
		
		root.getChildren().add(imageView);
		
		for(Rectangle rect : particleBodys) {
			root.getChildren().add(rect);
		}
		
		primaryStage.setScene(scene);
		primaryStage.setHeight(HEIGHT);
		primaryStage.setWidth(600);
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
