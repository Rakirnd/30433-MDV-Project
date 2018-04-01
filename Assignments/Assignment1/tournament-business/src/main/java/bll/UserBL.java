package bll;

import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserDAO;
import dao.UserDataAccess;

public class UserBL implements UserBusiness{
	
	public User findAccountByEmail(String email) {
		
		UserDataAccess aDAO = new UserDAO();
		return aDAO.findAccountByEmail(email);
		
	}

	public User findUserByID(int playerID) {
		
		UserDataAccess aDAO = new UserDAO();
		return aDAO.findById(playerID);
		
	}
	
	public void registerUser(String email, String pass){
		
		User u = new User(email, pass);
		u.setIsAdmin(0);
		
		UserDataAccess aDAO = new UserDAO();
		aDAO.insert(u);
		
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
		
		UserDataAccess udao = new UserDAO();
		udao.updateUser(uid, u);
		
	}
	
	

}
