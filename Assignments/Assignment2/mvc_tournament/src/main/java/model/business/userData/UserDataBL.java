package model.business.userData;

import dataAccess.dao.UserDataDAI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.user.User;
import model.userData.UserData;
import view.StartApp;

public class UserDataBL implements UserDataBusiness{
	
	public void regUserData(int userID, String firstName, String lastName) {
		
		UserData ud = new UserData(firstName, lastName);
		ud.setUserID(userID);
		
		UserDataDAI aDAO = StartApp.dataAccessWay.getUserDataDao();
		aDAO.insert(ud);
		
	}
	
	public void insertUserData(UserData ud) {
		
		UserDataDAI aDAO = StartApp.dataAccessWay.getUserDataDao();
		aDAO.insert(ud);
		
	}
	
	public UserData findDatabyUserId(int uid) {
		
		UserDataDAI uDAO = StartApp.dataAccessWay.getUserDataDao();
		return uDAO.findByUserId(uid);	
		
	}
	
	public UserData findById(int id) {
		
		UserDataDAI uDAO = StartApp.dataAccessWay.getUserDataDao();
		return uDAO.findById(id);	
		
	}
	
	public ObservableList<UserData> findAllUserData(){
		
		UserDataDAI uDAO = StartApp.dataAccessWay.getUserDataDao();
		ObservableList<UserData> ud = FXCollections.observableArrayList(uDAO.findAll());
		
		return ud;
		
	}
	
	public void updateUserData(int id, UserData ud) {
		
		UserDataDAI uDAO = StartApp.dataAccessWay.getUserDataDao();
		uDAO.update(id, ud);
		
	}
	
	public void increaseBalance(UserData ud, int money) {
		
		UserDataDAI uDAO = StartApp.dataAccessWay.getUserDataDao();
		ud.setBalance(ud.getBalance() + money);
		
		uDAO.update(ud.getId(), ud);
		
	}
	
	public void decreaseBalance(UserData ud, int money) {
		
		UserDataDAI uDAO = StartApp.dataAccessWay.getUserDataDao();
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
