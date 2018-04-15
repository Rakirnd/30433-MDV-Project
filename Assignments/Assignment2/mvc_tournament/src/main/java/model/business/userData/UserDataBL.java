package model.business.userData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.user.User;
import model.userData.UserData;
import model.userData.UserDataDA;
import model.userData.UserDataDAI;

public class UserDataBL implements UserDataBusiness{
	
	public void regUserData(int userID, String firstName, String lastName) {
		
		UserData ud = new UserData(firstName, lastName);
		ud.setUserID(userID);
		
		UserDataDAI aDAO = new UserDataDA();
		aDAO.insert(ud);
		
	}
	
	public void insertUserData(UserData ud) {
		
		UserDataDAI aDAO = new UserDataDA();
		aDAO.insert(ud);
		
	}
	
	public UserData findDatabyUserId(int uid) {
		
		UserDataDAI uDAO = new UserDataDA();
		return uDAO.findByUserId(uid);	
		
	}
	
	public UserData findById(int id) {
		
		UserDataDAI uDAO = new UserDataDA();
		return uDAO.findById(id);	
		
	}
	
	public ObservableList<UserData> findAllUserData(){
		
		UserDataDAI uDAO = new UserDataDA();
		ObservableList<UserData> ud = FXCollections.observableArrayList(uDAO.findAll());
		
		return ud;
		
	}
	
	public void updateUserData(int id, UserData ud) {
		
		UserDataDAI uDAO = new UserDataDA();
		uDAO.update(id, ud);
		
	}
	
	public void increaseBalance(UserData ud, int money) {
		
		UserDataDAI uDAO = new UserDataDA();
		ud.setBalance(ud.getBalance() + money);
		
		uDAO.update(ud.getId(), ud);
		
	}
	
	public void decreaseBalance(UserData ud, int money) {
		
		UserDataDAI uDAO = new UserDataDA();
		ud.setBalance(ud.getBalance() - money);
		
		if(ud.getBalance() < 0)
			ud.setBalance(0);
		
		uDAO.update(ud.getId(), ud);
		
	}
	
	public void updateWinnerBalance(User winner, int prize) {
		
		UserDataBusiness udbl = new UserDataBL();
		UserData winnerData = udbl.findDatabyUserId(winner.getId());
		
		int winnerBalance = winnerData.getBalance();
		winnerBalance += prize;
		winnerData.setBalance(winnerBalance);
		
		updateUserData(winnerData.getId(), winnerData);
		
	}

}
