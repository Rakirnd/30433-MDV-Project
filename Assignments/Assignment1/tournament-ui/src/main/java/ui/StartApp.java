package ui;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class StartApp extends Application{

	Stage window;
	
	public static void main(String args[]){
		
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
		
		loginButton.setOnAction(e ->{
			
			e.consume();
			login();
			
		});
		
		grid.getChildren().addAll(welcome, loginButton);
		
		Scene scene = new Scene(grid);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			closeProgram();
			
		});
		
		window.setScene(scene);
		window.show();
		
	}
	
	private void closeProgram(){
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer)
			window.close();
		
	}
	
	//checks the database for the credentials
	private void login(){
		
		window.close();
		Login.display();

	}

}
