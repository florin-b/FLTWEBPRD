package beans;

public class Borderou {

	private String cod;
	private String dataEmitere;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDataEmitere() {
		return dataEmitere;
	}

	public void setDataEmitere(String dataEmitere) {
		this.dataEmitere = dataEmitere;
	}

	@Override
	public String toString() {
		return "Borderou [cod=" + cod + ", dataEmitere=" + dataEmitere + "]";
	}

}
