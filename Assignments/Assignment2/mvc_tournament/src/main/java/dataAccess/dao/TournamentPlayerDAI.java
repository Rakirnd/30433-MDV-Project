package dataAccess.dao;

import java.util.List;

import model.tournamentPlayer.TournamentPlayer;

public interface TournamentPlayerDAI extends AbstractDAI<TournamentPlayer>{
	
	public int insert(TournamentPlayer tp);
	
	public TournamentPlayer findById(int id);
	public List<TournamentPlayer> findAllPlayersByTournamentId(int tid);
	public List<TournamentPlayer> findAllTournamentsByPlayerId(int pid);
	
	public void delete(int tpid);

	void update(int id, TournamentPlayer g);

}
