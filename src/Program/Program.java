package Program;

import Controller.Controller;
import Model.Registry;
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Until Championship Game");
		Registry theModel = new Registry();
		MainView theView = new MainView(primaryStage);
		Controller theController = new Controller(theModel, theView);

	}

}
