package client;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observer;

public class ServerListener extends Thread{
	
	private Socket socket;
	private Observer client;
	
	public ServerListener (Socket connection, Observer o) {
		
		socket = connection;
		client = o;
		
	}
	
	@Override
	public void run() {

        try {
	        
        	String received = null;
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	
        	while(true) {
        		
        		received = null;			
				received = (String) in.readObject();
				
				if(received != null) {
					
					System.out.println(received);
					client.update(null, received);
					
					return;
					
				}
        		
        	}
	        
		} catch (Exception e) {
			
			System.out.println("Listener connection closed");
			
		}
        
	}

}
