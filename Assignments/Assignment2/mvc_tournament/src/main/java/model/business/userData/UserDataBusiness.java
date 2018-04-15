package model.business.userData;

import javafx.collections.ObservableList;
import model.user.User;
import model.userData.UserData;

public interface UserDataBusiness {
	
	public void regUserData(int userID, String firstName, String lastName);
	public void insertUserData(UserData ud);
	
	public UserData findDatabyUserId(int uid);
	public UserData findById(int id);
	public ObservableList<UserData> findAllUserData();
	
	public void updateUserData(int id, UserData ud);
	public void increaseBalance(UserData ud, int money);
	public void decreaseBalance(UserData ud, int money);
	
	public void updateWinnerBalance(User winner, int prize);

}
