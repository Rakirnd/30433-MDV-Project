package match;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dao.AbstractDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tournament.Tournament;
import user.User;
import user.UserBL;

public class MatchBL {
	
	public static Vector<Match> findAllMatchesByTournament(Tournament t)
	{
		
		AbstractDAO aDAO = new AbstractDAO(Match.class);
		ObservableList<Vector<Object>> am = aDAO.findAllMatchesByTournamentId(t.getId());
		
		return createMatchList(am);
		
	}
	
	private static Vector<Match> createMatchList(ObservableList<Vector<Object>> am)
	{
		
		Vector<Match> ml = new Vector<Match>();
		
		am.forEach((rec) -> {
			
			int id = 0;
			int tournID = 0;
			
			int playerOneID = 0;
			int playerTwoID = 0;
			
			int playerOneGames = 0;
			int playerTwoGames = 0;
			
			if(rec.get(0) != null)
				id = Integer.parseInt(rec.get(0).toString());
			
			if(rec.get(1) != null)
				tournID = Integer.parseInt(rec.get(1).toString());
			
			if(rec.get(2) != null)
				playerOneID = Integer.parseInt(rec.get(2).toString());
			
			if(rec.get(3) != null)
				playerTwoID = Integer.parseInt(rec.get(3).toString());
			
			if(rec.get(4) != null)
				playerOneGames = Integer.parseInt(rec.get(4).toString());
			
			if(rec.get(5) != null)
				playerTwoGames = Integer.parseInt(rec.get(5).toString());
			
			Match m = new Match(playerOneID, playerTwoID);
					
			m.setId(id);
			m.setTournID(tournID);
			m.setPlayerOneGames(playerOneGames);
			m.setPlayerTwoGames(playerTwoGames);
					
			ml.add(m);
			
		});
		
		return ml;
		
	}
	
	public static int insertMatch(Match m)
	{
		
		AbstractDAO aDAO = new AbstractDAO(Match.class);
		return aDAO.insert(m);
		
	}
	
	public static void updateMatch(int id, Match m)
	{
		
		AbstractDAO aDAO = new AbstractDAO(Match.class);
		aDAO.update(id, m);
		
	}

}
