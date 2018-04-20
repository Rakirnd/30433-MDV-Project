package util;

import dataAccess.dao.GameDAI;
import dataAccess.dao.MatchDAI;
import dataAccess.dao.TournamentDAI;
import dataAccess.dao.TournamentPlayerDAI;
import dataAccess.dao.UserDAI;
import dataAccess.dao.UserDataDAI;

public abstract class DaoFactory {
	
	public enum Type {
		
		HIBERNATE,
		JDBC;
		
	}
	
	protected DaoFactory(){

	}
	
	public static DaoFactory getInstance(Type factoryType) {
		switch (factoryType) {
			case HIBERNATE:
				return new HibernateDAOFactory();
			case JDBC:
				return new JdbcDAOFactory();
			default:
				throw new IllegalArgumentException("Invalid factory");
		}
	}
	
	public abstract GameDAI getGameDao();
	public abstract MatchDAI getMatchDao();
	public abstract TournamentDAI getTournamentDao();
	public abstract TournamentPlayerDAI getTournamentPlayerDao();
	public abstract UserDAI getUserDao();
	public abstract UserDataDAI getUserDataDao();
	

}
