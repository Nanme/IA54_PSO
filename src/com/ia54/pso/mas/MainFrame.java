package com.ia54.pso.mas;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.ia54.pso.test.MapFunc;
import com.ia54.pso.test.util.FunctionRastrigin;

import io.janusproject.Boot;
import io.janusproject.Boot.Exiter;
import io.janusproject.kernel.Kernel;
//import io.janusproject.kernel.Kernel;
import io.sarl.util.OpenEventSpace;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainFrame extends Application {
	public static Integer NB_SWARM = 1;
	public static Integer NB_PARTICLES_PER_LINES = 2;
	public static int NB_LINES = 2;
	
	public static float HEIGHT = 1080;
	public static float WIDTH = 1920;
	public MapFunc MAP = new MapFunc((int) WIDTH, (int) HEIGHT, new FunctionRastrigin()	, -10,10,-10,10);
	
	public ArrayList<Rectangle> particleBodys = new ArrayList<>();
	
	public OpenEventSpace space;
	private Stage primaryStage;
	
	public MainFrame(){
		super();
	}
	
	public MainFrame(int swarm, int particles, int lines, float height, float width, MapFunc mf, ArrayList<Rectangle> arr){
		super();
		
		NB_SWARM = swarm;
		 NB_PARTICLES_PER_LINES = particles;
		 NB_LINES = lines;
		
		 HEIGHT = height;
		 WIDTH = width;
		MAP = mf;
		
		particleBodys = arr;

		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();
//		 MainFrameController controller = new MainFrameController();
//		 loader.setController(controller);
		 try {
			 
		//getClass().getClassLoader().getResource("test.fxml")
			 // getClass().getResource("test.fxml");
//		URL url = Paths.get("src", "com", "ia54", "pso", "mas", "fxml_example.fxml").toUri().toURL();
//	
//			FXMLLoader fxmlLoader = new FXMLLoader(url);
//		
//		final AnchorPane root = (AnchorPane) fxmlLoader.load();
//		
			 Parent root = loader.load(getClass().getResource("guiParametre.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		    } catch (IOException ex) {
		        System.err.println("Erreur au chargement: " + ex);
		    }
		 
//		 MainFrameController controller = loader.getController();
//		 controller = loader.getController();
//		 controller.MF = this;
		 
		 primaryStage.setTitle("Parametres");
		 primaryStage.show();

//		 launchPSO();
	}
	
	public void launchPSO() throws Exception{
		primaryStage.close();
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
		}

		Boot.startJanus((Class) null, BootAgent.class, this);
		
		
		

		 
		 
		 
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

//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			
//			@Override
//			public void handle(WindowEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		Group root = new Group();
		root.getChildren().add(imageView);
		
		for(Rectangle rect : particleBodys) {
			root.getChildren().add(rect);
		}
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				Exiter e  = Boot.getExiter();
				e.exit();
				
			}
		});		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setHeight(HEIGHT);
		primaryStage.setWidth(WIDTH);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);

	}

}
