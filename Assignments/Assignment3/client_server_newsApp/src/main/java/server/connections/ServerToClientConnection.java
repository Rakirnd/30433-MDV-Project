package server.connections;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.commands.LoginCommand;
import api.commands.PublishCommand;
import api.model.Article;

public class ServerToClientConnection extends Thread {
	
	private Socket connection;
	private int ID;
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static final String fileTarget = "target/json_command.json";
	private static final String dataTarget = "target/json_data.json";

	public ServerToClientConnection(Socket connection, int iD) {
		
		this.connection = connection;
		ID = iD;
		
		System.out.println("Client " + ID + " connected!");
		
	}

	@Override
	public void run() {
		try {
			
			inStream = new ObjectInputStream(connection.getInputStream());
			outStream = new ObjectOutputStream(connection.getOutputStream());
			
			while (true) {
				
				String received = null;			
				received = (String) inStream.readObject();
					
				if(received != null) {
					
					try {
						
						switch (received) {
						
							case "login":{
							
								LoginCommand lc = mapper.readValue(new File(fileTarget), LoginCommand.class);
								String loginStatus = (String) lc.execute();
								
								switch(loginStatus) {
								
									case "fail":{
										
										outStream.writeObject("fail");
										break;
										
									}
									case "ADMIN":{
										
										outStream.writeObject("ADMIN");
										break;
										
									}
										
									case "READER":{
										
										outStream.writeObject("READER");
										break;
										
									}
										
									case "WRITER":{
										
										outStream.writeObject("WRITER");
										break;
										
									}
										
									default:{
										
										outStream.writeObject("fail");
										break;
										
									}
									
								}
								
								break;
								
							}
							
							case "publish":{
								
								PublishCommand pc = mapper.readValue(new File(fileTarget), PublishCommand.class);
								Article newArticle = (Article) pc.execute();
								
								ServerStart.getServer().articleArchive.addArticle(newArticle);
								
								//updateAll();
								
								break;
								
							}
							
							case "articles":{
								
								mapper.writeValue(new File(dataTarget), ServerStart.getServer().articleArchive.articleDatabase);
								outStream.writeObject("sent");
								
								break;
								
							}

						}
						
					} catch (Exception e) {
						
						e.printStackTrace();
						
					} 
	        				
				}	

			}
		} catch (Exception e) {
        	
        	System.out.println("Client " + ID + " Disconnected!");
        	
        } finally {
        	
            try {
            	
                connection.close();
                MainServer.getConnections().remove((ServerToClientConnection) this);
                this.interrupt();
                this.finalize();
                
                
            } catch (Throwable e) {}
        }

	}
	
	private static void updateAll() throws Exception{
		
		for(ServerToClientConnection scc: MainServer.getConnections()) {
			
			try {
				
				scc.getOutStream().writeObject("updateAll");
				
			}catch(Exception e) {
				
				System.out.println("Don't fail on me exception!");
				
			}
			
			
		}
		
	}

	public ObjectOutputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(ObjectOutputStream outStream) {
		this.outStream = outStream;
	}

}