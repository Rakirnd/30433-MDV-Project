package model;

public class Game{

	private int id;
	private int matchID;
	private int playerOneScore;
	private int playerTwoScore;
	private int gameFinished;
	
	public Game(){
		
		playerOneScore = 0;
		playerTwoScore = 0;
		gameFinished = 0;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public int getPlayerOneScore() {
		return playerOneScore;
	}

	public void setPlayerOneScore(int playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	public void setPlayerTwoScore(int playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}

	public int getGameFinished() {
		return gameFinished;
	}

	public void setGameFinished(int gameFinished) {
		this.gameFinished = gameFinished;
	}
	
	
	
}
