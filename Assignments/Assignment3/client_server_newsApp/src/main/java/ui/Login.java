package ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.commands.LoginCommand;
import client.ArticleClient;
import javafx.geometry.*;

public class Login{
	
	private static Stage window;
	//private static String loginStatus;

	public static void display(ArticleClient clientConnection){
		
		window = new Stage();	
		window.setTitle("Login");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//email label
		Label usernameLabel = new Label("Username: ");
		GridPane.setConstraints(usernameLabel, 0, 0);
		
		//email text
		TextField usernameInput = new TextField();
		GridPane.setConstraints(usernameInput, 1, 0);
		
		//pass label
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 1);
		
		//pass text
		PasswordField passInput = new PasswordField();
		GridPane.setConstraints(passInput, 1, 1);
		
		//login button
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 1, 2);
		
		loginButton.setOnAction(e -> loginProgram(clientConnection, usernameInput, passInput));
		grid.getChildren().addAll(usernameLabel, usernameInput, passLabel, passInput, loginButton);
		
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
	
	public static void loginProgram(ArticleClient clientConnection, TextField username, TextField pass){
		
		String usernameInput = username.getText();
		String passInput = pass.getText();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fileTarget = "target/json_command.json";
		
		LoginCommand lc = new LoginCommand(usernameInput, passInput);
		
		String loginStatus = null;
		
		try {
			
			objectMapper.writeValue(new File(fileTarget), lc);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			clientConnection.send("login");
			
		} catch (Exception e) {
			
			System.out.println(e + " at Login");
			
		}
		
		while(loginStatus == null) {
			
			try {
				
				loginStatus = clientConnection.waitOnInput();
				
				switch(loginStatus) {
				
				case "fail":{
					
					System.out.println("Login Failed!");
					AlertBox.display("Incorrect Credentials", "Incorrect Username or Password!");
					break;
					
				}
				case "ADMIN":{
					
					System.out.println("Login Success!");
					System.out.println("ADMIN");
					
					WriterView.display(clientConnection);
					window.close();
					
					break;
					
				}
				case "WRITER":{
					
					System.out.println("Login Success!");
					System.out.println("WRITER");
					
					WriterView.display(clientConnection);			
					window.close();
					
					break;
					
				}
				default:{
					
					System.out.println("Login Failed!");
					AlertBox.display("Incorrect Credentials", "Incorrect Username or Password!");
					break;
					
				}
					
			}
				
			} catch (Exception e) {
				
				System.out.println("Login Failed!");
				e.printStackTrace();
				break;
				
			}
			
		}

	}

}
