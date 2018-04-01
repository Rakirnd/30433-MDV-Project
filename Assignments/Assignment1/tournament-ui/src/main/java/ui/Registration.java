package ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import bll.UserBL;
import javafx.geometry.*;

public class Registration
{
	
	@SuppressWarnings("restriction")
	public static void display()
	{
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Registration");
		window.setMinWidth(250);
		
		Label reg = new Label();
		reg.setText("Create a new account!");
		
		Label email = new Label();
		email.setText("Email: ");
		
		TextField emailInput = new TextField();
		emailInput.setPromptText("example@domain.com");
		
		Label pass = new Label();
		pass.setText("Password: ");
		
		Label confPass = new Label();
		confPass.setText("Confirm Password: ");
		
		PasswordField passInput = new PasswordField();
		PasswordField confPassInput = new PasswordField();
		
		Button regButton = new Button("Register");
		regButton.setOnAction(e -> {
			
			e.consume();
			regProgram(emailInput, passInput, confPassInput);
			
		});
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> window.close());
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(reg, 1, 0);
		
		GridPane.setConstraints(email, 0, 4);
		GridPane.setConstraints(emailInput, 1, 4, 2, 1);
		
		GridPane.setConstraints(pass, 0, 8);
		GridPane.setConstraints(passInput, 1, 8, 2, 1);
		
		GridPane.setConstraints(confPass, 0, 12);
		GridPane.setConstraints(confPassInput, 1, 12, 2, 1);
		
		GridPane.setConstraints(regButton, 0, 16);
		GridPane.setConstraints(closeButton, 1, 16);
		
		grid.getChildren().addAll(reg, email, emailInput, pass, passInput, regButton, closeButton, confPass, confPassInput);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	@SuppressWarnings("restriction")
	private static void regProgram(TextField email, PasswordField pass, PasswordField confPass)
	{
		
		if(email.getText() == "" || pass.getText() == "")
			AlertBox.display("Creation Failed!", "Please provide required input!");
		
		UserBL ubl = new UserBL();
		
		if(!ubl.checkDuplicates(email.getText()))
		{
			
			if(ubl.checkEmailFormat(email.getText()))
			{
				
				if(pass.getText().equals(confPass.getText())) {
					
					ubl.registerUser(email.getText(), pass.getText());
					
					System.out.println("Registration request sent!");
					AlertBox.display("Creation Successful!", "Your account has been created!");
					
				}
				else
					AlertBox.display("Creation Failed!", "Passwords don't match!");
				
				
				
			}
			else
				AlertBox.display("Creation Failed!", "Invalid Email!");
					
		}
		else
			AlertBox.display("Creation Failed!", "Email already in use!");
		
	}
	

}

