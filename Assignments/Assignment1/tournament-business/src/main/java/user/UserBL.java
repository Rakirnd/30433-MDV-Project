package user;

import java.util.*;

import dao.AbstractDAO;
import javafx.collections.ObservableList;
import match.Match;

public class UserBL 
{
	
	@SuppressWarnings("null")
	public static User findAccountByEmail(String email) 
	{
		
		AbstractDAO aDAO = new AbstractDAO(User.class);
		
		List<Object> user = aDAO.findAccountByEmail(email);
		
		if (user == null) {
			
			//return false;
			throw new NoSuchElementException("There is no " + user.getClass() + " with email: " + email);
			
		}
		
		if(user.isEmpty())
			return null;
		
		return createUserObject(user);
		
	}
	
	public static User createUserObject(List<Object> user)
	{
		
		User u = new User();
		
		Iterator<Object> it = user.iterator();
		
		if(it.hasNext())
			u.setID(Integer.parseInt(it.next().toString()));
		else
			return null;
		
		if(it.hasNext())
			u.setEmail(it.next().toString());
		else
			return null;

		if(it.hasNext())
			u.setPass(it.next().toString());
		else
			return null;
	
		if(it.hasNext())
			u.setIsAdmin(Integer.parseInt(it.next().toString()));
		else
			return null;
		
		return u;
		
	}

	public static User findUserByID(int playerID) {
		
		AbstractDAO aDAO = new AbstractDAO(User.class);
		ObservableList<Vector<Object>> au = aDAO.findByID(playerID);
		
		Vector<User> ul = createUserFromID(au);
		
		if(ul.size() != 1)
			return null;
		else
			if(ul.get(0) != null)
				return ul.get(0);
		
		return null;
		
	}
	
	private static Vector<User> createUserFromID(ObservableList<Vector<Object>> au)
	{
		
		Vector<User> ul = new Vector<User>();
		
		au.forEach((rec) -> {
			
			User u = new User();
			
			if(rec.get(0) != null)
				u.setID(Integer.parseInt(rec.get(0).toString()));
			
			if(rec.get(1) != null)
				u.setEmail(rec.get(1).toString());
			
			if(rec.get(2) != null)
				u.setPass(rec.get(2).toString());
			
			if(rec.get(3) != null)
				u.setIsAdmin(Integer.parseInt(rec.get(3).toString()));
			
			ul.add(u);
			
		});
		
		return ul;
		
	}
	
	public static int registerUser(Object u)
	{
		
		AbstractDAO aDAO = new AbstractDAO(User.class);
		
		return aDAO.insert(u);
		
	}
	
	

}
