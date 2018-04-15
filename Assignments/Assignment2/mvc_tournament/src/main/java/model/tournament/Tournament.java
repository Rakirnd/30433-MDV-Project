package model.tournament;

public class Tournament {
	
	private int id;
	private String name;
	private String tournamentType;
	
	private int entryFee;
	private int prizePool;
	
	private String status;
	private int winner;
	
	private int isFinished;
	
	public Tournament() {
		
		entryFee = 0;
		prizePool = 0;
		winner = 0;
		
		isFinished = 0;
		setStatus("Upcoming");
		
	}
	
	public Tournament(String name, String type) {
		
		this.name = name;
		this.tournamentType = type;
		
		entryFee = 0;
		prizePool = 0;
		winner = 0;
		
		setStatus("Upcoming");
		isFinished = 0;
		
	}
	
	public Tournament(String name, String type, int entryFee) {
		
		this.name = name;
		this.tournamentType = type;
		
		this.entryFee = entryFee;
		prizePool = 0;
		winner = 0;
		
		isFinished = 0;		
	}
	
	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public String getTournamentType() {
		return tournamentType;
	}

	public void setTournamentType(String type) {
		this.tournamentType = type;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}

	public int getPrizePool() {
		return prizePool;
	}

	public void setPrizePool(int prizePool) {
		this.prizePool = prizePool;
	}
	
	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

