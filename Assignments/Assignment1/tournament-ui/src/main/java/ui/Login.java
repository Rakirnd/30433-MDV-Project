package ui;

import bll.UserBL;
import bll.UserBusiness;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class Login{
	
	
	private static Stage window;

	public static void display(){
		
		window = new Stage();	
		window.setTitle("Login");
		
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
		PasswordField passInput = new PasswordField();
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
	
	private static void closeProgram(){
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer)
			window.close();
		
	}
	
	//checks the database for the credentials
	private static void loginProgram(TextField email, TextField pass){
		
		//System.out.println("Login request sent");
		
		String emailInput = email.getText();
		String passInput = pass.getText();
		
		UserBusiness ubl = new UserBL();
		boolean checkCredentials = ubl.authenticate(emailInput, passInput);
		
		if(checkCredentials){
			
			window.close();
			AppUI.display(emailInput);
			//System.out.println(credentials.getID());
			
		}
		else
			AlertBox.display("Incorrect Credentials", "Incorrect Username or Password!");

	}
	
	//creates a register window used for creating a new account
	private static void registerProgram()	{
		
		Registration.display();
		
	}
	

}
