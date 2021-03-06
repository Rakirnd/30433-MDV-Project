package bll;

import model.User;

public interface UserBusiness {
	
	public boolean checkDuplicates(String email);
	public boolean checkEmailFormat(String email);
	
	public void registerUser(String email, String password);
	public void updateUser(int uid, User u);
	
	public boolean authenticate(String email, String password);
	public boolean isUserAdmin(String email);
	
	public User findUserByID(int playerID);
	public User findAccountByEmail(String email);
	

}
