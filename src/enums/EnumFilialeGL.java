package enums;

import java.util.ArrayList;
import java.util.List;

public enum EnumFilialeGL {

	GALATI("Galati", "GL10"), GALATI_CENTRAL("Galati central", "GL90");

	private String nume;
	private String cod;

	EnumFilialeGL(String nume, String cod) {
		this.nume = nume;
		this.cod = cod;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public static String getCodFiliala(String numeFiliala) {

		for (EnumFilialeGL enumF : EnumFilialeGL.values()) {
			if (enumF.getNume().equals(numeFiliala))
				return enumF.cod;
		}

		return "";
	}

	public static String getNumeFiliala(String codFiliala) {

		for (EnumFilialeGL enumF : EnumFilialeGL.values()) {
			if (enumF.getCod().equals(codFiliala))
				return enumF.nume;
		}

		return "";
	}

	public static List<String> getFiliale() {
		List<String> listFiliale = new ArrayList<String>();

		listFiliale.add("Selectati filiala");
		for (EnumFilialeGL enumF : EnumFilialeGL.values())
			listFiliale.add(enumF.nume);

		return listFiliale;
	}

	public static int getItemPosition(String codFiliala) {
		int pos = 0;
		for (EnumFilialeGL enumF : EnumFilialeGL.values()) {
			if (enumF.getCod().equals(codFiliala)) {
				return pos;
			}
			pos++;
		}

		return -1;
	}

}
