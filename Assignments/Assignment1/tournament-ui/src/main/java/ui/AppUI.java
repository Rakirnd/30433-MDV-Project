package ui;

import javafx.stage.*;
import match.Match;
import match.MatchBL;
import tournament.Tournament;
import tournament.TournamentBL;
import tournament.TournamentPlayer;
import tournament.TournamentPlayerBL;
import user.User;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.Vector;

import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppUI {
	
	private static Stage window;
	private static Scene appScene, tournamentScene;
	private static User loggedInUser;
	
	@SuppressWarnings("restriction")
	public static void display(User loggedUser)
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
		
		/*Button logOut = new Button("Log Out");
		logOut.setOnAction(e -> {
			
			e.consume();
			window.close();
			startLoginScreen();
			
		});*/
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			closeProgram();
			
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(welcome, 1, 0);
		GridPane.setConstraints(question, 1, 1);
		
		GridPane.setConstraints(tournamentButton, 1, 5);
		//GridPane.setConstraints(logOut, 0, 5);
		
		grid.getChildren().addAll(welcome, question, tournamentButton);
		
		appScene = new Scene(grid);
		window.setScene(appScene);
		window.show();
		
	}
	
	private static void closeProgram()
	{
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer)
			window.close();
		
	}
	
	//changes to the Ongoing Tournaments scene
	@SuppressWarnings("restriction")
	private static void changeToTournaments()
	{
		
		//tournament table
		TableView<Tournament> tournamentTable;
		
		//table columns
		//id
		TableColumn<Tournament, Integer> idColumn = new TableColumn<> ("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//name
		TableColumn<Tournament, String> nameColumn = new TableColumn<> ("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				
		//date
		TableColumn<Tournament, String> dateColumn = new TableColumn<> ("Date");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("tournamentDate"));
				
		//place
		TableColumn<Tournament, String> placeColumn = new TableColumn<> ("Place");
		placeColumn.setMinWidth(200);
		placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
				
		//pool
		TableColumn<Tournament, Integer> prizeColumn = new TableColumn<> ("Prize");
		prizeColumn.setMinWidth(100);
		prizeColumn.setCellValueFactory(new PropertyValueFactory<>("prizePool"));
				
		
		tournamentTable = new TableView<>();
		tournamentTable.setItems(TournamentBL.getAllTournaments());
		tournamentTable.getColumns().addAll(idColumn, nameColumn, dateColumn, placeColumn, prizeColumn);
		
		Button viewTournament = new Button("View Tournament");
		viewTournament.setOnAction(e -> {
			
			Tournament t;
			
			if(tournamentTable.getSelectionModel().getSelectedItem() != null)			
			{	
				
				t = tournamentTable.getSelectionModel().getSelectedItem();
				viewTournamentMatches(t);
				
			}
			
		});
		
		//return to main app window
		Button backButton = new Button("Back");
		backButton.setOnAction(e -> window.setScene(appScene));
		
		//enroll button
		Button enrollButton = new Button("Enroll");
		enrollButton.setOnAction(e -> {
			
			Tournament t;
			
			if(tournamentTable.getSelectionModel().getSelectedItem() != null)			
			{	
				
				t = tournamentTable.getSelectionModel().getSelectedItem();
				enrolmentScript(t);
				
			}
			
			
			
		});
		
		//create tournament button
		Button createTournament = new Button("Create Tournament");
		createTournament.setOnAction(e -> {
			
			e.consume();
			createTournament();
			
		});
		
		if(loggedInUser.getIsAdmin() == 1)
			createTournament.setVisible(true);
		else
			createTournament.setVisible(false);
		
		GridPane grid = new GridPane();
		GridPane.setConstraints(tournamentTable, 0, 0);
		GridPane.setConstraints(viewTournament, 0, 2);
		GridPane.setConstraints(enrollButton, 0, 4);
		GridPane.setConstraints(backButton, 0, 6);
		GridPane.setConstraints(createTournament, 0, 8);
		
		grid.getChildren().addAll(tournamentTable, backButton, enrollButton, createTournament, viewTournament);
		
		tournamentScene = new Scene(grid);
		window.setScene(tournamentScene);
	
		
	}
	
	private static void createTournament()
	{
		
		TournamentCreation.display();
		
	}
	
	private static void viewTournamentMatches(Tournament t)
	{
		
		Vector<TournamentPlayer> tp = TournamentBL.getPlayersFromTournament(t);
		
		if(tp.size() < 8)
		{
			
			AlertBox.display("Failed!", "The tournament is not open yet!");
			return;
			
		}
		
		MatchDisplay.display(t, tp, loggedInUser);
		
	}
	
	private static void enrolmentScript(Tournament t)
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
		
		
	}

}
