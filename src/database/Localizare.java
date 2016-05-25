package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import beans.BorderouMasina;
import beans.PozitieMasina;
import interfaces.ILocalizare;

public class Localizare implements ILocalizare {

	public String getPozitieMasini(String listMasini) throws SQLException {

		String strMasini = listMasini.replace("-", "");

		OperatiiBorderou opBord = new OperatiiBorderou();

		final List<BorderouMasina> listStare = opBord.getStareBordMasini(listMasini);

		String sqlString = " select d.device_id, d.latitude, d.longitude, to_char(d.record_time, 'dd-mon-yyyy hh24:mi:ss','NLS_DATE_LANGUAGE = ENGLISH') datac, "
				+ " m.nr_masina, d.speed from gps_index d, gps_masini m where d.device_id in (select id from gps_masini where nr_masina in (" + strMasini
				+ ")) and  d.device_id = m.id ";

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());

		return loadLocations(jdbc.query(sqlString, new RowMapper<PozitieMasina>() {

			public PozitieMasina mapRow(ResultSet rs, int rowNum) throws SQLException {
				PozitieMasina pozitie = new PozitieMasina();
				pozitie.setDeviceId(String.valueOf(rs.getInt("device_id")));
				pozitie.setLatitudine(String.valueOf(rs.getDouble("latitude")).replace(",", "."));
				pozitie.setLongitudine(String.valueOf(rs.getDouble("longitude")).replace(",", "."));
				pozitie.setData(rs.getString("datac"));
				pozitie.setNrAuto(rs.getString("nr_masina"));
				pozitie.setViteza(String.valueOf(rs.getInt("speed")));
				pozitie.setActual();

				BorderouMasina bord = new BorderouMasina();
				bord.setNrMasina(pozitie.getNrAuto());

				pozitie.setStatus(listStare.get(listStare.indexOf(bord)).getStatus());

				return pozitie;
			}

		}));

	}

	private String loadLocations(List<PozitieMasina> listPozitii) {

		String stringGeo = "";

		for (int i = 0; i < listPozitii.size(); i++) {
			if (stringGeo.length() == 0) {
				stringGeo = "" + listPozitii.get(i).getLatitudine() + "," + listPozitii.get(i).getLongitudine() + "," + listPozitii.get(i).getNrAuto() + ","
						+ listPozitii.get(i).getViteza() + "," + listPozitii.get(i).getData() + "," + listPozitii.get(i).getStatus().getCodStatus();
			} else {
				stringGeo += "#" + listPozitii.get(i).getLatitudine() + "," + listPozitii.get(i).getLongitudine() + "," + listPozitii.get(i).getNrAuto() + ","
						+ listPozitii.get(i).getViteza() + "," + listPozitii.get(i).getData() + "," + listPozitii.get(i).getStatus().getCodStatus() + "";
			}
		}

		return stringGeo;
	}

}