package model;

import java.util.Date;

public class Racun {
	
	private TipRacuna tip;
	private Status status;
	private int iznos;
	private Date datum;
	
	
	public Racun(TipRacuna tip, Status status, int iznos, Date datum) {
		super();
		this.tip = tip;
		this.status = status;
		this.iznos = iznos;
		this.datum = datum;
	}


	public TipRacuna getTip() {
		return tip;
	}


	public void setTip(TipRacuna tip) {
		this.tip = tip;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public int getIznos() {
		return iznos;
	}


	public void setIznos(int iznos) {
		this.iznos = iznos;
	}


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Racun) {
			Racun racun = (Racun) obj;
			return racun.tip.equals(this.tip) && racun.status.equals(this.status) && racun.datum.equals(this.datum);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return tip + " " + iznos + " din." + " " + status + " " + datum.toString(); 
	}
	
}
