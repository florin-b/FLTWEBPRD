package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import beans.PozitieClient;
import beans.TraseuBorderou;

public class OperatiiTraseu {

	public List<TraseuBorderou> getTraseuBorderou(String codBorderou) {

		DBManager manager = new DBManager();

		String sqlString = " select to_char(c.record_time,'dd-Mon-yy hh24:mi:ss') datarec , c.latitude, c.longitude, nvl(c.mileage,0) kilo, "
				+ " nvl(c.speed,0) viteza from borderouri a, gps_masini b, gps_date c  where a.numarb =:codBorderou "
				+ " and b.nr_masina = replace(a.masina,'-','') and c.device_id = b.id "
				+ " and  trunc(c.record_time) >= trunc(a.data_e) order by c.record_time ";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codBorderou", codBorderou);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(manager.getProdDataSource());

		return jdbc.query(sqlString, parameter, new RowMapper<TraseuBorderou>() {

			public TraseuBorderou mapRow(ResultSet rs, int rowNum) throws SQLException {
				TraseuBorderou pozitie = new TraseuBorderou();
				pozitie.setDataInreg(rs.getString("datarec"));
				pozitie.setLatitudine(rs.getDouble("latitude"));
				pozitie.setLongitudine(rs.getDouble("longitude"));
				pozitie.setKm(rs.getInt("kilo"));
				pozitie.setViteza(rs.getInt("viteza"));
				return pozitie;
			}
		});

	}

	public List<PozitieClient> getCoordClientiBorderou(String codBorderou) {

		DBManager manager = new DBManager();

		String sqlString = " select  a.cod_client, b.coordonategps, c.nume from sapprd.zdocumentesms a, sapprd.zadresagpsclient b, clienti c "
				+ " where a.nr_bord=:codBorderou and b.codclient = a.cod_client and c.cod = a.cod_client order by a.poz";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codBorderou", codBorderou);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(manager.getProdDataSource());

		return jdbc.query(sqlString, parameter, new RowMapper<PozitieClient>() {

			public PozitieClient mapRow(ResultSet rs, int rowNum) throws SQLException {
				PozitieClient pozitie = new PozitieClient();
				pozitie.setCodClient(rs.getString("cod_client"));
				pozitie.setLatitudine(Double.valueOf(rs.getString("coordonategps").split(",")[0]));
				pozitie.setLongitudine(Double.valueOf(rs.getString("coordonategps").split(",")[1]));
				pozitie.setNumeClient(rs.getString("nume"));
				return pozitie;
			}
		});

	}

}
