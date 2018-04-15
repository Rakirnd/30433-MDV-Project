package controller;

import org.hibernate.SessionFactory;

import javafx.stage.Stage;
import util.HibernateUtil;
import view.ConfirmBox;

public class UtilityController {
	
	public static void closeProgram(Stage window){
		
		boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to quit?");
		
		if(answer) {
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			sessionFactory.close();
			window.close();
			
		}
			
		
	}

}
