package api.commands;

import api.model.Article;
import server.data.Archive;

public class PublishCommand {
	
	private String articleTitle;
	private String articleAuthor;
	private String articleAbstract;
	private String articleBody;
	
	public PublishCommand() {
		
		articleTitle = null;
		articleAuthor = null;	
		articleAbstract = null;
		articleBody = null;
		
	}
	
	public PublishCommand(String title, String author, String abstr, String body) {
		
		this.articleTitle = title;
		this.articleAuthor = author;
		this.articleAbstract = abstr;
		this.articleBody = body;
		
	}
	
	public Object execute() {
		
		System.out.println("Publish command received!");
		
		Article a = new Article();
		a.setId(Archive.availableID++);
		a.setTitle(articleTitle);
		a.setAuthor(articleAuthor);
		a.setArticleAbstract(articleAbstract);
		a.setBody(articleBody);
		a.setRelatedArticles(null);
		
		return a;
		
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public String getArticleBody() {
		return articleBody;
	}

	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}

}
