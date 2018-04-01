package bll;

import java.util.Vector;

import dao.MatchDAO;
import dao.MatchDataAccess;
import model.Match;
import model.Tournament;
import model.TournamentPlayer;
import model.User;

public class MatchBL implements MatchBusiness{
	
	public Match findByID(int mid){
		
		MatchDataAccess aDAO = new MatchDAO();
		Match am = aDAO.findById(mid);
		
		return am;
		
	}
	
	public Vector<Match> findAllMatchesByTournament(int tid){
		
		MatchDataAccess aDAO = new MatchDAO();
		Vector<Match> am = aDAO.findAllMatchesByTournamentId(tid);
		
		return am;
		
	}
	
	public String findEmailForPlayer(Match m, int playerNumber) {
		
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
	
	
	public String findScoreForPlayer(Match m, int player) {
		
		String score = "";
		
		if(player == 1)
			score = Integer.toString(m.getPlayerOneGames());
		
		if(player == 2)
			score = Integer.toString(m.getPlayerTwoGames());
		
		return score;
		
	}
	
	public Match getPlayerCurrentMatch(String email) {
		
		UserBusiness ubl = new UserBL();
		User u = ubl.findAccountByEmail(email);
		
		if(u == null)
			return null;
		
		MatchDataAccess mdao = new MatchDAO();
		
		Vector<Match> playerMatches = mdao.findAllMatchesOfPlayer(u.getID());
		
		for(int i = 0; i < playerMatches.size(); i++) {
			
			if(playerMatches.get(i).getMatchFinished() == 0)
				return playerMatches.get(i);
			
		}
		
		return null;
		
	}
	
	public void insertMatch(Match m){
		
		MatchDataAccess aDAO = new MatchDAO();
		aDAO.insert(m);
		
	}
	
	public void updateMatch(int id, Match m){
		
		MatchDataAccess aDAO = new MatchDAO();
		aDAO.updateMatch(id, m);
		
	}
	
	public boolean checkIfParticipantIsLogged(Tournament t, String email) {
		
		TournamentPlayerBusiness tpb = new TournamentPlayerBL();
		UserBusiness ubl = new UserBL();
		
		Vector<TournamentPlayer> tournamentParticipants = new Vector<TournamentPlayer>();
		tournamentParticipants = tpb.getPlayersFromTournament(t.getId());
		
		User user = ubl.findAccountByEmail(email);
		
		for(int i = 0; i < tournamentParticipants.size(); i++)
			if(tournamentParticipants.get(i).getPlayerID() == user.getID())
				return true;
		
		return false;
		
	}
	
	public boolean checkIfMatchesExistInTournament(Tournament t) {
		
		Vector<Match> matchList = findAllMatchesByTournament(t.getId());
		
		if(matchList.size() < 7)
			return false;
		
		return true;
		
	}
	
	public Vector<Match> createInitialMatchSetup(Tournament t) {
		
		TournamentPlayerBusiness tpb = new TournamentPlayerBL();
		
		Vector<Match> matchList = new Vector<Match>();
		Vector<TournamentPlayer> tournamentParticipants = new Vector<TournamentPlayer>();
		
		tournamentParticipants = tpb.getPlayersFromTournament(t.getId());
		
		Match m1 = new Match(tournamentParticipants.get(0).getPlayerID(), tournamentParticipants.get(1).getPlayerID());
		m1.setTournID(t.getId());
		m1.setMatchFinished(0);
		insertMatch(m1);
		matchList.add(m1);
		
		Match m2 = new Match(tournamentParticipants.get(2).getPlayerID(), tournamentParticipants.get(3).getPlayerID());
		m2.setTournID(t.getId());
		m2.setMatchFinished(0);
		insertMatch(m2);
		matchList.add(m2);
		
		Match m3 = new Match(tournamentParticipants.get(4).getPlayerID(), tournamentParticipants.get(5).getPlayerID());
		m3.setTournID(t.getId());
		m3.setMatchFinished(0);
		insertMatch(m3);
		matchList.add(m3);
		
		Match m4 = new Match(tournamentParticipants.get(6).getPlayerID(), tournamentParticipants.get(7).getPlayerID());
		m4.setTournID(t.getId());
		m4.setMatchFinished(0);
		insertMatch(m4);
		matchList.add(m4);
		
		Match m5 = new Match();
		m5.setTournID(t.getId());
		m5.setMatchFinished(0);
		insertMatch(m5);
		matchList.add(m5);
		
		Match m6 = new Match();
		m6.setTournID(t.getId());
		m6.setMatchFinished(0);
		insertMatch(m6);
		matchList.add(m6);
		
		Match m7 = new Match();
		m7.setTournID(t.getId());
		m7.setMatchFinished(0);
		insertMatch(m7);
		matchList.add(m7);
		
		
		return matchList;
		
	}
	
	public boolean isMatchFinished(int mid) {
		
		Match currentMatch = findByID(mid);
		
		int playerOneGames = currentMatch.getPlayerOneGames();
		int playerTwoGames = currentMatch.getPlayerTwoGames();
		
		if(playerOneGames == 3 || playerTwoGames == 3)
			return true;
		
		return false;
		
	}

	public void updateMatchScore(int matchID, int player) {
		
		Match currentMatch = findByID(matchID);
		int won = 0;
		
		if(player == 1) {
			
			won = currentMatch.getPlayerOneGames();
			currentMatch.setPlayerOneGames(won + 1);
			
			if(won == 3)
				currentMatch.setMatchFinished(1);
			
			updateMatch(matchID, currentMatch);
			
		}
		
		if(player == 2) {
			
			won = currentMatch.getPlayerTwoGames();
			currentMatch.setPlayerTwoGames(won + 1);
			
			if(won == 3)
				currentMatch.setMatchFinished(1);
			
			updateMatch(matchID, currentMatch);
			
		}
		
	}
	
	public void updateFinishedMatches(Vector<Match> matchList) {
		
		for(int i = 0; i< matchList.size(); i++) {
			
			Match m = matchList.get(i);
			
			if(m.getPlayerOneGames() == 3 || m.getPlayerTwoGames() == 3) {
				
				m.setMatchFinished(1);
				updateMatch(m.getId(), m);
				
			}
				
		}
		
	}

}
