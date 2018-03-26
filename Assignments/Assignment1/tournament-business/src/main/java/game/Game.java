package game;

public class Game 
{
	
	private int id;
	private int matchID;
	private int playerOneScore;
	private int playerTwoScore;
	
	//add a game finished int representing if game has finished
	//a game has finished if any score is >11 and the difference is greater than or equal to 2
	
	public Game()
	{
		
		playerOneScore = 0;
		playerTwoScore = 0;
		
	}
	
	public int getPlayerOneScore()
	{
		
		return playerOneScore;
		
	}
	
	public void setPlayerOneScore(int score)
	{
		
		playerOneScore = score;
		
	}
	
	public int getPlayerTwoScore()
	{
		
		return playerTwoScore;
		
	}
	
	public void setPlayerTwoScore(int score)
	{
		
		playerTwoScore = score;
		
	}
	
	/*public boolean getGameFinished()
	{
		
		return isFinished;
		
	}
	
	public void setGameFinished(boolean b)
	{
		
		isFinished = b;
		
	}
	
	public int getWinner()
	{
		
		return winner;
		
	}
	
	public void setWinner(int w)
	{
		
		winner = w;
	}*/
	
}
