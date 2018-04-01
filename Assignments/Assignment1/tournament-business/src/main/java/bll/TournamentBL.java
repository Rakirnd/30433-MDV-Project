package bll;

import java.util.Vector;

import dao.TournamentDAO;
import dao.TournamentDataAccess;
import javafx.collections.*;
import model.Tournament;
import model.TournamentPlayer;

public class TournamentBL implements TournamentBusiness{
	
	@SuppressWarnings({ "restriction" })
	public ObservableList<Tournament> getAllTournaments() {
		
		TournamentDataAccess aDAO = new TournamentDAO();
		Vector<Tournament> tData = aDAO.findAll();
		ObservableList<Tournament> at = FXCollections.observableArrayList(tData);
		
		return at;
		
	}
	
	public void registerTournament(Object t)	{
		
		TournamentDataAccess aDAO = new TournamentDAO();
		aDAO.insert(t);
		
	}
	
	public boolean isTournamentOpen(Tournament t) {
		
		TournamentPlayerBusiness tpbl = new TournamentPlayerBL();
		Vector<TournamentPlayer> tp = tpbl.getPlayersFromTournament(t.getId());
		
		if(tp.size() == 8)
			return true;
		
		return false;
		
	}
	

}
