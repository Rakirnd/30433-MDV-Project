package dataAccess.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import dataAccess.dao.UserDAI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.user.User;
import util.ConnectionFactory;
import util.SQLOperation;

public class JdbcUserDA implements UserDAI {
	
	private static String classTypeName = User.class.getSimpleName();
	private static Class<?> classType = User.class;
	
	@Override
	public int insert(User t) {
		
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
			
			System.out.println("Exception: UserDAO: insert " + e.getMessage());
			
		}catch (IllegalAccessException e){
			
			System.out.println("Exception: UserDAO: insert " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
			
		}
			
		return insertedID;
		
	}
	
	//find operations
	public User findAccountByEmail(String email){
		
		Vector<User> result = new Vector<User>();
		Vector<Vector<Object>> ir = new Vector<Vector<Object>>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectQuery("email", classTypeName));
			selectStatement.setString(1, email);
			
			resultSet = selectStatement.executeQuery();
			
			ir = buildMultipleResults(resultSet);
			result = createMultipleUsersFromData(ir);
			
			if(result.size() > 0)
				return result.get(0);
			else
				return null;
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: UserDAO: find account by email " + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		return null;
		
	}
	
	public Vector<User> findAll() {

		Vector<Vector<Object>> resultList = new Vector<Vector<Object>>();
		Vector<User> res = new Vector<User>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			selectStatement = dbConnection.prepareStatement(SQLOperation.createSelectAllQuery(classTypeName));
			resultSet = selectStatement.executeQuery();
			
			resultList = buildMultipleResults(resultSet);
			res = createMultipleUsersFromData(resultList);
			
			
		} catch (SQLException e) {
			
			System.out.println("Exception: UserDAO: find all " + e.getMessage());
			
		}finally {
			
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		
		}
		
		
		return res;
		
		
	}
	
	public User findById(int id) {

		Vector<User> result = new Vector<User>();
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
			
			System.out.println("Exception: UserDAO: find by id " + e.getMessage());
			
		}finally {
			
		ConnectionFactory.close(selectStatement);
		ConnectionFactory.close(dbConnection);
		
		}
		
		return null;
		
	}
	
	//update operation
	public void update(int id, User u) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try{
			
			updateStatement = dbConnection.prepareStatement(createUserUpdateQuery(id, u));
			
			updateStatement.executeUpdate();
			
		}catch (SQLException | IllegalArgumentException | SecurityException | IllegalAccessException | NoSuchFieldException e) {
			
			System.out.println("Exception: UserDAO: update " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	private static String createUserUpdateQuery(int id, User u) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("UPDATE ");
		sb.append(" ping_pong_t_db.");
		sb.append(classType.getSimpleName());
		sb.append(" SET ");
		
		sb.append("ID" + " = ");
		sb.append("'" + u.getId() + "'" + ", ");
		
		sb.append("email" + " = ");
		sb.append("'" + u.getEmail() + "'" + ", ");
		
		sb.append("password" + " = ");
		sb.append("'" + u.getPassword() + "'" + ", ");
		
		sb.append("isAdmin" + " = ");
		sb.append("'" + u.getIsAdmin() + "'");
	
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
			
			System.out.println("Exception: UserDAO: delete " + e.getMessage());
			
		}finally {
				
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
			
		}
		
	}
	
	//create a single user from Data
	public static User createSingleUserFromData(Vector<Object> ud){
		
		User u = new User();
		
		if(ud.get(0) != null)
			u.setId(Integer.parseInt(ud.get(0).toString()));
		
		if(ud.get(1) != null)
			u.setEmail(ud.get(1).toString());
		
		if(ud.get(2) != null)
			u.setPassword(ud.get(2).toString());
		
		if(ud.get(3) != null)
			u.setIsAdmin(Integer.parseInt(ud.get(3).toString()));
		
		return u;
		
	}

	
	//create multiple users from Data
	public static Vector<User> createMultipleUsersFromData(Vector<Vector<Object>> at){
		
		Vector<User> result = new Vector<User>();
		
		at.forEach((rec) ->{
			
			result.add(createSingleUserFromData(rec));
			
		});
		
		return result;
		
	}

	
	
	//converts to observable list for table display
	public ObservableList<Vector<User>> convertToObservableList(Vector<Vector<User>> rl) {

		ObservableList<Vector<User>> resultList = FXCollections.observableArrayList(rl);
		
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
