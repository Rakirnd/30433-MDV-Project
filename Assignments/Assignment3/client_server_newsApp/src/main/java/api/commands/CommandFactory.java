	package api.commands;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommandFactory {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static final String fileTarget = "target/json_command.json";
	
	public static Object getCommand(String command) {
		try {
			switch (command) {
			case "login":{
				
				LoginCommand lc = mapper.readValue(new File(fileTarget), LoginCommand.class);
				return lc;
				
			}
				
			default:
				return null;

			}
		} catch (ArrayIndexOutOfBoundsException arrE) {
			
			return null;
			
		} catch (JsonParseException e) {

			e.printStackTrace();
			return null;
			
		} catch (JsonMappingException e) {

			e.printStackTrace();
			return null;
			
		} catch (IOException e) {

			e.printStackTrace();
			return null;
			
		}

	}

}