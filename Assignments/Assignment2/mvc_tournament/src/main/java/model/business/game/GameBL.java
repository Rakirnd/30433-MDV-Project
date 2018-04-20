package model.business.game;

import java.util.List;

import dataAccess.dao.GameDAI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.business.match.MatchBL;
import model.business.match.MatchBusiness;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import model.game.Game;
import model.match.MatchC;
import model.user.User;
import view.StartApp;

public class GameBL implements GameBusiness{

	public int insertGame(Game g) {
		
		GameDAI gda = StartApp.dataAccessWay.getGameDao();
		return gda.insert(g);
		
	}

	public void updateGame(int gid, Game g) {
		
		GameDAI gda = StartApp.dataAccessWay.getGameDao();
		gda.update(gid, g);
		
	}

	public ObservableList<Game> getAllGamesInMatch(MatchC m) {
		
		GameDAI gda = StartApp.dataAccessWay.getGameDao();
		List<Game> games = gda.findAllGamesByMatchId(m.getId());
		
		if(games.size() == 0) {
			
			Game g = new Game();
			g.setMatchID(m.getId());
			
			int gid = insertGame(g);
			g.setId(gid);
			
			games.add(g);
			
		}
		
		ObservableList<Game> gameList = FXCollections.observableArrayList(games);
		
		return gameList;
		
	}
	
	public Game findById(int gid) {
		
		GameDAI gda = StartApp.dataAccessWay.getGameDao();
		return gda.findById(gid);
		
	}
	
	public String findPlayerEmail(Game g, int playerNumber) {
		
		String email = "";
		
		MatchBusiness mbl = new MatchBL();
		MatchC currentMatch = mbl.findByID(g.getMatchID());
		
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
	
	public int increasePlayerScore(int p1Score, int p2Score, int playerNumber) {
		
		if(playerNumber == 1) {
			
			if(p1Score >= 11 || p2Score >= 11){
				
				if(Math.abs(p1Score - p2Score) >= 2)
					return p1Score;
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
					return p2Score;
				else
					p2Score++;

			}
			else 
				p2Score++;
			
			return p2Score;
			
		}
		
		return 0;
		
	}
	
	public int decreasePlayerScore(int p1Score, int p2Score, int playerNumber) {
		
		if(playerNumber == 1) {
			
			p1Score--;
			
			if(p1Score < 0)
				p1Score = 0;
			
			return p1Score;
			
		}
		
		if(playerNumber == 2) {
			
			p2Score--;
			
			if(p2Score < 0)
				p2Score = 0;
			
			return p1Score;
			
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
