package com.ia54.pso.test;

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

import com.ia54.pso.test.util.FunctionAckley;
import com.ia54.pso.test.util.FunctionAlpine;
import com.ia54.pso.test.util.FunctionNocon_rastrigin;
import com.ia54.pso.test.util.FunctionNoise;
import com.ia54.pso.test.util.FunctionRastrigin;
import com.ia54.pso.test.util.FunctionWeierstrass;

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
    	
    	MapFunc map = new MapFunc(width,height,rast);
   	  //MapFunc map = new MapFunc(width,height,weir, 0,0.002f,0,0.002f);
 //   	MapFunc mapnoise = new MapFunc(width,height, noise);
    	
     	//MapFunc map = new MapFunc(600,600,weir, weir.interval2());
		//MapFunc map = new MapFunc(600,600,alp, alp.interval2());   	
    	
    	map.noise();
    //	System.out.println(alp.applyAsDouble(0.5f, 0.5f));
  // 	System.out.println(map.getValue(0.5f, 0.5f));
    	System.out.println("min");
    	System.out.println(map.getValue(map.Min.x, map.Min.y));
    	System.out.println(map.Min.x);
    	System.out.println(map.Min.y);
    	System.out.println(map.Min.val);
    	System.out.println("max");
    	System.out.println(map.getValue(map.Max.x, map.Max.y));
    	System.out.println(map.Max.x);
    	System.out.println(map.Max.y);
    	System.out.println(map.Max.val);
    	
    	map.negatif();
    	System.out.println("neg");
    	System.out.println("max");
    	System.out.println(map.getValue(map.Max.x, map.Max.y));
    	System.out.println(map.Max.x);
    	System.out.println(map.Max.y);
    	System.out.println(map.Max.val);
    	System.out.println("min");
    	System.out.println(map.getValue(map.Min.x, map.Min.y));
    	System.out.println(map.Min.x);
    	System.out.println(map.Min.y);
    	System.out.println(map.Min.val);
    	//map.fusion(mapnoise);
   // 	System.out.println(map.getValue(0.5f, 0.5f));
    	
    	//map.fusion(mapnoise,0.5);
		
    	//float[] range = {0,25,0,25};
		//map.addF(alp,range,0.5);
		
    	//map.negatif();

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
//        primaryStage.setTitle( String.valueOf(verif));
        primaryStage.show();
        
 
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
  
}
   

