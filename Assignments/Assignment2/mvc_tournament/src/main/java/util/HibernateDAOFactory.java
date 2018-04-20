package util;

import dataAccess.dao.GameDAI;
import dataAccess.dao.MatchDAI;
import dataAccess.dao.TournamentDAI;
import dataAccess.dao.TournamentPlayerDAI;
import dataAccess.dao.UserDAI;
import dataAccess.dao.UserDataDAI;
import dataAccess.hibernate.GameDA;
import dataAccess.hibernate.MatchDA;
import dataAccess.hibernate.TournamentDA;
import dataAccess.hibernate.TournamentPlayerDA;
import dataAccess.hibernate.UserDA;
import dataAccess.hibernate.UserDataDA;

public class HibernateDAOFactory extends DaoFactory {

	@Override
	public GameDAI getGameDao() {
		
		return new GameDA();
		
	}

	@Override
	public MatchDAI getMatchDao() {
		
		return new MatchDA();
		
	}
	
	@Override
	public TournamentDAI getTournamentDao() {
		
		return new TournamentDA();
		
	}
	
	@Override
	public TournamentPlayerDAI getTournamentPlayerDao() {
		
		return new TournamentPlayerDA();
		
	}
	
	@Override
	public UserDAI getUserDao() {
		
		return new UserDA();
		
	}
	
	@Override
	public UserDataDAI getUserDataDao() {
		
		return new UserDataDA();
		
	}

}
