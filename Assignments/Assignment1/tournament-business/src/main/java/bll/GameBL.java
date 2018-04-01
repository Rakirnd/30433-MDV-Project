package bll;

import java.util.Vector;

import dao.GameDAO;
import dao.GameDataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Match;
import model.User;

@SuppressWarnings("restriction")
public class GameBL implements GameBusiness{

	public int insertGame(Object g) {
		
		GameDataAccess gda = new GameDAO();
		return gda.insert(g);
		
	}

	public void updateGame(int gid, Game g) {
		
		GameDataAccess gda = new GameDAO();
		gda.updateGame(gid, g);
		
	}

	public ObservableList<Game> getAllGamesInMatch(Match m) {
		
		GameDataAccess gda = new GameDAO();
		Vector<Game> games = gda.findAllGamesByMatchId(m.getId());
		
		if(games.size() == 0) {
			
			Game g = new Game();
			g.setMatchID(m.getId());
			
			int gid = insertGame(g);
			g.setId(gid);
			
			games.addElement(g);
			
		}
		
		ObservableList<Game> gameList = FXCollections.observableArrayList(games);
		
		return gameList;
		
	}
	
	public String findPlayerEmail(Game g, int playerNumber) {
		
		String email = "";
		
		MatchBusiness mbl = new MatchBL();
		Match currentMatch = mbl.findByID(g.getMatchID());
		
		UserBusiness ubl = new UserBL();
		
		int player = 0;
		User playerUser = new User();
		
		if(playerNumber == 1) {
			
			player = currentMatch.getPlayerOne();
			playerUser = ubl.findUserByID(player);
			email = playerUser.getEmail();
			
		}
		
		if(playerNumber == 2) {
			
			player = currentMatch.getPlayerTwo();
			playerUser = ubl.findUserByID(player);
			email = playerUser.getEmail();
			
		}
		
		return email;
		
	}
	
	public void updateGameScore(Game currentGame) {
			
		Game g = new Game();
		g.setId(currentGame.getId());
		g.setMatchID(currentGame.getMatchID());
		g.setPlayerOneScore(currentGame.getPlayerOneScore());
		g.setPlayerTwoScore(currentGame.getPlayerTwoScore());
		g.setGameFinished(currentGame.getGameFinished());
		
		updateGame(currentGame.getId(), g);	
			
	}
	
	public int increasePlayerScore(Game currentGame, int p1Score, int p2Score, int playerNumber) {
		
		if(playerNumber == 1) {
			
			if(p1Score >= 11 || p2Score >= 11){
				
				if(Math.abs(p1Score - p2Score) >= 2)
					currentGame.setGameFinished(1);
				else
					p1Score++;

			}
			else 
				p1Score++;
			
			return p1Score;
			
		}
		
		if(playerNumber == 2) {
			
			if(p1Score >= 11 || p2Score >= 11){
				
				if(Math.abs(p1Score - p2Score) >= 2)
					currentGame.setGameFinished(1);
				else
					p2Score++;

			}
			else 
				p2Score++;
			
			return p2Score;
			
		}
		
		return 0;
		
	}
	
	public void checkGameFinished(Game currentGame) {
		
		int p1Score = currentGame.getPlayerOneScore();
		int p2Score = currentGame.getPlayerTwoScore();
		
		if( p1Score >= 11 || p2Score >= 11)
			if(Math.abs(p1Score - p2Score) >= 2)
				currentGame.setGameFinished(1);
		
	}

}
