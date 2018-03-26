package ui;

import javafx.stage.*;
import user.User;
import user.UserBL;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		TextField passInput = new TextField();
		
		Button regButton = new Button("Register");
		regButton.setOnAction(e -> {
			
			e.consume();
			regProgram(emailInput, passInput);
			
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
		
		GridPane.setConstraints(regButton, 0, 12);
		GridPane.setConstraints(closeButton, 1, 12);
		
		grid.getChildren().addAll(reg, email, emailInput, pass, passInput, regButton, closeButton);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	@SuppressWarnings("restriction")
	private static void regProgram(TextField email, TextField pass)
	{
		
		if(email.getText() == "" || pass.getText() == "")
			AlertBox.display("Creation Failed!", "Please provide required input!");
		
		if(!checkDuplicates(email.getText()))
		{
			
			if(checkEmailFormat(email.getText()))
			{
				
				User u = new User(email.getText(), pass.getText());
				
				int id = UserBL.registerUser(u);
				
				System.out.println("Registration request sent!");
				AlertBox.display("Creation Successful!", "Your account has been created!");
				
				u.setID(id);
				
			}
			else
				AlertBox.display("Creation Failed!", "Invalid Email!");
				
				
		}
		else
			AlertBox.display("Creation Failed!", "Email already in use!");
		
	}
	
	private static boolean checkDuplicates(String email)
	{
		
		User u = UserBL.findAccountByEmail(email);
		
		if(u != null)
			return true;
		
		return false;
		
	}
	
	private static boolean checkEmailFormat(String email)
	{
		
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher matcher = pattern.matcher(email);
		
		return matcher.find();
		
	}
	

}

