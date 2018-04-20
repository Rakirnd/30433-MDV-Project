package util;

import java.lang.reflect.Field;

public class SQLOperation {
	
		//select query operation
		public static String createSelectQuery(String field, String table) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(" ping_pong_t_db.");
			sb.append(table);
			sb.append(" WHERE " + field + " =? ");
			
			//System.out.println(sb.toString());
			
			return sb.toString();
			
		}
		
		//select all query operation
		public static String createSelectAllQuery(String table){
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(" ping_pong_t_db.");
			sb.append(table);
			
			return sb.toString();
			
		}
		
		//select all where query operation
		public static String createSelectAllWhereQuery(String field, String table){
					
			StringBuilder sb = new StringBuilder();
					
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(" ping_pong_t_db.");
			sb.append(table);
			sb.append(" WHERE " + field + " =? ");
					
			return sb.toString();
					
		}
		
		//select all or query
		public static String createSelectAllOrQuery(String fieldOne, String fieldTwo, String table){
			
			StringBuilder sb = new StringBuilder();
					
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(" ping_pong_t_db.");
			sb.append(table);
			sb.append(" WHERE " + fieldOne + " =? ");
			sb.append(" OR " + fieldTwo + " =? ");
					
			return sb.toString();
					
		}
		
		//delete query operation
		public static String createDeleteQuery(int id, String table) {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("DELETE FROM ");
			sb.append(table);
			sb.append(" WHERE ID =" + id);
			
			return sb.toString();
			
		}
		
		//insert query operation
		public static String createInsertQuery(Class<?> type){
			
			StringBuilder sb = new StringBuilder();
			int dfLength = 1;
			
			sb.append("INSERT ");
			sb.append("INTO ");
			sb.append(" ping_pong_t_db.");
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

}
