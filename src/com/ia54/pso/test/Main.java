import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.lang.Math;

import javafx.stage.Stage;



public class Main extends Application {

	
    public void start(Stage primaryStage) {

    	int width = 600, height = 600;
    	float contraste = 1f;
    	
    	
    	FunctionAlpine alp = new FunctionAlpine();
    	FunctionAckley ack = new FunctionAckley();
    	FunctionWeierstrass weir  = new FunctionWeierstrass();
    	FunctionNocon_rastrigin NCrast = new FunctionNocon_rastrigin();
    	FunctionRastrigin rast = new FunctionRastrigin();
    	FunctionNoise noise = new FunctionNoise(400);
    	
    	MapFunc map = new MapFunc(width,height,ack);
   	//  MapFunc map = new MapFunc(width,height,ack, -3,10,-3,12);
    	//MapFunc mapnoise = new MapFunc(width,height, noise);
    	
     	//MapFunc map = new MapFunc(600,600,weir, weir.interval2());
		//MapFunc map = new MapFunc(600,600,alp, alp.interval2());   	
    	
    	//map.noise();
    	//map.fusion(mapnoise);
    	//map.fusion(mapnoise,0.5);
		
    	//float[] range = {0,25,0,25};
		//map.addF(alp,range,0.5);
		
    	map.negatif();

		//map.calc(alp);
		Image image = map.draw(contraste);
		ImageView imageView = new ImageView(image);

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putImage(image); // the image you want, as javafx.scene.image.Image
        clipboard.setContent(content);
        

        VBox root = new VBox(10, imageView);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(rast.toString());
        primaryStage.show();
        
 
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
  
}
   

