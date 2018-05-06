package api.commands;

import api.model.User;
import server.connections.MainServer;

public class LoginCommand {
	
	private String username;
	private String password;
	
	public LoginCommand() {
		
		username = null;
		password = null;		
		
	}
	
	public LoginCommand(String username, String password) {
		
		this.username = username;
		this.password = password;
		
	}
	
	public Object execute() {
		
		System.out.println("Login command received!");
		
		for(User u: MainServer.userDatabase) {
			
			//System.out.println(username + " " + password);
			
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				
				/*for(User us: MainServer.loggedUsers) {
					
					if(us.getUsername().equals(username) && us.getPassword().equals(password))
						return false;
					
				}*/
				
				//MainServer.loggedUsers.add(u);
				
				return u.getAccType().toString();
				
			}
			
		}
		
		return "fail";
		
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
