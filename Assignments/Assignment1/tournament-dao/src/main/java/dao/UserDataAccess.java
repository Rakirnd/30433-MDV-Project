package dao;

import java.util.Vector;
import javafx.collections.ObservableList;
import model.User;

@SuppressWarnings("restriction")
public interface UserDataAccess extends AbstractDataAccess{
	
	
	//find operations
	public User findAccountByEmail(String email);
	
	public Vector<User> findAll();
	
	public void updateUser(int id, User u);
	
	public User findById(int id); 

	//converts to observable list for table display
	public ObservableList<Vector<User>> convertToObservableList(Vector<Vector<User>> rl);

}
