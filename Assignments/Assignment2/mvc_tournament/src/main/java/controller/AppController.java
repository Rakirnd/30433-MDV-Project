package controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import view.AccountView;
import view.AllAccountsView;
import view.ConfirmBox;
import view.Login;
import view.TournamentView;

public class AppController {
	
	public static void changeToTournaments(String loggedInUser){
		
		TournamentView.display(loggedInUser);
		
	}
	
	public static void viewAccount(String loggedInUser) {
		
		AccountView.display(loggedInUser);
		
	}
	
	public static void viewAllAccounts() {
		
		AllAccountsView.display();
		
	}
	
	public static void logOut(Stage window) {
			
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to log out?");
			
		if(answer) {
				
			window.close();
			Login.display();
				
		}
			
	}
	
	public static void setButtonVisibleForAdmin(Button b, String loggedInUser) {
		
		UserBusiness ubl = new UserBL();
		boolean isLoggedInUserAdmin = ubl.isUserAdmin(loggedInUser);
		
		if(isLoggedInUserAdmin == true)
			b.setVisible(true);
		else
			b.setVisible(false);
		
	}

}
