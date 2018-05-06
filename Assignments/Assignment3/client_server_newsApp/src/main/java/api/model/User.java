package api.model;

public class User {
	
	private String username;
	private String password;
	private AccountType accType;
	
	public User() {
		
	}
	
	public User(String name, String pass, AccountType type) {
		
		this.username = name;
		this.password = pass;
		this.accType = AccountType.ADMIN;
		
	}
	
	public AccountType getAccType() {
		return accType;
	}
	public void setAccType(AccountType accType) {
		this.accType = accType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}


