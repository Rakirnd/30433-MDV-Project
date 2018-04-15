package model.business.tournamentPlayer;

import java.util.List;

import model.tournamentPlayer.TournamentPlayer;


public interface TournamentPlayerBusiness {
	
	public void registerPlayerForTournament(TournamentPlayer u);
	public List<TournamentPlayer> getPlayersFromTournament(int tid);
	public List<TournamentPlayer> getTournamentsWherePlayerPlayed(int pid);
	
	public boolean hasEnoughMoneyToEnroll(int uid, int entryFee);
	public boolean isPlayerEnrolledInTournament(int pid, int tid);
	
	public void enrollPlayerInTournament(int uid, int tid, int fee);

}
