package server.connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import api.model.AccountType;
import api.model.User;
import server.data.Archive;

public class MainServer extends Thread {
	
	public static final int portNumber = 9990;
	public static List<ServerToClientConnection> connections;
	
	public static List<User> loggedUsers;
	public static List<User> userDatabase;
	public Archive articleArchive;
	
	public MainServer() {
		
		connections = new ArrayList<ServerToClientConnection>();
		loggedUsers = new ArrayList<User>();
		userDatabase = new ArrayList<User>();
		articleArchive = new Archive();
		
	}

	public void run() {
		
		int count = 0;
		setupUserDatabase();
		articleArchive.setupDatabase();
		System.out.println("Starting server at port: " + portNumber);
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Server Started!");

			while (true) {
				
				Socket client = serverSocket.accept();
				ServerToClientConnection thread = new ServerToClientConnection(client, ++count);
				connections.add(thread);
				thread.start();	
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			if (serverSocket != null)
				try {
					
					serverSocket.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
		}

	}

	public static List<ServerToClientConnection> getConnections() {
		
		return connections;
		
	}
	
	private static void setupUserDatabase() {
		
		User admin = new User("admin", "admin", AccountType.ADMIN);
		User writerOne = new User("dan", "mdv", AccountType.WRITER);
		userDatabase.add(admin);
		userDatabase.add(writerOne);
		
	}

}
