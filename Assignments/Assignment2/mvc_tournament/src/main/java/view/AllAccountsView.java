package view;

import controller.AccountController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.userData.UserData;

public class AllAccountsView {
	
	private static Stage window;

	@SuppressWarnings("unchecked")
	public static void display(){
		
		window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("All Accounts");
		window.setMinWidth(400);
		
		//games table
		TableView<UserData> accountTable = new TableView<>();
		
		//table columns
		//id
		TableColumn<UserData, Integer> idColumn = new TableColumn<> ("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//user ID
		TableColumn<UserData, Integer> userIdColumn = new TableColumn<> ("User ID");
		userIdColumn.setMinWidth(50);
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
				
		//first name
		TableColumn<UserData, String> firstNameColumn = new TableColumn<> ("First Name");
		firstNameColumn.setMinWidth(100);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
				
		//last name
		TableColumn<UserData, String> lastNameColumn = new TableColumn<> ("Last Name");
		lastNameColumn.setMinWidth(100);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
				
		//balance
		TableColumn<UserData, Integer> balanceColumn = new TableColumn<> ("Balance");
		balanceColumn.setMinWidth(50);
		balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
				
		accountTable.getColumns().addAll(idColumn, userIdColumn, firstNameColumn, lastNameColumn, balanceColumn);
		
		AccountController.bindAccountsToView(accountTable);
		
		TextField balanceChange = new TextField();
		
		Button addMoneyButton = new Button("Add Money");
		addMoneyButton.setOnAction(e -> AccountController.increaseSelectedBalance(accountTable, balanceChange));
		
		Button withdrawMoneyButton = new Button("Withdraw Money");
		withdrawMoneyButton.setOnAction(e -> AccountController.decreaseSelectedBalance(accountTable, balanceChange));
		
		Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction(e -> AccountController.bindAccountsToView(accountTable));
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(accountTable, 0, 0, 4, 1);
		GridPane.setConstraints(balanceChange, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(addMoneyButton, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(withdrawMoneyButton, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(refreshButton, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		grid.getChildren().addAll(accountTable, balanceChange, addMoneyButton, withdrawMoneyButton, refreshButton);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			window.close();
			
		});
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
