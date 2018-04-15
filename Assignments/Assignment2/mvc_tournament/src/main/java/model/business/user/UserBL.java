package model.business.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.user.User;
import model.user.UserDA;
import model.user.UserDAI;

public class UserBL implements UserBusiness{
	
	public User findAccountByEmail(String email) {
		
		UserDAI aDAO = new UserDA();
		return aDAO.findAccountByEmail(email);
		
	}

	public User findUserByID(int playerID) {
		
		UserDAI aDAO = new UserDA();
		return aDAO.findById(playerID);
		
	}
	
	public int registerUser(String email, String pass){
		
		User u = new User(email, pass);
		u.setIsAdmin(0);
		
		UserDAI aDAO = new UserDA();
		 return aDAO.insert(u);
		
	}
	
	public boolean checkDuplicates(String email){
	
		User u = findAccountByEmail(email);
	
		if(u != null)
			return true;
	
		return false;
	
	}
	
	public boolean checkEmailFormat(String email){
		
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher matcher = pattern.matcher(email);
		
		return matcher.find();
		
	}
	
	public boolean authenticate(String email, String pass) {
		
		User credentials = findAccountByEmail(email);
		boolean checkCredentials = false;
		
		if(credentials != null)
		{
			
			if(credentials.getPassword().equals(pass))
				checkCredentials = true;
			else
				checkCredentials = false;			
			
		}
		
		return checkCredentials;
		
	}
	
	public boolean isUserAdmin(String email) {
		
		int isAdmin;
		
		User checkee = findAccountByEmail(email);
		isAdmin = checkee.getIsAdmin();
		
		if(isAdmin == 1)
			return true;
		
		return false;
		
	}
	
	public void updateUser(int uid, User u) {
		
		UserDAI udao = new UserDA();
		udao.update(uid, u);
		
	}
	
	

}
