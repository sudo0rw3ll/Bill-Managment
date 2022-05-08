package model;

import java.util.ArrayList;
import java.util.List;

public class Base {
	private static Base instance = null;
	
	private List<Racun> racuni;
	
	private Base() {
		racuni = new ArrayList<Racun>();
	}
	
	public boolean dodajRacun(Racun racun) {
		if(!racuni.contains(racun)) {
			racuni.add(racun);
			return true;
		}
		return false;
	}
	
	public static Base getInstance() {
		if(instance == null) {
			instance = new Base();
		}
		return instance;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}
	
	
}
