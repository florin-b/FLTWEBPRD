package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Filiala;
import beans.Masina;
import enums.EnumFiliale;
import enums.EnumFilialeGL;

public class OperatiiFiliala {

	public static List<Filiala> getListFilialeStatic() {
		Filiala filiala = null;
		List<Filiala> listFiliale = new ArrayList<>();

		for (EnumFiliale enumF : EnumFiliale.values()) {
			filiala = new Filiala();
			filiala.setCod(enumF.getCod());
			filiala.setNume(enumF.getNume().toUpperCase());
			listFiliale.add(filiala);
		}

		return listFiliale;
	}
	
	
	public static List<Filiala> getListFilialeGL() {
		Filiala filiala = null;
		List<Filiala> listFiliale = new ArrayList<>();

		for (EnumFilialeGL enumF : EnumFilialeGL.values()) {
			filiala = new Filiala();
			filiala.setCod(enumF.getCod());
			filiala.setNume(enumF.getNume().toUpperCase());
			listFiliale.add(filiala);
		}

		return listFiliale;
	}	
	

	public List<Masina> getMasiniFiliala(String codFiliala) throws SQLException {

		String sqlString = " select distinct a.ktext masina, nvl(b.id,-1) deviceid from sapprd.coas a, gps_masini b where  a.phas3<>'X' and "
				+ " a.werks !=' ' and a.mandt = '900' and a.auart = '1001' and " + " replace(a.ktext, '-') = b.nr_masina and a.werks=:werks order by a.ktext";

		List<Masina> listMasini = new ArrayList<Masina>();

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, codFiliala);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {

				Masina masina = new Masina();
				masina.setNrAuto(rs.getString("masina"));
				masina.setDeviceId(rs.getString("deviceid"));
				listMasini.add(masina);

			}

		}

		return listMasini;

	}

	public static List<Filiala> getListFiliale(String numeFiliala) {


		Filiala filiala = null;
		List<Filiala> listFiliale = new ArrayList<>();

		for (EnumFiliale enumF : EnumFiliale.values()) {

			if (enumF.getNume().toLowerCase().equals(numeFiliala.toLowerCase())) {
				filiala = new Filiala();
				filiala.setCod(enumF.getCod());
				filiala.setNume(enumF.getNume().toUpperCase());
				listFiliale.add(filiala);
			}

		}
		return listFiliale;

	}

}
