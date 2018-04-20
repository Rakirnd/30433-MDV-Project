package dataAccess.dao;

import java.util.List;

import model.tournament.Tournament;

public interface TournamentDAI extends AbstractDAI<Tournament>{
	
	public int insert(Tournament t);
	
	public Tournament findById(int id);
	public List<Tournament> findAll();
	public List<Tournament> findLike(String filter);
	
	public void updateData(int id, Tournament t);
	public void updatePrize(int id, Tournament t);
	public void updateWinner(int id, Tournament t);
	public void updateStatus(int id, Tournament t);
	
	public void delete(int id);

	void update(int id, Tournament t);
	
}
