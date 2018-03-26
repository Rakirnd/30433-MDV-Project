package ui;

import javafx.stage.*;
import match.Match;
import match.MatchBL;
import tournament.Tournament;
import tournament.TournamentPlayer;
import user.User;
import user.UserBL;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.List;
import java.util.Vector;

import javafx.geometry.*;

public class MatchDisplay
{
	
	private static User loggedInUser;
	
	@SuppressWarnings("restriction")
	public static void display(Tournament t, Vector<TournamentPlayer> tp, User loggedUser)
	{
		
		Stage window = new Stage();
		loggedInUser = loggedUser;
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Matches");
		window.setMinWidth(250);
		
		//round 1
		TextField m1P1 = createEmptyMatchPlayerTF();
		TextField m1P2 = createEmptyMatchPlayerTF();
		TextField m2P1 = createEmptyMatchPlayerTF();
		TextField m2P2 = createEmptyMatchPlayerTF();
		TextField m3P1 = createEmptyMatchPlayerTF();
		TextField m3P2 = createEmptyMatchPlayerTF();
		TextField m4P1 = createEmptyMatchPlayerTF();
		TextField m4P2 = createEmptyMatchPlayerTF();
				
		Vector<Match> matchList = MatchBL.findAllMatchesByTournament(t);
		Vector<Integer> participantIDs = new Vector<Integer>();
		
		for(int i = 0; i < tp.size(); i++)
			participantIDs.add(tp.get(i).getPlayerID());
		
		if(matchList.size() == 0)
		{
			
			//create match 1
			Match m1 = new Match(participantIDs.get(0).intValue(), participantIDs.get(1).intValue());
			m1.setTournID(t.getId());
			MatchBL.insertMatch(m1);
			matchList.add(m1);
			
			updateMatchPlayerTF(m1P1, m1, 1);
			updateMatchPlayerTF(m1P2, m1, 2);
			
			//create match 2
			Match m2 = new Match(participantIDs.get(2).intValue(), participantIDs.get(3).intValue());
			m2.setTournID(t.getId());
			MatchBL.insertMatch(m2);
			matchList.add(m2);
			
			updateMatchPlayerTF(m2P1, m2, 1);
			updateMatchPlayerTF(m2P2, m2, 2);
			
			//create match 3
			Match m3 = new Match(participantIDs.get(4).intValue(), participantIDs.get(5).intValue());
			m3.setTournID(t.getId());
			MatchBL.insertMatch(m3);
			matchList.add(m3);
			
			updateMatchPlayerTF(m3P1, m3, 1);
			updateMatchPlayerTF(m3P2, m3, 2);
			
			//create match 4
			Match m4 = new Match(participantIDs.get(6).intValue(), participantIDs.get(7).intValue());
			m4.setTournID(t.getId());
			MatchBL.insertMatch(m4);
			matchList.add(m4);
			
			updateMatchPlayerTF(m4P1, m4, 1);
			updateMatchPlayerTF(m4P2, m4, 2);
			
		}
		else
		{
			
			updateMatchPlayerTF(m1P1, matchList.get(0), 1);
			updateMatchPlayerTF(m1P2, matchList.get(0), 2);
			updateMatchPlayerTF(m2P1, matchList.get(1), 1);
			updateMatchPlayerTF(m2P2, matchList.get(1), 2);
			updateMatchPlayerTF(m3P1, matchList.get(2), 1);
			updateMatchPlayerTF(m3P2, matchList.get(2), 2);
			updateMatchPlayerTF(m4P1, matchList.get(3), 1);
			updateMatchPlayerTF(m4P2, matchList.get(3), 2);
			
		}
		
		//round 2
		TextField m5P1 = createEmptyMatchPlayerTF();
		TextField m5P2 = createEmptyMatchPlayerTF();
		TextField m6P1 = createEmptyMatchPlayerTF();
		TextField m6P2 = createEmptyMatchPlayerTF();
				
		//round 3
		TextField m7P1 = createEmptyMatchPlayerTF();
		TextField m7P2 = createEmptyMatchPlayerTF();
				
		//round 4
		TextField m8P1 = createEmptyMatchPlayerTF();
		
		//increase score button
		Button increaseScore = new Button("Increase Own Score");
		
		if(checkIfParticipantIsLogged(participantIDs))
			increaseScore.setVisible(true);
		else
			increaseScore.setVisible(false);
		
		increaseScore.setOnAction(e -> {
			
			Match loggedUserMatch = findUserMatch(matchList);
			
			if(loggedUserMatch.getPlayerOneGames() < 3 && loggedUserMatch.getPlayerTwoGames() < 3)
			{
				
				if(loggedUserMatch.getPlayerOne() == loggedInUser.getID())
					loggedUserMatch.setPlayerOneGames(loggedUserMatch.getPlayerOneGames() + 1);
				
				if(loggedUserMatch.getPlayerTwo() == loggedInUser.getID())
					loggedUserMatch.setPlayerTwoGames(loggedUserMatch.getPlayerTwoGames() + 1);
				
				MatchBL.updateMatch(loggedUserMatch.getId(), loggedUserMatch);
				
			}
			else
			{
				
				//update next textfield with winner
				//if both textfields on the next column are filled, create new match and add it to database
				
			}
			
			
			
		});
		
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
		
		
		//round2
		GridPane.setConstraints(m5P1, 1, 2);
		GridPane.setConstraints(m5P2, 1, 10);
		GridPane.setConstraints(m6P1, 1, 18);
		GridPane.setConstraints(m6P2, 1, 26);
		
		//round3
		GridPane.setConstraints(m7P1, 2, 6);
		GridPane.setConstraints(m7P2, 2, 22);
		
		//winner
		GridPane.setConstraints(m8P1, 3, 14);
		
		//close button
		GridPane.setConstraints(closeButton, 0, 30);
		
		//increase score button
		GridPane.setConstraints(increaseScore, 1, 30);
		
		grid.getChildren().addAll(
			
				 m1P1, m1P2, m2P1, m2P2, m3P1, m3P2, m4P1, m4P2, 
				 m5P1, m5P2, m6P1, m6P2, m7P1, m7P2, m8P1, closeButton, increaseScore
			
		);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private static void updateMatchPlayerTF(TextField tf, Match m, int playerNumber)
	{
		
		if(playerNumber == 1)
		{
			
			User p1 = UserBL.findUserByID(m.getPlayerOne());
			tf.setText(p1.getEmail());
			
		}
		if(playerNumber == 2)
		{
			
			User p2 = UserBL.findUserByID(m.getPlayerTwo());
			tf.setText(p2.getEmail());
			
		}
		
	}
	
	private static TextField createMatchPlayerTF(Vector<Match> matchList, int matchNumber, int playerNumber)
	{
		
		TextField tf = new TextField();
		
		if(playerNumber == 1)
		{
			
			User p1 = UserBL.findUserByID(matchList.get(matchNumber).getPlayerOne());
			tf.setText(p1.getEmail());
			
		}
		if(playerNumber == 2)
		{
			
			User p2 = UserBL.findUserByID(matchList.get(matchNumber).getPlayerTwo());
			tf.setText(p2.getEmail());
			
		}
		
		tf.setEditable(false);
		tf.setOnMouseClicked(null);
		
		return tf;
		
	}
	
	private static TextField createEmptyMatchPlayerTF()
	{
		
		TextField tf = new TextField();
		tf.setEditable(false);
		tf.setOnMouseClicked(null);
		
		return tf;
		
	}
	
	private static boolean checkIfParticipantIsLogged(Vector<Integer> participantIDs)
	{
		
		for(int i = 0; i < participantIDs.size(); i++)
			if(participantIDs.get(i).intValue() == loggedInUser.getID())
				return true;
		
		return false;
		
	}
	
	private static Match findUserMatch(Vector<Match> ml)
	{
		
		for(int i = 0; i < ml.size(); i++)
		{
			
			Match m = ml.get(i);
			
			if(m.getPlayerOne() == loggedInUser.getID() || m.getPlayerTwo() == loggedInUser.getID())
				return m;
			
		}
		
		return null;
		
	}
	
	@SuppressWarnings("restriction")
	private static void viewMatchGames(Match m)
	{
		
		
		
	}
	

}
