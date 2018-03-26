package tournament;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import dao.AbstractDAO;
import javafx.collections.*;
import match.Match;
import user.User;
import user.UserBL;

public class TournamentBL {
	
	@SuppressWarnings("null")
	public static ObservableList<Tournament> getAllTournaments() 
	{
		
		AbstractDAO aDAO = new AbstractDAO(Tournament.class);
		ObservableList<Vector<Object>> at = aDAO.findAll();
		
		return createTournamentList(at);
		
	}
	
	private static ObservableList<Tournament> createTournamentList(ObservableList<Vector<Object>> at)
	{
		
		ObservableList<Tournament> tl = FXCollections.observableArrayList();
		
		at.forEach((rec) -> {
			
			Tournament t = new Tournament();
			
			if(rec.get(0) != null)
				t.setId(Integer.parseInt(rec.get(0).toString()));
			
			if(rec.get(1) != null)
				t.setName(rec.get(1).toString());
			
			if(rec.get(2) != null)
				t.setTournamentDate(rec.get(2).toString());
			
			if(rec.get(3) != null)
				t.setPlace(rec.get(3).toString());
			
			if(rec.get(4) != null)
				t.setPrizePool(Integer.parseInt(rec.get(4).toString()));
			
			
			tl.add(t);
			
		});
		
		return tl;
		
	}
	
	private static void printTournaments(ObservableList<Vector<Object>> at)
	{
		
		at.forEach((rec) -> {
			
			for(int i = 0; i < rec.size(); i++)
				if(rec.get(i) != null)
					System.out.println(rec.get(i).toString());
			
		});
		
	}
	
	public static int registerTournament(Object t)
	{
		
		AbstractDAO aDAO = new AbstractDAO(Tournament.class);
		
		return aDAO.insert(t);
		
	}
	
	public static Vector<TournamentPlayer> getPlayersFromTournament(Tournament t)
	{
		
		AbstractDAO aDAO = new AbstractDAO(TournamentPlayer.class);
		ObservableList<Vector<Object>> am = aDAO.findPlayersByTournamentID(t.getId());
		
		return createTournamentPlayerList(am);
		
	}
	
	private static Vector<TournamentPlayer> createTournamentPlayerList(ObservableList<Vector<Object>> am)
	{
		
		Vector<TournamentPlayer> ml = new Vector<TournamentPlayer>();
		
		am.forEach((rec) -> {
			
			TournamentPlayer tp = new TournamentPlayer();
			
			if(rec.get(0) != null)
				tp.setId(Integer.parseInt(rec.get(0).toString()));
			
			if(rec.get(1) != null)
				tp.setTournamentID(Integer.parseInt(rec.get(1).toString()));
			
			if(rec.get(2) != null)
				tp.setPlayerID(Integer.parseInt(rec.get(2).toString()));
			
			ml.add(tp);
			
		});
		
		return ml;
		
	}

}
