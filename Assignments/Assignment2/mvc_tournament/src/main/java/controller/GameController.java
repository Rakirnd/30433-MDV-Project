package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.business.game.GameBL;
import model.business.game.GameBusiness;
import model.business.match.MatchBL;
import model.business.match.MatchBusiness;
import model.game.Game;
import model.match.MatchC;
import view.AlertBox;
import view.GameDisplay;

public class GameController {
	
	public static void initialViewSetup(int gid, Label playerOneName, Label playerTwoName, Label playerOneScore, Label playerTwoScore) {
		
		GameBusiness gbl = new GameBL();
		Game currentGame = gbl.findById(gid);
		
		String playerOne = gbl.findPlayerEmail(currentGame, 1);
		playerOneName.setText(playerOne + ": ");
		
		String playerTwo = gbl.findPlayerEmail(currentGame, 2);;
		playerTwoName.setText(playerTwo  + ": ");
		
		int p1Score = currentGame.getPlayerOneScore();
		playerOneScore.setText(Integer.toString(p1Score));
		
		int p2Score = currentGame.getPlayerTwoScore();
		playerTwoScore.setText(Integer.toString(p2Score));
		
	}
	
	public static void bindGamesToView(TableView<Game> gameTable, int mid) {
		
		GameBusiness gbl = new GameBL();
		MatchBusiness mbl = new MatchBL();
		MatchC m = mbl.findByID(mid);
		
		gameTable.setItems(gbl.getAllGamesInMatch(m));
		
	}
	
	public static void viewSelectedGame(TableView<Game> gameTable) {
		
		Game g;
		
		if(gameTable.getSelectionModel().getSelectedItem() != null){	
			
			g = gameTable.getSelectionModel().getSelectedItem();
			viewGame(g);
			
		}
		
	}
	
	public static void updateScoreScript(int gid, boolean needsUpdate, Label p1Score, Label p2Score) {
		
		if(needsUpdate) {
				
			needsUpdate = false;
			
			GameBusiness gbl = new GameBL();
			MatchBusiness mbl = new MatchBL();
			
			Game currentGame = gbl.findById(gid);
			currentGame.setPlayerOneScore(Integer.parseInt(p1Score.getText()));
			currentGame.setPlayerTwoScore(Integer.parseInt(p2Score.getText()));
			gbl.checkGameFinished(currentGame);
			
			gbl.updateGame(currentGame.getId(), currentGame);
				
			if(currentGame.getGameFinished() == 1) {
					
				if(currentGame.getPlayerOneScore() > currentGame.getPlayerTwoScore())
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
	
	public static void increasePlayerScore(Label playerOneScore, Label playerTwoScore, int playerNumber) {
		
			GameBusiness gbl = new GameBL();
			
			int p1Score = Integer.parseInt(playerOneScore.getText());
			int p2Score = Integer.parseInt(playerTwoScore.getText());
			
			if(playerNumber == 1) {
				
				p1Score = gbl.increasePlayerScore(p1Score, p2Score, playerNumber);
				playerOneScore.setText(Integer.toString(p1Score));
				
			}
			
			if(playerNumber == 2) {
				
				p2Score = gbl.increasePlayerScore(p1Score, p2Score, playerNumber);
				playerTwoScore.setText(Integer.toString(p2Score));
				
			}
		
	}
	
	public static void decreasePlayerScore(Label playerOneScore, Label playerTwoScore, int playerNumber) {
		
		GameBusiness gbl = new GameBL();
		
		int p1Score = Integer.parseInt(playerOneScore.getText());
		int p2Score = Integer.parseInt(playerTwoScore.getText());
		
		if(playerNumber == 1) {
			
			p1Score = gbl.decreasePlayerScore(p1Score, p2Score, playerNumber);
			playerOneScore.setText(Integer.toString(p1Score));
			
		}
		
		if(playerNumber == 2) {
			
			p2Score = gbl.decreasePlayerScore(p1Score, p2Score, playerNumber);
			playerTwoScore.setText(Integer.toString(p2Score));
			
		}
		
	}
	
	private static void viewGame(Game g) {
		
		GameDisplay.display(g.getId());	
		
	}
	
	

}
