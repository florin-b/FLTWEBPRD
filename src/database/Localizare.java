package database;

import interfaces.ILocalizare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import beans.PozitieMasina;

public class Localizare implements ILocalizare {

	public String getPozitieMasini(String listMasini)  {

		String strMasini = "(" + listMasini + ")";

		DBManager manager = new DBManager();

		String sqlString = " select d.device_id, d.latitude, d.longitude, to_char(d.record_time, 'dd-mon-yyyy hh24:mi:ss') datac, "
				+ " m.nr_masina, d.speed from gps_date d, gps_masini m where d.device_id in " + strMasini + " and  d.device_id = m.id and  "
				+ " to_char(d.record_time, 'dd-mon-yyyy hh24:mi:ss') = " + " (select to_char(max(h.record_time), 'dd-mon-yyyy hh24:mi:ss') "
				+ " from gps_date h where h.device_id = d.device_id) ";

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(manager.getProdDataSource());

		return loadLocations(jdbc.query(sqlString, new RowMapper<PozitieMasina>() {

			public PozitieMasina mapRow(ResultSet rs, int rowNum) throws SQLException {
				PozitieMasina pozitie = new PozitieMasina();
				pozitie.setDeviceId(String.valueOf(rs.getInt("device_id")));
				pozitie.setLatitudine(String.valueOf(rs.getDouble("latitude")).replace(",", "."));
				pozitie.setLongitudine(String.valueOf(rs.getDouble("longitude")).replace(",", "."));
				pozitie.setData(rs.getString("datac"));
				pozitie.setNrAuto(rs.getString("nr_masina"));
				pozitie.setViteza(String.valueOf(rs.getInt("speed")));
				return pozitie;
			}

		}));

	}

	private String loadLocations(List<PozitieMasina> listPozitii) {

		String stringGeo = "";

		for (int i = 0; i < listPozitii.size(); i++) {
			if (stringGeo.length() == 0) {
				stringGeo = "" + listPozitii.get(i).getLatitudine() + "," + listPozitii.get(i).getLongitudine() + "," + listPozitii.get(i).getNrAuto() + ","
						+ listPozitii.get(i).getViteza() + "," + listPozitii.get(i).getData();
			} else {
				stringGeo += "#" + listPozitii.get(i).getLatitudine() + "," + listPozitii.get(i).getLongitudine() + "," + listPozitii.get(i).getNrAuto() + ","
						+ listPozitii.get(i).getViteza() + "," + listPozitii.get(i).getData() + "";
			}
		}

		return stringGeo;
	}

}
