package com.ia54.pso.mas;



import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import com.ia54.pso.test.MapFunc;
import com.ia54.pso.test.util.FunctionAckley;
import com.ia54.pso.test.util.FunctionAlpine;
import com.ia54.pso.test.util.FunctionNocon_rastrigin;
import com.ia54.pso.test.util.FunctionNoise;
import com.ia54.pso.test.util.FunctionPSO;
import com.ia54.pso.test.util.FunctionRastrigin;
import com.ia54.pso.test.util.FunctionWeierstrass;

import io.janusproject.Boot;
import io.janusproject.Boot.Exiter;
import io.sarl.util.OpenEventSpace;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class MainFrameController {
	public int NB_SWARM = 1;
	public int NB_PARTICLES_PER_LINES = 2;
	public int NB_LINES = 2;
	
	public float HEIGHT = 1080;
	public float WIDTH = 1920;
	public MapFunc MAP;

	public ArrayList<Rectangle> particleBodys = new ArrayList<>();
	
	public OpenEventSpace space;
	
	@FXML TextField Wfeild;
	@FXML TextField Hfeild;
	@FXML TextField nb_particule_per_lines;
	@FXML TextField nb_swarm;
	@FXML TextField nb_lines;
	@FXML ComboBox<String> combobox;
	@FXML TextField Xmin;
	@FXML TextField Xmax;
	@FXML TextField Ymin;
	@FXML TextField Ymax;
	@FXML CheckBox CNoise;
	@FXML TextField freq;
	@FXML Label labFreq;
	
	public Label labsol = new Label("kjghydrsefd");

	
	
	@FXML public void OpenParam(){
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Ackley",
		        "Alpine",
		        "Noise", 
		        "Rastrigin",
		        "Nocon_rastrigin",
		        "Weierstrass"
		    );
	
	combobox.setItems(options);
	
	initXY();
	
	}
	
	
	@FXML public void initXY()
	{
		FunctionPSO funcChoice = getFuncChoice();
		float[] i = funcChoice.interval();
		Xmin.setText(String.valueOf(i[0]));
		Xmax.setText(String.valueOf(i[1]));
		Ymin.setText(String.valueOf(i[2]));
		Ymax.setText(String.valueOf(i[3]));
		if (funcChoice.getClass().equals(FunctionNoise.class))
		{
			freq.setVisible(true);
			labFreq.setVisible(true);
		}
		else {
			freq.setVisible(false);
			labFreq.setVisible(false);
		}
	}
	
	@FXML public void init_nb_lines()
	{
		TextFormatter<Integer> formatter = new TextFormatter<>(
			    new IntegerStringConverter(), // Standard converter form JavaFX
			    2, 
			    new IntegerFilter());

		
		nb_lines.setTextFormatter(formatter);
	}
	
	@FXML public void init_nb_particule_per_lines()
	{
		TextFormatter<Integer> formatter = new TextFormatter<>(
			    new IntegerStringConverter(), // Standard converter form JavaFX
			    2, 
			    new IntegerFilter());

		
		nb_particule_per_lines.setTextFormatter(formatter);
	}

	@FXML public void init_nb_swarm()
	{
		TextFormatter<Integer> formatter = new TextFormatter<>(
			    new IntegerStringConverter(), // Standard converter form JavaFX
			    1, 
			    new IntegerFilter());

		
		nb_swarm.setTextFormatter(formatter);
	}
	
	@FXML public void FormatW()
	{
		TextFormatter<Integer> formatter = new TextFormatter<>(
			    new IntegerStringConverter(), // Standard converter form JavaFX
			    1920, 
			    new IntegerFilter());

		
		Wfeild.setTextFormatter(formatter);
	}
	
	@FXML public void FormatH()
	{

		TextFormatter<Integer> formatter = new TextFormatter<>(
			    new IntegerStringConverter(), // Standard converter form JavaFX
			    1080, 
			    new IntegerFilter());

		Hfeild.setTextFormatter(formatter);
	}
	
	@FXML public void FormatFreq()
	{

		TextFormatter<Integer> formatter = new TextFormatter<>(
			    new IntegerStringConverter(), // Standard converter form JavaFX
			    1080, 
			    new IntegerFilter());

		freq.setTextFormatter(formatter);
	}
	
	@FXML public void FormatXmin()
	{
		TextFormatter<Integer> formatter = new TextFormatter(
			    new FloatStringConverter(), // Standard converter form JavaFX
			    1920, 
			    new FloatFilter());

		
		Xmin.setTextFormatter(formatter);
	}
	
	@FXML public void FormatXmax()
	{

		TextFormatter<Integer> formatter = new TextFormatter(
			    new FloatStringConverter(), // Standard converter form JavaFX
			    1080, 
			    new FloatFilter());

		Xmax.setTextFormatter(formatter);
	}
	
	@FXML public void FormatYmin()
	{
		TextFormatter<Integer> formatter = new TextFormatter(
			    new FloatStringConverter(), // Standard converter form JavaFX
			    1920, 
			    new FloatFilter());

		
		Ymin.setTextFormatter(formatter);
	}
	
	@FXML public void FormatYmax()
	{

		TextFormatter<Integer> formatter = new TextFormatter(
			    new FloatStringConverter(), // Standard converter form JavaFX
			    1080, 
			    new FloatFilter());

		Ymax.setTextFormatter(formatter);
	}
	
	private FunctionPSO getFuncChoice()
	{
		String choice = combobox.getValue();
		FunctionPSO funcChoice;
		
		if(choice == null)
		{
			funcChoice = new FunctionRastrigin();
		}
		else if(choice.equals("Rastrigin"))
		{
			funcChoice = new FunctionRastrigin();
		}
		else if(choice.equals("Ackley"))
		{
			funcChoice = new FunctionAckley();
		}
		else if(choice.equals("Alpine"))
		{
			funcChoice = new FunctionAlpine();
		}
		else if(choice.equals("Noise"))
		{
			IntegerStringConverter conv = new IntegerStringConverter();
			
			funcChoice = new FunctionNoise(Math.abs(conv.fromString(freq.getText())));
		}
		else if(choice.equals("Nocon_rastrigin"))
		{
			funcChoice = new FunctionNocon_rastrigin();
		}
		else if(choice.equals("Weierstrass"))
		{
			funcChoice = new FunctionWeierstrass();
		}
		else 
		{
			funcChoice = new FunctionRastrigin();
		}
		return funcChoice;
		
	}
	
	public void launchPSO() throws Exception{
		IntegerStringConverter conv = new IntegerStringConverter();
		FloatStringConverter fconv = new FloatStringConverter();
		
		
		NB_SWARM = Math.abs(conv.fromString(nb_swarm.getText()));
		NB_PARTICLES_PER_LINES = Math.abs(conv.fromString(nb_particule_per_lines.getText()));
		NB_LINES = Math.abs(conv.fromString(nb_lines.getText()));
		
		WIDTH =  Math.abs(conv.fromString(Wfeild.getText()));
		HEIGHT =  Math.abs(conv.fromString(Wfeild.getText()));
		
		FunctionPSO funcChoice = getFuncChoice();
		
		labsol.textProperty().addListener(new ChangeListener<String>() {


			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				System.out.println(oldValue);
//				System.out.println(newValue);
				
			}
		});
		
		MAP = new MapFunc((int) WIDTH, (int) HEIGHT, funcChoice, fconv.fromString(Xmin.getText()),fconv.fromString(Xmax.getText()),fconv.fromString(Ymin.getText()),fconv.fromString(Ymax.getText()));
		if(CNoise.isSelected())
			MAP.noise();
		
		
		 Stage primaryStage = new Stage();
		 
		 
//		MapFunc map = new MapFunc(600, 400, FUNCTION, 0,60,0,40);
//		map.noise();
		Image img = MAP.draw();
		ImageView imageView = new ImageView(img);
		primaryStage.setTitle(MAP.Min.ToString());
		
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
		labsol.setTextFill(Color.RED);

		Boot.startJanus((Class) null, BootAgent.class, new MainFrame(NB_SWARM, NB_PARTICLES_PER_LINES, NB_LINES, HEIGHT, WIDTH, MAP, particleBodys), labsol.textProperty());
		
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

		Group root = new Group();
		root.getChildren().add(imageView);
		
		
		root.getChildren().add(labsol);
		
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
		primaryStage.setResizable(false);
		primaryStage.show();
	}

//    public MainFrameController(MainFrame mainFrame) {
//		MF = mainFrame;
//	}
//    public MainFrameController() {
//	}
	
//	@FXML public Button bout;

	@FXML protected void handleSubmitButtonAction(ActionEvent event) throws Exception {
//		System.out.println("blblbl");
////		bout.setText("lblblblb");
//		launchPSO();
       
    }
	public class IntegerFilter implements UnaryOperator<TextFormatter.Change> {
	    private final Pattern DIGIT_PATTERN = Pattern.compile("\\d*");

	    @Override
	    public Change apply(TextFormatter.Change aT) {
	    	if(aT.getText().equals("-"))
	    	{
	    		return aT;
	    	}
	        return DIGIT_PATTERN.matcher(aT.getText()).matches() ? aT : null;
	    }
	}
	
	public class FloatFilter implements UnaryOperator<TextFormatter.Change> {
	    private final Pattern DIGIT_PATTERN = Pattern.compile("\\d*");

	    @Override
	    public Change apply(TextFormatter.Change aT) {
	    	if(aT.getText().equals("-") || aT.getText().equals("."))
	    	{
	    		return aT;
	    	}
	        return DIGIT_PATTERN.matcher(aT.getText()).matches() ? aT : null;
	    }
	}
	
	 public static class FuncPar {
	        private final SimpleStringProperty FuncPso;
	        public SimpleStringProperty getFuncPso() {
				return FuncPso;
			}

			public SimpleStringProperty getXmin() {
				return Xmin;
			}

			public SimpleStringProperty getXmax() {
				return Xmax;
			}

			public SimpleStringProperty getYmin() {
				return Ymin;
			}

			public SimpleStringProperty getYmax() {
				return Ymax;
			}

			private final SimpleStringProperty Xmin;
	        private final SimpleStringProperty Xmax;
	        private final SimpleStringProperty Ymin;
	        private final SimpleStringProperty Ymax;

	        private FuncPar(String FuncPso, String Xmin, String Xmax, String Ymin, String Ymax) {
	            this.FuncPso = new SimpleStringProperty(FuncPso);
	            this.Xmin = new SimpleStringProperty(Xmin);
	            this.Xmax = new SimpleStringProperty(Xmax);
	            this.Ymin = new SimpleStringProperty(Ymin);
	            this.Ymax = new SimpleStringProperty(Ymax);
	            
	        }
	        
	        
	        
	 }
}


