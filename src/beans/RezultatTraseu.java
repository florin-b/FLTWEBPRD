package beans;

public class RezultatTraseu {

	private String codClient;
	private String numeClient;
	private PozitieGps sosire;
	private PozitieGps plecare;
	private int distantaCamion;

	public String getCodClient() {
		return codClient;
	}

	public void setCodClient(String codClient) {
		this.codClient = codClient;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public PozitieGps getSosire() {

		return sosire;
	}

	public void setSosire(PozitieGps sosire) {
		this.sosire = sosire;
	}

	public PozitieGps getPlecare() {

		return plecare;
	}

	public void setPlecare(PozitieGps plecare) {
		this.plecare = plecare;
	}

	public int getDistantaCamion() {
		return distantaCamion;
	}

	public void setDistantaCamion(int distantaCamion) {
		this.distantaCamion = distantaCamion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codClient == null) ? 0 : codClient.hashCode());
		result = prime * result + ((sosire == null) ? 0 : sosire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RezultatTraseu other = (RezultatTraseu) obj;
		if (codClient == null) {
			if (other.codClient != null)
				return false;
		} else if (!codClient.equals(other.codClient))
			return false;
		if (sosire == null) {
			if (other.sosire != null)
				return false;
		} else if (!sosire.equals(other.sosire))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RezultatTraseu [codClient=" + codClient + ", numeClient=" + numeClient + ", sosire=" + sosire + ", plecare=" + plecare + ", distantaCamion="
				+ distantaCamion + "]";
	}

}
