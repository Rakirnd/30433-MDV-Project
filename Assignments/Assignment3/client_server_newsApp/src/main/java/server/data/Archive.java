package server.data;

import java.util.ArrayList;
import api.model.Article;


public class Archive {
	
	public static int availableID = 0;
	public ArrayList<Article> articleDatabase = new ArrayList<Article>();
	//private List<Observer> observerList = new ArrayList<Observer>();
	
	public Archive() {
		
	}

	public void setupDatabase() {
		
		Article a = new Article();
		
		a.setId(availableID++);
		a.setTitle("Dummy article");
		a.setAuthor("MDV");
		a.setArticleAbstract("This is a dummy article.\n It has no abstract!");
		a.setBody("This is the body of a dummy article!");
		a.setRelatedArticles(null);
		
		articleDatabase.add(a);
		
		Article a2 = new Article();
		
		a2.setId(availableID++);
		a2.setTitle("How to set up an article");
		a2.setAuthor("MDV");
		
		a2.setArticleAbstract("The purpose of this article is to show you how to set up an artile. It isn't to be taken seriously though!");
		a2.setBody("1. Press a button;\n2. Press another button;\n3. Press the third button, dummy!");
		a2.setRelatedArticles(null);
		
		articleDatabase.add(a2);
		
	}
	
	public void addArticle(Article a) {
		
		articleDatabase.add(a);
		
	}

	public void printArticleTitlesOnConsole() {
		
		for(Article a: articleDatabase) {
			
			System.out.println(a.getTitle());
			
		}
		
	}

	
}
