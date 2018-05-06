package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ArticleClient {
	
	//private ConnectionThread connThread = new ConnectionThread();
	//private Consumer<Serializable> onReceiveCallback;
	
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	    
	private static final String serverAddress = "127.0.0.1";
	private static final int connectionPort = 9990;
    
    public ArticleClient() {
    	
    	try {
    		
    		connection = new Socket(serverAddress, connectionPort);
	        out = new ObjectOutputStream(connection.getOutputStream());
	        in = new ObjectInputStream(connection.getInputStream());
			
		} catch (IOException e) {
			
			System.out.println("Client Connection Closed");
			
		}
    	
    }
    
    public void send(String data) throws Exception{
    	
    	out.writeObject(data);
    	
    }
    
    public void sendObject(Object data) throws Exception{
    	
    	out.writeObject(data);
    	
    }
    
    public void closeConnection() throws Exception{
    	
    	this.connection.close();
	
    }

	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}
    
    public String waitOnInput() throws Exception{
    	
    	String receivedMsg = null;
    	
    	while(true) {
			
    		receivedMsg = (String) in.readObject();
    		
			if(receivedMsg != null) {
				
				System.out.println("Received (ArticleClient): " + receivedMsg);
				return receivedMsg;
				
			}
    		
    	}
    	
    }

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
    
}
