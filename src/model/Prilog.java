package model;

public class Prilog {

	private String naziv;
	private String path;
	
	public Prilog(String naziv, String path) {
		super();
		this.naziv = naziv;
		this.path = path;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Prilog) {
			Prilog prilog = (Prilog) obj;
			return this.path.equals(prilog.path);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return naziv;
 	}
	
}
