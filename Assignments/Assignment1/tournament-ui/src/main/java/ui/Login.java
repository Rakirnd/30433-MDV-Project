package ui;

import javafx.application.Application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import user.User;
import user.UserBL;

public class Login extends Application
{
	
	Stage window;
	
	public static void main(String args[]){
		
		launch(args);
		
	}
	
	@SuppressWarnings("restriction")
	public void start(Stage primaryStage) throws Exception
	{
		
		window = primaryStage;
		window.setTitle("Tournament");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//email label
		Label emailLabel = new Label("Email: ");
		GridPane.setConstraints(emailLabel, 0, 4);
		
		//email text
		TextField emailInput = new TextField();
		GridPane.setConstraints(emailInput, 1, 4);
		
		//pass label
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 5);
		
		//pass text
		TextField passInput = new TextField();
		GridPane.setConstraints(passInput, 1, 5);
		
		//login button
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 0, 6);
		
		loginButton.setOnAction(e ->{
			
			e.consume();
			loginProgram(emailInput, passInput);
			
		});
		
		//create new account button
		Button regButton = new Button("Create Account");
		GridPane.setConstraints(regButton, 1, 6);
		
		regButton.setOnAction(e ->{
			
			e.consume();
			registerProgram();
			
		});
		
		grid.getChildren().addAll(emailLabel, emailInput, passLabel, passInput, loginButton, regButton);
		
		Scene scene = new Scene(grid, 300, 200);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			closeProgram();
			
		});
		
		window.setScene(scene);
		window.show();
		
	}
	
	private void closeProgram()
	{
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer)
			window.close();
		
	}
	
	//checks the database for the credentials
	private void loginProgram(TextField email, TextField pass)
	{
		
		//System.out.println("Login request sent");
		
		String emailInput = email.getText();
		String passInput = pass.getText();
		
		User credentials = UserBL.findAccountByEmail(emailInput);
		boolean checkCredentials = false;
		
		if(credentials != null)
		{
			
			if(credentials.getPassword().equals(passInput))
				checkCredentials = true;
			else
				checkCredentials = false;			
			
		}
		
		if(checkCredentials)
		{
			
			window.close();
			AppUI.display(credentials);
			//System.out.println(credentials.getID());
			
		}
		else
			AlertBox.display("Incorrect Credentials", "Incorrect Username or Password!");

	}
	
	//creates a register window used for creating a new account
	private void registerProgram()
	{
		
		Registration.display();
		
	}
	

}
