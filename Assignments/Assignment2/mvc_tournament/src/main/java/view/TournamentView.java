package view;

import javafx.stage.*;
import model.tournament.Tournament;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.TournamentController;
import javafx.geometry.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class TournamentView {
	
	private static Stage window;
	private static String loggedInUser;
	
	@SuppressWarnings("unchecked")
	public static void display(String loggedUser){
		
		window = new Stage();
		loggedInUser = loggedUser;
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Tournament View");
		window.setMinWidth(250);
		
		//tournaments table
		TableView<Tournament> tournamentTable = new TableView<>();
		
		//table columns
		//id
		TableColumn<Tournament, Integer> idColumn = new TableColumn<> ("ID");
		idColumn.setMinWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		//name
		TableColumn<Tournament, String> nameColumn = new TableColumn<> ("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
				
		//type paid/free
		TableColumn<Tournament, String> typeColumn = new TableColumn<> ("Type");
		typeColumn.setMinWidth(50);
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("tournamentType"));
				
		//entry fee
		TableColumn<Tournament, Integer> entryFeeColumn = new TableColumn<> ("Entry Fee");
		entryFeeColumn.setMinWidth(50);
		entryFeeColumn.setCellValueFactory(new PropertyValueFactory<>("entryFee"));
				
		//prize pool
		TableColumn<Tournament, Integer> prizeColumn = new TableColumn<> ("Prize");
		prizeColumn.setMinWidth(50);
		prizeColumn.setCellValueFactory(new PropertyValueFactory<>("prizePool"));
		
		//status 
		TableColumn<Tournament, String> statusColumn = new TableColumn<> ("Status");
		statusColumn.setMinWidth(50);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		//winner
		TableColumn<Tournament, String> winnerColumn = new TableColumn<> ("Winner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winner"));
				
		tournamentTable.getColumns().addAll(idColumn, nameColumn, typeColumn, entryFeeColumn, prizeColumn, statusColumn, winnerColumn);
		
		TournamentController.bindTournamentsToView(tournamentTable);
		
		Button viewTournament = new Button("View Tournament");
		viewTournament.setOnAction(e -> TournamentController.viewSelectedTournament(tournamentTable, loggedInUser));
		
		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> {
			
			TournamentController.checkTournamentStatus();
			TournamentController.bindTournamentsToView(tournamentTable);
			
		});
		
		Button createTournament = new Button("Create Tournament");
		createTournament.setOnAction(e -> TournamentController.createTournament());
		TournamentController.setButtonVisibleForAdmin(createTournament, loggedInUser);
		
		Button enrollButton = new Button("Enroll");
		enrollButton.setOnAction(e -> TournamentController.enrolmentScript(tournamentTable, loggedInUser));
		
		Label search = new Label();
		search.setText("Search: ");
		
		TextField searchInput = new TextField();
		searchInput.setMaxWidth(100);
		searchInput.setOnKeyPressed(e -> {
			
			if(e.getCode() == KeyCode.ENTER)
				TournamentController.searchTournament(tournamentTable, searchInput);
			
		});
		
		Button viewByCategory = new Button("View by Category");
		
		ChoiceBox<String> category = new ChoiceBox<>();
		category.getItems().addAll("Upcoming", "Ongoing", "Finished");
		category.setValue("Upcoming");
		
		ChoiceBox<String> type = new ChoiceBox<>();
		type.getItems().addAll("Free", "Paid");
		type.setValue("Free");
		
		viewByCategory.setOnAction(e -> TournamentController.viewByCategory(tournamentTable, category, type));
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		GridPane.setConstraints(tournamentTable, 0, 0, 7, 1);
		GridPane.setConstraints(viewTournament, 0, 2);
		GridPane.setConstraints(enrollButton, 1, 2);
		GridPane.setConstraints(refresh, 2, 2);
		GridPane.setConstraints(createTournament, 3, 2);
		
		GridPane.setConstraints(search, 0, 3);
		GridPane.setConstraints(searchInput, 1, 3);
		
		GridPane.setConstraints(viewByCategory, 2, 3);
		GridPane.setConstraints(category, 3, 3);
		GridPane.setConstraints(type, 4, 3);
		
		grid.getChildren().addAll(tournamentTable, enrollButton, createTournament, viewTournament, refresh,
									search, searchInput, viewByCategory, category, type);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			window.close();
			
		});
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
