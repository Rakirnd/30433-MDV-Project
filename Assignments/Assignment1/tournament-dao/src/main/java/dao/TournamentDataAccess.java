package dao;

import java.util.Vector;

import javafx.collections.ObservableList;
import model.Tournament;

@SuppressWarnings("restriction")
public interface TournamentDataAccess extends AbstractDataAccess{

	public Tournament findById(int id);
	public Vector<Tournament> findAll();
	public ObservableList<Vector<Tournament>> convertToObservableList(Vector<Vector<Tournament>> rl);

}
