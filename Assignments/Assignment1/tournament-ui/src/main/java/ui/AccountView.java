package ui;

import javafx.stage.*;
import model.User;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import bll.UserBL;
import bll.UserBusiness;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class AccountView{
	
	private static String loggedInUser;
	private static User loggedU;
	
	public static void display(String loggedUser){
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Registration");
		window.setMinWidth(250);
		
		loggedInUser = loggedUser;
		
		UserBusiness ubl = new UserBL();
		
		Label acc = new Label();
		acc.setText("This is your account!");
		
		Label email = new Label();
		email.setText("Email: ");
		
		Label pass = new Label();
		pass.setText("Password: ");
		
		TextField emailInput = new TextField();
		TextField passInput = new TextField();
		
		loggedU = ubl.findAccountByEmail(loggedInUser);
		
		emailInput.setText(loggedU.getEmail());
		passInput.setText(loggedU.getPassword());
		
		Button updateButton = new Button("Update Account");
		updateButton.setOnAction(e -> {
			
			e.consume();
			updateAccount(emailInput, passInput);
			
		});
		
		Button deleteButton = new Button("Delete Account");
		deleteButton.setOnAction(e -> {
			
			e.consume();
			deleteAccount(emailInput.getText());
			
		});
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> window.close());
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(acc, 1, 0);
		
		GridPane.setConstraints(email, 0, 4);
		GridPane.setConstraints(emailInput, 1, 4, 2, 1);
		
		GridPane.setConstraints(pass, 0, 8);
		GridPane.setConstraints(passInput, 1, 8, 2, 1);
		
		GridPane.setConstraints(updateButton, 0, 12);
		GridPane.setConstraints(deleteButton, 1, 12);
		GridPane.setConstraints(closeButton, 2, 12);
		
		grid.getChildren().addAll(acc, email, emailInput, pass, passInput, updateButton, deleteButton, closeButton);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private static void updateAccount(TextField emailInput, TextField passInput){
		
		User u = new User();
		u.setID(loggedU.getID());
		u.setEmail(emailInput.getText());
		u.setPass(passInput.getText());
		u.setIsAdmin(loggedU.getIsAdmin());
		
		UserBusiness ubl = new UserBL();
		
		if(ubl.checkEmailFormat(u.getEmail())) {
			
			ubl.updateUser(loggedU.getID(), u);
			AlertBox.display("Update", "Update Successful!");
			
		}
		else
			AlertBox.display("Can't Update", "Invalid email format!");
		
		
	}
	
	private static void deleteAccount(String email) {
		
		
		
	}
	

}

