package ui;

import api.model.Article;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ArticleReadView {
	
private static Stage window;
	
	public static void display(Article a){
		
		window = new Stage();	
		window.setTitle("Read Article");
		window.initModality(Modality.APPLICATION_MODAL);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label title = new Label("Title: ");
		GridPane.setConstraints(title, 0, 0);
		
		TextField titleInput = new TextField();
		titleInput.setEditable(false);
		titleInput.setText(a.getTitle());
		GridPane.setConstraints(titleInput, 1, 0);
		
		Label author = new Label("Author: ");
		GridPane.setConstraints(author, 0, 1);
		
		TextField authorInput = new TextField();
		authorInput.setEditable(false);
		authorInput.setText(a.getAuthor());
		GridPane.setConstraints(authorInput, 1, 1);
		
		Label articleAbstract = new Label("Abstract: ");
		GridPane.setConstraints(articleAbstract, 0, 2);
		
		TextArea articleAbstractInput = new TextArea();
		articleAbstractInput.setEditable(false);
		articleAbstractInput.setText(a.getArticleAbstract());
		GridPane.setConstraints(articleAbstractInput, 1, 2, 3, 5);
		
		Label articleBody = new Label("Body: ");
		GridPane.setConstraints(articleBody, 0, 7);
		
		TextArea articleBodyInput = new TextArea();
		articleBodyInput.setEditable(false);
		articleBodyInput.setText(a.getBody());
		GridPane.setConstraints(articleBodyInput, 1, 7, 3, 10);
		
		grid.getChildren().addAll(title, titleInput, author, authorInput, articleAbstract, articleAbstractInput, articleBody, articleBodyInput);
		
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

}
