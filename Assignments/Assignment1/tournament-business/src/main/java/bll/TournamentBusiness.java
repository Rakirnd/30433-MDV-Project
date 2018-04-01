package bll;

import javafx.collections.ObservableList;
import model.Tournament;

@SuppressWarnings("restriction")
public interface TournamentBusiness {
	
	public void registerTournament(Object t);
	public ObservableList<Tournament> getAllTournaments();
	public boolean isTournamentOpen(Tournament t);

}
