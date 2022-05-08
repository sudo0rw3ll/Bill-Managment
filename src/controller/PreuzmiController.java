package controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Base;
import utils.DBConnection;
import view.MainStage;

public class PreuzmiController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Task<Void> fetchTask = fetch();
		
		fetchTask.setOnSucceeded((WorkerStateEvent e1) ->{
			MainStage.getInstance().getTblRacuni().setItems(FXCollections.observableArrayList(Base.getInstance().getRacuni()));
		});
		
		fetchTask.setOnFailed((WorkerStateEvent e1) ->{
			new Alert(AlertType.ERROR,"Neuspesno preuzimanje iz baze, proverite konekciju").show();
		});
		
		Thread thread = new Thread(fetchTask);
		thread.setDaemon(true);
		thread.start();
	}
	
	private Task<Void> fetch(){
		Task<Void> fetchTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				DBConnection.fetchData();
				return null;
			}
		};
		return fetchTask;
	}

}
