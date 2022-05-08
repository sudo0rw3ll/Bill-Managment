package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Base;
import view.MainStage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Base.getInstance();
		MainStage.getInstance();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
