package model.business.tournamentPlayer;

import java.util.List;

import dataAccess.dao.TournamentPlayerDAI;
import model.business.tournament.TournamentBL;
import model.business.tournament.TournamentBusiness;
import model.business.userData.UserDataBL;
import model.business.userData.UserDataBusiness;
import model.tournament.Tournament;
import model.tournamentPlayer.TournamentPlayer;
import model.userData.UserData;
import view.StartApp;

public class TournamentPlayerBL implements TournamentPlayerBusiness{
	
	public void registerPlayerForTournament(TournamentPlayer u){
		
		TournamentPlayerDAI aDAO = StartApp.dataAccessWay.getTournamentPlayerDao();
		aDAO.insert(u);
		
	}
	
	public List<TournamentPlayer> getPlayersFromTournament(int tid){
		
		TournamentPlayerDAI aDAO = StartApp.dataAccessWay.getTournamentPlayerDao();
		List<TournamentPlayer> tp = aDAO.findAllPlayersByTournamentId(tid);
		
		return tp;
		
	}
	
	public List<TournamentPlayer> getTournamentsWherePlayerPlayed(int pid){
		
		TournamentPlayerDAI aDAO = StartApp.dataAccessWay.getTournamentPlayerDao();
		List<TournamentPlayer> tp = aDAO.findAllTournamentsByPlayerId(pid);
		
		return tp;
		
	}
	
	public boolean isPlayerEnrolledInTournament(int pid, int tid) {
		
		List<TournamentPlayer> tp = getTournamentsWherePlayerPlayed(pid);
		
		for(TournamentPlayer t: tp) {
			
			if(t.getTournamentID() == tid)
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean hasEnoughMoneyToEnroll(int uid, int entryFee) {
		
		UserDataBusiness udbl = new UserDataBL();
		UserData player = udbl.findDatabyUserId(uid);
		
		if((player.getBalance() - entryFee) >= 0)
			return true;
		
		return false;
		
	}
	
	public void enrollPlayerInTournament(int uid, int tid, int fee) {
		
		UserDataBusiness udbl = new UserDataBL();
		TournamentBusiness tb = new TournamentBL();
		
		UserData player = udbl.findDatabyUserId(uid);
		Tournament currentTournament = tb.findById(tid);
		
		player.setBalance(player.getBalance() - fee);
		currentTournament.setPrizePool(currentTournament.getPrizePool() + fee);
		
		TournamentPlayer tp = new TournamentPlayer();
		tp.setPlayerID(uid);
		tp.setTournamentID(tid);
		
		registerPlayerForTournament(tp);
		udbl.updateUserData(player.getId(), player);
		tb.updateTournamentPrize(currentTournament);
		
	}

}
