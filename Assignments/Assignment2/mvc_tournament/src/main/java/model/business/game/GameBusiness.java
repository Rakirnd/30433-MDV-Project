package model.business.game;

import javafx.collections.ObservableList;
import model.game.Game;
import model.match.MatchC;

public interface GameBusiness {
	
	public int insertGame(Game g);
	public void updateGame(int gid, Game g);
	
	public ObservableList<Game> getAllGamesInMatch(MatchC m);
	public String findPlayerEmail(Game g, int playerNumber);
	public Game findById(int gid);
	
	public void updateGameScore(Game currentGame);
	public int increasePlayerScore(int p1Score, int p2Score, int playerNumber);
	public int decreasePlayerScore(int p1Score, int p2Score, int playerNumber);
	public void checkGameFinished(Game currentGame);

}
