package dad.calculadorafx;

	import javafx.application.Application;
	import javafx.beans.binding.DoubleBinding;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Label;
	import javafx.scene.control.Separator;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;
	import javafx.util.converter.NumberStringConverter;

	public class CalculadoraComplejoSamuel extends Application {

	    @Override
	    public void start(Stage primaryStage) {
	        //Elementos a emplear en la interfaz
	        ComboBox<String> comboBox = new ComboBox<>();
	        comboBox.getItems().addAll("+", "-", "*", "/");
	        comboBox.getSelectionModel().selectFirst();

	        TextField textField1 = new TextField();
	        TextField textField2 = new TextField();
	        TextField textField3 = new TextField();
	        TextField textField4 = new TextField();
	        TextField textField5 = new TextField();
	        TextField textField6 = new TextField();

	        Label plusLabel1 = new Label("+");
	        Label plusLabel2 = new Label("+");
	        Label iLabel1 = new Label("i");
	        Label iLabel2 = new Label("i");
	        Label iLabel3 = new Label("i");
	        Label plusLabel3 = new Label("+");

	        Separator separator = new Separator();

	        //Creación de la interfaz
	        HBox hbox1 = new HBox(5, textField1, plusLabel1, textField2, iLabel1);
	        HBox hbox2 = new HBox(5, textField3, plusLabel2, textField4, iLabel2);
	        HBox hbox3 = new HBox(5, textField5, plusLabel3, textField6, iLabel3);
	        VBox vbox1 = new VBox(20, comboBox);
	        VBox vbox2 = new VBox(20, hbox1, hbox2, separator, hbox3);
	        HBox mainHBox = new HBox(20, vbox1, vbox2);

	        hbox1.setAlignment(Pos.CENTER);
	        hbox2.setAlignment(Pos.CENTER);
	        hbox3.setAlignment(Pos.CENTER);
	        vbox1.setAlignment(Pos.CENTER_LEFT);
	        vbox2.setAlignment(Pos.CENTER);
	        mainHBox.setAlignment(Pos.CENTER);

	        //Instanciación de complejos
	        Complejo complejo1 = new Complejo();
	        Complejo complejo2 = new Complejo();
	        Complejo resultado = new Complejo();

	        //Bindings a texto
	        textField1.textProperty().bindBidirectional(complejo1.realProperty(), new NumberStringConverter());
	        textField2.textProperty().bindBidirectional(complejo1.imaginarioProperty(), new NumberStringConverter());
	        textField3.textProperty().bindBidirectional(complejo2.realProperty(), new NumberStringConverter());
	        textField4.textProperty().bindBidirectional(complejo2.imaginarioProperty(), new NumberStringConverter());
	        textField5.textProperty().bindBidirectional(resultado.realProperty(), new NumberStringConverter());
	        textField6.textProperty().bindBidirectional(resultado.imaginarioProperty(), new NumberStringConverter());

	        //Bindings
	        DoubleBinding addReal = complejo1.realProperty().add(complejo2.realProperty());
	        DoubleBinding addImaginario = complejo1.imaginarioProperty().add(complejo2.imaginarioProperty());

	        DoubleBinding subtractReal = complejo1.realProperty().subtract(complejo2.realProperty());
	        DoubleBinding subtractImaginario = complejo1.imaginarioProperty().subtract(complejo2.imaginarioProperty());

	        DoubleBinding multiplyReal = complejo1.realProperty().multiply(complejo2.realProperty())
	                .subtract(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty()));
	        DoubleBinding multiplyImaginario = complejo1.realProperty().multiply(complejo2.imaginarioProperty())
	                .add(complejo1.imaginarioProperty().multiply(complejo2.realProperty()));

	        DoubleBinding denominator = complejo2.realProperty().multiply(complejo2.realProperty())
	                .add(complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty()));
	        DoubleBinding divideReal = complejo1.realProperty().multiply(complejo2.realProperty())
	                .add(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())).divide(denominator);
	        DoubleBinding divideImaginario = complejo1.imaginarioProperty().multiply(complejo2.realProperty())
	                .subtract(complejo1.realProperty().multiply(complejo2.imaginarioProperty())).divide(denominator);

	        //Lógica de operaciones
	        comboBox.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
	            if (nv.equals("+")) {
	                resultado.realProperty().bind(addReal);
	                resultado.imaginarioProperty().bind(addImaginario);
	            } else if (nv.equals("-")) {
	                resultado.realProperty().bind(subtractReal);
	                resultado.imaginarioProperty().bind(subtractImaginario);
	            } else if (nv.equals("*")) {
	                resultado.realProperty().bind(multiplyReal);
	                resultado.imaginarioProperty().bind(multiplyImaginario);
	            } else if (nv.equals("/")) {
	                resultado.realProperty().bind(divideReal);
	                resultado.imaginarioProperty().bind(divideImaginario);
	            }
	        });

	        //Configuración de la Escena
	        Scene scene = new Scene(mainHBox, 600, 200);
	        primaryStage.setTitle("Calculadora Compleja");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	}

