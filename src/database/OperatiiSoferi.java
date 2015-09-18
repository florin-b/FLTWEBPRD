package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import beans.Borderou;
import beans.Sofer;

public class OperatiiSoferi {

	public List<Sofer> getListSoferi(String codFiliala) {
		DBManager manager = new DBManager();

		String sqlString = " select distinct nume, cod from soferi where fili =:codFiliala order by nume ";

		SqlParameterSource parameter = new MapSqlParameterSource("codFiliala", codFiliala);
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(manager.getProdDataSource());

		return jdbc.query(sqlString, parameter, new RowMapper<Sofer>() {

			public Sofer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sofer sofer = new Sofer();
				sofer.setCodSofer(rs.getString("cod"));
				sofer.setNumeSofer(rs.getString("nume"));
				return sofer;
			}
		});

	}

	public List<Borderou> getBorderouri(String codSofer, String dataStart, String dataStop) {
		DBManager manager = new DBManager();

		String sqlString = " select numarb, to_char(data_e) dataEmitere from borderouri a where  cod_sofer =:codSofer and "
				+ " data_e between to_date(:dataStart,'yyyymmdd')  and to_date(:dataStop,'yyyymmdd') order by data_e ";

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("codSofer", codSofer);
		parameter.addValue("dataStart", dataStart);
		parameter.addValue("dataStop", dataStop);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(manager.getProdDataSource());

		return jdbc.query(sqlString, parameter, new RowMapper<Borderou>() {

			public Borderou mapRow(ResultSet rs, int rowNum) throws SQLException {
				Borderou borderou = new Borderou();
				borderou.setCod(rs.getString("numarb"));
				borderou.setDataEmitere(rs.getString("dataEmitere"));
				return borderou;
			}
		});

	}

}
