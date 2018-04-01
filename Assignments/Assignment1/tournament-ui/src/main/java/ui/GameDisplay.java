package ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import bll.GameBL;
import bll.GameBusiness;
import bll.MatchBL;
import bll.MatchBusiness;
import model.Game;

@SuppressWarnings("restriction")
public class GameDisplay {
	
	private static Stage window;
	private static int p1Score, p2Score;
	private static Game currentGame;
	private static boolean needsUpdate = false;

	public static void display(Game g){
		
		window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game View");
		window.setMinWidth(400);
		
		currentGame = g;
		
		GameBusiness gbl = new GameBL();
		
		//player one name
		Label playerOneName = new Label();
		String playerOne = gbl.findPlayerEmail(currentGame, 1);
		playerOneName.setText(playerOne + ": ");
		
		//player two name
		Label playerTwoName = new Label();
		String playerTwo = gbl.findPlayerEmail(currentGame, 2);;
		playerTwoName.setText(playerTwo  + ": ");
		
		//player one score
		Label playerOneScore = new Label();
		p1Score = currentGame.getPlayerOneScore();
		playerOneScore.setText(Integer.toString(p1Score));
		
		//player two score
		Label playerTwoScore = new Label();
		p2Score = currentGame.getPlayerTwoScore();
		playerTwoScore.setText(Integer.toString(p2Score));
		
		Button increasePlayerOneScore = new Button("+");
		increasePlayerOneScore.setOnAction(e -> {
			
			e.consume();
			increasePlayerScore(playerOneScore, playerTwoScore, 1);
			
		});
		
		Button increasePlayerTwoScore = new Button("+");
		increasePlayerTwoScore.setOnAction(e -> {
			
			e.consume();
			increasePlayerScore(playerOneScore, playerTwoScore, 2);
			
		});
		
		Button decreasePlayerOneScore = new Button("-");
		decreasePlayerOneScore.setOnAction(e -> {
			
			e.consume();
			decreasePlayerScore(playerOneScore, playerTwoScore, 1);
			
		});
		
		Button decreasePlayerTwoScore = new Button("-");
		decreasePlayerTwoScore.setOnAction(e -> {
			
			e.consume();
			decreasePlayerScore(playerOneScore, playerTwoScore, 2);
			
		});
		
		Button updateGame = new Button("Update Score");
		updateGame.setOnAction(e -> {
			
			e.consume();
			updateScoreScript();
			
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
	
	private static void updateScoreScript() {
		
		if(needsUpdate) {
				
			GameBusiness gbl = new GameBL();
			MatchBusiness mbl = new MatchBL();
			gbl.updateGameScore(currentGame);
				
			needsUpdate = false;
				
			if(currentGame.getGameFinished() == 1) {
					
				if(p1Score > p2Score)
					mbl.updateMatchScore(currentGame.getMatchID(), 1);
				else
					mbl.updateMatchScore(currentGame.getMatchID(), 2);
				
				if(!mbl.isMatchFinished(currentGame.getMatchID())) {
						
					Game newGame = new Game();
					newGame.setMatchID(currentGame.getMatchID());
						
					gbl.insertGame(newGame);
							
				}
				else
					AlertBox.display("Match Finished", "The match is Over!");
					
			}
				
		}
		else
			AlertBox.display("No Update", "Nothing to Update");
		
	}

	private static void increasePlayerScore(Label playerOneScore, Label playerTwoScore, int playerNumber) {
		
		if(currentGame.getGameFinished() == 0) {
			
			GameBusiness gbl = new GameBL();
			
			if(playerNumber == 1) {
				
				p1Score = Integer.parseInt(playerOneScore.getText());
				p1Score = gbl.increasePlayerScore(currentGame, p1Score, p2Score, playerNumber);
				currentGame.setPlayerOneScore(p1Score);
				
				playerOneScore.setText(Integer.toString(p1Score));
				
			}
			
			if(playerNumber == 2) {
				
				p2Score = Integer.parseInt(playerTwoScore.getText());
				p2Score = gbl.increasePlayerScore(currentGame, p1Score, p2Score, playerNumber);
				currentGame.setPlayerTwoScore(p2Score);
				
				playerTwoScore.setText(Integer.toString(p2Score));
				
			}
			
			needsUpdate = true;
			gbl.checkGameFinished(currentGame);
					
		}
		else
			AlertBox.display("Game Finished", "The game is over!");
		
	}
	
	private static void decreasePlayerScore(Label playerOneScore, Label playerTwoScore, int playerNumber) {
		
		if(playerNumber == 1) {
			
			p1Score = Integer.parseInt(playerOneScore.getText());
			p1Score--;
			
			if(p1Score < 0)
				p1Score = 0;
			
			currentGame.setPlayerOneScore(p1Score);
			playerOneScore.setText(Integer.toString(p1Score));
			
		}
		
		if(playerNumber == 2) {
			
			p2Score = Integer.parseInt(playerTwoScore.getText());
			p2Score--;
			
			if(p2Score < 0)
				p2Score = 0;
			
			currentGame.setPlayerTwoScore(p2Score);
			playerTwoScore.setText(Integer.toString(p2Score));
			
		}
		
		if(p1Score < 11 || p2Score < 11)
			currentGame.setGameFinished(0);
		
	}
	
}
