package controller;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import model.business.match.MatchBL;
import model.business.match.MatchBusiness;
import model.business.tournament.TournamentBL;
import model.business.tournament.TournamentBusiness;
import model.business.user.UserBL;
import model.business.user.UserBusiness;
import model.business.userData.UserDataBL;
import model.business.userData.UserDataBusiness;
import model.match.MatchC;
import model.tournament.Tournament;
import model.user.User;
import view.AlertBox;
import view.GameView;

public class MatchController {

	public static TextField createEmptyMatchPlayerTF(String loggedInUser)	{
		
		UserBusiness ubl = new UserBL();
		MatchBusiness mbl = new MatchBL();
		
		User loggedU = ubl.findAccountByEmail(loggedInUser);
		
		TextField tf = new TextField();
		tf.setEditable(false);
		tf.setOnMouseClicked(e -> {
		   
			e.consume();
			if(e.getButton().equals(MouseButton.PRIMARY))
				if(e.getClickCount() == 2) {
		    	   
					String email = tf.getText();
					
					if(email.equals(loggedU.getEmail()) || loggedU.getIsAdmin() == 1) {
						
						if(ubl.checkEmailFormat(email)){
							
							MatchC currentMatch = mbl.getPlayerCurrentMatch(email);
							
							if(currentMatch != null) {
								
								viewMatchGames(currentMatch);
								
							}
							else
								AlertBox.display("Match finished", "Match has finished!");
							
							
						}
						else
							AlertBox.display("Match not open", "Match not open yet!");
						
					}
					else
						AlertBox.display("Invalid operation", "Cannot interfere with another player's match!");
					
				}
			
		});
		
		return tf;
	
	}
	
	public static void updateMatches(int tournamentID, TextField[] mfc, Label[] mlc) {
		
		TournamentBusiness tbl = new TournamentBL();
		Tournament currentTournament = tbl.findById(tournamentID);
		
		MatchBusiness mb = new MatchBL();
		List<MatchC> matchList;
		
		if(!mb.checkIfMatchesExistInTournament(currentTournament))
			matchList = mb.createInitialMatchSetup(currentTournament);
		else
			matchList = mb.findAllMatchesByTournament(currentTournament.getId());
		
		mb.updateFinishedMatches(matchList);
		
		if(matchList.get(0).getMatchFinished() == 1) {
			
			if(matchList.get(0).getPlayerOneGames() == 3)
				matchList.get(4).setPlayerOne(matchList.get(0).getPlayerOne());
			
			if(matchList.get(0).getPlayerTwoGames() == 3)
				matchList.get(4).setPlayerOne(matchList.get(0).getPlayerTwo());
			
			mb.updateMatch(matchList.get(4).getId(), matchList.get(4));
			
		}
		
		if(matchList.get(1).getMatchFinished() == 1) {
			
			if(matchList.get(1).getPlayerOneGames() == 3)
				matchList.get(4).setPlayerTwo(matchList.get(1).getPlayerOne());
			
			if(matchList.get(1).getPlayerTwoGames() == 3)
				matchList.get(4).setPlayerTwo(matchList.get(1).getPlayerTwo());
			
			mb.updateMatch(matchList.get(4).getId(), matchList.get(4));
			
		}
		
		if(matchList.get(2).getMatchFinished() == 1) {
			
			if(matchList.get(2).getPlayerOneGames() == 3)
				matchList.get(5).setPlayerOne(matchList.get(2).getPlayerOne());
			
			if(matchList.get(2).getPlayerTwoGames() == 3)
				matchList.get(5).setPlayerOne(matchList.get(2).getPlayerTwo());
			
			mb.updateMatch(matchList.get(5).getId(), matchList.get(5));
			
		}
		
		if(matchList.get(3).getMatchFinished() == 1) {
			
			if(matchList.get(3).getPlayerOneGames() == 3)
				matchList.get(5).setPlayerTwo(matchList.get(3).getPlayerOne());
			
			if(matchList.get(3).getPlayerTwoGames() == 3)
				matchList.get(5).setPlayerTwo(matchList.get(3).getPlayerTwo());
			
			mb.updateMatch(matchList.get(5).getId(), matchList.get(5));
			
		}
		
		if(matchList.get(4).getMatchFinished() == 1) {
			
			if(matchList.get(4).getPlayerOneGames() == 3)
				matchList.get(6).setPlayerOne(matchList.get(4).getPlayerOne());
			
			if(matchList.get(4).getPlayerTwoGames() == 3)
				matchList.get(6).setPlayerOne(matchList.get(4).getPlayerTwo());
			
			mb.updateMatch(matchList.get(6).getId(), matchList.get(6));
			
		}
		
		if(matchList.get(5).getMatchFinished() == 1) {
			
			if(matchList.get(5).getPlayerOneGames() == 3)
				matchList.get(6).setPlayerTwo(matchList.get(5).getPlayerOne());
			
			if(matchList.get(5).getPlayerTwoGames() == 3)
				matchList.get(6).setPlayerTwo(matchList.get(5).getPlayerTwo());
			
			mb.updateMatch(matchList.get(6).getId(), matchList.get(6));
			
		}
		
		if(matchList.get(6).getMatchFinished() == 1) {
			
			if(matchList.get(6).getPlayerOneGames() == 3)
				updateWinnerPlayerTF(mfc[14], matchList.get(6).getPlayerOne());
			
			if(matchList.get(6).getPlayerTwoGames() == 3)
				updateWinnerPlayerTF(mfc[14], matchList.get(6).getPlayerTwo());
					
		}
		
		updateMatchPlayerTF(mfc[0], mfc[1], matchList.get(0));
		updateMatchPlayerTF(mfc[2], mfc[3], matchList.get(1));
		updateMatchPlayerTF(mfc[4], mfc[5], matchList.get(2));
		updateMatchPlayerTF(mfc[6], mfc[7], matchList.get(3));
		
		updateMatchPlayerTF(mfc[8], mfc[9], matchList.get(4));
		updateMatchPlayerTF(mfc[10], mfc[11], matchList.get(5));
		updateMatchPlayerTF(mfc[12], mfc[13], matchList.get(6));
		
		updateScoreLabel(mlc[0], mlc[1], matchList.get(0));
		updateScoreLabel(mlc[2], mlc[3], matchList.get(1));
		updateScoreLabel(mlc[4], mlc[5], matchList.get(2));
		updateScoreLabel(mlc[6], mlc[7], matchList.get(3));
		
		updateScoreLabel(mlc[8], mlc[9], matchList.get(4));
		updateScoreLabel(mlc[10], mlc[11], matchList.get(5));
		updateScoreLabel(mlc[12], mlc[13], matchList.get(6));
		
	}
	
	private static void updateScoreLabel(Label p1, Label p2, MatchC m) {
		
		MatchBusiness mb = new MatchBL();
		p1.setText(mb.findScoreForPlayer(m, 1));
		p2.setText(mb.findScoreForPlayer(m, 2));
		
	}

	private static void updateMatchPlayerTF(TextField p1, TextField p2, MatchC m){
		
		MatchBusiness mb = new MatchBL();
		p1.setText(mb.findEmailForPlayer(m, 1));
		p2.setText(mb.findEmailForPlayer(m, 2));
		
	}
	
	private static void updateWinnerPlayerTF(TextField p1, int pid){
		
		UserBusiness ub = new UserBL();
		User u = ub.findUserByID(pid);
		p1.setText(u.getEmail());
		
	}
	
	public static void updateWinner(int tournamentID, TextField lastMatchField) {
		
		if(!lastMatchField.getText().isEmpty()) {
			
			TournamentBusiness tbl = new TournamentBL();
			Tournament currentTournament = tbl.findById(tournamentID);
			
			UserBusiness ubl = new UserBL();
			User winner = ubl.findAccountByEmail(lastMatchField.getText());
			
			currentTournament.setStatus("Finished");
			currentTournament.setWinner(winner.getId());
			
			if(currentTournament.getTournamentType().equals("Paid")) {
				
				UserDataBusiness udbl = new UserDataBL();
				udbl.updateWinnerBalance(winner, currentTournament.getPrizePool());
				
			}
			
			tbl.updateStatusWinner(currentTournament.getId(), currentTournament);
			
		}
		
	}
	
	
	private static void viewMatchGames(MatchC m){
		
		GameView.display(m.getId());
		
	}
	
}
