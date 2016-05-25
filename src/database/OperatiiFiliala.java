package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import beans.Filiala;
import beans.Masina;
import enums.EnumFiliale;

public class OperatiiFiliala {

	public List<Filiala> getListFilialeStatic() {
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

	public List<Filiala> getListFiliale() throws SQLException {

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());
		return jdbc.query("select distinct fili from soferi order by fili", new RowMapper<Filiala>() {

			public Filiala mapRow(ResultSet rs, int rowNum) throws SQLException {

				Filiala filiala = new Filiala();
				filiala.setCod(rs.getString("fili"));
				filiala.setNume(helper.Filiala.getNumeFiliala(filiala.getCod()));

				return filiala;
			}

		});

	}

	public List<Masina> getMasiniFiliala(String codFiliala) {

		String sql = " select distinct a.ktext masina, nvl(b.id,-1) deviceid from sapprd.coas a, gps_masini b where  a.phas3<>'X' and "
				+ " a.werks !=' ' and a.mandt = '900' and a.auart = '1001' and " + " replace(a.ktext, '-') = b.nr_masina and a.werks=:werks order by a.ktext";

		SqlParameterSource paramter = new MapSqlParameterSource("werks", codFiliala);
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());
		return jdbc.query(sql, paramter, new RowMapper<Masina>() {

			public Masina mapRow(ResultSet rs, int rowNum) throws SQLException {
				Masina masina = new Masina();
				masina.setNrAuto(rs.getString("masina"));
				masina.setDeviceId(rs.getString("deviceid"));
				return masina;
			}
		});

	}

}
