package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.LoginController;
import controller.UtilityController;
import javafx.geometry.*;

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
		GridPane.setConstraints(emailLabel, 0, 0);
		
		//email text
		TextField emailInput = new TextField();
		GridPane.setConstraints(emailInput, 1, 0);
		
		//pass label
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 1);
		
		//pass text
		PasswordField passInput = new PasswordField();
		GridPane.setConstraints(passInput, 1, 1);
		
		//login button
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 0, 2);
		
		//create new account button
		Button regButton = new Button("Create Account");
		GridPane.setConstraints(regButton, 1, 2);
		
		LoginController.bindLoginButton(loginButton, emailInput, passInput, window);
		LoginController.bindRegisterButton(regButton);
		
		grid.getChildren().addAll(emailLabel, emailInput, passLabel, passInput, loginButton, regButton);
		
		Scene scene = new Scene(grid);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			UtilityController.closeProgram(window);
			
		});
		
		window.setScene(scene);
		window.show();
		
	}

}
