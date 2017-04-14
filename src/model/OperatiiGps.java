package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.GpsInactiv;
import beans.Masina;
import database.DBManager;
import database.OperatiiFiliala;
import database.OperatiiTraseu;
import utils.Utils;

public class OperatiiGps {

	private static final Logger logger = LogManager.getLogger(OperatiiTraseu.class);
	
	public List<GpsInactiv> getGpsInactiv(String codFiliala) throws SQLException {

		OperatiiFiliala opFiliala = new OperatiiFiliala();
		List<Masina> listMasini = null;
		List<GpsInactiv> listGps = new ArrayList<GpsInactiv>();

		try {
			listMasini = opFiliala.getMasiniFiliala(codFiliala);
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		if (listMasini.size() == 0)
			return new ArrayList<GpsInactiv>();

		String sqlString = " select b.nr_masina, sysdate - record_time interval from gps_index a, gps_masini b where   a.device_id in ("
						+ formatMasini(listMasini) + ") and a.device_id = b.id and sysdate - record_time > 1 order by interval ";

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				GpsInactiv gpsInactiv = new GpsInactiv();
				gpsInactiv.setNrAuto(rs.getString("nr_masina"));
				gpsInactiv.setNrZileInact(rs.getInt("interval"));
				listGps.add(gpsInactiv);
			}

		}

		return listGps;

	}

	private String formatMasini(List<Masina> listMasini) {

		StringBuilder strMasini = new StringBuilder();

		for (Masina masina : listMasini) {
			if (strMasini.toString().length() == 0)
				strMasini.append(masina.deviceId);
			else {
				strMasini.append(",");
				strMasini.append(masina.deviceId);
			}

		}

		return strMasini.toString();
	}

}
