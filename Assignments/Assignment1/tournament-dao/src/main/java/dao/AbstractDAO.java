package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.*;
import javax.swing.table.DefaultTableModel;

import connection.ConnectionFactory;

/**	
 * 
 * 
 */

public class AbstractDAO {

		protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

		private final Class<?> type;

		
		public AbstractDAO(){
			
			this.type = null;
			
		}
		
		public AbstractDAO(Class<?> T) {
			
			this.type = T;
			
		}

		public String createSelectQuery(String field) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(" ping_pong_t_db.");
			sb.append(type.getSimpleName());
			sb.append(" WHERE " + field + " =? ");
			
			return sb.toString();
			
		}
		
		public String createSelectAllQuery(){
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(type.getSimpleName());
			
			return sb.toString();
			
		}
		
		public String createUpdateQuery(int id, Object t) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
			
			StringBuilder sb = new StringBuilder();
			int dfLength = 1;
			
			sb.append("UPDATE ");
			sb.append(type.getSimpleName());
			sb.append(" SET ");
			
			for(Field field : type.getDeclaredFields()){
				
				field.setAccessible(true);
				
				sb.append(field.getName() + " = ");
				
				if(dfLength == type.getDeclaredFields().length)
					sb.append("'" + t.getClass().getDeclaredField(field.getName()).get(t) + "'");
				else
					sb.append("'" + t.getClass().getDeclaredField(field.getName()).get(t) + "'" + ", ");
				
				field.setAccessible(false);
				
				dfLength++;	
				
			}
			
			sb.append(" WHERE ID = " + id);
			
			return sb.toString();
			
		}
		
		public String createDeleteQuery(int id) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("DELETE FROM ");
			sb.append(type.getSimpleName());
			sb.append(" WHERE ID =" + id);
			
			return sb.toString();
			
		}
		
		public String createInsertQuery(){
			
			StringBuilder sb = new StringBuilder();
			int dfLength = 1;
			
			sb.append("INSERT ");
			sb.append("INTO ");
			sb.append(type.getSimpleName());
			sb.append(" (");
			
			for(Field field : type.getDeclaredFields()){
				
				if(dfLength == type.getDeclaredFields().length)
					sb.append(field.getName());
				else
					sb.append(field.getName() + "," );
				
				dfLength++;
				
			}
			
			sb.append(") ");
			sb.append("VALUES ");
			sb.append("(");
			
			for(int i = 0; i < type.getDeclaredFields().length; i++){
				
				if(i == type.getDeclaredFields().length - 1)
					sb.append("?");
				else
					sb.append("?,");
				
			}
			sb.append(")");
			
			
			return sb.toString();
			
		}
		
		public static ObservableList<Vector<Object>> buildObservableList(ResultSet rs) throws SQLException
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
		    
		    ObservableList<Vector<Object>> resultList = FXCollections.observableArrayList(data);
		    
			return resultList;	
			
		}
		
		public ObservableList<Vector<Object>> findAll()
		{
			
			ObservableList<Vector<Object>> resultList = FXCollections.observableArrayList();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectAllQuery());
				resultSet = selectStatement.executeQuery();
				
				resultList = buildObservableList(resultSet);
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findAll " + e.getMessage());
				
			}finally {
				
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			
			}
			
			
			return resultList;
			
		}
		
		public ObservableList<Vector<Object>> findAllMatchesByTournamentId(int tid)
		{
			
			ObservableList<Vector<Object>> resultList = FXCollections.observableArrayList();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectQuery("tournID"));
				selectStatement.setInt(1, tid);
				//System.out.println(selectStatement.toString());
				
				resultSet = selectStatement.executeQuery();
				
				resultList = buildObservableList(resultSet);
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findAllMatchesByTournamentId " + e.getMessage());
				
			}finally {
				
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			
			}
			
			
			return resultList;
			
		}
		
		public ObservableList<Vector<Object>> findByID(int id)
		{
			
			ObservableList<Vector<Object>> resultList = FXCollections.observableArrayList();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectQuery("id"));
				selectStatement.setInt(1, id);
				
				resultSet = selectStatement.executeQuery();
				
				resultList = buildObservableList(resultSet);
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findAllMatchesByTournamentId " + e.getMessage());
				
			}finally {
				
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			
			}
			
			return resultList;
			
		}
		
		public ObservableList<Vector<Object>> findPlayersByTournamentID(int tid)
		{
			
			ObservableList<Vector<Object>> resultList = FXCollections.observableArrayList();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectQuery("tournamentID"));
				selectStatement.setInt(1, tid);
				
				resultSet = selectStatement.executeQuery();
				
				resultList = buildObservableList(resultSet);
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findPlayersByTournamentID " + e.getMessage());
				
			}finally {
				
				ConnectionFactory.close(selectStatement);
				ConnectionFactory.close(dbConnection);
			
			}
			
			return resultList;
			
		}
		
		public List<Object> findById(int id){
			
			List<Object> result = new ArrayList<Object>();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectQuery("id"));
				selectStatement.setInt(1, id);
				
				resultSet = selectStatement.executeQuery();
				
				result = createObjects(resultSet);
				
				return (List<Object>) result;
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findById " + e.getMessage());
				
			}finally {
				
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
			
			}
			
			return null;
			
		}
		
		public List<Object> findByName(String name){
			
			List<Object> result = new ArrayList<Object>();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectQuery("productName"));
				selectStatement.setString(1, name);
				
				resultSet = selectStatement.executeQuery();
				
				result = createObjects(resultSet);
				
				return (List<Object>) result;
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findByName " + e.getMessage());
				
			}finally {
				
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
			
			}
			
			return null;
			
		}

		private List<Object> createObjects(ResultSet resultSet) {
			
			List<Object> list = new ArrayList<Object>();

			try {
				
				while (resultSet.next()) {
					
					for (Field field : type.getDeclaredFields()) {
						
						Object value = resultSet.getObject(field.getName());
						//System.out.println(value.toString());
						list.add(value);
						
					}
							
				}
				
			} catch ( SecurityException | IllegalArgumentException  | SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: createObjects " + e.getMessage());
				
			}
			
			return list;
			
		}

		public int insert(Object t) {
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement insertStatement = null;
			
			int insertedID = -1;
			int i = 0;
			
			try{
				
				insertStatement = dbConnection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
				
				for(Field field : type.getDeclaredFields()){
				
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
				
				LOGGER.log(Level.WARNING, "AbstractDAO: insert " + e.getMessage());
				
			}catch (IllegalAccessException e){
				
				LOGGER.log(Level.WARNING, "AbstractDAO: insert " + e.getMessage());
				
			}finally {
					
				ConnectionFactory.close(insertStatement);
				ConnectionFactory.close(dbConnection);
				
			}
				
			return insertedID;
			
		}

		public void update(int id, Object t) {
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement updateStatement = null;
			
			try{
				
				updateStatement = dbConnection.prepareStatement(createUpdateQuery(id, t));
				
				updateStatement.executeUpdate();
				
			}catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: update " + e.getMessage());
				
			}finally {
					
				ConnectionFactory.close(updateStatement);
				ConnectionFactory.close(dbConnection);
				
			}
			
		}
		
		public void delete(int id){
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement deleteStatement = null;
			
			try{
				
				deleteStatement = dbConnection.prepareStatement(createDeleteQuery(id));
				
				deleteStatement.executeUpdate();
				
			}catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: delete " + e.getMessage());
				
			}finally {
					
				ConnectionFactory.close(deleteStatement);
				ConnectionFactory.close(dbConnection);
				
			}
			
		}
		
		public List<Object> findAccountByEmail(String email)
		{
			
			List<Object> result = new ArrayList<Object>();
			
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement selectStatement = null;
			ResultSet resultSet = null;
			
			try{
				
				selectStatement = dbConnection.prepareStatement(createSelectQuery("email"));
				selectStatement.setString(1, email);
				
				resultSet = selectStatement.executeQuery();
				
				result = createObjects(resultSet);
				
				return (List<Object>) result;
				
				
			} catch (SQLException e) {
				
				LOGGER.log(Level.WARNING, "AbstractDAO: findAccountByEmail " + e.getMessage());
				
			}finally {
				
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
			
			}
			
			return null;
			
		}
		
		

}
