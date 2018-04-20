package dataAccess.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dataAccess.dao.GameDAI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.game.Game;
import util.ConnectionFactory;
import util.SQLOperation;

public class JdbcGameDA implements GameDAI{
	
	private static String classTypeName = Game.class.getSimpleName();
	private static Class<?> classType = Game.class;

	@Override
	public int insert(Game t) {
		
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
			
			System.out.println("Exception: GameDAO: insert " + e.getMessage());
			
		}catch (IllegalAccessException e){
			
			System.out.println("Exception: GameDAO: insert " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
			
		}
			
		return insertedID;
		
	}
	
	//find operations
	public Game findById(int id) {

		Vector<Game> result = new Vector<Game>();
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
			
			System.out.println("Exception: GameDAO find by id" + e.getMessage());
			
		}finally {
			
		ConnectionFactory.close(selectStatement);
		ConnectionFactory.close(dbConnection);
		
		}
		
		return null;
		
	}
	
	@Override
	public Vector<Game> findAllGamesByMatchId(int mid) {
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<Game> res = new Vector<Game>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllWhereQuery("MatchID", classTypeName));
			selectStatement.setInt(1, mid);
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: GameDAO find all" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		return res;
		
	}
	
	//update operation
	public void update(int id, Game g) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try{
			
			updateStatement = dbConnection.prepareStatement(createGameUpdateQuery(id, g));
			updateStatement.executeUpdate();
			
		}catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			System.out.println("Exception: GameDAO update" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	private static String createGameUpdateQuery(int id, Game g) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE ");
		sb.append(classType.getSimpleName());
		sb.append(" SET ");
		
		sb.append("ID" + " = ");
		sb.append("'" + g.getId() + "'" + ", ");
		
		sb.append("MatchID" + " = ");
		sb.append("'" + g.getMatchID() + "'" + ", ");
		
		sb.append("PlayerOneScore" + " = ");
		sb.append("'" + g.getPlayerOneScore() + "'" + ", ");
		
		sb.append("PlayerTwoScore" + " = ");
		sb.append("'" + g.getPlayerTwoScore() + "'" + ", ");
		
		sb.append("gameFinished" + " = ");
		sb.append("'" + g.getGameFinished() + "'");
		
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
			
			System.out.println("Exception: GameDAO delete" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	//create a single user from Data
	public static Game createSingleUserFromData(Vector<Object> ud){
		
		Game u = new Game();
		
		if(ud.get(0) != null)
			u.setId(Integer.parseInt(ud.get(0).toString()));
		
		if(ud.get(1) != null)
			u.setMatchID(Integer.parseInt(ud.get(1).toString()));
		
		if(ud.get(2) != null)
			u.setPlayerOneScore(Integer.parseInt(ud.get(2).toString()));
		
		if(ud.get(3) != null)
			u.setPlayerTwoScore(Integer.parseInt(ud.get(3).toString()));
		
		if(ud.get(4) != null)
			u.setGameFinished(Integer.parseInt(ud.get(4).toString()));
		
		return u;
		
	}
	
	//create multiple users from Data
	public static Vector<Game> createMultipleUsersFromData(Vector<Vector<Object>> at){
		
		Vector<Game> result = new Vector<Game>();
		
		at.forEach((rec) ->{
			
			result.add(createSingleUserFromData(rec));
			
		});
		
		return result;
		
	}

	//converts to observable list for table display
	public ObservableList<Vector<Game>> convertToObservableList(Vector<Vector<Game>> rl) {

		ObservableList<Vector<Game>> resultList = FXCollections.observableArrayList(rl);
		
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
