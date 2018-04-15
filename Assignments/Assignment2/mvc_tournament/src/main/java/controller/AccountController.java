package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import view.AlertBox;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import model.business.userData.UserDataBL;
import model.business.userData.UserDataBusiness;
import model.user.User;
import model.userData.UserData;

public class AccountController {
	
	public static void displayInitialData(TextField emailInput, TextField passInput, TextField firstName, 
											TextField lastName, TextField balance, String loggedInUser) {
		
		UserDataBusiness udbl = new UserDataBL();
		UserBusiness ubl = new UserBL();
		
		User loggedU = ubl.findAccountByEmail(loggedInUser);
		UserData loggedUData = udbl.findDatabyUserId(loggedU.getId());
		
		emailInput.setText(loggedU.getEmail());
		passInput.setText(loggedU.getPassword());
		
		if(loggedUData != null) {
			
			firstName.setText(loggedUData.getFirstName());
			lastName.setText(loggedUData.getLastName());
			balance.setText(Integer.toString(loggedUData.getBalance()));
			
		}
		
	}
	
	public static void updateAccount(TextField emailInput, TextField passInput, String loggedInUser){
		
		UserBusiness ubl = new UserBL();
		User loggedU = ubl.findAccountByEmail(loggedInUser);
		
		loggedU.setEmail(emailInput.getText());
		loggedU.setPassword(passInput.getText());
		
		if(ubl.checkEmailFormat(loggedU.getEmail())) {
			
			ubl.updateUser(loggedU.getId(), loggedU);
			
		}
		else
			AlertBox.display("Can't Update", "Invalid email format!");
		
	}
	
	public static void updateAccountData(TextField firstName, TextField lastName, TextField balance, String loggedInUser) {
		
		UserDataBusiness udbl = new UserDataBL();
		UserBusiness ubl = new UserBL();
		
		User loggedU = ubl.findAccountByEmail(loggedInUser);
		UserData loggedUData = udbl.findDatabyUserId(loggedU.getId());
		
		if(loggedUData == null) {
			
			UserData ud = new UserData(firstName.getText(), lastName.getText());
			ud.setBalance(Integer.parseInt(balance.getText()));
			ud.setUserID(loggedU.getId());
			
			udbl.insertUserData(ud);
			
		}
		else {
			
			loggedUData.setFirstName(firstName.getText());
			loggedUData.setLastName(lastName.getText());
			loggedUData.setBalance(Integer.parseInt(balance.getText()));
			
			udbl.updateUserData(loggedUData.getId(), loggedUData);
			
		}
		
		
	}
	
	public static void bindAccountsToView(TableView<UserData> accountTable) {
		
		UserDataBusiness udbl = new UserDataBL();
		accountTable.setItems(udbl.findAllUserData());
		
	}
	
	public static void increaseSelectedBalance(TableView<UserData> accountTable, TextField balanceChange) {
		
		UserData u;
		UserDataBusiness udbl = new UserDataBL();
		
		if(accountTable.getSelectionModel().getSelectedItem() != null){	
			
			u = accountTable.getSelectionModel().getSelectedItem();
			udbl.increaseBalance(u, Integer.parseInt(balanceChange.getText()));
			
		}
		
	}
	
	public static void decreaseSelectedBalance(TableView<UserData> accountTable, TextField balanceChange) {
		
		UserData u;
		UserDataBusiness udbl = new UserDataBL();
		
		if(accountTable.getSelectionModel().getSelectedItem() != null){	
			
			u = accountTable.getSelectionModel().getSelectedItem();
			udbl.decreaseBalance(u, Integer.parseInt(balanceChange.getText()));
			
		}
		
	}
	
	public static void deleteAccount(String email) {
		
		
		
	}
	

}
