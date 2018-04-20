package controller;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import model.business.userData.UserDataBL;
import model.business.userData.UserDataBusiness;
import view.AlertBox;
import view.AppUI;
import view.Login;
import view.Registration;

public class LoginController {
	
	//checks the database for the credentials
	public static void login(Stage window){
		
		window.close();
		Login.display();

	}
	
	public static void loginProgram(TextField email, TextField pass, Stage window){
		
		String emailInput = email.getText();
		String passInput = pass.getText();
		
		UserBusiness ubl = new UserBL();
		boolean checkCredentials = ubl.authenticate(emailInput, passInput);
		
		if(checkCredentials){
			
			window.close();
			AppUI.display(emailInput);
			
		}
		else
			AlertBox.display("Incorrect Credentials", "Incorrect Username or Password!");

	}
	
	public static void registerProgram()	{
		
		Registration.display();
		
	}
	
	public static void bindLoginButton(Button loginButton, TextField emailInput, TextField passInput, Stage window) {
		
		loginButton.setOnAction(e -> loginProgram(emailInput, passInput, window));
		
	}
	public static void bindRegisterButton(Button regButton) {
		
		regButton.setOnAction(e -> LoginController.registerProgram());
		
	}
	
	public static void regProgram(TextField email, PasswordField pass, PasswordField confPass, TextField firstName, TextField lastName)	{
		
		if(email.getText().isEmpty() || pass.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
			
			AlertBox.display("Creation Failed!", "Please provide required input!");
			return;
			
		}
			
		
		UserBusiness ubl = new UserBL();
		UserDataBusiness udbl = new UserDataBL();
		
		if(!ubl.checkDuplicates(email.getText()))
		{
			
			if(ubl.checkEmailFormat(email.getText()))
			{
				
				if(pass.getText().equals(confPass.getText())) {
					
					int userID = ubl.registerUser(email.getText(), pass.getText());
					udbl.regUserData(userID, firstName.getText(), lastName.getText());
					
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
