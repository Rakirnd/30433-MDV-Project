package bll;

import java.util.Vector;

import model.TournamentPlayer;

public interface TournamentPlayerBusiness {
	
	public void registerPlayerForTournament(Object u);
	public Vector<TournamentPlayer> getPlayersFromTournament(int tid);
	public Vector<TournamentPlayer> getTournamentsWherePlayerPlayed(int pid);

}
