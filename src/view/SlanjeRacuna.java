package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.SlanjeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Prilog;
import model.Racun;
import utils.MailUtil;

public class SlanjeRacuna extends Stage{
	
	private ListView<Racun> lvRacuni = new ListView<Racun>();
	private Button btnIzaberi = new Button("Izaberi");
	private Button btnPosalji = new Button("Posalji");

	private HBox botBox = new HBox(10);
	private VBox root = new VBox(10);
	private Scene scene;
	private List<File> racuniSlike;
	private List<Prilog> prilozi;
	
	public SlanjeRacuna() {
		init();
		build();
		addActions();
		racuniSlike = new ArrayList<File>();
		prilozi = new ArrayList<Prilog>();
		
		scene = new Scene(root);
		this.setScene(scene);
		this.setWidth(500);
		this.setHeight(500);
		this.setTitle("[BMS] Slanje racuna");
	}
	
	private void init() {
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		botBox.setPadding(new Insets(10));
	}
	
	private void build() {
		root.getChildren().add(lvRacuni);
		botBox.getChildren().addAll(btnIzaberi,btnPosalji);
		root.getChildren().add(botBox);
	}
	
	private void addActions() {
		btnIzaberi.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				racuniSlike = fileChooser.showOpenMultipleDialog(getOwner());
				List<Racun> racuni = lvRacuni.getItems();
				
				if(racuniSlike.isEmpty() || racuniSlike.size() != racuni.size()) {
					new Alert(AlertType.ERROR,"Izaberite sliku za svaki racun").show();
					return;
				}
	
				for(int i=0;i<racuni.size();i++) {
					String absPath = racuniSlike.get(i).getAbsolutePath();
					String naziv = racuni.get(i).getTip().toString().toLowerCase();
					Prilog p = new Prilog(naziv + "_racun_" + (i+1) ,absPath);
					dodajPrilog(p);
				}
				
				System.out.println(prilozi);
				MailUtil.prilozi = prilozi;
			}
		});
		
		btnPosalji.setOnAction(new SlanjeController());
	}

	private void dodajPrilog(Prilog p) {
		if(!prilozi.contains(p)) {
			prilozi.add(p);
		}
	}
	
	public ListView<Racun> getLvRacuni() {
		return lvRacuni;
	}

	public void setLvRacuni(ListView<Racun> lvRacuni) {
		this.lvRacuni = lvRacuni;
	}
}
