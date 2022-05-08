package view;

import java.util.Date;

import controller.PlatiController;
import controller.PosaljiRacuneController;
import controller.PreuzmiController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Racun;
import model.Status;
import model.TipRacuna;

public class MainStage extends Stage{

	private static MainStage instance = null;
	
	//Components
	private VBox root = new VBox(10);
	private Scene scene;
	private HBox topHbox = new HBox(10);
	private Button btnPreuzmi = new Button("Preuzmi podatke");
	
	private TableView<Racun> tblRacuni = new TableView<Racun>();
	private HBox botHbox = new HBox(10);
	private Button btnStatistika = new Button("Statistika");
	private Button btnPlati = new Button("Plati racun");
	private Button btnPosalji = new Button("Posalji racune");
	
	private MainStage() {
		init();
		build();
		buildTable();
		addActions();
	}
	
	private void init() {
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		topHbox.setAlignment(Pos.TOP_LEFT);
		botHbox.setAlignment(Pos.BOTTOM_LEFT);
		tblRacuni.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		scene = new Scene(root);
		this.setScene(scene);
		this.setHeight(500);
		this.setWidth(800);
		this.setTitle("[BMS] Bill Managment System");
		this.show();
	}
	
	private void build() {
		topHbox.getChildren().add(btnPreuzmi);
		botHbox.getChildren().add(btnStatistika);
		botHbox.getChildren().add(btnPlati);
		botHbox.getChildren().add(btnPosalji);
		root.getChildren().add(topHbox);
		root.getChildren().add(tblRacuni);
		root.getChildren().add(botHbox);
	}
	
	@SuppressWarnings("unchecked")
	private void buildTable() {
		TableColumn<Racun, TipRacuna> tcTip = new TableColumn<Racun, TipRacuna>("Tip Racuna");
		TableColumn<Racun, Status> tcStatus = new TableColumn<Racun, Status>("Status");
		TableColumn<Racun, Integer> tcIznos = new TableColumn<Racun, Integer>("Iznos");
		TableColumn<Racun, Date> tcDate = new TableColumn<Racun, Date>("Datum");
		
		tcTip.setCellValueFactory(new PropertyValueFactory<Racun,TipRacuna>("tip"));
		tcStatus.setCellValueFactory(new PropertyValueFactory<Racun,Status>("status"));
		tcIznos.setCellValueFactory(new PropertyValueFactory<Racun,Integer>("iznos"));
		tcDate.setCellValueFactory(new PropertyValueFactory<Racun,Date>("datum"));
		
		tblRacuni.getColumns().addAll(tcTip,tcStatus,tcIznos,tcDate);
	}
	
	private void addActions() {
		btnPreuzmi.setOnAction(new PreuzmiController());
		btnPlati.setOnAction(new PlatiController());
		btnPosalji.setOnAction(new PosaljiRacuneController(this));
	}
	
	public static MainStage getInstance() {
		if(instance == null) {
			instance = new MainStage();
		}
		return instance;
	}

	public TableView<Racun> getTblRacuni() {
		return tblRacuni;
	}

	public void setTblRacuni(TableView<Racun> tblRacuni) {
		this.tblRacuni = tblRacuni;
	}
}
