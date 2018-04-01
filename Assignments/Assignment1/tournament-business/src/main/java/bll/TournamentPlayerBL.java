package bll;

import java.util.Vector;

import dao.TournamentPlayerDAO;
import dao.TournamentPlayerDataAccess;
import model.TournamentPlayer;

public class TournamentPlayerBL implements TournamentPlayerBusiness{
	
	public void registerPlayerForTournament(Object u){
		
		TournamentPlayerDataAccess aDAO = new TournamentPlayerDAO();
		aDAO.insert(u);
		
	}
	
	public Vector<TournamentPlayer> getPlayersFromTournament(int tid){
		
		TournamentPlayerDataAccess aDAO = new TournamentPlayerDAO();
		Vector<TournamentPlayer> tp = aDAO.findAllPlayersByTournamentId(tid);
		
		return tp;
		
	}
	
	public Vector<TournamentPlayer> getTournamentsWherePlayerPlayed(int pid){
		
		TournamentPlayerDataAccess aDAO = new TournamentPlayerDAO();
		Vector<TournamentPlayer> tp = aDAO.findAllTournamentsByPlayerId(pid);
		
		return tp;
		
	}

}
