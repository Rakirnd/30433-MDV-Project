package model.business.tournamentPlayer;

import java.util.List;

import model.business.userData.UserDataBL;
import model.business.userData.UserDataBusiness;
import model.tournamentPlayer.TournamentPlayer;
import model.tournamentPlayer.TournamentPlayerDA;
import model.tournamentPlayer.TournamentPlayerDAI;
import model.userData.UserData;

public class TournamentPlayerBL implements TournamentPlayerBusiness{
	
	public void registerPlayerForTournament(TournamentPlayer u){
		
		TournamentPlayerDAI aDAO = new TournamentPlayerDA();
		aDAO.insert(u);
		
	}
	
	public List<TournamentPlayer> getPlayersFromTournament(int tid){
		
		TournamentPlayerDAI aDAO = new TournamentPlayerDA();
		List<TournamentPlayer> tp = aDAO.findAllPlayersByTournamentId(tid);
		
		return tp;
		
	}
	
	public List<TournamentPlayer> getTournamentsWherePlayerPlayed(int pid){
		
		TournamentPlayerDAI aDAO = new TournamentPlayerDA();
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
		UserData player = udbl.findDatabyUserId(uid);
		
		player.setBalance(player.getBalance() - fee);
		
		TournamentPlayer tp = new TournamentPlayer();
		tp.setPlayerID(uid);
		tp.setTournamentID(tid);
		
		registerPlayerForTournament(tp);
		udbl.updateUserData(player.getId(), player);
		
	}

}
