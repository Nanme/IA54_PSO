package com.ia54.pso.mas;


import java.util.ArrayList;

import com.ia54.pso.test.MapFunc;
import com.ia54.pso.test.util.FunctionRastrigin;

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


public class MainFrame extends Application {
	public static Integer NB_SWARM = 1;
	public static Integer NB_PARTICLES_PER_LINES = 2;
	public static int NB_LINES = 2;
	
	public static float HEIGHT = 1080;
	public static float WIDTH = 1920;
	public MapFunc MAP = new MapFunc((int) WIDTH, (int) HEIGHT, new FunctionRastrigin()	, -10,10,-10,10);
	
	public ArrayList<Rectangle> particleBodys = new ArrayList<>();
	
	public OpenEventSpace space;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
//		MapFunc map = new MapFunc(600, 400, FUNCTION, 0,60,0,40);
//		map.noise();
		Image img = MAP.draw();
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
		primaryStage.setWidth(WIDTH);
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
