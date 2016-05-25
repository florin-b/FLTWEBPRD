package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import beans.Borderou;
import beans.Sofer;
import hibernate.HibernateUtilities;

public class OperatiiSoferi {

	public List<Sofer> getListSoferi(String codFiliala) {

		String sqlString = " select distinct nume, cod from soferi where fili =:codFiliala order by nume ";

		SqlParameterSource parameter = new MapSqlParameterSource("codFiliala", codFiliala);
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());

		return jdbc.query(sqlString, parameter, new RowMapper<Sofer>() {

			public Sofer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sofer sofer = new Sofer();
				sofer.setCodSofer(rs.getString("cod"));
				sofer.setNumeSofer(rs.getString("nume"));
				return sofer;
			}
		});

	}

	@SuppressWarnings("unchecked")
	public List<Sofer> getListSoferiHibernate(String codFiliala) {
		Session session = HibernateUtilities.getSessionFactoryInstance();
		Query query = session.getNamedQuery("GET_SOFERI_BY_FILIALA");
		query.setParameter("codFiliala", codFiliala);
		List<Sofer> soferi = query.list();
		return soferi;
	}

	public List<Borderou> getBorderouri(String codSofer, String dataStart, String dataStop) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		String sqlString = "select numarb, trunc(to_date(daten,'yyyymmdd')) dataEmitere, 'false' activ from "
				+ " (select v.tknum as numarb,  v.daten, p.pernr as cod_sofer "
				+ " from sapprd.vttk v join sapprd.vekp m on v.mandt = m.mandt and v.tknum = m.vpobjkey and m.vpobj = '04' "
				+ " join sapprd.vtpa p on v.mandt = p.mandt and v.tknum = p.vbeln and p.parvw = 'ZF' " + " where v.mandt = '900') "
				+ " where cod_sofer =:codSofer and daten between :dataStart and :dataStop " + " union "
				+ " select numarb, trunc(a.data_e) dataEmitere, 'true' activ from borderouri a where  cod_sofer =:codSofer and "
				+ " data_e between to_date(:dataStart,'yyyymmdd')  and to_date(:dataStop,'yyyymmdd') order by dataEmitere ";

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("codSofer", codSofer);
		parameter.addValue("dataStart", dataStart);
		parameter.addValue("dataStop", dataStop);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());

		return jdbc.query(sqlString, parameter, new RowMapper<Borderou>() {

			public Borderou mapRow(ResultSet rs, int rowNum) throws SQLException {
				Borderou borderou = new Borderou();
				borderou.setCod(rs.getString("numarb"));
				borderou.setDataEmitere(rs.getDate("dataEmitere").toLocalDate().format(formatter));
				borderou.setActiv(Boolean.valueOf(rs.getString("activ")));
				return borderou;
			}
		});

	}

}
