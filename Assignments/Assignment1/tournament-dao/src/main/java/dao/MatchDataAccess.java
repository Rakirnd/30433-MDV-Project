package dao;

import java.util.Vector;

import javafx.collections.ObservableList;
import model.Match;

@SuppressWarnings("restriction")
public interface MatchDataAccess extends AbstractDataAccess{
	
	public Match findById(int id);
	public Vector<Match> findAll();
	public void updateMatch(int id, Match m);
	public Vector<Match> findAllMatchesByTournamentId(int tid);
	public Vector<Match> findAllMatchesOfPlayer(int pid);
	public ObservableList<Vector<Match>> convertToObservableList(Vector<Vector<Match>> rl);

}
