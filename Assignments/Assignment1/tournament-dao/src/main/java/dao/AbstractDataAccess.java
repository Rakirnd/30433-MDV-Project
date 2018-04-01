package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public interface AbstractDataAccess {
	
	//create operations
	public int insert(Object t);
	
	//delete operations
	public void delete(int id);
	
	//build a result from a result set representing multiple objects
	public default Vector<Vector<Object>> buildMultipleResults(ResultSet rs) throws SQLException
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
