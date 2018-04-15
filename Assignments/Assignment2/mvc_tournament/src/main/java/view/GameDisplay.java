package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.GameController;
import javafx.geometry.*;

public class GameDisplay {
	
	private static Stage window;
	private static boolean needsUpdate = false;

	public static void display(int gid){
		
		window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game View");
		window.setMinWidth(400);
		
		Label playerOneName = new Label();
		Label playerTwoName = new Label();
		Label playerOneScore = new Label();
		Label playerTwoScore = new Label();
		GameController.initialViewSetup(gid, playerOneName, playerTwoName, playerOneScore, playerTwoScore);
		
		Button increasePlayerOneScore = new Button("+");
		increasePlayerOneScore.setOnAction(e ->	{
			
			GameController.increasePlayerScore(playerOneScore, playerTwoScore, 1);
			needsUpdate = true;
			
		});
		
		Button increasePlayerTwoScore = new Button("+");
		increasePlayerTwoScore.setOnAction(e -> {
			
			GameController.increasePlayerScore(playerOneScore, playerTwoScore, 2);
			needsUpdate = true;
			
		});
		
		Button decreasePlayerOneScore = new Button("-");
		decreasePlayerOneScore.setOnAction(e -> {
			
			e.consume();
			GameController.decreasePlayerScore(playerOneScore, playerTwoScore, 1);
			needsUpdate = true;
			
		});
		
		Button decreasePlayerTwoScore = new Button("-");
		decreasePlayerTwoScore.setOnAction(e -> {
			
			e.consume();
			GameController.decreasePlayerScore(playerOneScore, playerTwoScore, 2);
			needsUpdate = true;
			
		});
		
		Button updateGame = new Button("Update Score");
		updateGame.setOnAction(e -> {
			
			GameController.updateScoreScript(gid, needsUpdate, playerOneScore, playerTwoScore);
			needsUpdate = false;
			
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		//player 1 constraints
		GridPane.setConstraints(playerOneName, 0, 0);
		GridPane.setConstraints(playerOneScore, 1, 0);
		GridPane.setConstraints(increasePlayerOneScore, 2, 0);
		GridPane.setConstraints(decreasePlayerOneScore, 3, 0);
		
		//player 2 constraints
		GridPane.setConstraints(playerTwoName, 0, 2);
		GridPane.setConstraints(playerTwoScore, 1, 2);
		GridPane.setConstraints(increasePlayerTwoScore, 2, 2);
		GridPane.setConstraints(decreasePlayerTwoScore, 3, 2);
		
		//update score
		GridPane.setConstraints(updateGame, 0, 4);
		
		grid.getChildren().addAll(
				playerOneName, playerOneScore, increasePlayerOneScore, decreasePlayerOneScore, 
				playerTwoName, playerTwoScore, increasePlayerTwoScore, decreasePlayerTwoScore, 
				updateGame);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			window.close();
			
		});
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
}
