package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.AppController;
import controller.UtilityController;
import javafx.geometry.*;

public class AppUI {
	
	private static Stage window;
	private static String loggedInUser;
	
	public static void display(String loggedUser)
	{
		
		window = new Stage();
		loggedInUser = loggedUser;
		
		window.setTitle("Tournament App");
		window.setMinWidth(250);
		
		Label welcome = new Label();
		welcome.setText("Welcome to the Ping-Pong Tournament App!");
		
		Label question = new Label();
		question.setText("What would you like to do?");
		
		Button tournamentButton = new Button("View Tournaments");
		tournamentButton.setOnAction(e -> AppController.changeToTournaments(loggedInUser));
		
		Button viewAccountButton = new Button("View Account");
		viewAccountButton.setOnAction(e -> AppController.viewAccount(loggedInUser));
		
		Button viewAllAccountsButton = new Button("View All Accounts");
		viewAllAccountsButton.setOnAction(e -> AppController.viewAllAccounts());
		AppController.setButtonVisibleForAdmin(viewAllAccountsButton, loggedInUser);
		
		Button logOut = new Button("Log Out");
		logOut.setOnAction(e -> AppController.logOut(window));
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			UtilityController.closeProgram(window);
			
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(welcome, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(question, 0, 1, 3, 1, HPos.CENTER, VPos.CENTER);
		
		GridPane.setConstraints(tournamentButton, 0, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(viewAccountButton, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(logOut, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(viewAllAccountsButton, 3, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		
		grid.getChildren().addAll(welcome, question, tournamentButton, viewAccountButton, logOut, viewAllAccountsButton);
		
		Scene appScene = new Scene(grid);
		window.setScene(appScene);
		window.show();
		
	}

}
