package ui;

import client.ArticleClient;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class StartApp extends Application{

	private static Stage window;
	private ArticleClient clientConnection = new ArticleClient();
	
	@Override
	public void start(Stage primaryStage) throws Exception	{
		
		window = primaryStage;
		window.setTitle("Fake News Times!");
		window.setMinWidth(250);
		
		Label welcome = new Label();
		welcome.setText("Welcome to Fake News Times!\nAll the time, fake news!");
		GridPane.setConstraints(welcome, 0, 0);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		//login button
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);
		
		//view Articles button
		Button viewArticles = new Button("View Articles");
		GridPane.setConstraints(viewArticles, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		viewArticles.setOnAction(e -> viewArticlesProgram(clientConnection));
		
		loginButton.setOnAction(e -> {
			
			window.close();
			Login.display(clientConnection);
			
		});
		
		grid.getChildren().addAll(welcome, loginButton, viewArticles);
		
		Scene scene = new Scene(grid);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
			
			if(answer) {
				
				window.close();
				
			}
			
		});
		
		window.setScene(scene);
		window.show();
		
	}
	
	@Override
	public void stop() throws Exception {
		
		clientConnection.closeConnection();
		
	}
	
	public static void viewArticlesProgram(ArticleClient clientConnection) {
		
		window.close();
		ReaderView.display(clientConnection);
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

}
