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
import model.Tournament;

@SuppressWarnings("restriction")
public class TournamentDAO implements TournamentDataAccess {
	
	private static String classTypeName = Tournament.class.getSimpleName();
	private static Class<?> classType = Tournament.class;

	
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
			
			System.out.println("Exception: TournamentDAO: insert " + e.getMessage());
			
		}catch (IllegalAccessException e){
			
			System.out.println("Exception: TournamentDAO: insert " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
			
		}
			
		return insertedID;
		
	}
	
	//find operations
	
	public Vector<Tournament> findAll() {

		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<Tournament> res = new Vector<Tournament>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllQuery(classTypeName));
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: TournamentDAO find all" + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		
		return res;
		
		
	}
	
	public Tournament findById(int id) {

		Vector<Tournament> result = new Vector<Tournament>();
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
			
			System.out.println("Exception: TournamentDAO find by id" + e.getMessage());
			
		}finally {
			
		ConnectionFactory.close(selectStatement);
		ConnectionFactory.close(dbConnection);
		
		}
		
		return null;
		
	}
	
	//update operation
	public void updateTournament(int id, Tournament t) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try{
			
			updateStatement = dbConnection.prepareStatement(createTournamentUpdateQuery(id, t));
			
			updateStatement.executeUpdate();
			
		}catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			System.out.println("Exception: TournamentDAO update" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	private static String createTournamentUpdateQuery(int id, Tournament t) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE ");
		sb.append(classType.getSimpleName());
		sb.append(" SET ");
		
		sb.append("ID" + " = ");
		sb.append("'" + t.getId() + "'" + ", ");
		
		sb.append("name	" + " = ");
		sb.append("'" + t.getName() + "'" + ", ");
		
		sb.append("tournamentDate" + " = ");
		sb.append("'" + t.getTournamentDate() + "'" + ", ");
		
		sb.append("place" + " = ");
		sb.append("'" + t.getPlace() + "'" + ", ");
		
		sb.append("prizePool" + " = ");
		sb.append("'" + t.getPrizePool() + "'");
		
		sb.append("isFinished" + " = ");
		sb.append("'" + t.getIsFinished() + "'");
		
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
			
			System.out.println("Exception: TournamentDAO delete" + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	//create a single user from Data
	public static Tournament createSingleUserFromData(Vector<Object> ud){
		
		Tournament u = new Tournament();
		
		if(ud.get(0) != null)
			u.setId(Integer.parseInt(ud.get(0).toString()));
		
		if(ud.get(1) != null)
			u.setName(ud.get(1).toString());
		
		if(ud.get(2) != null)
			u.setTournamentDate(ud.get(2).toString());
		
		if(ud.get(3) != null)
			u.setPlace(ud.get(3).toString());
		
		if(ud.get(4) != null)
			u.setPrizePool(Integer.parseInt(ud.get(4).toString()));
		
		if(ud.get(5) != null)
			u.setIsFinished(Integer.parseInt(ud.get(5).toString()));
		
		return u;
		
	}
	
	//create multiple users from Data
	public static Vector<Tournament> createMultipleUsersFromData(Vector<Vector<Object>> at){
		
		Vector<Tournament> result = new Vector<Tournament>();
		
		at.forEach((rec) ->{
			
			result.add(createSingleUserFromData(rec));
			
		});
		
		return result;
		
	}

	//converts to observable list for table display
	public ObservableList<Vector<Tournament>> convertToObservableList(Vector<Vector<Tournament>> rl) {

		ObservableList<Vector<Tournament>> resultList = FXCollections.observableArrayList(rl);
		
		return resultList;
		
	}

}
