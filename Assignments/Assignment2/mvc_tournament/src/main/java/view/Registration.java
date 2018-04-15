package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.LoginController;
import javafx.geometry.*;

public class Registration{
	
	public static void display(){
		
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
		
		Label firstName = new Label();
		firstName.setText("First Name: ");
		
		TextField firstNameInput = new TextField();
		
		Label lastName = new Label();
		lastName.setText("Last Name: ");
		
		TextField lastNameInput = new TextField();
		
		Button regButton = new Button("Register");
		regButton.setOnAction(e -> LoginController.regProgram(emailInput, passInput, confPassInput, firstNameInput, lastNameInput));
		
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
		
		GridPane.setConstraints(firstName, 0, 16);
		GridPane.setConstraints(firstNameInput, 1, 16, 2, 1);
		
		GridPane.setConstraints(lastName, 0, 20);
		GridPane.setConstraints(lastNameInput, 1, 20, 2, 1);
		
		GridPane.setConstraints(regButton, 0, 24);
		GridPane.setConstraints(closeButton, 1, 24);
		
		grid.getChildren().addAll(reg, email, emailInput, pass, passInput, firstName, firstNameInput, lastName, lastNameInput, regButton, closeButton, confPass, confPassInput);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	

}

