package bll;

import java.util.Vector;

import model.Match;
import model.Tournament;

public interface MatchBusiness {
	
	public Match findByID(int mid);
	public Vector<Match> findAllMatchesByTournament(int tid);
	public String findEmailForPlayer(Match m, int playerNumber);
	public Match getPlayerCurrentMatch(String email);
	public void insertMatch(Match m);
	public void updateMatch(int id, Match m);
	public boolean checkIfMatchesExistInTournament(Tournament t);
	public Vector<Match> createInitialMatchSetup(Tournament t);
	public boolean checkIfParticipantIsLogged(Tournament t, String user);
	
	public boolean isMatchFinished(int mid);
	public void updateMatchScore(int matchID, int player);
	
	public String findScoreForPlayer(Match m, int player);
	public void updateFinishedMatches(Vector<Match> matchList);

}
