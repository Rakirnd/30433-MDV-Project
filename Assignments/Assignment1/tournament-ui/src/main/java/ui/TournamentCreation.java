package ui;

import javafx.stage.*;
import tournament.Tournament;
import tournament.TournamentBL;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class TournamentCreation
{
	
	@SuppressWarnings("restriction")
	public static void display()
	{
		
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
		
		//tournament date
		Label date = new Label();
		date.setText("Date: ");
		
		TextField dateInput = new TextField();
		
		//tournament place
		Label place = new Label();
		place.setText("Place: ");
		
		TextField placeInput = new TextField();
		
		//prize pool
		Label pool = new Label();
		pool.setText("Prize Pool: ");
		
		TextField poolInput = new TextField();
		
		//create tournament button
		Button regButton = new Button("Create Tournament");
		regButton.setOnAction(e -> {
			
			e.consume();
			regProgram(nameInput, dateInput, placeInput, poolInput);
			
		});
		
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
		
		GridPane.setConstraints(date, 0, 6);
		GridPane.setConstraints(dateInput, 1, 6, 2, 1);
		
		GridPane.setConstraints(place, 0, 8);
		GridPane.setConstraints(placeInput, 1, 8, 2, 1);
		
		GridPane.setConstraints(pool, 0, 10);
		GridPane.setConstraints(poolInput, 1, 10, 2, 1);
		
		GridPane.setConstraints(regButton, 0, 12);
		GridPane.setConstraints(closeButton, 1, 12);
		
		grid.getChildren().addAll(ct, name, nameInput, date, dateInput, place, placeInput, pool, poolInput, regButton, closeButton);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	@SuppressWarnings("restriction")
	private static void regProgram(TextField nameInput, TextField dateInput, TextField placeInput, TextField poolInput)
	{
		
		if(nameInput.getText() == "")
			AlertBox.display("Failed!", "Tournament needs a name!");
		
		if(dateInput.getText() == "")
			AlertBox.display("Failed!", "Tournament needs a date!");
		
		if(placeInput.getText() == "")
			AlertBox.display("Failed!", "Tournament needs a place!");
		
		if(poolInput.getText() == "")
			AlertBox.display("Failed!", "Tournament needs a prize pool!");
		
		Tournament t = new Tournament();
		
		t.setName(nameInput.getText());
		t.setPlace(placeInput.getText());
		t.setTournamentDate(dateInput.getText());
		t.setPrizePool(Integer.parseInt(poolInput.getText())); //should check if not a number
		
		int id = TournamentBL.registerTournament(t);
		
		t.setId(id);
			
		//check For Non-Null input();
		//register in database
		//AlertBox.display("Creation Success!", "The tournament has been created!");
		
	}
	

}

