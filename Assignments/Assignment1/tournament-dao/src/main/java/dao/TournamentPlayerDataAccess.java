package dao;

import java.util.Vector;

import javafx.collections.ObservableList;
import model.TournamentPlayer;

@SuppressWarnings("restriction")
public interface TournamentPlayerDataAccess extends AbstractDataAccess {
	
	public TournamentPlayer findById(int id);
	public Vector<TournamentPlayer> findAllPlayersByTournamentId(int tid);
	public Vector<TournamentPlayer> findAllTournamentsByPlayerId(int pid);
	public ObservableList<Vector<TournamentPlayer>> convertToObservableList(Vector<Vector<TournamentPlayer>> rl);

}
