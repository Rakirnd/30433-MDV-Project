package ui;

import bll.TournamentBL;
import bll.TournamentBusiness;
import bll.UserBL;
import bll.UserBusiness;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tournament;

@SuppressWarnings("restriction")
public class TournamentView {
	
	private static Stage window;
	private static String loggedInUser;
	
	@SuppressWarnings("unchecked")
	public static void display(String loggedUser)
	{
		
		window = new Stage();
		loggedInUser = loggedUser;
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Tournament View");
		window.setMinWidth(250);
		
		//tournaments table
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
				
		TournamentBusiness tbl = new TournamentBL();
		tournamentTable = new TableView<>();
		tournamentTable.setItems(tbl.getAllTournaments());
		tournamentTable.getColumns().addAll(idColumn, nameColumn, dateColumn, placeColumn, prizeColumn);
		
		Button viewTournament = new Button("View Tournament");
		viewTournament.setOnAction(e -> {
			
			e.consume();
			Tournament t;
			
			if(tournamentTable.getSelectionModel().getSelectedItem() != null)			
			{	
				
				t = tournamentTable.getSelectionModel().getSelectedItem();
				viewTournamentMatches(t);
				
			}
			
		});
		
		Button createTournament = new Button("Create Tournament");
		createTournament.setOnAction(e -> {
			
			e.consume();
			createTournament();
			
		});
		
		Button enrollButton = new Button("Enroll");
		enrollButton.setOnAction(e -> {
			
			Tournament t;
			
			if(tournamentTable.getSelectionModel().getSelectedItem() != null)			
			{	
				
				t = tournamentTable.getSelectionModel().getSelectedItem();
				enrolmentScript(t);
				
			}
			
		});
		
		UserBusiness ubl = new UserBL();
		boolean isLoggedInUserAdmin = ubl.isUserAdmin(loggedInUser);
		
		if(isLoggedInUserAdmin == true)
			createTournament.setVisible(true);
		else
			createTournament.setVisible(false);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(tournamentTable, 0, 0);
		GridPane.setConstraints(viewTournament, 0, 2);
		GridPane.setConstraints(enrollButton, 0, 4);
		GridPane.setConstraints(createTournament, 0, 8);
		
		grid.getChildren().addAll(tournamentTable, enrollButton, createTournament, viewTournament);
		
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			closeProgram();
			
		});
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private static void closeProgram()	{
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer)
			window.close();
		
	}
	
	private static void createTournament()	{
		
		TournamentCreation.display();
		
	}
	
	private static void viewTournamentMatches(Tournament t) {
		
		TournamentBusiness tb = new TournamentBL();
		boolean openTournament = tb.isTournamentOpen(t);
		
		if(openTournament)
			MatchDisplay.display(t, loggedInUser);
		else
			AlertBox.display("Tournament Not Open", "Tournament is not open yet!");
		
	}
	
	private static void enrolmentScript(Tournament t) {
		
		
		
	}
	

}
