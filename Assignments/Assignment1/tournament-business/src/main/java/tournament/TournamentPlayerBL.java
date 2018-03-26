package tournament;

import dao.AbstractDAO;
import user.User;

public class TournamentPlayerBL {
	
	public static int registerPlayerTournament(Object u)
	{
		
		AbstractDAO aDAO = new AbstractDAO(TournamentPlayer.class);
		
		return aDAO.insert(u);
		
	}

}
