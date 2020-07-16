package Program;

import Controller.Controller;
import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Until Championship Game");
		Model theModel = new Model();
		View theView = new View(primaryStage);
		Controller theController = new Controller(theModel, theView);
		
	}

}
