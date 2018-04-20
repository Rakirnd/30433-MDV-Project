package dataAccess.dao;

import java.util.List;

import model.user.User;

public interface UserDAI extends AbstractDAI<User>{
	
	public int insert(User u);
	
	public User findById(int id);
	public List<User> findAll();
	public User findAccountByEmail(String email);
	
	public void update(int id, User u);
	
	public void delete(int id);

}
