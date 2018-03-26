package tournament;

import java.util.List;

import match.Match;
import user.User;

import java.util.ArrayList;

public class Tournament
{
	
	private int id;
	private String name;
	private String tournamentDate;
	private String place;
	private int prizePool;
	
	
	/*private User winner;
	private List<User> participantList;
	private List<Match> matchList;
	
	private boolean isRegistrationOpen;
	private boolean isTournamentOpen;*/
	
	public Tournament()
	{
		
		//isRegistrationOpen = true;
		
		//matchList = new ArrayList<Match>();
		//participantList = new ArrayList<User>();
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTournamentDate() {
		return tournamentDate;
	}

	public void setTournamentDate(String tournamentDate) {
		this.tournamentDate = tournamentDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getPrizePool() {
		return prizePool;
	}

	public void setPrizePool(int prizePool) {
		this.prizePool = prizePool;
	}

	/*public List<User> getParticipantList() {
		return participantList;
	}

	public void setParticipantList(List<User> participantList) {
		this.participantList = participantList;
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}

	public boolean isRegistrationOpen() {
		return isRegistrationOpen;
	}

	public void setRegistrationOpen(boolean isRegistrationOpen) {
		this.isRegistrationOpen = isRegistrationOpen;
	}

	public boolean getIsTournamentOpen() {
		return isTournamentOpen;
	}



	public void setTournamentOpen(boolean isTournamentOpen) {
		this.isTournamentOpen = isTournamentOpen;
	}*/

	
}

