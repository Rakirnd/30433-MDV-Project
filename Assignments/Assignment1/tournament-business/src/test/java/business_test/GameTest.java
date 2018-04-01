package business_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bll.GameBL;
import bll.GameBusiness;
import model.Game;

class GameTest {

		Game currentGame;
		GameBusiness gbl = new GameBL();
		
		@Test
		void checkIfGameHasFinished() {
			
			currentGame = new Game();
			currentGame.setId(0);
			currentGame.setMatchID(0);
			
			//start
			assertEquals(currentGame.getPlayerOneScore(), 0);
			assertEquals(currentGame.getPlayerTwoScore(), 0);
			assertEquals(currentGame.getGameFinished(), 0);
			
			gbl.checkGameFinished(currentGame);
			assertEquals(currentGame.getGameFinished(), 0);
			
			//score: 10-11 not finished
			currentGame.setPlayerOneScore(10);
			currentGame.setPlayerTwoScore(11);
			
			gbl.checkGameFinished(currentGame);
			assertEquals(currentGame.getGameFinished(), 0);
			currentGame.setGameFinished(0);
			
			//score: 9-11 finished
			currentGame.setPlayerOneScore(9);
			currentGame.setPlayerTwoScore(11);
			
			gbl.checkGameFinished(currentGame);
			assertEquals(currentGame.getGameFinished(), 1);
			currentGame.setGameFinished(0);
			
			//score: 11-9 finished
			currentGame.setPlayerOneScore(11);
			currentGame.setPlayerTwoScore(9);
			
			gbl.checkGameFinished(currentGame);
			assertEquals(currentGame.getGameFinished(), 1);
			currentGame.setGameFinished(0);
			
			//score: 17-15 finished
			currentGame.setPlayerOneScore(17);
			currentGame.setPlayerTwoScore(15);
			
			gbl.checkGameFinished(currentGame);
			assertEquals(currentGame.getGameFinished(), 1);
			currentGame.setGameFinished(0);
			
			//score: 17-16 not finished
			currentGame.setPlayerOneScore(17);
			currentGame.setPlayerTwoScore(16);
			
			gbl.checkGameFinished(currentGame);
			assertEquals(currentGame.getGameFinished(), 0);
			currentGame.setGameFinished(0);
			
			
			//fail("Not yet implemented");
		}


}
