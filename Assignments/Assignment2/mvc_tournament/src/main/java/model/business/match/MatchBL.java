package model.business.match;

import java.util.List;
import java.util.Vector;

import dataAccess.dao.MatchDAI;
import model.business.tournamentPlayer.TournamentPlayerBL;
import model.business.tournamentPlayer.TournamentPlayerBusiness;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import model.match.MatchC;
import model.tournament.Tournament;
import model.tournamentPlayer.TournamentPlayer;
import model.user.User;
import view.StartApp;

public class MatchBL implements MatchBusiness{
	
	public MatchC findByID(int mid){
		
		MatchDAI aDAO = StartApp.dataAccessWay.getMatchDao();
		MatchC am = aDAO.findById(mid);
		
		return am;
		
	}
	
	public List<MatchC> findAllMatchesByTournament(int tid){
		
		MatchDAI aDAO = StartApp.dataAccessWay.getMatchDao();
		List<MatchC> am = aDAO.findAllMatchesByTournamentId(tid);
		
		return am;
		
	}
	
	public String findEmailForPlayer(MatchC m, int playerNumber) {
		
		String email = "";
		
		UserBusiness ub = new UserBL();
		User u = new User();
		
		if(m.getPlayerOne() > 0 && playerNumber == 1) {
			
			u = ub.findUserByID(m.getPlayerOne());
			email = u.getEmail();
			
		}
			
		if(m.getPlayerTwo() > 0 && playerNumber == 2) {
			
			u = ub.findUserByID(m.getPlayerTwo());
			email = u.getEmail();
			
		}
		
		return email;
		
	}
	
	
	public String findScoreForPlayer(MatchC m, int player) {
		
		String score = "";
		
		if(player == 1)
			score = Integer.toString(m.getPlayerOneGames());
		
		if(player == 2)
			score = Integer.toString(m.getPlayerTwoGames());
		
		return score;
		
	}
	
	public MatchC getPlayerCurrentMatch(String email) {
		
		UserBusiness ubl = new UserBL();
		User u = ubl.findAccountByEmail(email);
		
		if(u == null)
			return null;
		
		MatchDAI mdao = StartApp.dataAccessWay.getMatchDao();
		
		List<MatchC> playerMatches = mdao.findAllMatchesOfPlayer(u.getId());
		
		for(int i = 0; i < playerMatches.size(); i++) {
			
			if(playerMatches.get(i).getMatchFinished() == 0)
				return playerMatches.get(i);
			
		}
		
		return null;
		
	}
	
	public void insertMatch(MatchC m){
		
		MatchDAI aDAO = StartApp.dataAccessWay.getMatchDao();
		aDAO.insert(m);
		
	}
	
	public void updateMatch(int id, MatchC m){
		
		MatchDAI aDAO = StartApp.dataAccessWay.getMatchDao();
		aDAO.update(id, m);
		
	}
	
	public boolean checkIfParticipantIsLogged(Tournament t, String email) {
		
		TournamentPlayerBusiness tpb = new TournamentPlayerBL();
		UserBusiness ubl = new UserBL();
		
		List<TournamentPlayer> tournamentParticipants = tpb.getPlayersFromTournament(t.getId());
		
		User user = ubl.findAccountByEmail(email);
		
		for(int i = 0; i < tournamentParticipants.size(); i++)
			if(tournamentParticipants.get(i).getPlayerID() == user.getId())
				return true;
		
		return false;
		
	}
	
	public boolean checkIfMatchesExistInTournament(Tournament t) {
		
		List<MatchC> matchList = findAllMatchesByTournament(t.getId());
		
		if(matchList.size() < 7)
			return false;
		
		return true;
		
	}
	
	public List<MatchC> createInitialMatchSetup(Tournament t) {
		
		TournamentPlayerBusiness tpb = new TournamentPlayerBL();
		
		List<MatchC> matchList = new Vector<MatchC>();
		List<TournamentPlayer> tournamentParticipants = tpb.getPlayersFromTournament(t.getId());
		
		MatchC m1 = new MatchC(tournamentParticipants.get(0).getPlayerID(), tournamentParticipants.get(1).getPlayerID());
		m1.setTournID(t.getId());
		m1.setMatchFinished(0);
		insertMatch(m1);
		matchList.add(m1);
		
		MatchC m2 = new MatchC(tournamentParticipants.get(2).getPlayerID(), tournamentParticipants.get(3).getPlayerID());
		m2.setTournID(t.getId());
		m2.setMatchFinished(0);
		insertMatch(m2);
		matchList.add(m2);
		
		MatchC m3 = new MatchC(tournamentParticipants.get(4).getPlayerID(), tournamentParticipants.get(5).getPlayerID());
		m3.setTournID(t.getId());
		m3.setMatchFinished(0);
		insertMatch(m3);
		matchList.add(m3);
		
		MatchC m4 = new MatchC(tournamentParticipants.get(6).getPlayerID(), tournamentParticipants.get(7).getPlayerID());
		m4.setTournID(t.getId());
		m4.setMatchFinished(0);
		insertMatch(m4);
		matchList.add(m4);
		
		MatchC m5 = new MatchC();
		m5.setTournID(t.getId());
		m5.setMatchFinished(0);
		insertMatch(m5);
		matchList.add(m5);
		
		MatchC m6 = new MatchC();
		m6.setTournID(t.getId());
		m6.setMatchFinished(0);
		insertMatch(m6);
		matchList.add(m6);
		
		MatchC m7 = new MatchC();
		m7.setTournID(t.getId());
		m7.setMatchFinished(0);
		insertMatch(m7);
		matchList.add(m7);
		
		return matchList;
		
	}
	
	public boolean isMatchFinished(int mid) {
		
		MatchC currentMatch = findByID(mid);
		
		int playerOneGames = currentMatch.getPlayerOneGames();
		int playerTwoGames = currentMatch.getPlayerTwoGames();
		
		if(playerOneGames == 3 || playerTwoGames == 3)
			return true;
		
		return false;
		
	}

	public void updateMatchScore(int matchID, int player) {
		
		MatchC currentMatch = findByID(matchID);
		int won = 0;
		
		if(player == 1) {
			
			won = currentMatch.getPlayerOneGames();
			currentMatch.setPlayerOneGames(won + 1);
			
			if(won == 3)
				currentMatch.setMatchFinished(1);
			
		}
		
		if(player == 2) {
			
			won = currentMatch.getPlayerTwoGames();
			currentMatch.setPlayerTwoGames(won + 1);
			
		}
		
		if(won == 3)
			currentMatch.setMatchFinished(1);
		
		updateMatch(matchID, currentMatch);
		
	}
	
	public void updateFinishedMatches(List<MatchC> matchList) {
		
		for(int i = 0; i < matchList.size(); i++) {
			
			MatchC m = matchList.get(i);
			
			if(m.getPlayerOneGames() == 3 || m.getPlayerTwoGames() == 3) {
				
				m.setMatchFinished(1);
				updateMatch(m.getId(), m);
				
			}
				
		}
		
	}

}
