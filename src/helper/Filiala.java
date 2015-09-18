package helper;

public class Filiala {

	public static String getNumeFiliala(String codFiliala) {
		String numeFiliala = "NN10";

		if (codFiliala.equals("BC10"))
			numeFiliala = "BACAU";

		if (codFiliala.equals("GL10"))
			numeFiliala = "GALATI";

		if (codFiliala.equals("AG10"))
			numeFiliala = "PITESTI";

		if (codFiliala.equals("BU90"))
			numeFiliala = "BUCURESTI CENTRAL";

		if (codFiliala.equals("BZ10"))
			numeFiliala = "BUZAU";

		if (codFiliala.equals("GL90"))
			numeFiliala = "GALATI CENTRAL";

		if (codFiliala.equals("HD10"))
			numeFiliala = "HUNEDOARA";

		if (codFiliala.equals("TM10"))
			numeFiliala = "TIMISOARA";

		if (codFiliala.equals("BH10"))
			numeFiliala = "ORADEA";

		if (codFiliala.equals("VN10"))
			numeFiliala = "FOCSANI";

		if (codFiliala.equals("BU10"))
			numeFiliala = "GLINA";

		if (codFiliala.equals("BU13"))
			numeFiliala = "ANDRONACHE";

		if (codFiliala.equals("BU12"))
			numeFiliala = "OTOPENI";

		if (codFiliala.equals("CJ10"))
			numeFiliala = "CLUJ";

		if (codFiliala.equals("MM10"))
			numeFiliala = "BAIA MARE";

		if (codFiliala.equals("BU11"))
			numeFiliala = "MILITARI";

		if (codFiliala.equals("CT10"))
			numeFiliala = "CONSTANTA";

		if (codFiliala.equals("BV10"))
			numeFiliala = "BRASOV";

		if (codFiliala.equals("PH10"))
			numeFiliala = "PLOIESTI";

		if (codFiliala.equals("NT10"))
			numeFiliala = "PIATRA NEAMT";

		if (codFiliala.equals("MS10"))
			numeFiliala = "MURES";

		if (codFiliala.equals("IS10"))
			numeFiliala = "IASI";

		if (codFiliala.equals("DJ10"))
			numeFiliala = "CRAIOVA";

		return numeFiliala;

	}

}
