package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.AccountController;
import javafx.geometry.*;

public class AccountView{
	
	private static String loggedInUser;
	
	public static void display(String loggedUser){
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Your Account");
		window.setMinWidth(250);
		
		loggedInUser = loggedUser;
		
		Label acc = new Label();
		acc.setText("This is your account!");
		
		Label email = new Label();
		email.setText("Email: ");
		
		Label pass = new Label();
		pass.setText("Password: ");
		
		Label firstName = new Label();
		firstName.setText("First Name: ");
		
		Label lastName = new Label();
		lastName.setText("Last Name: ");
		
		Label balance = new Label();
		balance.setText("Balance: ");
		
		TextField emailInput = new TextField();
		TextField passInput = new TextField();
		TextField firstNameInput = new TextField();
		TextField lastNameInput = new TextField();
		TextField balanceInput = new TextField();
		
		balanceInput.setEditable(false);
		
		AccountController.displayInitialData(emailInput, passInput, firstNameInput, lastNameInput, balanceInput, loggedInUser);
		
		Button updateButton = new Button("Update Account");
		updateButton.setOnAction(e -> {
			
			AccountController.updateAccount(emailInput, passInput, loggedInUser);
			AccountController.updateAccountData(firstNameInput, lastNameInput, balanceInput, loggedInUser);
			AlertBox.display("Update", "Update Successful!");
			
		});
		
		Button deleteButton = new Button("Delete Account");
		deleteButton.setOnAction(e -> AccountController.deleteAccount(loggedInUser));
		
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
		
		GridPane.setConstraints(firstName, 0, 12);
		GridPane.setConstraints(firstNameInput, 1, 12, 2, 1);
		
		GridPane.setConstraints(lastName, 0, 16);
		GridPane.setConstraints(lastNameInput, 1, 16, 2, 1);
		
		GridPane.setConstraints(balance, 0, 20);
		GridPane.setConstraints(balanceInput, 1, 20, 2, 1);
		
		GridPane.setConstraints(updateButton, 0, 24);
		GridPane.setConstraints(deleteButton, 1, 24);
		GridPane.setConstraints(closeButton, 2, 24);
		
		grid.getChildren().addAll(acc, email, emailInput, pass, passInput, firstName, firstNameInput, lastName, lastNameInput, balance, balanceInput, updateButton, deleteButton, closeButton);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	

}

