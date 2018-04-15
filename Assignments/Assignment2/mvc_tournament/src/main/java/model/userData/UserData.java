package model.userData;

public class UserData {
	
	private int id;
	private int userID;
	
	private String firstName;
	private String lastName;
	
	private int balance;
	
	public UserData() {
		
		this.balance = 0;
		
	}
	
	public UserData(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.balance = 0;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
