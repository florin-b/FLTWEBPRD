package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Borderou;
import beans.Sofer;
import hibernate.HibernateUtilities;
import queries.SqlQueries;

public class OperatiiSoferi {

	public List<Sofer> getListSoferi(String codFiliala) throws SQLException {

		String sqlString = " select distinct nume, cod from soferi where fili =:codFiliala order by nume ";

		List<Sofer> listSoferi = new ArrayList<Sofer>();

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, codFiliala);
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Sofer sofer = new Sofer();
				sofer.setCodSofer(rs.getString("cod"));
				sofer.setNumeSofer(rs.getString("nume"));
				listSoferi.add(sofer);
			}

		}

		return listSoferi;

	}

	@SuppressWarnings("unchecked")
	public List<Sofer> getListSoferiHibernate(String codFiliala) {
		Session session = HibernateUtilities.getSessionFactoryInstance();
		Query query = session.getNamedQuery("GET_SOFERI_BY_FILIALA");
		query.setParameter("codFiliala", codFiliala);
		List<Sofer> soferi = query.list();
		return soferi;
	}

	public List<Borderou> getBorderouri(String codSofer, String dataStart, String dataStop) throws SQLException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		String sqlString = "select numarb, trunc(to_date(daten,'yyyymmdd')) dataEmitere, 'false' activ from "
				+ " (select v.tknum as numarb,  v.daten, p.pernr as cod_sofer "
				+ " from sapprd.vttk v join sapprd.vekp m on v.mandt = m.mandt and v.tknum = m.vpobjkey and m.vpobj = '04' "
				+ " join sapprd.vtpa p on v.mandt = p.mandt and v.tknum = p.vbeln and p.parvw = 'ZF' " + " where v.mandt = '900') "
				+ " where cod_sofer =:codSofer and daten between :dataStart and :dataStop " + " union "
				+ " select numarb, trunc(a.data_e) dataEmitere, 'true' activ from borderouri a where  cod_sofer =:codSofer and "
				+ " data_e between to_date(:dataStart,'yyyymmdd')  and to_date(:dataStop,'yyyymmdd') order by dataEmitere ";

		List<Borderou> listBorderouri = new ArrayList<Borderou>();

		try (Connection conn = DBManager.getProdInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			stmt.setString(1, codSofer);
			stmt.setString(2, dataStart);
			stmt.setString(3, dataStop);
			stmt.setString(4, codSofer);
			stmt.setString(5, dataStart);
			stmt.setString(6, dataStop);
			
			
			stmt.executeQuery();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				Borderou borderou = new Borderou();
				borderou.setCod(rs.getString("numarb"));
				borderou.setDataEmitere(rs.getDate("dataEmitere").toLocalDate().format(formatter));
				borderou.setActiv(Boolean.valueOf(rs.getString("activ")));
				listBorderouri.add(borderou);
			}

		}

		return listBorderouri;

	}

	public String getBorderouCurent(String nrMasina) throws SQLException {

		String borderouCurent = "";
		Connection conn = DBManager.getProdInstance().getConnection();

		PreparedStatement stmt = conn.prepareStatement(SqlQueries.getBorderouActiv().toString());

		stmt.setString(1, nrMasina);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			borderouCurent = rs.getString("numarb");
		}

		if (rs != null)
			rs.close();

		if (conn != null)
			conn.close();

		return borderouCurent;
	}

}
