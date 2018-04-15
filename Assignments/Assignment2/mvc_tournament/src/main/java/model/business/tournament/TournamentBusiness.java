package model.business.tournament;

import javafx.collections.ObservableList;
import model.tournament.Tournament;

public interface TournamentBusiness {
	
	public Tournament findById(int tid);
	public void registerTournament(Tournament t);
	public ObservableList<Tournament> getAllTournaments();
	
	public ObservableList<Tournament> getSearchedForItems(String searchText);
	public ObservableList<Tournament> getFilteredItems(String category, String type);
	public void updateStatusWinner(int tid, Tournament t);
	
	public boolean isTournamentOpen(Tournament t);
	public void checkAllTournamentStatus();

}
