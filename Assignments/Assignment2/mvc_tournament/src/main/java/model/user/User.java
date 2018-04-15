package model.user;

public class User{
	
	private int id;
	private String email; //used to login inside the application, PK in the User database table
	private String password;
	private int isAdmin; //0 for false, 1 for true
	
	//private UserData personalData;
	
	public User(){
		
		id = 0;
		email = null;
		password = null;
		
		isAdmin = 0;
		
	}
	
	public User(String email, String pass){
		
		id = 0;
		this.email = email;
		this.password = pass;
		//personalData = data;
		
		isAdmin = 0;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	

}
