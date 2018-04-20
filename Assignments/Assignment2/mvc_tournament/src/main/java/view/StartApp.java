package view;

import controller.LoginController;
import controller.UtilityController;
import javafx.application.Application;
import javafx.stage.*;
import util.DaoFactory;
import util.DaoFactory.Type;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class StartApp extends Application{

	private static Stage window;
	public static DaoFactory dataAccessWay;
	
	public static void main(String args[]){
		
		dataAccessWay = DaoFactory.getInstance(Type.HIBERNATE);
		launch(args);
		
		
	}
	
	public void start(Stage primaryStage) throws Exception	{
		
		window = primaryStage;
		window.setTitle("Ping Pong Tournament");
		window.setMinWidth(250);
		
		Label welcome = new Label();
		welcome.setText("Welcome to the Ping-Pong Tournament App!");
		GridPane.setConstraints(welcome, 0, 0);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		//login button
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		loginButton.setOnAction(e -> LoginController.login(window));
		
		grid.getChildren().addAll(welcome, loginButton);
		
		Scene scene = new Scene(grid);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			UtilityController.closeProgram(window);
			
		});
		
		window.setScene(scene);
		window.show();
		
	}

}
