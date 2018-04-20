package util;

import dataAccess.dao.GameDAI;
import dataAccess.dao.MatchDAI;
import dataAccess.dao.TournamentDAI;
import dataAccess.dao.TournamentPlayerDAI;
import dataAccess.dao.UserDAI;
import dataAccess.dao.UserDataDAI;
import dataAccess.jdbc.JdbcGameDA;
import dataAccess.jdbc.JdbcMatchDA;
import dataAccess.jdbc.JdbcTournamentDA;
import dataAccess.jdbc.JdbcTournamentPlayerDA;
import dataAccess.jdbc.JdbcUserDA;
import dataAccess.jdbc.JdbcUserDataDA;

public class JdbcDAOFactory extends DaoFactory{
	
	@Override
	public GameDAI getGameDao() {
		
		return new JdbcGameDA();
		
	}

	@Override
	public MatchDAI getMatchDao() {
		
		return new JdbcMatchDA();
		
	}
	
	@Override
	public TournamentDAI getTournamentDao() {
		
		return new JdbcTournamentDA();
		
	}
	
	@Override
	public TournamentPlayerDAI getTournamentPlayerDao() {
		
		return new JdbcTournamentPlayerDA();
		
	}
	
	@Override
	public UserDAI getUserDao() {
		
		return new JdbcUserDA();
		
	}
	
	@Override
	public UserDataDAI getUserDataDao() {
		
		return new JdbcUserDataDA();
		
	}

}
