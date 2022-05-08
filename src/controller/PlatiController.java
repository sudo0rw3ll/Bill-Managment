package controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Base;
import model.Racun;
import utils.DBConnection;
import view.MainStage;

public class PlatiController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		Racun racun = MainStage.getInstance().getTblRacuni().getSelectionModel().getSelectedItem();
		
		Task<Void> platiTask = plati(racun);
		
		platiTask.setOnSucceeded((WorkerStateEvent e1) -> {
			DBConnection.fetchData();
			MainStage.getInstance().getTblRacuni().setItems(FXCollections.observableArrayList(Base.getInstance().getRacuni()));
		});
		
		Thread thread = new Thread(platiTask);
		thread.setDaemon(true);
		thread.start();
	}

	private Task<Void> plati(Racun racun){
		Task<Void> platiTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				DBConnection.platiRacun(racun);
				MainStage.getInstance().getTblRacuni().getItems().clear();
				return null;
			}
		};
		return platiTask;
	}
	
}
