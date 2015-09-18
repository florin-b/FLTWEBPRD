package beans;

import java.io.Serializable;

public class Filiala implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cod;
	private String nume;

	public Filiala() {

	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	
	public String toString() {
		return "Filiala [cod=" + cod + ", nume=" + nume + "]";
	}

}
