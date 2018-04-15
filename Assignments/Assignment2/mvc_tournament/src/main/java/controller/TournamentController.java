package controller;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.business.tournament.TournamentBL;
import model.business.tournament.TournamentBusiness;
import model.business.tournamentPlayer.TournamentPlayerBL;
import model.business.tournamentPlayer.TournamentPlayerBusiness;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import model.tournament.Tournament;
import model.user.User;
import view.AlertBox;
import view.MatchDisplay;
import view.TournamentCreation;


public class TournamentController {
	
	public static void setButtonVisibleForAdmin(Button b, String loggedInUser) {
		
		UserBusiness ubl = new UserBL();
		boolean isLoggedInUserAdmin = ubl.isUserAdmin(loggedInUser);
		
		if(isLoggedInUserAdmin == true)
			b.setVisible(true);
		else
			b.setVisible(false);
		
	}
	
	public static void bindTournamentsToView(TableView<Tournament> tournamentTable) {
		
		TournamentBusiness tbl = new TournamentBL();
		tournamentTable.setItems(tbl.getAllTournaments());
		
	}
	
	public static void viewSelectedTournament(TableView<Tournament> tournamentTable, String loggedInUser) {
		
		Tournament t;
		
		if(tournamentTable.getSelectionModel().getSelectedItem() != null)			
		{	
			
			t = tournamentTable.getSelectionModel().getSelectedItem();
			viewTournamentMatches(t, loggedInUser);
			
		}
		
	}
	
	private static void viewTournamentMatches(Tournament t, String loggedInUser) {
		
		TournamentBusiness tb = new TournamentBL();
		boolean openTournament = tb.isTournamentOpen(t);
		
		if(openTournament)
			MatchDisplay.display(t.getId(), loggedInUser);
		else
			AlertBox.display("Tournament Not Open", "Tournament is not open yet!");
		
	}
	
	public static void regProgram(TextField nameInput, ChoiceBox<String> type, TextField entryFeeInput){
		
		if(nameInput.getText().isEmpty())
			AlertBox.display("Failed!", "Tournament needs a name!");
		
		Tournament t = new Tournament();
		t.setName(nameInput.getText());
		t.setTournamentType(type.getValue());
		
		if(entryFeeInput.isEditable())
			t.setEntryFee(Integer.parseInt(entryFeeInput.getText()));
		else
			t.setEntryFee(0);
		
		t.setPrizePool(0);
		t.setWinner(0);
		t.setIsFinished(0);
		
		TournamentBusiness tb = new TournamentBL();
		tb.registerTournament(t);
			
		//AlertBox.display("Creation Success!", "The tournament has been created!");
		
	}
	
	public static void updateTournamentType(ChoiceBox<String> type, TextField entryFeeInput) {
		
		if(type.getValue().equals("Free"))
			entryFeeInput.setEditable(false);
		
		if(type.getValue().equals("Paid"))
			entryFeeInput.setEditable(true);
		
	}
	
	public static void enrolmentScript(TableView<Tournament> tournamentTable, String loggedInUser) {
		
		Tournament t;
		User u;
		
		UserBusiness ub = new UserBL();
		TournamentPlayerBusiness tb = new TournamentPlayerBL();
		
		if(tournamentTable.getSelectionModel().getSelectedItem() != null){	
			
			t = tournamentTable.getSelectionModel().getSelectedItem();
			u = ub.findAccountByEmail(loggedInUser);
			
			if(t.getStatus().equals("Upcoming")) {
				
				if(!tb.isPlayerEnrolledInTournament(u.getId(), t.getId())) {
					
					if(tb.hasEnoughMoneyToEnroll(u.getId(), t.getEntryFee()))
						tb.enrollPlayerInTournament(u.getId(), t.getId(), t.getEntryFee());
					else {
						
						AlertBox.display("Not enough money!", "You don't have enough money to enlist in the tournament!");
						return;
						
					}
						
					
				}
				else {
					
					AlertBox.display("Already Enrolled", "You are alredy enrolled in this tournament!");
					return;
					
				}	
				
			}
			else {
				
				AlertBox.display("Cannot Enrol", "Tournament is Ongoing or Finished!");
				return;
				
			}
				
		}
		
	}
	
	public static void searchTournament(TableView<Tournament> tournamentTable, TextField searchBox) {
		
		TournamentBusiness tbl = new TournamentBL();
		tournamentTable.setItems(tbl.getSearchedForItems(searchBox.getText()));
		
	}
	
	public static void checkTournamentStatus() {
		
		TournamentBusiness tb = new TournamentBL();
		tb.checkAllTournamentStatus();
		
	}
	
	public static void viewByCategory(TableView<Tournament> tournamentTable, ChoiceBox<String> category, ChoiceBox<String> type) {
		
		TournamentBusiness tbl = new TournamentBL();
		tournamentTable.setItems(tbl.getFilteredItems(category.getValue(), type.getValue()));
		
	}
	
	public static void createTournament()	{
		
		TournamentCreation.display();
		
	}
	
}
