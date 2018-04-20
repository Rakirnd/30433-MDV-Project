package dataAccess.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dataAccess.dao.MatchDAI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.match.MatchC;
import util.ConnectionFactory;
import util.SQLOperation;

public class JdbcMatchDA implements MatchDAI {
	
	private static String classTypeName = MatchC.class.getSimpleName();
	private static Class<?> classType = MatchC.class;

	
	@Override
	public int insert(MatchC t) {
		
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
	
	public Vector<MatchC> findAll() {

		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<MatchC> res = new Vector<MatchC>();
		
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
	
	public MatchC findById(int id) {

		Vector<MatchC> result = new Vector<MatchC>();
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
	public Vector<MatchC> findAllMatchesByTournamentId(int tid) {
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<MatchC> res = new Vector<MatchC>();
		
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
	
	public Vector<MatchC> findAllMatchesOfPlayer(int pid){
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<MatchC> res = new Vector<MatchC>();
		
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
	public void update(int id, MatchC m) {
		
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
	
	private static String createMatchUpdateQuery(int id, MatchC m) 
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
	public static MatchC createSingleUserFromData(Vector<Object> ud){
		
		MatchC u = new MatchC();
		
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
	public static Vector<MatchC> createMultipleUsersFromData(Vector<Vector<Object>> at){
		
		Vector<MatchC> result = new Vector<MatchC>();
		
		at.forEach((rec) ->{
			
			result.add(createSingleUserFromData(rec));
			
		});
		
		return result;
		
	}

	//converts to observable list for table display
	public ObservableList<Vector<MatchC>> convertToObservableList(Vector<Vector<MatchC>> rl) {

		ObservableList<Vector<MatchC>> resultList = FXCollections.observableArrayList(rl);
		
		return resultList;
		
	}
	
	private Vector<Vector<Object>> buildMultipleResults(ResultSet rs) throws SQLException
	{
		
		java.sql.ResultSetMetaData metaData = rs.getMetaData();
		
	    int columnCount = metaData.getColumnCount();
		
	    // data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    
	    while (rs.next()) {
	    	
	        Vector<Object> vector = new Vector<Object>();
	        
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	        	
	            vector.add(rs.getObject(columnIndex));
	            
	        }
	        
	        data.add(vector);
	        
	    }
	    
		return data;	
		
	}

}
