package ui;

import java.io.File;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.model.Article;
import client.ArticleClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WriterView {
	
	private static Stage window;
	private static ListView<String> listView;
	private static WriterView instance;
	
	public static void display(ArticleClient clientConnection){
		
		window = new Stage();	
		window.setTitle("Articles");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		listView = new ListView<>();
		listView.setItems(simplifiedArticleDatabase(requestArticles(clientConnection)));
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		GridPane.setConstraints(listView, 0, 0, 10, 10);
		
		Button openArticleButton = new Button("Read");
		GridPane.setConstraints(openArticleButton, 0, 11);
		
		Button writeArticleButton = new Button("Write");
		GridPane.setConstraints(writeArticleButton, 1, 11);
		
		openArticleButton.setOnAction(e -> openArticle(clientConnection, listView));
		writeArticleButton.setOnAction(e -> writeArticle(clientConnection));
		grid.getChildren().addAll(listView, openArticleButton, writeArticleButton);
		
		Scene scene = new Scene(grid);
		
		/*new Thread() {

            public void run() {
				
	    		try {
	    			
	    			String receivedMsg = clientConnection.waitOnInput();
	
					if(receivedMsg.equals("updateAll")) {
							
						Platform.runLater(new Runnable() {
								
							public void run() {
									
								update(clientConnection);
									
							}

						});
							
					}
								
				} catch (Exception e) {

					System.out.println("Exception at thread: WriterView");
				}
			
            }
        }.start();*/
	    
		window.setOnCloseRequest(e -> {
			
			e.consume();
			boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
			
			if(answer) {
				
				window.close();
				
			}
			
		});
		
		window.setScene(scene);
		window.show();
		
	}
	
	private static void openArticle(ArticleClient clientConnection, ListView<String> simplifiedArticleList) {
			
		Article a = null;
		String selectedArticle = simplifiedArticleList.getSelectionModel().getSelectedItem();
		
		if(selectedArticle != null)			
		{	
			
			System.out.println(selectedArticle);
			
			try {
				
				a = getSelectedArticle(clientConnection, selectedArticle);
				
				if(a != null)
					ArticleReadView.display(a);
					
				
			}catch(Exception e) {
				
				AlertBox.display("Invalid operation", "Can't read article!");
				
			}
			
		}
			
	}
		
	private static Article getSelectedArticle(ArticleClient clientConnection, String selectedItem) {
		
		ArrayList<Article> articleList = requestArticles(clientConnection);
		
		for(Article a: articleList) {
			
			System.out.println(a.getTitle());
			
			if(a.getTitle().equals(selectedItem))
				return a;
			
		}
		
		return null;
		
	}
	
	private static void writeArticle(ArticleClient clientConnection) {
		
		ArticleWritingView.display(clientConnection);
		
	}
	
	public static WriterView getInstance(){
		
		if(instance==null){
			
			instance = new WriterView();
			
		}
		
		return instance;
		
	}
	
	public static ArrayList<Article> requestArticles(ArticleClient clientConnection) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String dataTarget = "target/json_data.json";
		
		ArrayList<Article> artList = new ArrayList<Article>();
		
		try {
			
			clientConnection.send("articles");
			
		} catch (Exception e) {
			
			System.out.println(e + " at Article Request");
			
		}
		
		String dataStatus = null;
		
		while(dataStatus == null) {
			
			try {
				
				dataStatus = clientConnection.waitOnInput();
				
				switch(dataStatus) {
				
				case "sent":{
					
					System.out.println("Data received!");
					artList = objectMapper.readValue(new File(dataTarget), new TypeReference<ArrayList<Article>>() {});
					return artList;
					
				}
				default:{
					
					System.out.println("Data not received!");
					break;
					
				}
					
			}
				
			} catch (Exception e) {
				
				System.out.println("Data not received!");
				e.printStackTrace();
				return null;
				
			}
			
		}
		
		return null;
		
	}
	
	public static void update(ArticleClient clientConnection) {
		
		System.out.println("Update method called!");
		
		ArrayList<Article> articleList = requestArticles(clientConnection);
		
		ObservableList<String> simplifiedArticleList = simplifiedArticleDatabase(articleList);
		listView.setItems(simplifiedArticleList);
		
	}
	
	public static ObservableList<String> simplifiedArticleDatabase(ArrayList<Article> articleList){
		
		ObservableList<String> sdb = FXCollections.observableArrayList();
		
		for(Article a: articleList) {
			
			sdb.add(a.getTitle());
			
		}
		
		return sdb;
		
	}

}
