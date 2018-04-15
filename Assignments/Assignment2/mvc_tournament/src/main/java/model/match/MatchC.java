package model.match;

public class MatchC{
	
	private int id;
	private int tournID;
	private int playerOne;
	private int playerTwo;
	
	private int playerOneGames;
	private int playerTwoGames;
	
	private int matchFinished;
	
	public MatchC() {
		
		playerOne = 0;
		playerTwo = 0;
		
		playerOneGames = 0;
		playerTwoGames = 0;
		
		matchFinished = -1;
		
	}
	
	public MatchC(int p1, int p2)	{
		
		playerOne = p1;
		playerTwo = p2;
		
		playerOneGames = 0;
		playerTwoGames = 0;
		
		matchFinished = -1;
		
	}
	
	public int getMatchFinished() {
		return matchFinished;
	}

	public void setMatchFinished(int matchFinished) {
		this.matchFinished = matchFinished;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTournID() {
		return tournID;
	}

	public void setTournID(int tournID) {
		this.tournID = tournID;
	}

	public int getPlayerOneGames()	{
		return playerOneGames;
	}
	
	public void setPlayerOneGames(int score){
		playerOneGames = score;
	}
	
	public int getPlayerTwoGames(){
		return playerTwoGames;	
	}
	
	public void setPlayerTwoGames(int score){
		playerTwoGames = score;
	}

	public int getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(int playerOne) {
		this.playerOne = playerOne;
	}

	public int getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(int playerTwo) {
		this.playerTwo = playerTwo;
	}
	
}
