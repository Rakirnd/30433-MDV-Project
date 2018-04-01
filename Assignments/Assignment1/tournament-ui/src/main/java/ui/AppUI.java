package ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.geometry.*;

@SuppressWarnings("restriction")
public class AppUI {
	
	private static Stage window;
	private static String loggedInUser;
	
	public static void display(String loggedUser)
	{
		
		window = new Stage();
		loggedInUser = loggedUser;
		
		window.setTitle("Tournament App");
		window.setMinWidth(250);
		
		Label welcome = new Label();
		welcome.setText("Welcome to the Ping-Pong Tournament App!");
		
		Label question = new Label();
		question.setText("What would you like to do?");
		
		Button tournamentButton = new Button("View Tournaments");
		tournamentButton.setOnAction(e -> {
			
			e.consume();
			changeToTournaments();
			
		});
		
		Button viewAccountButton = new Button("View Account");
		viewAccountButton.setOnAction(e -> {
			
			e.consume();
			viewAccount();
			
		});
		
		Button logOut = new Button("Log Out");
		logOut.setOnAction(e -> {
			
			e.consume();
			logOut();
			
		});
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			closeProgram();
			
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(welcome, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(question, 0, 1, 3, 1, HPos.CENTER, VPos.CENTER);
		
		GridPane.setConstraints(tournamentButton, 0, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(viewAccountButton, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		GridPane.setConstraints(logOut, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER);
		
		grid.getChildren().addAll(welcome, question, tournamentButton, viewAccountButton, logOut);
		
		Scene appScene = new Scene(grid);
		window.setScene(appScene);
		window.show();
		
	}
	
	private static void closeProgram()	{
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer)
			window.close();
		
	}
	
	//log out script
	private static void logOut() {
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to log out?");
		
		if(answer) {
			
			window.close();
			Login.display();
			
		}
		
	}
	
	//script for viewing the user account
	private static void viewAccount() {
		
		AccountView.display(loggedInUser);
		
	}
	
	//changes to the Ongoing Tournaments scene
	private static void changeToTournaments(){
		
		TournamentView.display(loggedInUser);
		
	}
	/*private static void enrolmentScript(Tournament t)
	{
		
		//get all enrolled players and check if registrant is already registered
		//start tournament when 8 are enrolled
		Vector<TournamentPlayer> tp = TournamentBL.getPlayersFromTournament(t);
		
		if(tp.size() == 8)
		{
			
			AlertBox.display("Failed!", "The tournament is closed to registration!");
			return;
			
		}
		
		for(int i = 0; i < tp.size(); i++)
		{
			
			TournamentPlayer p = tp.get(i);
			System.out.println(p.getPlayerID());
			
			if(p.getPlayerID() == loggedInUser.getID())
			{
				
				AlertBox.display("Failed!", "You are already enrolled!");
				return;
				
			}
			
		}
		
		TournamentPlayer newPlayer = new TournamentPlayer();
		newPlayer.setPlayerID(loggedInUser.getID());
		newPlayer.setTournamentID(t.getId());
		
		int id = TournamentPlayerBL.registerPlayerTournament(newPlayer);
		newPlayer.setId(id);
		
		tp.add(newPlayer);
		
		if(tp.size() == 8)
		{
			
			AlertBox.display("Open!", "Tournament is now open for play!");
			return;
			
		}
		
		
	}*/

}
