package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import connection.ConnectionFactory;
import connection.SQLOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Match;

@SuppressWarnings("restriction")
public class MatchDAO implements MatchDataAccess {
	
	private static String classTypeName = Match.class.getSimpleName();
	private static Class<?> classType = Match.class;

	
	@Override
	public int insert(Object t) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		
		int insertedID = -1;
		int i = 0;
		
		try{
			
			insertStatement = dbConnection.prepareStatement(SQLOperation.createInsertQuery(classType), Statement.RETURN_GENERATED_KEYS);
			
			for(Field field : classType.getDeclaredFields()){
			
				i++;
				
				field.setAccessible(true);
				insertStatement.setObject(i, field.get(t));
				field.setAccessible(false);
					
			}
			
			insertStatement.execute();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if(rs.next()){
				
				insertedID = rs.getInt(1);
				
			}
			
			
		}catch (SQLException e) {
			
			System.out.println("Exception: MatchDAO: insert " + e.getMessage());
			
		}catch (IllegalAccessException e){
			
			System.out.println("Exception: MatchDAO: insert " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
			
		}
			
		return insertedID;
		
	}
	
	//find operations
	
	public Vector<Match> findAll() {

		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<Match> res = new Vector<Match>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllQuery(classTypeName));
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: MatchDAO find all" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		
		return res;
		
		
	}
	
	public Match findById(int id) {

		Vector<Match> result = new Vector<Match>();
		Vector<Vector<Object>> collectedData = new Vector<Vector<Object>>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectQuery("id", classTypeName));
			selectStatement.setInt(1, id);
			
			resultSet = selectStatement.executeQuery();
			
			collectedData = buildMultipleResults(resultSet);
			result = createMultipleUsersFromData(collectedData);
			
			return result.get(0);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: MatchDAO find by id" + e.getMessage());
			
		}finally {
			
		ConnectionFactory.close(selectStatement);
		ConnectionFactory.close(dbConnection);
		
		}
		
		return null;
		
	}
	
	@Override
	public Vector<Match> findAllMatchesByTournamentId(int tid) {
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<Match> res = new Vector<Match>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllWhereQuery("tournID", classTypeName));
			selectStatement.setInt(1, tid);
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: MatchDAO find all" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		return res;
		
	}
	
	public Vector<Match> findAllMatchesOfPlayer(int pid){
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<Match> res = new Vector<Match>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllOrQuery("playerOne", "playerTwo", classTypeName));
			selectStatement.setInt(1, pid);
			selectStatement.setInt(2, pid);
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: MatchDAO find all matches of player" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		return res;
		
	}
	
	//update operation
	public void updateMatch(int id, Match m) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try{
			
			updateStatement = dbConnection.prepareStatement(createMatchUpdateQuery(id, m));
			
			updateStatement.executeUpdate();
			
		}catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			System.out.println("Exception: MatchDAO update" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	private static String createMatchUpdateQuery(int id, Match m) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("UPDATE ");
		sb.append(" ping_pong_t_db.");
		sb.append(classType.getSimpleName());
		sb.append(" SET ");
		
		sb.append("ID" + " = ");
		sb.append("'" + m.getId() + "'" + ", ");
		
		sb.append("tournID" + " = ");
		sb.append("'" + m.getTournID() + "'" + ", ");
		
		sb.append("playerOne" + " = ");
		sb.append("'" + m.getPlayerOne() + "'" + ", ");
		
		sb.append("playerTwo" + " = ");
		sb.append("'" + m.getPlayerTwo() + "'" + ", ");
		
		sb.append("playerOneGames" + " = ");
		sb.append("'" + m.getPlayerOneGames() + "'" + ", ");
		
		sb.append("playerTwoGames" + " = ");
		sb.append("'" + m.getPlayerTwoGames() + "'" + ", ");
		
		sb.append("matchFinished" + " = ");
		sb.append("'" + m.getMatchFinished() + "'");
		
		sb.append(" WHERE ID = " + id);
		
		return sb.toString();
		
	}
	
	
	//delete operation
	@Override
	public void delete(int id) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try{
			
			deleteStatement = dbConnection.prepareStatement(SQLOperation.createDeleteQuery(id, classTypeName));
			
			deleteStatement.executeUpdate();
			
		}catch (SQLException e) {
			
			System.out.println("Exception: MatchDAO delete" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	//create a single user from Data
	public static Match createSingleUserFromData(Vector<Object> ud){
		
		Match u = new Match();
		
		if(ud.get(0) != null)
			u.setId(Integer.parseInt(ud.get(0).toString()));
		
		if(ud.get(1) != null)
			u.setTournID(Integer.parseInt(ud.get(1).toString()));
		
		if(ud.get(2) != null)
			u.setPlayerOne(Integer.parseInt(ud.get(2).toString()));
		
		if(ud.get(3) != null)
			u.setPlayerTwo(Integer.parseInt(ud.get(3).toString()));
		
		if(ud.get(4) != null)
			u.setPlayerOneGames(Integer.parseInt(ud.get(4).toString()));
		
		if(ud.get(5) != null)
			u.setPlayerTwoGames(Integer.parseInt(ud.get(5).toString()));
		
		if(ud.get(6) != null)
			u.setMatchFinished(Integer.parseInt(ud.get(6).toString()));
		
		return u;
		
	}
	
	//create multiple users from Data
	public static Vector<Match> createMultipleUsersFromData(Vector<Vector<Object>> at){
		
		Vector<Match> result = new Vector<Match>();
		
		at.forEach((rec) ->{
			
			result.add(createSingleUserFromData(rec));
			
		});
		
		return result;
		
	}

	//converts to observable list for table display
	public ObservableList<Vector<Match>> convertToObservableList(Vector<Vector<Match>> rl) {

		ObservableList<Vector<Match>> resultList = FXCollections.observableArrayList(rl);
		
		return resultList;
		
	}

}
