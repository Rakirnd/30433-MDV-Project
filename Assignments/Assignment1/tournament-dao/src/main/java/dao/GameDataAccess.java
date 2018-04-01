package dao;

import java.util.Vector;

import javafx.collections.ObservableList;
import model.Game;

@SuppressWarnings("restriction")
public interface GameDataAccess extends AbstractDataAccess{
	
	public Game findById(int id);
	public void updateGame(int id, Game g);
	public Vector<Game> findAllGamesByMatchId(int mid);
	public ObservableList<Vector<Game>> convertToObservableList(Vector<Vector<Game>> rl);

}
