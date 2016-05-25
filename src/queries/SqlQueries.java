package queries;

public class SqlQueries {

	public static String getEvenimenteTableta() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select client, codadresa, eveniment, data, ora,  gps, fms from sapprd.zevenimentsofer ");
		sqlString.append(" where document =? order by data, ora");

		return sqlString.toString();
	}

	public static String getCoordStartStopBorderou() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.plecare, ");
		sqlString.append(" nvl((select b.latitude from sapprd.zgpsdepcoord b, sapprd.zgpsdep c where b.id = c.gpsid and ");
		sqlString.append(" c.pct = a.plecare),'0,0') plec_lat, ");
		sqlString.append(" nvl((select b.longitude from sapprd.zgpsdepcoord b, sapprd.zgpsdep c where b.id = c.gpsid and ");
		sqlString.append(" c.pct = a.plecare),'0,0') plec_long, a.adr_plecare, ");
		sqlString.append(" a.sosire, ");
		sqlString.append(" nvl((select b.latitude from sapprd.zgpsdepcoord b, sapprd.zgpsdep c where b.id = c.gpsid and ");
		sqlString.append(" c.pct = a.sosire),'0,0') sosire_lat, ");
		sqlString.append(" nvl((select b.longitude from sapprd.zgpsdepcoord b, sapprd.zgpsdep c where b.id = c.gpsid and ");
		sqlString.append(" c.pct = a.sosire),'0,0') sosire_long, a.adr_sosire ");
		sqlString.append(" from websap.bord_ends a where a.tknum=?");

		return sqlString.toString();

	}

	public static String getClientiBorderou() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.poz, c.nume, decode(a.cod_client,'', a.cod_furnizor, a.cod_client) cod_client, ");
		sqlString.append(" decode(a.cod_client,'',a.adresa_furnizor, a.adresa_client) cod_adresa,  b.city1,  nvl(b.street,' ') street,   ");
		sqlString.append(" nvl(b.house_num1,' ') house_num1, b.region from sapprd.zdocumentesms a, sapprd.adrc b, clienti c where a.nr_bord =:codBorderou  ");
		sqlString.append(" and c.cod = a.cod_client and b.client = '900' and b.addrnumber = decode(a.cod_client,'',a.adresa_furnizor, a.adresa_client) ");
		sqlString.append(" order by a.poz ");

		return sqlString.toString();
	}

	public static String getStartStopBorderou() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select a.plecare, a.adr_plecare, a.sosire, a.adr_sosire  from websap.bord_ends a where a.tknum=? ");

		return sqlString.toString();
	}

	public static String getBorderouActiv() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select document from sapprd.zevenimentsofer where ");
		sqlString.append(" document = (select numarb from ( ");
		sqlString.append(" select numarb from websap.borderouri where sttrg in ( 4, 6) ");
		sqlString.append(" and masina =? order by sttrg desc,data_e asc) x where rownum<2) ");

		return sqlString.toString();

	}

	public static String getStareEvenimente() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select count(distinct decode(cod_client,'', cod_furnizor, cod_client)) bord from sapprd.zdocumentesms where nr_bord =? ");
		sqlString.append(" union ");
		sqlString.append(" select count(distinct client) bord from sapprd.zevenimentsofer where  document =? and client != document and eveniment = 'S' ");

		return sqlString.toString();
	}

	public static String getStareMasina() {
		StringBuilder sqlString = new StringBuilder();

		sqlString.append(" select  count(distinct decode(cod_client,'', cod_furnizor, cod_client)) clnt from sapprd.zdocumentesms where nr_bord =? ");
		sqlString.append(" union all ");
		sqlString.append(" select distinct count(client) clnt  from sapprd.zevenimentsofer where document =?  and document != client and eveniment = 'S'");

		return sqlString.toString();
	}

}
