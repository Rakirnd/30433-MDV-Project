package bll;

import javafx.collections.ObservableList;
import model.Game;
import model.Match;

@SuppressWarnings("restriction")
public interface GameBusiness {
	
	public int insertGame(Object g);
	public void updateGame(int gid, Game g);
	public ObservableList<Game> getAllGamesInMatch(Match m);
	public String findPlayerEmail(Game g, int playerNumber);
	
	public void updateGameScore(Game currentGame);
	public int increasePlayerScore(Game currentGame, int p1Score, int p2Score, int playerNumber);
	public void checkGameFinished(Game currentGame);

}
