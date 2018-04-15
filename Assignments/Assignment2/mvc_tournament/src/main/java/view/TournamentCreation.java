package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.TournamentController;
import javafx.geometry.*;

public class TournamentCreation{
	
	public static void display(){
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Tournament Creation");
		window.setMinWidth(250);
		
		Label ct = new Label();
		ct.setText("Create a new tournament!");
		
		//tournament name
		Label name = new Label();
		name.setText("Name: ");
		
		TextField nameInput = new TextField();
		
		//tournament type
		ChoiceBox<String> type = new ChoiceBox<>();
		type.getItems().addAll("Free", "Paid");
		type.setValue("Free");
		
		//tournament entry fee
		Label entryFee = new Label();
		entryFee.setText("Entry Fee: ");
				
		TextField entryFeeInput = new TextField();
		entryFeeInput.setText("0");
		
		type.setOnAction(e -> TournamentController.updateTournamentType(type, entryFeeInput));
		
		//create tournament button
		Button regButton = new Button("Create Tournament");
		regButton.setOnAction(e -> TournamentController.regProgram(nameInput, type, entryFeeInput));
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> window.close());
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(ct, 1, 0);
		
		GridPane.setConstraints(name, 0, 4);
		GridPane.setConstraints(nameInput, 1, 4, 2, 1);
		
		GridPane.setConstraints(type, 0, 6);
		
		GridPane.setConstraints(entryFee, 0, 8);
		GridPane.setConstraints(entryFeeInput, 1, 8, 2, 1);
		
		GridPane.setConstraints(regButton, 0, 12);
		GridPane.setConstraints(closeButton, 1, 12);
		
		grid.getChildren().addAll(ct, name, nameInput, type, entryFee, entryFeeInput, regButton, closeButton);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	

	

}

