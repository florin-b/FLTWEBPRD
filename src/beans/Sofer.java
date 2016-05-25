package beans;

public class Sofer {

	private String codSofer;
	private String numeSofer;
	private String filiala;

	public String getCodSofer() {
		return codSofer;
	}

	public void setCodSofer(String codSofer) {
		this.codSofer = codSofer;
	}

	public String getNumeSofer() {
		return numeSofer;
	}

	public void setNumeSofer(String numeSofer) {
		this.numeSofer = numeSofer;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	@Override
	public String toString() {
		return "Sofer [codSofer=" + codSofer + ", numeSofer=" + numeSofer + ", filiala=" + filiala + "]";
	}

}
