package beans;

public class PozitieClient {

	private String codClient;
	private String numeClient;
	private double latitudine;
	private double longitudine;
	private int distantaCamion;

	public PozitieClient() {

	}

	public String getCodClient() {
		return codClient;
	}

	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}

	public double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}

	public double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public int getDistantaCamion() {
		return distantaCamion;
	}

	public void setDistantaCamion(int distantaCamion) {
		this.distantaCamion = distantaCamion;
	}

	@Override
	public String toString() {
		return "PozitieClient [codClient=" + codClient + ", numeClient=" + numeClient + ", latitudine=" + latitudine + ", longitudine=" + longitudine
				+ ", distantaCamion=" + distantaCamion + "]";
	}

}
