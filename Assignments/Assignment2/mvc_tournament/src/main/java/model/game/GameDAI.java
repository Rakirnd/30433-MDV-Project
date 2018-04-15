package model.game;

import java.util.List;

import model.dataAccess.AbstractDAI;

public interface GameDAI extends AbstractDAI<Game>{
	
	public int insert(Game g);
	
	public Game findById(int id);
	public List<Game> findAllGamesByMatchId(int mid);
	
	public void update(int id, Game g);
	
	public void delete(int id);

}
