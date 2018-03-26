package match;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import user.User;

public class Match 
{
	
	//public static final int MATCH_LENGTH = 3; 
	private int id;
	private int tournID;
	private int playerOne;
	private int playerTwo;
	
	private int playerOneGames;
	private int playerTwoGames;
	
	//add an int representing the game winner's id
	//add an int to check if the match has finished
	//add both previous to the database
	
	//private List<Game> gameList;
	
	public Match(int p1, int p2)
	{
		
		playerOne = p1;
		playerTwo = p2;
		
		playerOneGames = 0;
		playerTwoGames = 0;
		
	}
	
	/*public ArrayList<Game> getGameList()
	{
		
		return (ArrayList<Game>) gameList;
		
	}
	
	public void setGameList(ArrayList<Game> gl)
	{
		
		gameList = gl;
		
	}*/
	
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

	public int getPlayerOneGames()
	{
		
		return playerOneGames;
		
	}
	
	public void setPlayerOneGames(int score)
	{
		
		playerOneGames = score;
		
	}
	
	public int getPlayerTwoGames()
	{
		
		return playerTwoGames;
		
	}
	
	public void setPlayerTwoGames(int score)
	{
		
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
