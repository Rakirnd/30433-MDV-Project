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
import model.TournamentPlayer;

@SuppressWarnings("restriction")
public class TournamentPlayerDAO implements TournamentPlayerDataAccess {
	
	private String classTypeName = TournamentPlayer.class.getSimpleName();
	private Class<?> classType = TournamentPlayer.class;

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
			
			System.out.println("Exception: TournamentPlayerDAO: insert " + e.getMessage());
			
		}catch (IllegalAccessException e){
			
			System.out.println("Exception: TournamentPlayerDAO: insert " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
			
		}
			
		return insertedID;
		
	}
	
	//find operations
	public TournamentPlayer findById(int id) {

		Vector<TournamentPlayer> result = new Vector<TournamentPlayer>();
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
			
			System.out.println("Exception: TournamentPlayerDAO find by id" + e.getMessage());
			
		}finally {
			
		ConnectionFactory.close(selectStatement);
		ConnectionFactory.close(dbConnection);
		
		}
		
		return null;
		
	}
	
	@Override
	public Vector<TournamentPlayer> findAllPlayersByTournamentId(int tid) {
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<TournamentPlayer> res = new Vector<TournamentPlayer>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllWhereQuery("tournamentID", classTypeName));
			selectStatement.setInt(1, tid);
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: TournamentPlayerDAO find all" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		return res;
		
	}
	
	@Override
	public Vector<TournamentPlayer> findAllTournamentsByPlayerId(int pid) {
		
		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<TournamentPlayer> res = new Vector<TournamentPlayer>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllWhereQuery("playerID", classTypeName));
			selectStatement.setInt(1, pid);
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: TournamentPlayerDAO find all" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		return res;
		
	}
	
	//update operation
	public void update(int id, Object t) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try{
			
			updateStatement = dbConnection.prepareStatement("");
			
			updateStatement.executeUpdate();
			
		}catch (SQLException | IllegalArgumentException | SecurityException e) {
			
			System.out.println("Exception: TournamentPlayerDAO update" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
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
			
			System.out.println("Exception: TournamentPlayerDAO delete" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	//create a single user from Data
	public static TournamentPlayer createSingleUserFromData(Vector<Object> ud){
		
		TournamentPlayer u = new TournamentPlayer();
		
		if(ud.get(0) != null)
			u.setId(Integer.parseInt(ud.get(0).toString()));
		
		if(ud.get(1) != null)
			u.setTournamentID(Integer.parseInt(ud.get(1).toString()));
		
		if(ud.get(2) != null)
			u.setPlayerID(Integer.parseInt(ud.get(2).toString()));
		
		return u;
		
	}
	
	//create multiple users from Data
	public static Vector<TournamentPlayer> createMultipleUsersFromData(Vector<Vector<Object>> at){
		
		Vector<TournamentPlayer> result = new Vector<TournamentPlayer>();
		
		at.forEach((rec) ->{
			
			result.add(createSingleUserFromData(rec));
			
		});
		
		return result;
		
	}

	//converts to observable list for table display
	public ObservableList<Vector<TournamentPlayer>> convertToObservableList(Vector<Vector<TournamentPlayer>> rl) {

		ObservableList<Vector<TournamentPlayer>> resultList = FXCollections.observableArrayList(rl);
		
		return resultList;
		
	}

}
