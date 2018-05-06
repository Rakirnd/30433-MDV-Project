package ui;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.commands.PublishCommand;
import client.ArticleClient;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ArticleWritingView {
	
	private static Stage window;
	
	public static void display(ArticleClient clientConnection){
		
		window = new Stage();	
		window.setTitle("Write Article");
		window.initModality(Modality.APPLICATION_MODAL);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label title = new Label("Title: ");
		GridPane.setConstraints(title, 0, 0);
		
		TextField titleInput = new TextField();
		GridPane.setConstraints(titleInput, 1, 0);
		
		Label author = new Label("Author: ");
		GridPane.setConstraints(author, 0, 1);
		
		TextField authorInput = new TextField();
		GridPane.setConstraints(authorInput, 1, 1);
		
		Label articleAbstract = new Label("Abstract: ");
		GridPane.setConstraints(articleAbstract, 0, 2);
		
		TextArea articleAbstractInput = new TextArea();
		GridPane.setConstraints(articleAbstractInput, 1, 2, 3, 5);
		
		Label articleBody = new Label("Body: ");
		GridPane.setConstraints(articleBody, 0, 7);
		
		TextArea articleBodyInput = new TextArea();
		GridPane.setConstraints(articleBodyInput, 1, 7, 3, 10);
		
		Button writeArticleButton = new Button("Publish");
		GridPane.setConstraints(writeArticleButton, 0, 18);
		
		writeArticleButton.setOnAction(e -> publishArticle(clientConnection, titleInput, authorInput, articleAbstractInput, articleBodyInput));
		grid.getChildren().addAll(title, titleInput, writeArticleButton, author, authorInput, articleAbstract, articleAbstractInput, articleBody, articleBodyInput);
		
		Scene scene = new Scene(grid);
		
		window.setOnCloseRequest(e -> {
			
			e.consume();
			boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
			
			if(answer) {
				
				window.close();
				
			}
			
		});
		
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private static void publishArticle(ArticleClient clientConnection, TextField titleInput, TextField authorInput, TextArea articleAbstractInput, TextArea articleBodyInput) {
		
		String title = titleInput.getText();
		String author = authorInput.getText();
		String abstr = articleAbstractInput.getText();
		String body = articleBodyInput.getText();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fileTarget = "target/json_command.json";
		
		PublishCommand pc = new PublishCommand(title, author, abstr, body);
		
		try {
			
			objectMapper.writeValue(new File(fileTarget), pc);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			clientConnection.send("publish");
			
		} catch (Exception e) {
			
			System.out.println(e + " at Publish");
			
		}
		
		
		
	}

}
