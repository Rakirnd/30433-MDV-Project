package model.business.tournament;

import java.util.ArrayList;
import java.util.List;

import dataAccess.dao.TournamentDAI;
import javafx.collections.*;
import model.business.tournamentPlayer.TournamentPlayerBL;
import model.business.tournamentPlayer.TournamentPlayerBusiness;
import model.tournament.Tournament;
import model.tournamentPlayer.TournamentPlayer;
import view.StartApp;

public class TournamentBL implements TournamentBusiness{
	
	public ObservableList<Tournament> getAllTournaments() {
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		List<Tournament> tData = aDAO.findAll();
		ObservableList<Tournament> at = FXCollections.observableArrayList(tData);
		
		return at;
		
	}
	
	public ObservableList<Tournament> getFilteredItems(String category, String type){
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		List<Tournament> tData = aDAO.findAll();
		List<Tournament> filteredData = new ArrayList<Tournament>();
		
		for(Tournament t: tData) {
			
			if(t.getTournamentType().equals(type) && t.getStatus().equals(category))
				filteredData.add(t);
			
		}
		
		ObservableList<Tournament> at = FXCollections.observableArrayList(filteredData);
		
		return at;
		
	}
	
	public ObservableList<Tournament> getSearchedForItems(String searchText){
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		List<Tournament> tData = aDAO.findLike(searchText);
		ObservableList<Tournament> at = FXCollections.observableArrayList(tData);
		
		return at;
		
	}
	
	public Tournament findById(int tid) {
		
		TournamentDAI tdao = StartApp.dataAccessWay.getTournamentDao();
		return tdao.findById(tid);
		
	}
	
	public void registerTournament(Tournament t)	{
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		aDAO.insert(t);
		
	}
	
	public void updateTournamentPrize(Tournament t) {
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		aDAO.updatePrize(t.getId(), t);
		
	}
	
	public void updateStatusWinner(int tid, Tournament t) {
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		aDAO.updateStatus(tid, t);
		aDAO.updateWinner(tid, t);
		
	}
	
	public boolean isTournamentOpen(Tournament t) {
		
		TournamentPlayerBusiness tpbl = new TournamentPlayerBL();
		List<TournamentPlayer> tp = tpbl.getPlayersFromTournament(t.getId());
		
		if(tp.size() == 8)
			return true;
		
		return false;
		
	}
	
	public void checkAllTournamentStatus() {
		
		TournamentDAI aDAO = StartApp.dataAccessWay.getTournamentDao();
		List<Tournament> tData = aDAO.findAll();
		
		for(Tournament t: tData) {
			
			if(isTournamentOpen(t) && t.getStatus().equals("Upcoming"))
				t.setStatus("Ongoing");
			
			aDAO.updateStatus(t.getId(), t);
			
		}
		
	}
	

}
