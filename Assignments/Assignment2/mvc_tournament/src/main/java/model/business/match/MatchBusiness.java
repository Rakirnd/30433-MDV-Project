package model.business.match;

import java.util.List;
import model.match.MatchC;
import model.tournament.Tournament;

public interface MatchBusiness {
	
	public MatchC findByID(int mid);
	public List<MatchC> findAllMatchesByTournament(int tid);
	public String findEmailForPlayer(MatchC m, int playerNumber);
	public MatchC getPlayerCurrentMatch(String email);
	public void insertMatch(MatchC m);
	public void updateMatch(int id, MatchC m);
	public boolean checkIfMatchesExistInTournament(Tournament t);
	public List<MatchC> createInitialMatchSetup(Tournament t);
	public boolean checkIfParticipantIsLogged(Tournament t, String user);
	
	public boolean isMatchFinished(int mid);
	public void updateMatchScore(int matchID, int player);
	
	public String findScoreForPlayer(MatchC m, int player);
	public void updateFinishedMatches(List<MatchC> matchList);

}
