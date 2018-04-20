package dataAccess.dao;

import java.util.List;

import model.match.MatchC;

public interface MatchDAI extends AbstractDAI<MatchC>{
	
	public int insert(MatchC m);
	
	public MatchC findById(int id);
	public List<MatchC> findAllMatchesByTournamentId(int tid);
	public List<MatchC> findAllMatchesOfPlayer(int pid);
	public List<MatchC> findAll();
	
	public void update(int id, MatchC m);
	
	public void delete(int id);
	

}
