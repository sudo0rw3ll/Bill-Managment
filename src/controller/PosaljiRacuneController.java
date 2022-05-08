package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Racun;
import model.Status;
import view.MainStage;
import view.SlanjeRacuna;

public class PosaljiRacuneController implements EventHandler<ActionEvent>{

	private MainStage main;
	
	public PosaljiRacuneController(MainStage main) {
		this.main = main;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		SlanjeRacuna slanje = new SlanjeRacuna();
		ObservableList<Racun> racuni = main.getTblRacuni().getSelectionModel().getSelectedItems();
		
		if(racuni.isEmpty()) {
			new Alert(AlertType.ERROR,"Izaberite racune za slanje").show();
			return;
		}
		
		ObservableList<Racun> neplaceni = FXCollections.observableArrayList(new ArrayList<Racun>());
		
		for(Racun racun : racuni) {
			if(racun.getStatus().equals(Status.NEPLACEN)) {
				neplaceni.add(racun);
			}else {
				continue;
			}
		}
		
		slanje.getLvRacuni().setItems(neplaceni);
		slanje.show();
	}

}
