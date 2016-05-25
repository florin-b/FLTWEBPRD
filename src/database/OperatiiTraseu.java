package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import beans.Address;
import beans.BeanBoundBord;
import beans.Client;
import beans.CoordonateGps;
import beans.DateBorderou;
import beans.PozitieClient;
import beans.TraseuBorderou;
import enums.EnumTipClient;
import queries.SqlQueries;
import utils.MapUtils;
import utils.UtilsAdrese;

public class OperatiiTraseu {

	public List<TraseuBorderou> getTraseuBorderou(String codBorderou, String startBorderou, String stopBorderou) {

		DateBorderou dateBorderou = null;

		try {
			dateBorderou = getDateBorderou(codBorderou);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace().toString());
		}

		String sqlString = " select to_char(c.record_time,'dd-Mon-yy hh24:mi:ss', 'NLS_DATE_LANGUAGE = AMERICAN') datarec , c.latitude, c.longitude, nvl(c.mileage,0) kilo, "
				+ " nvl(c.speed,0) viteza from gps_masini b, gps_date c  where " + " b.nr_masina = replace(:nrMasina,'-','') and c.device_id = b.id "
				+ " and  c.record_time between to_date(:dataStart,'dd-mm-yy hh24:mi:ss', 'NLS_DATE_LANGUAGE = AMERICAN') " + 
				" and to_date(:dataStop,'dd-mm-yy hh24:mi:ss', 'NLS_DATE_LANGUAGE = AMERICAN') order by c.record_time ";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codBorderou", codBorderou);
		parameter.addValue("dataStart", startBorderou);
		parameter.addValue("dataStop", stopBorderou);
		parameter.addValue("nrMasina", dateBorderou.getNrMasina());

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());

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

	public List<Client> getClientiBorderou(String codBorderou) {
		List<Client> listClienti = new ArrayList<>();

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codBorderou", codBorderou);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());

		listClienti = jdbc.query(SqlQueries.getClientiBorderou(), parameter, new RowMapper<Client>() {

			public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
				Client client = new Client();
				client.setPoz(Integer.valueOf(rs.getString("poz")));
				client.setCodClient(rs.getString("cod_client"));
				client.setCodAdresa(rs.getString("cod_adresa"));
				client.setNumeClient(rs.getString("nume"));
				client.setTipClient(EnumTipClient.DISTRIBUTIE);
				client.setLocalitate(rs.getString("city1"));
				client.setStrada(rs.getString("street"));
				client.setNrStrada(rs.getString("house_num1"));
				client.setCodJudet(rs.getString("region"));
				return client;
			}
		});

		List<Client> listBorder = null;
		try {
			listBorder = getStartStopBorderou(codBorderou);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}

		listClienti.add(0, listBorder.get(0));
		listClienti.add(listClienti.size(), listBorder.get(1));

		return listClienti;
	}

	public List<Client> getStartStopBorderou(String codBorderou) throws SQLException {

		Connection conn = DBManager.getProdInstance().getConnection();

		String sqlString = SqlQueries.getStartStopBorderou();

		PreparedStatement stmt = conn.prepareStatement(sqlString);

		stmt.setString(1, codBorderou);

		ResultSet rs = stmt.executeQuery();

		List<Client> listClienti = new ArrayList<>();
		Client client = null;
		String adresa;
		while (rs.next()) {

			client = new Client();

			adresa = rs.getString("adr_plecare");

			client.setPoz(0);
			client.setCodClient(rs.getString("plecare"));
			client.setTipClient(getTipClient(client));

			client.setNumeClient("Start borderou " + getBoundBord(adresa).getNumeClient());
			client.setStartBord(true);
			listClienti.add(client);

			client = new PozitieClient();
			adresa = rs.getString("adr_sosire");

			client.setPoz(100);
			client.setCodClient(rs.getString("sosire") + " ");
			client.setTipClient(getTipClient(client));

			client.setNumeClient("Stop borderou " + getBoundBord(adresa).getNumeClient());
			client.setStopBord(true);
			listClienti.add(client);

		}

		if (rs != null)
			rs.close();

		if (conn != null)
			conn.close();

		return listClienti;

	}

	public List<PozitieClient> getCoordClientiBorderou(String codBorderou) {
		List<PozitieClient> listPozitii = null;

		String sqlString = " select a.poz, c.nume, decode(a.cod_client,'', a.cod_furnizor, a.cod_client) cod_client, "
				+ " decode(a.cod_client,'',a.adresa_furnizor, a.adresa_client) cod_adresa, " + " b.city1, b.street, b.house_num1, b.region, "
				+ " nvl(latitude,0) latitude, nvl(longitude,0) longitude "
				+ " from sapprd.zdocumentesms a, sapprd.adrc b, clienti c, sapprd.zcoordcomenzi d where a.nr_bord =:codBorderou and c.cod = a.cod_client "
				+ " and b.client = '900' and b.addrnumber = decode(a.cod_client,'',a.adresa_furnizor, a.adresa_client) "
				+ " and d.idcomanda(+) = a.idcomanda order by a.poz";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codBorderou", codBorderou);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(DBManager.getProdInstance());

		listPozitii = jdbc.query(sqlString, parameter, new RowMapper<PozitieClient>() {

			public PozitieClient mapRow(ResultSet rs, int rowNum) throws SQLException {
				PozitieClient pozitie = new PozitieClient();
				pozitie.setPoz(Integer.valueOf(rs.getString("poz")));
				pozitie.setCodClient(rs.getString("cod_client"));

				setCoordonate(pozitie, rs);
				pozitie.setCodAdresa(rs.getString("cod_adresa"));
				pozitie.setNumeClient(rs.getString("nume"));
				pozitie.setTipClient(EnumTipClient.DISTRIBUTIE);
				return pozitie;
			}
		});

		List<PozitieClient> listBorder = null;
		try {
			listBorder = getCoordStartStopBorderou(codBorderou);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace() + " sql = " + sqlString);
		}

		listPozitii.add(0, listBorder.get(0));
		listPozitii.add(listPozitii.size(), listBorder.get(1));

		return listPozitii;
	}

	private void setCoordonate(PozitieClient pozitieClient, ResultSet rs) {
		try {

			if (Double.valueOf(rs.getString("latitude")) != 0) {
				pozitieClient.setLatitudine(Double.valueOf(rs.getString("latitude")));
				pozitieClient.setLongitudine(Double.valueOf(rs.getString("longitude")));
			} else {
				CoordonateGps coordonate = getCoordonate(rs.getString("region"), rs.getString("city1"), rs.getString("house_num1"), rs.getString("street"));
				pozitieClient.setLatitudine(coordonate.getLatitude());
				pozitieClient.setLongitudine(coordonate.getLongitude());
			}

		} catch (Exception ex) {

		}
	}

	private CoordonateGps getCoordonate(String codJudet, String localitate, String strada, String nrStrada) {
		CoordonateGps coordonate = null;

		Address address = new Address();

		address.setStreet(strada);
		address.setNumber(nrStrada);
		address.setSector(UtilsAdrese.getNumeJudet(codJudet));
		address.setCity(localitate);

		try {
			coordonate = MapUtils.geocodeAddress(address);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return coordonate;
	}

	public DateBorderou getDateBorderou(String codBorderou) throws SQLException {
		Connection conn = DBManager.getProdInstance().getConnection();

		StringBuilder sqlString = new StringBuilder();

		sqlString.append("select trunc(to_date(daten,'yyyymmdd')) dataEmitere, masina from ");
		sqlString.append("(select v.tknum as numarb, m.exidv as masina, p.pernr as cod_sofer, ");
		sqlString.append(" (select fili from websap.soferi where cod=p.pernr) fili, v.shtyp, v.daten,v.uaten ");
		sqlString.append("from sapprd.vttk v join sapprd.vekp m on v.mandt = m.mandt and v.tknum = m.vpobjkey and m.vpobj = '04' ");
		sqlString.append("join sapprd.vtpa p on v.mandt = p.mandt and v.tknum = p.vbeln and v.daten != '00000000' and p.parvw = 'ZF' ");
		sqlString.append("where v.mandt = '900') ");
		sqlString.append("where numarb =? ");
		sqlString.append("union ");
		sqlString.append("select a.data_e dataEmitere, a.masina from borderouri a where  numarb =? order by dataEmitere");

		PreparedStatement stmt = conn.prepareStatement(sqlString.toString());

		stmt.setString(1, codBorderou);
		stmt.setString(2, codBorderou);

		ResultSet rs = stmt.executeQuery();
		DateBorderou dateBorderou = new DateBorderou();
		while (rs.next()) {
			dateBorderou.setDataEmitere(rs.getString("dataEmitere"));
			dateBorderou.setNrMasina(rs.getString("masina"));
		}

		if (rs != null)
			rs.close();

		if (conn != null)
			conn.close();

		return dateBorderou;
	}

	public List<PozitieClient> getCoordStartStopBorderou(String codBorderou) throws SQLException {

		Connection conn = DBManager.getProdInstance().getConnection();

		String sqlString = SqlQueries.getCoordStartStopBorderou();

		PreparedStatement stmt = conn.prepareStatement(sqlString);

		stmt.setString(1, codBorderou);

		ResultSet rs = stmt.executeQuery();

		List<PozitieClient> listPozitii = new ArrayList<>();
		PozitieClient pozitie = null;
		String adresa;
		while (rs.next()) {

			pozitie = new PozitieClient();

			adresa = rs.getString("adr_plecare");

			pozitie.setPoz(0);
			pozitie.setCodClient(rs.getString("plecare"));
			pozitie.setTipClient(getTipClient(pozitie));

			if (Double.valueOf(rs.getString("plec_lat").replace(",", ".")) > 0) {
				pozitie.setLatitudine(Double.valueOf(rs.getString("plec_lat").replace(",", ".")));
				pozitie.setLongitudine(Double.valueOf(rs.getString("plec_long").replace(",", ".")));
			} else {
				setCoordBound(pozitie, adresa);
			}
			pozitie.setNumeClient("Start borderou " + getBoundBord(adresa).getNumeClient());
			pozitie.setStartBord(true);
			listPozitii.add(pozitie);

			pozitie = new PozitieClient();
			adresa = rs.getString("adr_sosire");

			pozitie.setPoz(100);
			pozitie.setCodClient(rs.getString("sosire") + " ");
			pozitie.setTipClient(getTipClient(pozitie));

			if (Double.valueOf(rs.getString("sosire_lat").replace(",", ".")) > 0) {
				pozitie.setLatitudine(Double.valueOf(rs.getString("sosire_lat").replace(",", ".")));
				pozitie.setLongitudine(Double.valueOf(rs.getString("sosire_long").replace(",", ".")));
			} else {
				setCoordBound(pozitie, adresa);
			}
			pozitie.setNumeClient("Stop borderou " + getBoundBord(adresa).getNumeClient());
			pozitie.setStopBord(true);
			listPozitii.add(pozitie);

		}

		if (rs != null)
			rs.close();

		if (conn != null)
			conn.close();

		return listPozitii;

	}

	private void setCoordBound(PozitieClient pozitieClient, String dateCoord) {

		if (dateCoord != null && dateCoord.contains("#")) {

			CoordonateGps coord = new CoordonateGps();

			try {
				coord = MapUtils.geocodeAddress(getAddress(dateCoord));
			} catch (Exception e) {
				e.printStackTrace();
			}

			pozitieClient.setLatitudine(coord.getLatitude());
			pozitieClient.setLongitudine(coord.getLongitude());

		}

	}

	private BeanBoundBord getBoundBord(String adresa) {
		BeanBoundBord boundBord = new BeanBoundBord();

		if (adresa != null && adresa.contains("#")) {
			String[] tokAdresa = adresa.split("#");
			boundBord.setNumeClient(tokAdresa[0]);
			boundBord.setAddress(getAddress(adresa));

		}

		return boundBord;
	}

	private Address getAddress(String valAddress) {
		Address address = new Address();

		if (valAddress != null && valAddress.contains("#")) {
			String[] tokAdresa = valAddress.split("#");

			address.setSector(UtilsAdrese.getNumeJudet(tokAdresa[1]));
			address.setCity(tokAdresa[2]);

			if (tokAdresa.length >= 3)
				address.setStreet(tokAdresa[3]);

			if (tokAdresa.length >= 4)
				address.setNumber(tokAdresa[4]);

		}

		return address;
	}

	private EnumTipClient getTipClient(Client pozitieClient) {
		if (pozitieClient.getCodClient().length() == 4)
			return EnumTipClient.FILIALA;
		else
			return EnumTipClient.DISTRIBUTIE;
	}

}
