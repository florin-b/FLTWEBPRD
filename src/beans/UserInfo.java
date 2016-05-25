package beans;

public class UserInfo {

	private String nume;
	private String filiala;
	private String tipAcces;

	private static UserInfo instance;

	private UserInfo() {

	}

	public static UserInfo getInstance() {
		if (instance == null)
			instance = new UserInfo();

		return instance;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	public String getTipAcces() {
		return tipAcces;
	}

	public void setTipAcces(String tipAcces) {
		this.tipAcces = tipAcces;
	}

}
