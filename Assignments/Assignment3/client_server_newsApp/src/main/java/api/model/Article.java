package api.model;

import java.util.List;

public class Article {
	
	private int id;
	private String title;
	private String articleAbstract;
	private String body;
	
	private String author;
	
	private List<Article> relatedArticles;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<Article> getRelatedArticles() {
		return relatedArticles;
	}

	public void setRelatedArticles(List<Article> relatedArticles) {
		this.relatedArticles = relatedArticles;
	}
	

}
