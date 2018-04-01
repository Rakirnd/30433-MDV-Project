package model;

public class User
{
	
	private int id;
	private String email; //used to login inside the application, PK in the User database table
	private String password; 
	private int isAdmin; //0 for false, 1 for true
	
	//private UserData personalData;
	
	public User()
	{
		
		id = 0;
		email = null;
		password = null;
		
		isAdmin = 0;
		
	}
	
	public User(String email, String pass)
	{
		
		id = 0;
		this.email = email;
		this.password = pass;
		//personalData = data;
		
		isAdmin = 0;
		
	}
	
	public User(int i, String email, String pass)
	{
		
		id = i;
		this.email = email;
		this.password = pass;
		//personalData = data;
		
		isAdmin = 0;
		
	}
	
	public String getEmail()
	{
		
		return email;
		
	}
	
	public String getPassword()
	{
		
		return password;
		
	}
	
	public int getIsAdmin()
	{
		
		return isAdmin;
		
	}
	
	public int getID()
	{
		
		return id;
		
	}
	
	public void setEmail(String e)
	{
		
		email = e;
		
	}
	
	public void setID(int i)
	{
		
		id = i;
		
	}
	
	public void setPass(String p)
	{
		
		password = p;
		
	}
	
	public void setIsAdmin(int i)
	{
		
		isAdmin = i;
		
	}

}
