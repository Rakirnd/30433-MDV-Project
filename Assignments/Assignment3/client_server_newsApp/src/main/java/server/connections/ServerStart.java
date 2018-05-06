package server.connections;

public class ServerStart {
	
	private static MainServer server;

	public static MainServer getServer(){
		
		return server;
		
	}
	
	public static void main(String[] args) {
		
		server = new MainServer();
		server.start();
		
	}

}
