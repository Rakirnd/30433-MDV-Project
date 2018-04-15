package model.userData;

import java.util.List;

import model.dataAccess.AbstractDAI;

public interface UserDataDAI extends AbstractDAI<UserData> {
	
	public int insert(UserData ud);
	
	public UserData findById(int id);
	public List<UserData> findAll();
	public UserData findByUserId(int uid);
	
	public void update(int id, UserData ud);
	public void updateBalance(int id, int balance);
	
	public void delete(int id);
	

}
