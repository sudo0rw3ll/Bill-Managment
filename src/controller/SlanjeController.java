package controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.MailUtil;

public class SlanjeController implements EventHandler<ActionEvent>{
	
	@Override
	public void handle(ActionEvent arg0) {
		Task<Void> sendTask = send();
		
		sendTask.setOnSucceeded((WorkerStateEvent e1)->{
			new Alert(AlertType.INFORMATION,"Racuni su uspesno poslati").show();
		});
		
		Thread thread = new Thread(sendTask);
		thread.setDaemon(true);
		thread.start();
	}
	
	private Task<Void> send(){
		Task<Void> sendTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				MailUtil.send();
				return null;
			}
			
		};
		return sendTask;
	}

}
