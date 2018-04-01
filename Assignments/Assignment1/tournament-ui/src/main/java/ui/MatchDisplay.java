package ui;

import javafx.stage.*;
import model.Match;
import model.Tournament;
import model.User;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.Vector;

import bll.MatchBL;
import bll.MatchBusiness;
import bll.UserBL;
import bll.UserBusiness;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class MatchDisplay
{
	
	private static User loggedU;
	private static TextField 	m1P1, m1P2, m2P1, m2P2, m3P1, m3P2, m4P1, m4P2, 
								m5P1, m5P2, m6P1, m6P2, m7P1, m7P2, m8P1;
	private static Label 	l1P1, l1P2, l2P1, l2P2, l3P1, l3P2, l4P1, l4P2,
							l5P1, l5P2, l6P1, l6P2, l7P1, l7P2;
	private static Tournament currentTournament;
	
	public static void display(Tournament t, String loggedUser){
		
		Stage window = new Stage();
		currentTournament = t;
		
		UserBusiness ubl = new UserBL();
		loggedU = ubl.findAccountByEmail(loggedUser);
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Matches");
		window.setMinWidth(250);
		
		//round 1
		m1P1 = createEmptyMatchPlayerTF();
		m1P2 = createEmptyMatchPlayerTF();
		m2P1 = createEmptyMatchPlayerTF();
		m2P2 = createEmptyMatchPlayerTF();
		m3P1 = createEmptyMatchPlayerTF();
		m3P2 = createEmptyMatchPlayerTF();
		m4P1 = createEmptyMatchPlayerTF();
		m4P2 = createEmptyMatchPlayerTF();
		
		//round 2
		m5P1 = createEmptyMatchPlayerTF();
		m5P2 = createEmptyMatchPlayerTF();
		m6P1 = createEmptyMatchPlayerTF();
		m6P2 = createEmptyMatchPlayerTF();
				
		//round 3
		m7P1 = createEmptyMatchPlayerTF();
		m7P2 = createEmptyMatchPlayerTF();
				
		//round 4
		m8P1 = createEmptyMatchPlayerTF();
		
		l1P1 = new Label();
		l1P2 = new Label();
		l2P1 = new Label();
		l2P2 = new Label();
		l3P1 = new Label();
		l3P2 = new Label();
		l4P1 = new Label();
		l4P2 = new Label();
		l5P1 = new Label();
		l5P2 = new Label();
		l6P1 = new Label();
		l6P2 = new Label();
		l7P1 = new Label();
		l7P2 = new Label();
		
		Button updateButton = new Button("Refresh");
		updateButton.setOnAction(e -> updateMatches());
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> window.close());
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		//round 1
		GridPane.setConstraints(m1P1, 0, 0);
		GridPane.setConstraints(m1P2, 0, 4);
		GridPane.setConstraints(m2P1, 0, 8);
		GridPane.setConstraints(m2P2, 0, 12);
		GridPane.setConstraints(m3P1, 0, 16);
		GridPane.setConstraints(m3P2, 0, 20);
		GridPane.setConstraints(m4P1, 0, 24);
		GridPane.setConstraints(m4P2, 0, 28);
		
		GridPane.setConstraints(l1P1, 1, 0);
		GridPane.setConstraints(l1P2, 1, 4);
		GridPane.setConstraints(l2P1, 1, 8);
		GridPane.setConstraints(l2P2, 1, 12);
		GridPane.setConstraints(l3P1, 1, 16);
		GridPane.setConstraints(l3P2, 1, 20);
		GridPane.setConstraints(l4P1, 1, 24);
		GridPane.setConstraints(l4P2, 1, 28);
		
		
		//round2
		GridPane.setConstraints(m5P1, 2, 2);
		GridPane.setConstraints(m5P2, 2, 10);
		GridPane.setConstraints(m6P1, 2, 18);
		GridPane.setConstraints(m6P2, 2, 26);
		
		GridPane.setConstraints(l5P1, 3, 2);
		GridPane.setConstraints(l5P2, 3, 10);
		GridPane.setConstraints(l6P1, 3, 18);
		GridPane.setConstraints(l6P2, 3, 26);
		
		//round3
		GridPane.setConstraints(m7P1, 4, 6);
		GridPane.setConstraints(m7P2, 4, 22);
		
		GridPane.setConstraints(l7P1, 5, 6);
		GridPane.setConstraints(l7P2, 5, 22);
		
		//winner
		GridPane.setConstraints(m8P1, 6, 14);
		
		//close & update button
		GridPane.setConstraints(closeButton, 0, 30);
		GridPane.setConstraints(updateButton, 2, 30);
		
		grid.getChildren().addAll(
			
				 m1P1, m1P2, m2P1, m2P2, m3P1, m3P2, m4P1, m4P2, 
				 m5P1, m5P2, m6P1, m6P2, m7P1, m7P2, m8P1, closeButton, updateButton,
				 l1P1, l1P2, l2P1, l2P2, l3P1, l3P2, l4P1, l4P2,
				l5P1, l5P2, l6P1, l6P2, l7P1, l7P2
			
		);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private static void updateMatches() {
		
		MatchBusiness mb = new MatchBL();
		Vector<Match> matchList = new Vector<Match>();
		
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
				updateWinnerPlayerTF(m8P1, matchList.get(6).getPlayerOne());
			
			if(matchList.get(6).getPlayerTwoGames() == 3)
				updateWinnerPlayerTF(m8P1, matchList.get(6).getPlayerTwo());
					
		}
		
		updateMatchPlayerTF(m1P1, m1P2, matchList.get(0));
		updateMatchPlayerTF(m2P1, m2P2, matchList.get(1));
		updateMatchPlayerTF(m3P1, m3P2, matchList.get(2));
		updateMatchPlayerTF(m4P1, m4P2, matchList.get(3));
		
		updateMatchPlayerTF(m5P1, m5P2, matchList.get(4));
		updateMatchPlayerTF(m6P1, m6P2, matchList.get(5));
		updateMatchPlayerTF(m7P1, m7P2, matchList.get(6));
		
		updateScoreLabel(l1P1, l1P2, matchList.get(0));
		updateScoreLabel(l2P1, l2P2, matchList.get(1));
		updateScoreLabel(l3P1, l3P2, matchList.get(2));
		updateScoreLabel(l4P1, l4P2, matchList.get(3));
		
		updateScoreLabel(l5P1, l5P2, matchList.get(4));
		updateScoreLabel(l6P1, l6P2, matchList.get(5));
		updateScoreLabel(l7P1, l7P2, matchList.get(6));
		
	}
	
	private static void updateScoreLabel(Label p1, Label p2, Match m) {
		
		MatchBusiness mb = new MatchBL();
		p1.setText(mb.findScoreForPlayer(m, 1));
		p2.setText(mb.findScoreForPlayer(m, 2));
		
	}

	private static void updateMatchPlayerTF(TextField p1, TextField p2, Match m){
		
		MatchBusiness mb = new MatchBL();
		p1.setText(mb.findEmailForPlayer(m, 1));
		p2.setText(mb.findEmailForPlayer(m, 2));
		
	}
	
	private static void updateWinnerPlayerTF(TextField p1, int pid){
		
		UserBusiness ub = new UserBL();
		User u = ub.findUserByID(pid);
		p1.setText(u.getEmail());
		
	}
	
	private static TextField createEmptyMatchPlayerTF()	{
		
		MatchBusiness mbl = new MatchBL();
		
		TextField tf = new TextField();
		tf.setEditable(false);
		tf.setOnMouseClicked(e -> {
		   
			e.consume();
			if(e.getButton().equals(MouseButton.PRIMARY))
				if(e.getClickCount() == 2) {
		    	   
					String email = tf.getText();
					UserBusiness ubl = new UserBL();
					
					if(email.equals(loggedU.getEmail()) || loggedU.getIsAdmin() == 1) {
						
						if(ubl.checkEmailFormat(email)){
							
							Match currentMatch = mbl.getPlayerCurrentMatch(email);
							viewMatchGames(currentMatch);
							
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
	
	private static void viewMatchGames(Match m){
		
		GameView.display(m);
		
	}
	

}
