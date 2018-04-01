package ui;

import javafx.stage.*;
import model.Match;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import bll.GameBL;
import bll.GameBusiness;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Game;

@SuppressWarnings("restriction")
public class GameView {
	
	
	private static Stage window;

	@SuppressWarnings("unchecked")
	public static void display(Match m)
	{
		
		window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game View");
		window.setMinWidth(400);
		
		//games table
		TableView<Game> gameTable;
		
		//table columns
		//id
		TableColumn<Game, Integer> idColumn = new TableColumn<> ("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//match id
		TableColumn<Game, Integer> matchIdColumn = new TableColumn<> ("Match ID");
		matchIdColumn.setMinWidth(50);
		matchIdColumn.setCellValueFactory(new PropertyValueFactory<>("MatchID"));
				
		//player one score
		TableColumn<Game, Integer> playerOneScoreColumn = new TableColumn<> ("Score of player 1"); //to be replaced by email
		playerOneScoreColumn.setMinWidth(100);
		playerOneScoreColumn.setCellValueFactory(new PropertyValueFactory<>("PlayerOneScore"));
				
		//player two score
		TableColumn<Game, Integer> playerTwoScoreColumn = new TableColumn<> ("Score of player 2"); //to be replaced by email
		playerTwoScoreColumn.setMinWidth(100);
		playerTwoScoreColumn.setCellValueFactory(new PropertyValueFactory<>("PlayerTwoScore"));
				
		//game finished column
		TableColumn<Game, Integer> gameFinishedColumn = new TableColumn<> ("Game Finished");
		gameFinishedColumn.setMinWidth(50);
		gameFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("gameFinished"));
				
		GameBusiness gbl = new GameBL();
		gameTable = new TableView<>();
		gameTable.setItems(gbl.getAllGamesInMatch(m));
		gameTable.getColumns().addAll(idColumn, matchIdColumn, playerOneScoreColumn, playerTwoScoreColumn, gameFinishedColumn);
		
		Button viewGameButton = new Button("View Game");
		viewGameButton.setOnAction(e -> {
			
			e.consume();
			Game g;
			
			if(gameTable.getSelectionModel().getSelectedItem() != null)			
			{	
				
				g = gameTable.getSelectionModel().getSelectedItem();
				viewGame(g);
				
			}
			
		});
		
		Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction(e -> {
			
			e.consume();
			gameTable.setItems(gbl.getAllGamesInMatch(m));
			
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(gameTable, 0, 0, 2, 1);
		GridPane.setConstraints(viewGameButton, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(refreshButton, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER);
		
		grid.getChildren().addAll(gameTable, viewGameButton, refreshButton);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			window.close();
			
		});
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private static void viewGame(Game g) {
		
		GameDisplay.display(g);	
		
	}

}
