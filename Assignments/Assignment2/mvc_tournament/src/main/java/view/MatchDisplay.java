package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import controller.MatchController;
import javafx.geometry.*;

public class MatchDisplay{
	
	private static TextField 	m1P1, m1P2, m2P1, m2P2, m3P1, m3P2, m4P1, m4P2, 
								m5P1, m5P2, m6P1, m6P2, m7P1, m7P2, m8P1;
	private static Label 	l1P1, l1P2, l2P1, l2P2, l3P1, l3P2, l4P1, l4P2,
							l5P1, l5P2, l6P1, l6P2, l7P1, l7P2;
	private static String loggedInUser;
	
	public static void display(int tournamentID, String loggedUser){
		
		Stage window = new Stage();
		loggedInUser = loggedUser;
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Matches");
		window.setMinWidth(250);
		
		//round 1
		m1P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m1P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m2P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m2P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m3P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m3P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m4P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m4P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		
		//round 2
		m5P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m5P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m6P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m6P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
				
		//round 3
		m7P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		m7P2 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
				
		//round 4
		m8P1 = MatchController.createEmptyMatchPlayerTF(loggedInUser);
		
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
		
		TextField[] matchFieldCollection = {
				m1P1, m1P2, m2P1, m2P2, m3P1, m3P2, m4P1, m4P2, 
				m5P1, m5P2, m6P1, m6P2, m7P1, m7P2, m8P1
		};
		
		Label[] matchLabelCollection = {
				l1P1, l1P2, l2P1, l2P2, l3P1, l3P2, l4P1, l4P2,
				l5P1, l5P2, l6P1, l6P2, l7P1, l7P2
		};
		
		MatchController.updateMatches(tournamentID, matchFieldCollection, matchLabelCollection);
		
		Button updateButton = new Button("Refresh");
		updateButton.setOnAction(e ->{
			
			MatchController.updateMatches(tournamentID, matchFieldCollection, matchLabelCollection);
			MatchController.updateWinner(tournamentID, m8P1);
			
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
	

}
